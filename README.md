# RB RS

RB RS is a Restful Webservice application, which can process csv and xml data. 

## App Usage

Post .csv or .xml files in the correct format to get them validated by the service.

## Checkout and Run

Step 1. git clone https://github.com/vishnubharath/RB_RS.git

Step 2. cd RB_RS/

Step 3. mvn spring-boot:run

Please find below the mvn command to run test.

mvn test

## Develop

Use the below mvn command to build an eclipse project and import the same to eclipse.

mvn eclipse:eclipse

## Running unit tests

To run the unit test, please exicute the below command. Please not that the app is designed to run on Java 1.7.

mvn test

Please make necessary changes to the records.csv and records.xml to test different scenarios.