# User Guide

### 1. Introduction
### 2. Quick Start
### 3. Features
###   Task Managment
####   3.1. Add a To Do task: todo
####   3.2. Add an Event task: event
####   3.3. Add a Deadline task: deadline
####   3.4. Mark completion of task: done
####   3.5. Find a task: find
####   3.6. List all tasks: list
####   3.7. Delete a task: delete
###   Flashcard management 
####   3.9. Create a topic for Flashcards: topic 
####   3.10. Create a Flashcard: flashcard
####   3.11. List all questions: list_qns
####   3.12. List all answers: list_ans
### 4. FAQ


## 1. Introduction
#### This application allows the user to create, store and manage tasks.
#### The user can also create flashcards with this application. 

## 2. Quick Start
####    1. Install Java 11 or above in your computer 
####    2. Download the duke-0.1.3.jar file
####    3. Double click on the app to run.

## 3. Features 
#### Follow the formats given below to execute each command.

###   3.1 Add a To Do task: todo
####    Adds a To Do task to the schedule.
####    Format: `todo` *task_name*

###   3.2 Add an Event task: event
####    Adds an Event task to the schedule.
####    Format: `event` *task_name* /at *date* *time*
######  Note: Date format (DD/MM/YYYY). Time format (24hr. Eg. 1900) 

### 3.3 Add a Deadline task: deadline
#### Adds a Deadline task to the schedule.
#### Format: `deadline` *task_name* *task_name* /by *date* *time*
###### Note: Date format (DD/MM/YYYY). Time format (24hr. Eg. 1900)

### 3.4 Mark completion of task: done
#### Marks a task as completed.
#### Format: `done` *index_of_task*

### 3.5 Find a task: find
#### Finds and lists task(s) that contain the specified keyword.
#### Format: `find` *keyword*

### 3.7 List all tasks: list
#### Lists all tasks in schedule.
#### Format: `list`

### 3.8 Delete a task: delete
#### Deletes a task in schedule with specified index.
#### Format: `delete` *index_of_task*

### 3.9 Create a topic for Flashcards: topic
#### Creates a topic folder for flashcards with specified topic name.
#### Format: `topic` *topic_name*
 
### 3.10 Create a Flashcard: flashcard
#### Creates a flashcard (with question and answer) in the specified topic folder.
#### Format: `flashcard` *topic_name*/*question*/*answer*
###### Note: *topic_name*, *question* & *answer* are seperated by "/"

### 3.11 List all questions: list_qns
#### Lists all questions in specified topic folder.
#### Format: `list_qns` *topic_name*

### 3.12 List all answers: list_ans
#### Lists all answers in specified topic folder.
#### Format: `list_ans` *topic_name*

## 4. FAQ
#### Q. How do I transfer my schedule to another computer?
#### A. Simply install the app on the other computer. Then replace the text file it creates with the text file that contains your previous schedule.
#### Q. Does the app keep my topic folders & flashcards after the app is shut down?
#### A. No. The app is currently in it's beta version. More upgrades to come.
#### Q. Can I set reminders for tasks?
#### A. No. The app is currently in it's beta version. More upgrades to come.
