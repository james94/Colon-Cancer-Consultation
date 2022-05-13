# Notes on Building a RASA Assistant in Docker

~~~bash
conda create -n rasa-310_rasa-sdk-311 python=3.7
~~~

### Pull Rasa Docker Image

~~~bash
docker pull rasa/rasa:3.1.0-full
~~~

Alternatively, you can build a custom Rasa Docker image that pulls Rasa, then installs some Python packages and more:

~~~bash
cd /mnt/c/Users/james/Documents/GitHub/GI-Cancers-2D-Gallery/rasa_apps/RasaDockerfile
docker build -t rasa_3.1.0_rasa_sdk_3.1.1:dev .

docker build -t rasa_3.1.0:dev .
~~~

### Setup RASA Project

~~~bash
# Setup RASA GPU Project
docker run --name rasa-init --gpus all -it --privileged -v C:\Users\james\Documents\GitHub\GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va:/app rasa/rasa:3.1.0-full init --no-prompt
~~~

### Train RASA Model

~~~bash
# Train Rasa Model
docker run --name rasa-train --gpus all -it --privileged -v C:\Users\james\Documents\GitHub\GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va:/app rasa/rasa:3.1.0-full train --domain domain.yml --data data --out models
~~~


### Talk to your Virtual Assistant From Shell

~~~bash
# Talk to your Virtual Assistant From Shell
docker run --name rasa-shell --gpus all -it --privileged -p 5005:5005 -v C:\Users\james\Documents\GitHub\GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va:/app rasa/rasa:3.1.0-full shell


docker run --name rasa-shell --gpus all -it --privileged -p 5005:5005 -v C:\Users\james\Documents\GitHub\GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va:/app rasa_3.1.0_rasa_sdk_3.1.1:dev shell
~~~

### Talk to your Virtual Assistant From Unity

To launch Rasa server, so our Virtual Assistant is just running in the Docker container, you do docker run followed by Rasa **run** command, so then external clients like Unity in our case can interact with our Virtual Assistant.

~~~bash
# Talk to your Virtual Assistant from Unity
docker run --name rasa-run --gpus all -it --privileged -p 5005:5005 -v C:\Users\james\Documents\GitHub\GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va:/app rasa/rasa:3.1.0-full run

docker run --name rasa-run --gpus all -it --privileged -v C:\Users\james\Documents\GitHub\GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va:/app --net colon-cancer-mayoclinic rasa_3.1.0_rasa_sdk_3.1.1:dev

cd /app
rasa run
~~~

### Adding Custom Actions

IMPORTANT: For your custom actions to be called, you need at least 2 story paths where they are called. If you only have 1, it wont be called.

1\. Build a custom action using Rasa SDK by editing **`actions/actions.py`**:

~~~python
import requests
import json
from rasa_sdk import Action

class ActionJoke(Action):
    def name(self):
        return "action_joke"

    def run(self, dispatcher, tracker, domain):
        request = requests.get("http://api.icndb.com/jokes/random").json() # make an api call
        joke = request["value"]["joke"] # extract a joke from returned json response
        dispatcher.utter_message(text=joke) # send the message back to the user
        return []

~~~

1\. In **`data/stories.yml`**, replace **`utter_cheer_up`** with the custom action **`action_joke`** tell your bot to use this new action:

2\. In **`domain.yml`**, add a section for custom actions, including your
new action

~~~yml
actions:
  - action_joke
~~~

3\. After updating your domain and stories, you must retrain your model

~~~bash
docker run --name rasa-train --gpus all -it --privileged -v C:\Users\james\Documents\GitHub\GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va:/app rasa/rasa:3.1.0-full train --domain domain.yml --data data --out models

docker run --name rasa-train --gpus all -it --privileged -v C:\Users\james\Documents\GitHub\GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va:/app rasa_3.1.0_rasa_sdk_3.1.1:dev

# in docker container run: 
cd /app
rasa train --domain domain.yml --data data --out models

~~~

Your custom actions will run on a separate server from your Rasa server.

5\. Create a network to connect the two containers: docker network create my-project

~~~bash
# Test
docker network create my-project

# MVP Project
docker network create colon-cancer-mayoclinic
~~~

6\. Run the custom actions with the following command: 

