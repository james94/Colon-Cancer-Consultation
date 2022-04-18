# GI Cancers 2D Gallery

GI Cancers 2D Gallery is the integration between Unity and one or more Rasa Unity Chatbots. Unity acts as a client connecting to a Rasa server deployed in a Docker container.

## Part 1: Colon Cancer Rasa Chatbot

When you hit play in Unity, you are taken to the Colon Cancer Rasa Chatbot view:

![colon_cancer_rasa](images/rasa_unity_colon_cancer_part1.jpg)

## Appendix: Building a RASA Assistant in Docker

### Pull Rasa Docker Image

~~~bash
docker pull rasa/rasa:latest
~~~

### Setup RASA Project

~~~bash
# Setup RASA GPU Project
docker run --name rasa-init --gpus all -it --privileged -p 5005:5005 -v C:\Users\james\Documents\GitHub\GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va:/app rasa/rasa:latest init --no-prompt
~~~

### Train RASA Model

~~~bash
# Train Rasa Model
docker run --name rasa-train --gpus all -it --privileged -p 5005:5005 -v C:\Users\james\Documents\GitHub\GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va:/app rasa/rasa:latest train --domain domain.yml --data data --out models
~~~


### Talk to your Virtual Assistant From Shell

~~~bash
# Talk to your Virtual Assistant From Shell
docker run --name rasa-shell --gpus all -it --privileged -p 5005:5005 -v C:\Users\james\Documents\GitHub\GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va:/app rasa/rasa:latest shell
~~~

### Talk to your Virtual Assistant From Unity

To launch Rasa server, so our Virtual Assistant is just running in the Docker container, you do docker run followed by Rasa **run** command, so then external clients like Unity in our case can interact with our Virtual Assistant.

~~~bash
# Talk to your Virtual Assistant from Unity
docker run --name rasa-run --gpus all -it --privileged -p 5005:5005 -v C:\Users\james\Documents\GitHub\GI-Cancers-2D-Gallery\rasa_apps\colon_cancer_va:/app rasa/rasa:latest run
~~~


