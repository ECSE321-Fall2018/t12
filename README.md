# t12 Timeline of Design Decisions

September 21st: Sprint begins

September 22nd: First group meeting, catalogue of user stories begin to be compiled on shared google doc

September 23rd: first base preliminary UML model is created, base classes include User, Trip, Vehicle, Admin

September 24th: UML model updated to have more classes, Registration, Tripnode, as well as two enumeration classes to assist in state machines 

September 25th: Second group meeting, UML is finalized and User stories catalogue completed 

September 26th: Base Maven, Spring and Readme pushed to the repository 

September 27th: Travis CI added to the repository, UML model created in UML lab, 

September 28th: Team begins making psuedocode on their own machines for controller classes, based on the UML diagram

September 29th: Removed UML labview from the project: The system caused several issues within the eclipse IDE, so it was removed to improve 
performance

September 30th: Webservice repositories, service, and controller classes are assigned to members  

October 1st: Group meeting 3: preliminary JUnit integration begins, Webservice components worked  

October 2nd: three inital repositories, service  are created fro User, Trip and registration. More added later 

October 3rd: Sprint 1 deadline extended in class, JUnit testing begins, 

October 4th: further JUnit testing, additional edge cases added

October 5th: Original Sprint 1 dealine, Group meeting 4: Set goals for the last stretch of Sprint 1 

October 6th: More controller methods added, more repositiories added, OATH2 servers created 

October 7th: Sprint 1 deadline, finalized project for submission 

## Sample requests:
A set of sample requests

### Signup - localhost:8080/signup
- Authorization: None
- Headers: 
  - Content-Type = application/json
- Body:
`{
	"name": "Brendan",
    "username": "User",
    "password": "Pass",
    "drivingRate": 5,
    "passRate": 2
}`
- Status: `201 Created`
- Result:
`
{
    "id": 4,
    "name": "Brendan",
    "username": "User",
    "password": "$2a$10$i0.ltBYigSyYb7QGs16qkebQHygqGwyQRMo4pKdm9n7zmSyB4kBkK",
    "drivingRate": 5,
    "passRate": 2,
    "registrations": [],
    "vehicles": [],
    "roles": [
        {
            "id": 5,
            "name": "USER"
        }
    ],
    "dweight": 5,
    "pweight": 5
}
`

The rest of the API requests can be found here:
https://documenter.getpostman.com/view/5488695/RWgnZ1Mz#8b18a371-0fd9-4b03-a9e6-5a30e78aa67b



## SPRINT 2: Mobile App front end

October 17th: Sprint 2 begins 

October 22nd : Group Meeting, Created issues, discussed requirements within the Sprint 2 document, Created mock UI, Listed neccessary chnages/ updates to backend, Laid a timeline for sprint 2 and split up tasks,  

October 23rd

October 24th

October 25th

October 26th

October 27th

October 28th

October 29th: 

October 30th

October 31st: Sprint 2 ends 
