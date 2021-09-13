# TaskMaster2
## lab 26
### The main page  
 should have a heading at the top of the page, an image to mock the “my tasks” view, and buttons at the bottom of the page to allow going to the “add tasks” and “all tasks” page.

### Add a Task
On the “Add a Task” page, allow users to type in details about a new task, specifically a title and a body. When users click the “submit” button, show a “submitted!” label on the page.

### All Tasks
The all tasks page should just be an image with a back button; it needs no functionality.
 
 ## lab 27
 
### Task Detail Page
Create a Task Detail page. It should have a title at the top of the page, and a Lorem Ipsum description.

### Settings Page
Create a Settings page. It should allow users to enter their username and hit save.

### Homepage
The main page should be modified to contain three different buttons with hardcoded task titles. When a user taps one of the titles, it should go to the Task Detail page, and the title at the top of the page should match the task title that was tapped on the previous page.

The homepage should also contain a button to visit the Settings page, and once the user has entered their username, it should display “{username}’s tasks” above the three task buttons.

## lab 28
*refactor  homepage to look snazzy, with a RecyclerView full of Task data.
## Homepage
Refactor your homepage to use a RecyclerView for displaying Task data. This should have hardcoded Task data for now.




## home page 
![mytask](img/mytask.PNG)

## add task
![addtask](img/addtask.PNG)

## all task
![alltask](img/alltask.PNG)


## updated home page 
![home page](img/homepage.PNG)

## labs page
![labs](img/labs.PNG)

## codechallenges page 
![code challenges](img/codechallenges.PNG)

## sport page
![sport](img/sport.PNG)

## setting page 
![setting](img/setting.PNG)



## updated home page 
![home page](img/lab.PNG)
![home page](img/cc.PNG)
![home page](img/sports.PNG)



## lab 29
Saving Data with Room

## lab 31
Espresso Testing
Add Espresso to the application, and use it to test basic functionality of the main components of my application. For example:

assert that important UI elements are displayed on the page
tap on a task, and assert that the resulting activity displays the name of that task
edit the user’s username, and assert that it says the correct thing on the homepage


## lab 32
Tasks Are Cloudy
Using the amplify add api command, create a Task resource that replicates our existing Task schema. Update all references to the Task data to instead use AWS Amplify to access your data in DynamoDB instead of in Room.

Add Task Form
Modify your Add Task form to save the data entered in as a Task to DynamoDB.

Homepage
Refactor your homepage’s RecyclerView to display all Task entities in DynamoDB.



## lab 36
User Login
Add Cognito to your Amplify setup. Add in user login and sign up flows to your application, using Cognito’s pre-built UI as appropriate. Display the logged in user’s username somewhere relevant in your app.

User Logout
Allow users to log out of your application.
