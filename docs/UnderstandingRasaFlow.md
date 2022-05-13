# Understanding Rasa Conversation Flow

Order Rasa goes for Files to Answer User comment/question/etc:

- Rasa: nlu.yml --> stories.yml --> domain.yml

1\. I will start by greeting Rasa: **`hi`**. Then that example greet will be linked to **intent: `greet`**. Reference **`nlu.yml`**

- Me: `hi`
- Rasa: for `hi` example --> **`nlu.yml`** --> `intent: greet`

2\. Rasa goes to **`stories.yml`** for intents and actions to know how to respond to **intent: `greet`** with an **action: `utter_greet`**.

- Rasa: for `nlu.yml`'s `intent: greet` example -->  `stories.yml` --> `action: utter_greet`

3\. Rasa goes to **`domain.yml`** to know which action to say for **utter_greet**, which Rasa finds to say **text: `"Hey! How are you?"`**. We see that back in our chat window.

- Rasa: goes to `domain.yml` for response to `utter_greet` --> `text: "Hey! How are you?"`

Order Rasa goes through to collect data on user:

- Rasa: nlu.yml --> rules.yml --> domain.yml

Order Rasa goes through to run a custom action:

- Approach A: Rasa: nlu.yml --> stories.yml --> domain.yml --> actions.py

- Approach B: Rasa: nlu.yml --> rules.yml --> domain.yml --> actions.py

There could be multiple ways that Rasa goes to perform its actions and have a conversation with the user.

