# User Guide


## Introduction
Duke is a **Personal Assistant Chatbot** that helps a person organise a list of stuff to do; including deadlines or events. 
The name Duke was chosen in honor of Duke, the Java Mascot. **While Duke has a Graphical User Interface (GUI), its main form of input is Command Line Interface (CLI) based**, thus favoring people who can type fast.

 

## Pre-requisites
1. Ensure you have Java 11 or above installed in your Computer. You can do so at the [java website.](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
2. Download the latest Duke.jar [here.](https://github.com/TanZhanMing/duke/releases)

 

## Starting Off
1. Copy the file to the folder you want to use as the home folder for Duke.
2. Double-click the file to start the app. The GUI should appear in a few seconds.

![Image of Duke start screen](https://tanzhanming.github.io/duke/UGpic1.PNG)

3.Type the command in the command box and press `Enter` or click `Send` to execute it.\
4.Lastly, entering `bye` exits the app.

 

## Features 


### Feature 1: Adding a 'To-Do' Task
Creates a basic 'To-Do' task


#### **keywords**: `todo`  
Format: `todo` (insert task description here)


#### Example: `todo` finish JUnit testing

![Image of Duke todo screen](https://tanzhanming.github.io/duke/UGpic2.PNG)

 

### Feature 2: Adding a 'Deadline' Task
Creates a basic 'Deadline' task with a date and time value


#### **keywords**: `deadline` `/by`  
Format: `deadline` (insert task description here) `/by` (date and time)


#### Example: `deadline` finish Project `/by` 20/04/2019 10:00

:bulb: You can try different date-time formats such as 'dd/mm/yyyy HH:mm:ss' or 'dd-mmm-yyyy HHmm'

![Image of Duke deadline screen](https://tanzhanming.github.io/duke/UGpic3.PNG)

 

### Feature 3: Adding an 'Event' Task
Creates a basic 'Event' task with a date and time value


#### **keywords**: `event` `/at`  
Format: `event` (insert task description here) `/at` (date and time)


#### Example: `event` game event `/at` 23-DEC-2020 0230

![Image of Duke event screen](https://tanzhanming.github.io/duke/UGpic4.PNG)

 

### Feature 4: Showing a 'List' of All Tasks
Displays a list of all tasks that have been previously inputted.


#### **keywords**: `list`  
Format: `list`


#### Example: `list`

Let us say for example you followed the previous 3 examples and input all 3 commands at once. You should get something like this. 

![Image of Duke event screen](https://tanzhanming.github.io/duke/UGpic5.PNG)

 

### Feature 5: Marking a Task as 'Done'
Marks a selected task as completed.


#### **keywords**: `done`  
Format: `done` (insert task number here)


#### Example: `done` 2

:bulb: It is recommended that you use the `list` command beforehand to double check the task number of the task you wish to delete.

![Image of Duke done screen](https://tanzhanming.github.io/duke/UGpic6.PNG)

 

### Feature 6: 'Delete'-ing a Task
Removes a task from the task list.


#### **keywords**: `delete`  
Format: `delete` (insert task number here)


#### Example: `delete` 1

:bulb: Do note that when you delete a task, all the numbers after said task get shifted forward by 1. Do refresh the task list with the `list` command often to ensure you do not accidentally delete the wrong task. :sweat:

![Image of Duke delete screen](https://tanzhanming.github.io/duke/UGpic7.PNG) ![Image of Duke delete screen2](https://tanzhanming.github.io/duke/UGpic8.PNG)  

 

### Feature 7: 'Find'-ing a Task
Finds a task from the task list that contains your expression.


#### **keywords**: `find`  
Format: `find` (insert task description here)


#### Example: `find` finish

:bulb: The task description need not be a full word; even typing a partial word or just a few letters as the task description would work!

![Image of Duke find screen](https://tanzhanming.github.io/duke/UGpic9.PNG)

 

### Feature 8: Checking for Duplicate Entries
The application will automatically check for entries where the task descriptions are exactly the same and warn you about them.


#### **keywords**: NIL 
Format: Automatically enabled


#### Example: adding a duplicate 'game event' task.

![Image of Duke duplicate screen](https://tanzhanming.github.io/duke/UGpic10.PNG)

 

### Feature 9: Closing the application
The application will terminate and all data will be saved. (See feature 10)


#### **keywords**: `bye` 
Format: `bye`


#### Example: You can send the `bye` message or just click the 'x' button on the top right hand corner of the application to close it.

 

### Feature 10: Auto-save
The application will automatically save the data whenever changes are made.


#### **keywords**: NIL 
Format: Automatically enabled


#### Example: You can open up the Duke application again after closing it to find all your data safe and sound. :smile:

![Image of Duke autosave screen](https://tanzhanming.github.io/duke/UGpic11.PNG)

 

## FAQ

**Q:** How do I transfer my data to another Computer?\
**A:** Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke folder.

 

## Command Summary

Command | Syntax  | Example
------- | ------- | -------
`todo` | `todo` (insert task description here) | `todo` finish JUnit testing
`deadline` | `deadline` (insert task description here) `/by` (date and time) | `deadline` finish Project `/by` 20/04/2019 10:00
`event` | `event` (insert task description here) `/at` (date and time) | `event` game event `/at` 23-DEC-2020 0230
`list` | `list` |
`done` | `done` (insert task number here) | `done` 2
`delete` | `delete` (insert task number here) | `delete` 1
`find` | `find` (insert task description here) | `find` finish
`bye` | `bye` | 