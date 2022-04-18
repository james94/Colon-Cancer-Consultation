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
