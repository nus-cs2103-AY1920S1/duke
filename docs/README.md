# User Guide
1. [Introduction](#1-introduction)
2. [Quick Start](#2-quick-start)
3. [Features](#3-features)  
   3.1. [Viewing list of commands](#31-viewing-list-of-commands) : `help`   
   3.2. [Adding a new task](#32-adding-a-new-task) : `todo`, `event`, `deadline`  
   3.3. [Listing all tasks](#33-listing-all-tasks) : `list`  
   3.4. [Marking a task as done](#34-marking-a-task-as-done) : `done`   
   3.5. [Deleting a task](#35-deleting-a-task) : `delete`    
   3.6. [Searching for a task by keyword(s)](#36-searching-a-task-by-keyword) : `find`  
   3.7. [Clearing the window](#37-clearing-the-window) : `clear`    
   3.8. [Exiting the program](#37-exiting-the-program) : `bye`
   
## 1. Introduction
Duke is a Personal Assistant Chatbot that aids users in keeping track of various tasks.

## 2. Setting Up
1. Ensure that you have Java `11` or above installed on your computer. 
2. Download the latest `duke-0.2.jar` [here.](https://github.com/bruceskellator/duke/releases/tag/A-Release)
3. Copy the file to a folder. This will be the home folder for Duke.
4. Create a folder named `data` in the home folder.  
Then, create a text file in `data` named `tasks.txt`.   
Your tasks will be stored and loaded from there.
5. Double-click on the `duke-0.2.jar` to start the app.  
The GUI should appear in a few seconds.

![Screenshot of Duke GUI](https://github.com/bruceskellator/duke/blob/master/docs/Ui.png?raw=true)

6. Once the app has started, key in a valid command and hit `enter`.
Alternatively, you can click the `send` button to execute it.
7. You are now good to go!   
The list of tasks is saved to the file every
time a command executes.   
Therefore, you can simply close the app without losing any changes.  
Alternatively, the `bye` command also closes the program.

## 3. Features

### 3.1 Viewing list of commands
Displays a comprehensive list of all valid commands as well as what they do.

Format: `help`

### 3.2 Adding a new task
Creates a new task and adds it to the current list.

A task can be one of three types: a `todo`, `deadline` or `event` task.

Format: 
* `todo [description]` 
* `deadline [description] /by [date-time]` 
* `event [description] /at [date-time]`

For a `todo` task, only a description is needed but for `deadline` and `event`
tasks, both a **description** is needed as well as a **date-time** value,   
and these values have to be demarcated by ` /at ` for event tasks and ` /by ` for deadline tasks.

**Examples of valid date-time values:**  
* `3 October 2019 2359`
* `03/10/2019 2359`
* `03102019 11:59PM`
* `03 10 2019 11:59AM`

Examples of valid commands: 

* `todo run 2.4km`  
Creates a `todo` task

* `deadline submit assignment /by 5 November 2019 0900`  
Creates an `event` task

* `event play basketball /at 05/11/2019 9:00AM`  
Creates a `deadline` task

### 3.3 Listing all tasks
Displays the current list of all tasks.

Format: `list`

### 3.4 Marking a task as done
Marks the task with the specified task-number as done.
The task number of a task is indicated by its position
in the list of tasks. (starting from 1)  
This number can be displayed by using the `list` command.

Format: `done [task-number]`

Example:
* `done 3`  
Marks the 3rd task in the list as done.

### 3.5 Deleting a task
Removes the task with the specified task-number from the current list.  
This command has similar syntax as the `done` command.

Format: `delete [task-number]`

Example:
* `delete 4`  
Deletes the 4th task in the list.

### 3.6 Searching for a task by keyword(s)
Finds and displays tasks whose description contains the given keyword(s)  
Multiple keywords may be specified, delimited by a **single** space.

**Note**: 
* The order of the keywords do **not** matter.
* A task description is a match only if it contains **all** the keywords.

Example:   
* `find boss guild event`

Finds task with description: `help guild register for the boss event`

Format: `find [keywords delimited by a single space]`

### 3.7 Clearing the window
Clears the window of all dialog-boxes and outputs a message.

Format: `clear`

### 3.8 Exiting the program
Displays the exit message and closes the program.

Format: `bye`
