# User Guide

## 1. Introduction
Duke is a chat bot for you to tasks your schedule with an 
interactive command line interface (CLI). By typing commands you can 
add, set and save your task list in a simple and fast manner. Please 
try out Duke if you are interested!

## 2. Quickstart
1. Ensure you have Java 11 or above installed in your Computer.
   
2. Download the latest Duke.jar [here](https://github.com/brebeek/duke).
   
3. Copy the file to the folder you want to use as the home folder for Duke.
   
4. Double-click the file to start the app. The GUI should appear in a few seconds.

5. Type the command in the command box and press <kbd>Enter</kbd> to execute it.\
   e.g. typing `list` and pressing <kbd>Enter</kbd> will list all tasks in the list.

## 3. Features 

### 3.1 Adding to-do task: 
Adds an undone to-do task to task list.\
Format: `todo DESCRIPTION` 
* e.g. `todo Have dinner`

### 3.2 Adding event: 
Adds an undone event with a time stamp to task list.\
Format: `event DESCRIPTION /at YYYY/MM/DD HH:mm` 
* e.g. `event Orientation /at 2019/01/01 14:00`

### 3.3 Adding deadline task: 
Adds an undone deadline task with a due time to task list.\
Format: `deadline DESCRIPTION /by YYYY/MM/DD HH:mm` 
* e.g. `deadline Video project /by 2019/07/31 21:59`

### 3.4 Listing all tasks: 
Lists all the tasks currently in task list.\
Format: `list` 

### 3.5 Marking a task as done: 
Marks a selected task in list as done.\
Format: `done INDEX` 
* e.g. `done 1`

### 3.6 Deleting a task: 
Deletes a task from list.\
Format: `delete INDEX` 
* e.g. `delete 1`

###3.7 Finding tasks by content matching: 
Searches for all tasks with given keyword in task list.\
Format: `find KEYWORD` 
* e.g. `find book`

### 3.8 Clearing the task list: 
Clears the task list.\
Format: `clear` 

##4. FAQ

Q: How do I transfer my data to another Computer?\
A: Install the app in the other computer and overwrite the data 
file it creates at `\duke\data\duke.txt` 
with the file that contains the data of your previous 
Duke folder.

## 5. Acknowledgements
Some contents are implemented and modified in accordance to *CS2103T* 
course materials and tutorials.

