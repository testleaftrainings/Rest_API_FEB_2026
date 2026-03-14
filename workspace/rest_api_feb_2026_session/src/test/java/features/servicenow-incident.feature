Feature: Validate the CRUD operation of the servicenow incident table api

Scenario: Validate user should able to fetch all records from the incident table api
Given user should set baseuri as "https://dev324941.service-now.com" of the servicenow api
And user should set basepath as "api/now/table" of the servicenow api
And user should set basic authenication username as "admin" and password as "/eESj0uC3k+O"
When user should hit get method to retrieve all records from the incident table
Then user should see the status code should be "200"
And user should see the status line should be "OK"
And user should get response in the "JSON" format

Scenario: Validate user should able to fetch all records from the incident table api response in XML format
Given user should set baseuri as "https://dev324941.service-now.com" of the servicenow api
And user should set basepath as "api/now/table" of the servicenow api
And user should set basic authenication username as "admin" and password as "/eESj0uC3k+O"
And user should set header key as "Accept" and value as "application/xml"
When user should hit get method to retrieve all records from the incident table
Then user should see the status code should be "200"
And user should see the status line should be "OK"
And user should get response in the "XML" format

Scenario: Validate user should create new record in the incident table
Given user should set baseuri as "https://dev324941.service-now.com" of the servicenow api
And user should set basepath as "api/now/table" of the servicenow api
And user should set basic authenication username as "admin" and password as "/eESj0uC3k+O"
And user should set header key as "Content-Type" and value as "application/json"
And user should enter the short description as "RESTAPISESSIONJAN2025" in the request body
When user should hit post method to create new record in the incident table
Then user should see the status code should be "201"
And user should see the status line should be "Created"
And user should get response in the "JSON" format