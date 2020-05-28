# Email Service

This is a Rest App for counting unique email IDs. 

It uses maven POM file so it should be import as a Maven Project.

The Test Cases should be a good starting point. All test work successfully with current code, so if anything breaks, please provide feedback.

The application can also be started as a 'server' by running the Main.java and then using something like Postman. 
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
