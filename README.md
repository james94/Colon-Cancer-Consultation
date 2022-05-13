# GI Cancers 2D Gallery Rasa VA

## Overview

GI Cancers 2D Gallery originally was inspired by museums that feature virtual tours where you can have a conversations with a chatbot. But GI Cancers 2D Gallery would feature one or more types of GI Cancer with the stages of that cancer in a portrait and deployed on the backend of that portrait would be a Rasa chatbot that is knowledgeable in that cancer area.

As the requirements for the project changed for CMPE 252 and we were required to integrate our Rasa Chatbot with Slack, I stopped working on the Unity portion, so I could make sure Slack integration worked.

So what will be featured on this page is how to setup the development environment with Docker containers and/or Anaconda3 on your local machine. Then we will walk through how to connect our Rasa Chatbot to our Slack Channel workspace. Finally, we will go through some example statements and queries we can send Rasa, so we can get the conversation going about Colon Cancer.

Here is a link to the demo of me interacting with Rasa from Shell:

- [YouTube: Colon Cancer Consultation with Rasa Shell VA | SJSU CMPE 252 AI Demo](https://www.youtube.com/watch?v=A-pmkEfo9Ps)

Here is a link to the demo of me interacting with Rasa from Slack:

- [YouTube: Colon Cancer Consultation with Slack Rasa VA | SJSU CMPE 252 AI Demo](https://www.youtube.com/watch?v=5VjcWlqOXgU)
Here is a link to the demo of me interacting with Rasa from Unity:

- [YouTube: Colon Cancer Consultation with Unity Rasa VA | SJSU CMPE 252 AI Demo](https://www.youtube.com/watch?v=hQWY9gjZ7WU)

## Outline

- [GI Cancers 2D Gallery Rasa VA](#gi-cancers-2d-gallery-rasa-va)
  - [Overview](#overview)
  - [Outline](#outline)
  - [Contents](#contents)
  - [Chatbot Functionality](#chatbot-functionality)
  - [Deliverablees](#deliverablees)
  - [Setup Software Dev Environment for Project](#setup-software-dev-environment-for-project)
    - [Software Dependencies for Running Demo](#software-dependencies-for-running-demo)
    - [Anaconda Install Software Dependencies](#anaconda-install-software-dependencies)
  - [Launch Docker SpringBoot and MySQL Before Running Rasa](#launch-docker-springboot-and-mysql-before-running-rasa)
  - [How to Run Rasa Demo](#how-to-run-rasa-demo)
    - [Approach 1: Talk to Rasa VA from Shell](#approach-1-talk-to-rasa-va-from-shell)
    - [Approach 2: Talk to Rasa VA from Slack](#approach-2-talk-to-rasa-va-from-slack)
    - [Approach 3: Talk to Rasa VA from Unity](#approach-3-talk-to-rasa-va-from-unity)
  - [Appendix](#appendix)
    - [Understanding Rasa Conversation Flow](#understanding-rasa-conversation-flow)
    - [Building a RASA Assistant in Docker](#building-a-rasa-assistant-in-docker)
    - [Rasa Custom Actions Leverage: Scrapy](#rasa-custom-actions-leverage-scrapy)
  - [References](#references)


## Contents

- **[docs](./docs/)**: contains documentation on the project
- **[gi-cancers-api](./gi-cancers-api/)**: contains Java Spring Boot server code for connecting Rasa's temporary data collected on the patient to MySQL
- **[gi-cancers-mysql-db](./gi-cancers-mysql-db/)**: contains schema.sql file for creating the tables that could contain healthcare data from data collected in colon cancer consultations between the patient and doctor. Currently just one table we populate with patient colon cancer symptoms **pcc_symptoms** table. You will see other tables there too, but they aren't being used.
- **[images](./images)**: contains images that are used in this README and the docs files.
- **[rasa_apps](./rasa_apps/)**: contains one rasa chatbot app, which is focused on **colon cancer** virtual assistance. The idea here was that I could have multiple rasa chatbots that are each trained on different NLU data based on a particular GI cancer conversation flow. There is also a Rasa Dockerfile too.
- **[Rasa-Unity-2D-Gallery](./Rasa-Unity-2D-Gallery/)**: contains Unity 2D assets and C# code for integrating my Rasa chatbot Python app into a Unity 2D scene. To build this part of the app, I referenced this tutorial "[Integrating Rasa Open Source Chatbot Into Unity [Part 1] : The Connection](https://medium.com/analytics-vidhya/integrating-rasa-open-source-chatbot-into-unity-part-1-the-connection-9ba582c804cd)" and this one "[Integrating Rasa Open Source Chatbot Into Unity [Part 2] : The Visuals](https://medium.com/analytics-vidhya/integrating-rasa-open-source-chatbot-into-unity-part-2-the-visuals-f67a915a4b2d)".
- **README**: explains project overview, contents, steps on how to run the demo


## Chatbot Functionality

- [x] Ability to **understand 20 user utterances/queries** (including reasonable variations of those queries).
- [ ] Ability to have at least 2 multi-turn conversations where the user may ask follow-up questions once the bot responds to their original query.
- [x] Ability to collect data from the user, e.g., on what date did the issue occur, how many items did you have in your order, etc.
- [x] Ability to engage in some amount of small talk (e.g., hi, how are you, etc.)
- [x] Ability to use a **database to store information obtained from the user**
    - [x] Creating Tables in MySQL Docker Container for Database on Colon Cancer user symptoms, risk factors, etc
    - [x] Need to create SpringBoot Docker container middleware layer for passing data between Rasa chatbot and MySQL database

- [x] Ability to make **2 API calls to retrieve information from an external source**. (At least one of these APIs must be outside of what is built into Rasa i.e time/weather)
    - [x] Make API Call to Fetch Colon Cancer Symptoms
    - [x] Make API Call to Fetch Colon Cancer Risk Factors
- [x] Needs to be deployed on Slack (see below).

## Deliverablees

- [x] Presentation in class
    - Must cover the following for your chatbot:
      - [x] Motivation for your choice of use case
      - [x] Conversation flows you implemented + Demo
      - [x] Challenges you encountered
      - [x] Insights/lessons learned   
- [ ] Submission of project collatoral on SJSU Canvas
    - [ ] Link to code on GitHub + instructions on running it
    - [x] Create a private GitHub repository (not public) and share access with both Amanbeer and Prof. Jetcheva
    - [x] Mention in your README file what version of Rasa the chatbot has been developed on
    - [ ] The repository must contain a 'requirements.txt' file with all the dependencies required to run the chatbot (you can make one with `pip freeze` command)
    - [x] Mention any open issues that you found to exist in your chatbot

## Setup Software Dev Environment for Project

### Software Dependencies for Running Demo

The following software can be installed in Anaconda or Docker:

- Rasa 3.1.0 (Rasa Chatbot Server)
- Rasa 3.1.1 SDK (Rasa Actions Server)
- Scrapy Web Crawler
- Java Spring Boot 2.6.6
- MySQL 8.0.27
- Ngrok version latest

My **Docker Desktop version was 4.3.2** when I installed the software above.

We will install the software using **Anaconda 3** directly on our local machine.

### Anaconda Install Software Dependencies

1\. Clone this project repository

~~~batch
git clone https://github.com/james94/GI-Cancers-2D-Gallery
~~~

2\. Create ananconda environment:

~~~batch
conda create -n rasa-310_rasa-sdk-311 python=3.7
conda activate rasa-310_rasa-sdk-311
~~~

3\. Install Python dependencies:

~~~batch
cd GI-Cancers-2D-Gallery
pip install -r requirements.txt
~~~

## Launch Docker SpringBoot and MySQL Before Running Rasa

Refer to guide [Deploy MySQL and SpringBoot Containers for GiCancers App](./docs/DockerMySQLBackend.md) that goes through the necessary.

## How to Run Rasa Demo

1\. First we'll go to the Rasa project folder:

~~~batch
cd rasa_apps\colon_cancer_va
~~~

2\. Train Rasa NLU Model:

~~~bash
rasa train --domain domain.yml --data data --out models
~~~

Note: In case there is an issue with the newly trained model, I will leave the Rasa **model** version I used during the demo in **cmpe252_demo_model/** folder.

3\. Turn on Rasa Actions Server:

~~~bash
rasa run actions
~~~

Note: rasa **endpoints.yml** will be impacted if you choose to run rasa actions server locally or in docker. For this tutorial, I will leave **endpoints.yml** updated in a way that works for running it on local machine, so the **url** will contain **localhost** instead of docker **<container-name>**.

There are 3 approaches that we can use to talk to our Rasa Virtual Assistant: Slack, Shell or Unity.

### Approach 1: Talk to Rasa VA from Shell

Talking to Rasa from shell is probably the fastest approach.

4\. Since our rasa actions server is running in our current anaconda prompt, open a new conda prompt, activate our **rasa-310_rasa-sdk-311** conda environment and run rasa shell:

~~~batch
cd GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va
rasa shell
~~~

Example Statements you as the patient can send to Rasa:

- `"Im visiting about colon cancer"`
- `"What are the phases of colon cancer?"`
- `"What are the signs of colon cancer?"`
- `"What are colon cancer risk factors?"`

Here is a link to the demo of me interacting with Rasa from Shell:

- [YouTube: Colon Cancer Consultation with Rasa Shell VA | SJSU CMPE 252 AI Demo](https://www.youtube.com/watch?v=A-pmkEfo9Ps)

### Approach 2: Talk to Rasa VA from Slack

Assuming that you have setup your own Slack App using Slack's API that Rasa can connect, lets update our **[credentials.yml](./rasa_apps/colon_cancer_va/credentials.yml)** file.

4\. You will need to update the following key value pairs:

~~~yml
slack:
 slack_token: <your_token>
 slack_channel: <your_channel_id>
 slack_signaling_secret: <your_secret_token>
~~~

Once you have updated those slack related credentials, we can launch rasa run server, so we can talk to our chatbot from Slack.

5\. Launch Rasa run server:

~~~batch
cd GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va
rasa run
~~~

6\. Once the rasa run server is running, we should open a new conda prompt and launch ngrok to expose our Rasa app to the internet, so its easier for slack to connect to it:

~~~batch
cd GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va
ngrok http localhost:5005
~~~

7\. Now you'll want to go to your **Slack API Event Subscriptions**, under Enable Events, provide it with the url of where it can connect with your Rasa app through the url ngrok created:

~~~batch
rem Example of web url, change ngrok hostname "72f8-104-58-202-197.ngrok.io"
http://72f8-104-58-202-197.ngrok.io/webhooks/slack/webhook
~~~

If Slack API can connect to Rasa, you should get a verified check mark. 

**Troubleshooting**: If you run into issues where slack cant connect to Rasa, make sure "rasa run server" is running in one conda prompt and that the ngrok is running in another conda prompt. If you are running it at a public network, work network, you can try at your home network to see if there is a difference.

8\. Go to your Slack App where you will be interacting with Rasa. Here are some example statements you as the patient can send to Rasa:

- `"Im visiting about colon cancer"`
- `"What are the phases of colon cancer?"`
- `"What are the signs of colon cancer?"`
- `"What are colon cancer risk factors?"`

Here is a link to the demo of me interacting with Rasa from Slack:

- [YouTube: Colon Cancer Consultation with Slack Rasa VA | SJSU CMPE 252 AI Demo](https://www.youtube.com/watch?v=5VjcWlqOXgU)

### Approach 3: Talk to Rasa VA from Unity

Assuming you have Unity installed on your computer, you should be able to import the Unity project from this github repo there.

4\. Launch Rasa run server:

~~~batch
cd GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va
rasa run
~~~

5\. Once the rasa run server is running, we should open a new conda prompt and launch ngrok to expose our Rasa app to the internet(, even if our Unity App is running on our local machine with ngrok and Rasa, Unity should be able to connect to Rasa):

~~~batch
cd GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va
ngrok http localhost:5005
~~~

6\. Now we will need to update the **rasa_url** in our Unity **[NetworkManager.cs](Rasa-Unity-2D-Gallery\Assets\Scripts\NetworkManager.cs)** file:

~~~C#
    // Unity communicates to Rasa using custom connectors and POST requests.
    // Rasa implement a default rest connector which can be accessed at rasa_url
    private const string rasa_url = "http://72f8-104-58-202-197.ngrok.io/webhooks/rest/webhook";
~~~

This rasa_url string is in our NetworkManager class.

7\. Remember when we launched ngrok, it created a url for our Unity App to be able to connect to Rasa, we just have to take the **http://<hostname>** part and overwrite that part in our **rasa_url**:

~~~batch
rem Example of web url, change ngrok hostname "72f8-104-58-202-197.ngrok.io" to yours
http://72f8-104-58-202-197.ngrok.io/webhooks/slack/webhook
~~~

8\. Open the **Unity App: Rasa-Unity-2D-Gallery** in **Unity Engine** and run the simulation.

If the Unity App runs and you are able to connect to Rasa from it, you can try these example statements you as the patient can send to Rasa:

- `"Im visiting about colon cancer"`
- `"What are the phases of colon cancer?"`
- `"What are the signs of colon cancer?"`
- `"What are colon cancer risk factors?"`

Here is a link to the demo of me interacting with Rasa from Unity:

- [YouTube: Colon Cancer Consultation with Unity Rasa VA | SJSU CMPE 252 AI Demo](https://www.youtube.com/watch?v=hQWY9gjZ7WU)


## Appendix 

### Understanding Rasa Conversation Flow

For referencing Rasa conversation flow, check out this doc: [UnderstandingRasaFlow.md](./docs/UnderstandingRasaFlow.md)

### Building a RASA Assistant in Docker

For referencing steps on building the Rasa Assistant in Docker, check out this doc: [Building a RASA Assistant in Docker](./docs/BuildingRasaAssistantDocker.md)

### Rasa Custom Actions Leverage: Scrapy

For referencing Scrapy Web Crawler examples toward Mayo Clinic, check out this doc: [Scrapy Web Crawler Examples Scraping Mayo Clinic](./docs/ScrapyWebCrawlerExamples.md)

## References

- [MayoClinic: Colon Cancer](https://www.mayoclinic.org/diseases-conditions/colon-cancer/symptoms-causes/syc-20353669)

- [MayoClinic API](https://gbs.mayoclinic.org/licensable-content/content-platform.php)

- [Storing data to the Database | Rasa Chatbot | Part - 1](https://www.youtube.com/watch?v=rS4Wb8hvggI)

- [Storing data to the Database | Rasa Chatbot | Part - 2](https://www.youtube.com/watch?v=Ds8cB3LZwfU)

- [fediazgon/mayoclinic-scrapper]()

- [ReactorNotRestartable error in while loop with scrapy](https://stackoverflow.com/questions/39946632/reactornotrestartable-error-in-while-loop-with-scrapy): solution to allowing me to run my scrapy spider web crawler in my Actions run method multiple times.

- [Expose Docker Container services on the Internet using the ngrok docker image](https://medium.com/oracledevs/expose-docker-container-services-on-the-internet-using-the-ngrok-docker-image-3f1ea0f9c47a)

- [Change in Bowel Habits - Healthline](https://www.healthline.com/health/change-in-bowel-habits)

- [Abdominal Pain - Cleveland Clinic](https://my.clevelandclinic.org/health/symptoms/4167-abdominal-pain)