~~~bash
# Test
docker run -d --name rasa-action-server --gpus all -it --privileged -p 5055:5055 -v C:\Users\james\Documents\GitHub\GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va\actions:/app/actions --net my-project rasa/rasa-sdk:3.1.1

# MVP Project
docker run -d --name cc-action-server --gpus all -it --privileged -p 5055:5055 -v C:\Users\james\Documents\GitHub\GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va\actions:/app/actions --net colon-cancer-mayoclinic rasa/rasa-sdk:3.1.1
~~~

Run Rasa Action Server using Commands:

~~~bash
# MVP2 Project
docker run -d --name rasa-action-server --gpus all -it --privileged -p 5055:5055 -v C:\Users\james\Documents\GitHub\GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va:/app --net colon-cancer-mayoclinic rasa_3.1.0_rasa_sdk_3.1.1:dev

# in docker container run:
docker exec -it rasa-action-server /bin/bash
cd /app
rasa run actions
~~~

- **`d`**: Runs container in detached mode, so you can run the Rasa container in same window
- **`v $(pwd):/app`**: Mounts your project directory into Docker container, so that action server can run the code in **`actions`** folder
- **`net my-project`**: Run server on a specific network, so Rasa container can find it 
- **`--name rasa-action-server`**: Gives server a specific name for Rasa server to reference
- **rasa/rasa-sdk:3.1.1**: Uses Rasa SDK image with tag 3.1.1
  
Run **`docker stop rasa-action-server`**. Run **`docker ps`** to see all currently running containers

7\. To instruct Rasa server to use action server, tell Rasa its location by adding this endpoint to your **`endpoints.yml`** and referencing the **`--name`** you gave the server (in our example above **`rasa-action-server`**):

~~~yml
action_endpoint:
    url: "http://rasa-action-server:5055/webhook"
~~~

8\. Now we can talk to our Rasa Chatbot via shell or from our Unity Client

~~~bash
# Test: Talk to your Virtual Assistant From Shell
docker run --name rasa-shell --gpus all -it --privileged -p 5005:5005 -v C:\Users\james\Documents\GitHub\GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va:/app --net my-project rasa/rasa:3.1.0-full shell
~~~

Run rasa shell to see if we can talk to VA:

~~~bash
# Test: Talk to virtual assistant from shell
docker run --name rasa-shell --gpus all -it --privileged -p 5005:5005 -v C:\Users\james\Documents\GitHub\GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va:/app --net colon-cancer-mayoclinic rasa_3.1.0_rasa_sdk_3.1.1:dev

docker exec -it rasa-shell /bin/bash
cd /app
rasa shell
~~~

Talk to our Rasa Chatbot from Unity:

~~~bash
# Test: Talk to your Virtual Assistant from Unity
docker run --name rasa-run --gpus all -it --privileged -p 5005:5005 -v C:\Users\james\Documents\GitHub\GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va:/app --net my-project  rasa/rasa:3.1.0-full run
~~~

## Expose Rasa Chatbot App on Internet using Ngrok

Rasa Chatbot server is up and running on **port 5005** in our Docker container.

Launch **ngrok** Docker container to create a tunnel from the newly assigned public URL to port **5005** on the Rasa Chatbot server container called **rasa-run** (at which the **Rasa Chatbot Application** is handling requests.

~~~bash
docker run -d -p 4040:4040 --privileged --net colon-cancer-mayoclinic --name ngrok-integ-rasa wernight/ngrok ngrok http rasa-run:5005
~~~

The following public urls are examples of what you see going to **http://localhost:4040/inspect/http**

~~~md
### No requests to display yet

To get started, make a request to one of your tunnel URLs:

http://72f8-104-58-202-197.ngrok.io

https://72f8-104-58-202-197.ngrok.io
~~~

You can go to ngrok web page to see the public url, so now our Rasa Chatbot GI Cancers Application can be accessed from any client anywhere in the world:

## Talk to Rasa Virtual Assistant from Slack

To make our Rasa Chatbot available to Slack, use the **ngrok URL** followed by webhooks followed by Slack channel followed by webhook.

~~~bash
http://72f8-104-58-202-197.ngrok.io/webhooks/slack/webhook
~~~

