# User Guide

## 1. Introduction
Duke is a chat bot for you to manage your tasks with an 
interactive command line interface (CLI). By typing commands you can 
add, set and save your task list in a simple and fast manner. Please 
try out Duke if you are interested!

## 2. Quickstart
1. Ensure you have Java 11 or above installed in your Computer.
   
2. Download the latest Duke.jar [here](https://github.com/brebeek/duke/releases/download/v1.0.0/duke-1.0.0.jar).
   
3. Copy the file to the folder you want to use as the home folder for Duke. Please make sure that there is no prior
 file  named `duke.txt` in the working directory or in `duke/data` .
   
4. Double-click the file to start the app. The GUI should appear in a few seconds. If double-clicking does not 
work, please try `java -jar duke-1.0.0.jar` in terminal under the home folder of the jar.

5. Type the command in the command box and press <kbd>Enter</kbd> to execute it.<br/>
   e.g. typing `list` and pressing <kbd>Enter</kbd> will list all tasks in the list.

## 3. Features 
All commands are *case insensitive*.
### 3.1 `todo` - Adding to-do task: 
Adds an undone to-do task to task list.<br/>
Format: `todo DESCRIPTION` 
* e.g. `todo Have dinner`

### 3.2 `event` - Adding event: 
Adds an undone event with a time stamp to task list.<br/>
Format: `event DESCRIPTION /at YYYY/MM/DD HH:mm` 
* e.g. `event Orientation /at 2019/01/01 14:00`

### 3.3 `deadline` - Adding deadline task: 
Adds an undone deadline task with a due time to task list.<br/>
Format: `deadline DESCRIPTION /by YYYY/MM/DD HH:mm` 
* e.g. `deadline Video project /by 2019/07/31 21:59`

### 3.4 `list` - Listing all tasks: 
Lists all the tasks currently in task list.<br/>
Format: `list` 

### 3.5 `done` - Marking a task as done: 
Marks a selected task in list as done.<br/>
Format: `done INDEX` 
* e.g. `done 1`

### 3.6 `delete` - Deleting a task: 
Deletes a task from list.<br/>
Format: `delete INDEX` 
* e.g. `delete 1`

### 3.7 `find` - Finding tasks by content matching: 
Searches for all tasks including given keyword in task list. The `find` feature
does not support searching for separated keywords at the time.<br/>
Format: `find KEYWORD` 
* e.g. `find Orientation` `find 2019/07/31` 
<br/>`find Video project 2019/07/31`

### 3.8 `clear` - Clearing the task list: 
Clears the task list.<br/>
Format: `clear` 

### 3.9 `bye` - Exiting the chat bot:
Exits from Duke GUI.<br/>
Format: `bye`

## 4. FAQ

Q: How do I transfer my data to another Computer?<br/>
A: Install the app in the other computer and overwrite the data 
file it creates with the file that contains the data of your previous 
Duke folder.

## 5. Acknowledgements
Some contents are implemented and modified in accordance to 
*[NUS CS2103](https://github.com/nus-cs2103-AY1920S1/duke)* 
course materials.

