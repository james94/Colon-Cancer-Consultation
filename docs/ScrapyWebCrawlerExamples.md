# Scrapy Web Crawler Examples Scraping Mayo Clinic

Here are examples of running Scrapy web crawler in the shell for scraping data on colon cancer from Mayo Clinic through making API requests. I have 3 parts where I use scrapy to scrape data on:

- Part 1: Colon Cancer Symptoms, Risk Factors & Causes
- Part 2: Colon Cancer Diagnosis & Treatment
- Part 3: Colon Cancer Doctors & Departments

## Libraries

~~~bash
conda install -y Scrapy
~~~


### Part 1: Colon Cancer Symptoms, Risk Factors & Causes

Pass in Colon Cancer Symptomos & Causes page to **scrapy**:

~~~bash
scrapy shell "https://www.mayoclinic.org/diseases-conditions/colon-cancer/symptoms-causes/syc-20353669"
~~~

Extract Colon Cancer name:

~~~python
disease_name = response.xpath("//h1/a/text()").extract()[0]
~~~

Expected Output:

~~~python
'Colon cancer'
~~~

Extract Colon Cancer Symptoms:

~~~python
symptom_names = response.xpath('//h2[text()="Symptoms"]/following-sibling::ul[1]/li/text()').extract()
~~~

Expected Output from printing **symptom_names**:

~~~python
['A persistent change in your bowel habits, including diarrhea or constipation or a change in the consistency of your stool',
 'Rectal bleeding or blood in your stool',
 'Persistent abdominal discomfort, such as cramps, gas or pain',
 "A feeling that your bowel doesn't empty completely",
 'Weakness or fatigue',
 'Unexplained weight loss']
~~~

Extract Colon Cancer Risk Factors:

~~~python
risk_factor_names = response.xpath('//h2[text()="Risk factors"]/following-sibling::ul[1]/li/strong/text()').extract()
~~~

Expected Output from printing **risk_factor_names**:

~~~python
['Older age.',
 'African-American race.',
 'A personal history of colorectal cancer or polyps.',
 'Inflammatory intestinal conditions.',
 'Inherited syndromes that increase colon cancer risk.',
 'Family history of colon cancer.',
 'Low-fiber, high-fat diet.',
 'A sedentary lifestyle.',
 'Diabetes.',
 'Obesity.',
 'Smoking.',
 'Alcohol.',
 'Radiation therapy for cancer.']
~~~

Extract Colon Cancer Prevention:

~~~python
prevention_names = response.xpath('//h2[text()="Prevention"]/following-sibling::ul[1]/li/strong/text()').extract()
~~~

Expected Output from printing **prevention_names**:

~~~python
['Eat a variety of fruits, vegetables and whole grains.',
 'Drink alcohol in moderation, if at all.',
 'Stop smoking.',
 'Exercise most days of the week.',
 'Maintain a healthy weight.']
~~~

## Part 2: Colon Cancer Diagnosis & Treatment

~~~bash
scrapy shell "https://www.mayoclinic.org/diseases-conditions/colon-cancer/diagnosis-treatment/drc-20353674"
~~~

Extracting Early Stage Surgery Treatments:

~~~python
early_stage_treatments = response.xpath('//h2[text()="Treatment"]/following-sibling::ul[1]/li/strong/text()').extract()
~~~

Expected output:

~~~python
['Removing polyps during a colonoscopy (polypectomy).',
 'Endoscopic mucosal resection.',
 'Minimally invasive surgery (laparoscopic surgery).']
~~~

Extracting Advanced Stage Surgery Treatments:

~~~python
adv_stage_treatments = response.xpath('//h2[text()="Treatment"]/following-sibling::ul[2]/li/strong/text()').extract()
~~~

Expected output:

~~~python
['Partial colectomy.', 'Lymph node removal.']
~~~

Extract Information on What You Can Do to Prepare for the Apppointment:

~~~python
apt_prep_you_can_do = response.xpath('//h2[text()="Doctors who treat this condition"]/following-sibling::ul[1]/li/strong/text()').extract()
~~~

Expected Output:

~~~python
['Be aware of any pre-appointment restrictions.',
 "Write down any symptoms you're experiencing,",
 'Write down key personal information,',
 'Make a list of all medications,',
 'Consider taking a family member or friend along.',
 'Write down questions to ask your doctor.']
~~~

Extract What To Expect From Your Doctor Questions:

~~~python
queries_exp_from_doctor = response.xpath('//h3[text()="What to expect from your doctor"]/following-sibling::ul[1]/li/text()').extract()
~~~

Expected Output:

~~~python
['When did you first begin experiencing symptoms?',
 'Have your symptoms been continuous or occasional?',
 'How severe are your symptoms?',
 'What, if anything, seems to improve your symptoms?',
 'What, if anything, appears to worsen your symptoms?',
 'Do you have a family history of colon cancer or other cancers?']
~~~

## Part 3: Colon Cancer Doctors & Departments

~~~python
scrapy shell "https://www.mayoclinic.org/diseases-conditions/colon-cancer/doctors-departments/ddc-20353677"
~~~

Extract List of Doctors specializing in this area at Mayo Clinic:

~~~python
mc_cc_doctors = response.xpath('//h3[text()="Displaying 1-10 out of 50 doctors available"]/following-sibling::ol[1]/li/text()').extract()

# get a lot of info
response.xpath('//h3[text()="Displaying 1-10 out of 50 doctors available"]/following-sibling::ol[1]').extract()

# get 10 doctors on that current page 1. There are 5 pages
response.xpath('//h3[text()="Displaying 1-10 out of 50 doctors available"]/following-sibling::ol[1]/li/div/h4/a/text()').extract()

# TODO: Web crawler needs all 5 pages if I want the 50 doctors
~~~

