# This files contains your custom actions which can be used to run
# custom Python code.
#
# See this guide on how to implement these action:
# https://rasa.com/docs/rasa/custom-actions


# This is a simple example for a custom action which utters "Hello World!"

# from typing import Any, Text, Dict, List
#
# from rasa_sdk import Action, Tracker
# from rasa_sdk.executor import CollectingDispatcher
#
#
# class ActionHelloWorld(Action):
#
#     def name(self) -> Text:
#         return "action_hello_world"
#
#     def run(self, dispatcher: CollectingDispatcher,
#             tracker: Tracker,
#             domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
#
#         dispatcher.utter_message(text="Hello World!")
#
#         return []

import scrapy
from collections import OrderedDict
from tqdm import tqdm
from scrapy.spiders import CrawlSpider
import scrapydo

import requests
import json
from rasa_sdk import Action

# 1. In 'data/stories.yml', replace 'utter_cheer_up' with the custom action 'action_joke'
# tell your bot to use this new action
# 2. In 'domain.yml', add a section for custom actions, including your
# new action
# 3. After updating your domain and stories, you must retrain your model
# 4. Your custom actions will run on a separate server from your Rasa server
# 5. Create a network to connect the two containers: docker network create my-project
# 6. Run the custom actions with the following command: docker run '' '' --net my-project ..

# 1 API Call to MayoClinic Done
class ActionJoke(Action):
    cc_symptom_names = []

    class MayoClinicCCSymptomsSpider(scrapy.Spider):
        name = "mc_cc_symptoms_spider"

        def start_requests(self): # make an api request to mayoclinic
            cc_symptoms_url = "https://www.mayoclinic.org/diseases-conditions/colon-cancer/symptoms-causes/syc-20353669"
            yield scrapy.Request(url=cc_symptoms_url, callback=self.parse)

        def parse(self, response):
            global cc_symptom_names
            cc_symptom_names = response.xpath('//h2[text()="Symptoms"]/following-sibling::ul[1]/li/text()').extract()
            # print("Printing CC Symptom Names")
            # print(cc_symptom_names)

    def name(self):
        return "action_joke"

    def run(self, dispatcher, tracker, domain):
        print("ActionJoke")
        # request = requests.get("http://api.icndb.com/jokes/random").json() # make an api call
        # joke = request["value"]["joke"] # extract a joke from returned json response
        # print(joke)
        # dispatcher.utter_message(text=joke) # send the message back to the user
        # return []

        global cc_symptom_names
        scrapydo.setup()
        scrapydo.run_spider(self.MayoClinicCCSymptomsSpider)
        symptoms_pretty="\n".join(cc_symptom_names) # puts each item on newline in string
        # print(symptoms_pretty)
        dispatcher.utter_message(text=symptoms_pretty)

        return []

class ActionCCSymptoms(Action):
    symptom_list = []
    class MayoClinicCCSymptomsSpider(scrapy.Spider):
        name = "mc_cc_symptoms_spider"

        def start_requests(self): # make an api request to mayoclinic
            cc_symptoms_url = "https://www.mayoclinic.org/diseases-conditions/colon-cancer/symptoms-causes/syc-20353669"
            yield scrapy.Request(url=cc_symptoms_url, callback=self.parse)

        def parse(self, response):
            global symptom_list
            symptom_list = response.xpath('//h2[text()="Symptoms"]/following-sibling::ul[1]/li/text()').extract()
            # print("Printing CC Symptom Names")
            # print(symptom_list)

    def name(self):
        return "action_symptoms"

    def run(self, dispatcher, tracker, domain):
        print("ActionSymptoms")
        global symptom_list
        scrapydo.setup()
        scrapydo.run_spider(self.MayoClinicCCSymptomsSpider)
        symptoms_pretty="\n".join(symptom_list) # puts each item on newline in string
        # print(symptoms_pretty)
        dispatcher.utter_message(text=symptoms_pretty)
        return []

# risk_factor_names = response.xpath('//h2[text()="Risk factors"]/following-sibling::ul[1]/li/strong/text()').extract()

class ActionCCRiskFactors(Action):
    risk_factors = []

    class MayoClinicCCRiskFactorsSpider(scrapy.Spider):
        name = "mc_cc_risk_factors_spider"

        def start_requests(self): # make an api request to mayoclinic
            cc_risk_factors_url = "https://www.mayoclinic.org/diseases-conditions/colon-cancer/symptoms-causes/syc-20353669"
            yield scrapy.Request(url=cc_risk_factors_url, callback=self.parse)

        def parse(self, response):
            global risk_factors
            risk_factors = response.xpath('//h2[text()="Risk factors"]/following-sibling::ul[1]/li/strong/text()').extract()
            # print("Printing CC Risk Factors Names")
            # print(risk_factors)

    def name(self):
        return "action_riskfactors"

    def run(self, dispatcher, tracker, domain):
        print("ActionRiskFactors")
        global risk_factors
        scrapydo.setup()
        scrapydo.run_spider(self.MayoClinicCCRiskFactorsSpider)
        risk_factors_pretty="\n".join(risk_factors) # puts each item on newline in string
        # print(risk_factors_pretty)
        dispatcher.utter_message(text=risk_factors_pretty)
        return []

class ActionSubmitSymptomsFormDB(Action):
    def name(self):
        return "action_submit_symptoms_form"

    def run(self, dispatcher, tracker, domain):
        symptoms_json = {"patient_id": tracker.get_slot("patient_id"),
            "patient_name": tracker.get_slot("patient_name"),
            "patient_timestamp": tracker.get_slot("patient_timestamp"),
            "patient_bowel_habit_changes": tracker.get_slot("patient_bowel_habit_changes"),
            "patient_weakness": tracker.get_slot("patient_weakness"),
            "patient_fatigue": tracker.get_slot("patient_fatigue"),
            "patient_rectal_bleeding": tracker.get_slot("patient_rectal_bleeding"),
            "patient_poop_blood": tracker.get_slot("patient_poop_blood"),
            "patient_abdominal_discomfort": tracker.get_slot("patient_abdominal_discomfort"),
            "patient_bowel_not_empty_feeling": tracker.get_slot("patient_bowel_not_empty_feeling"),
            "patient_unexplained_weightloss": tracker.get_slot("patient_unexplained_weightloss")}

        ADD_SYMPTOMS_END_POINT = "http://rasa-sb-server:8080/addsymptoms"
        patient_id_header = {'id': tracker.get_slot("patient_id")}

        response = requests.post(url = ADD_SYMPTOMS_END_POINT, data = symptoms_json, headers = patient_id_header)
        print(response.status_code)
        dispatcher.utter_message(text="Sent CC Symptoms Json To SB: {0}".format(response.status_code))
        return []