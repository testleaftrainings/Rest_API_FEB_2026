Feature: Validate the CRUD operation of the servicenow incident table api

Background:
Given user should set baseuri as "https://dev324941.service-now.com" of the servicenow api
And user should set basepath as "api/now/table" of the servicenow api
And user should set basic authenication username as "admin" and password as "/eESj0uC3k+O"

Scenario: Validate user should able to fetch all records from the incident table api
When user should hit get method to retrieve all records from the incident table
Then user should see the successfull response with the below expected value
| statusCode | statusLine | responseFormat |
| 200        | OK         | JSON           |