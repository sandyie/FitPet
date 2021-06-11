# My Personal Project 
# "FitPet"
 
## What will the application do? 
This is a ***exercise record*** application in form of a game. The application resembles a virtual pet shop where user 
will be taking care of a pet. 
Possible features inclues:
1) able to record major types of daily activies and store the amount of calories burned in form
of energy points
2) energy points earned can be used to feed or play with pet 
3) feeding pet can increase energy level of pet 
4) playing with pet can increase relationship with pet but decrease energy level of pet
5) Pet will die when energy level = 0 
6) Record food intake and deduct pet's energy level according to calorie intake
7) print recorded activity log of user


##Who will use it?
Due to COVID, many people start to learn and work from home. Many parks and gyms are also temporary closed in bacause of
 social distancing. As a result,we exercise less and eat more at home. This application can motivate users to exercise
 while at home and promote a healthier lifestyle.
 
 Possible users include:
 1) need motivation to exercise at home
 2) currently following a diet plan
 3) want to record calories burned 
 
 ##Why is this project of interest to you?
Since UBC annouced the shift to online learning in April, I realized that I spent less time exercising at home. The 
pedometer on my phone often had shocking records of less than 600 steps per day. On the contract, I eat more because I 
have access to food/snack all the time. This phenomenon happens to many people around me as well. Thus, I want to 
motivate myself and others to start working out even at home. A cute interaction game seems to be a good approach. 

## User stories
* as a user, I want to record major types of exercises by selecting given options
* as a user, I want to store exercise recorded into an overall exercise log (add multiple X to Y)
* as a user, I want to have interaction with my virtual pets through feeding and playing 
* as a user, I want to enter food intake and update my game according to calorie intake
* as a user, I want to print activity log that include all exercises recorded and total calorie burned when wanted
* as a user, I want to know calcorie burned when enter new exercise to exercise log

* as a user, I want to be able to save my state of pet game any time
* as a user, I want to be able to load my previoud pet game 
* as a user, I want to be given an option of save game / quit game / reload game
* as a user, I want to automatically save game when I quit game

## Short notes (please read, thank you)
When designing new classes in the model package, I found a MyModelTest class in the **main** model package. I moved 
that class and wrote all my test in the test package (which contain model and ui package for model and ui tests). Thank
you for reading this quick note.

##Citation 
*When writing the phase2, I looked at the given demo lab and used some code of the demo lab in my own persistence pack


