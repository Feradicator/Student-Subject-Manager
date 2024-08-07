Spring Boot Student-Subject Enrollment System
Project Overview
This project is a Spring Boot application that manages students and subjects. It allows students to enroll in multiple subjects and provides REST APIs to interact with the data. The application uses H2 in-memory database for simplicity and supports basic security with JWT authentication.

Technologies Used
Spring Boot
Spring MVC
Spring Data JPA
Spring Security
H2 Database
Lombok

Prerequisites
Java 19 or later
Maven

Setup and Configuration
Clone the Repository
git clone https://github.com/yourusername/spring-student-subject-system.git
cd spring-student-subject-system
Build and Run
Build the Project

Use Maven to build the project:
mvn clean install
Run the Application

Run the Spring Boot application:
mvn spring-boot:run


Features Added in Application
Admin:
1. Create Admin account
2. Create Subjects 
3. Get the Number of Students
4. Get all the subject with all studnets enrolled in each subject
   
Student:
1. Create Student Account(by signup)
2. get the number of subjects available
3. enroll student in the subject
4. get the number of subjects student is enrolled

run on postman 
step1:sign up as admin using post method 
api- http://localhost:8080/api/admin/signup
method:post
 body : {
    "name":"admin",
    "email":"admin",
    "password":"admin"
}
step 2: jwt token is returned save it in global variable and use it for futher interaction with server
step 3: create subjects 
api - http://localhost:8080/api/admin/new/subject
method-post
body:
    {
  "name":"Scienece"
}
repeat this 3 to 4 times with different subjects

step 4:sign up as student
api- http://localhost:8080/api/student/signup
method-post
body -
{
    "email":"shivamyadav",
    "password":"12345",
    "name":"Shivam",
    "address":"mumbai"
}
step5: get the number of subjects available and remember id of subject and student,it will start from 1
api:http://localhost:8080/api/student/subjects
method-get

step6: enroll student in the subjects
api:http://localhost:8080/api/student/{student_id}/subjects/{subject_id}
metod:post

step 7- get the subjects in which the student is enrolled
api:http://localhost:8080/api/student/subjects/enrolled
method:get

step8-sigin as admin 
api:api- http://localhost:8080/api/admin/signin
method:post
 body : {
    
    "email":"admin",
    "password":"admin"
}

step9-get all the students
api-
http://localhost:8080/api/admin/students
method-get

step-10 get number of subjects in each sujects
api- http://localhost:8080/api/admin/subjects
method -get




