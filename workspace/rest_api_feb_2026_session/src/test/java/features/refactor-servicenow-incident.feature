Feature: Validate the CRUD operation of the servicenow incident table api

Background:
Given user should set baseuri as "https://dev324941.service-now.com" of the servicenow api
And user should set basepath as "api/now/table" of the servicenow api
And user should set basic authenication username as "admin" and password as "/eESj0uC3k+O"

@smoke
Scenario: Validate user should able to fetch all records from the incident table api
When user should hit get method to retrieve all records from the incident table
Then user should see the successfull response with the below expected value
| statusCode | statusLine | responseFormat |
| 200        | OK         | JSON           |

@smoke @regression
Scenario: Validate user should able to fetch all records from the incident table api response in XML format
Given user should set header key as "Accept" and value as "application/xml"
When user should hit get method to retrieve all records from the incident table
Then user should see the successfull response with the below expected value
| statusCode | statusLine | responseFormat |
| 200        | OK         | XML            |

@regression
Scenario Outline: Validate user should create new record in the incident table
Given user should set header key as "Content-Type" and value as "application/json"
And user should enter the short description as "<short_description>" in the request body
And user should enter the description as "<description>" in the request body
And user should enter the category as "<category>" in the request body
When user should hit post method to create new record in the incident table
Then user should see the successfull response with the below expected value
| statusCode | statusLine | responseFormat |
| 201        | Created    | JSON           |

Examples: 
| short_description | description        | category |
| RESTAPIFEB20261   | Create new record1 | hardware |
| RESTAPIFEB20262   | Create new record2 | software |
| RESTAPIFEB20263   | Create new record3 | inquiry  |