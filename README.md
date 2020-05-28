# Email Service

This is a REST App for counting unique email IDs. 

It has maven POM file so can be imported as a Maven Project.

The Test Cases will be a good starting point. If anything breaks then please provide feedback but as of this commit all test cases work successfully.

This application can also be started as a 'server' by executing the Main.java as Java Application. An application such as Postman can be used to perform REST calls. 

Here's a sample from Postman:

URL:http://localhost:8080/myapp/email/countUnique

Method: POST

Request Header:  Content-Type: application/json

Raw Body:
{
	"emailIDs": ["a@b", "a@c", "a@b"]
}

Raw Response will be:

{"resultCode":0,"resultMsg":"success","count":2}
