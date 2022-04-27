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

# import scrapy
# from scrapy.crawler import CrawlerProcess
# from collections import OrderedDict
# from tqdm import tqdm

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
class ActionJoke(Action):
    def name(self):
        return "action_joke"

    def run(self, dispatcher, tracker, domain):
        request = requests.get("http://api.icndb.com/jokes/random").json() # make an api call
        joke = request["value"]["joke"] # extract a joke from returned json response
        dispatcher.utter_message(text=joke) # send the message back to the user
        return []

# class FetchColonCancerSymptoms(Action):
#     global cc_symptom_names
#     cc_symptom_names = []

#     class MayoClinicCCSymptomsSpider(scrapy.Spider):
#         name = "mc_cc_symptoms_spider"

#         def start_requests(self): # make an api request to mayoclinic
#             cc_symptoms_url = "https://www.mayoclinic.org/diseases-conditions/colon-cancer/symptoms-causes/syc-20353669"
#             yield scrapy.Request(url=cc_symptoms_url, callback=self.parse)

#         def parse(self, response):
#             cc_symptom_names = response.xpath('//h2[text()="Symptoms"]/following-sibling::ul[1]/li/text()').extract()
        
#     def name(self):
#         return "fetch_cc_symptoms"

#     def run(self, dispatcher, tracker, domain):
#         process = CrawlerProcess({
#             'USER_AGENT': 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)'
#         })
#         process.crawl(self.MayoClinicCCSymptomsSpider)
#         process.start()
#         dispatcher.utter_message(text=cc_symptom_names)
#         return []