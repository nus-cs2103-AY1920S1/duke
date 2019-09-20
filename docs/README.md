# User Guide
1. [Introduction](#1-introduction)
2. [Quick Start](#2-quick-start)
3. [Features](#3-features)  
   3.1. [Viewing help](#31-viewing-help) : `help`   
   3.2. [Adding a new task](#32-adding-a-new-task) : `todo`, `event`, `deadline`  
   3.3. [Listing all tasks](#33-listing-all-tasks) : `list`  
   3.4. [Marking a task as done](#34-marking-a-task-as-done) : `done`   
   3.5. [Deleting a task](#35-deleting-a-task) : `delete`    
   3.6. [Searching for a task by keyword](#35-deleting-a-task) : `find`  
   3.7. [Archive commands](#36-searching-for-a-task-by-keyword)
      * 3.7.1 [Creating an archive](#371-creating-an-archive) : `archive create`
      * 3.7.2 [Adding a task to an archive](#372-adding-a-task-to-an-archive) : `archive add`
      * 3.7.3 [Listing archive names](#373-listing-archive-names) : `archive list`
      * 3.7.4 [Listing tasks in a particular archive](#374-listing-tasks-in-a-particular-archive) : `archive view`
      * 3.7.5 [Listing tasks in all archives](#375-listing-tasks-in-all-archives) : `archive viewall`
      * 3.7.6 [Deleting a task in an archive](#376-deleting-a-task-in-an-archive) : `archive delete`
      * 3.7.7 [Deleting an entire archive](#377-deleting-an-entire-archive) : `archive deleteall`
      * 3.7.8 [Unarchive a task](#378-unarchive-a-task) : `archive revert`   
      
   3.8. [Exiting the program](#38-exiting-the-program) : `bye`  
   
## 1. Introduction
Duke is a Personal Assistant Chatbot that helps users to keep track of various tasks.  

## 2. Quick Start
1. Ensure you have Java `11` or above installed on your computer.
2. Download the latest `duke-0.2.jar` [here.](https://github.com/Davidcwh/duke/releases/tag/A-Release)
3. Copy the file to the folder you want to use as the home folder for Duke.   
4. Double-click the file to start the app. The GUI should appear in a few seconds.  
![Image of Duke GUI](https://github.com/Davidcwh/duke/blob/master/docs/Ui.png.PNG?raw=true)

5. Type the command in the command box and press `Enter` or click the `send` button to execute it.  
e.g. typing `help` and pressing `Enter` will display the list of commands available.
7. Some example commands you can try:
   * `list` : lists all tasks 
   * `event david's birthday party /at 13/02/2019 1800` : adds an `event` task named `david's birthday party` to list of tasks.
   * `delete 3` : deletes the 3rd task shown in the current list of tasks.
   * `bye` : exits the app.
8. Refer to [Section 3, "Features"](#3-features) for details of each command.  

## 3. Features 

### 3.1 Viewing help 
Displays a full list of Duke commands and descriptions of what they do.  

Format: `help`
### 3.2 Adding a new task 
Creates a new task and adds it to the current list of tasks.  

There are 3 types of tasks: `todo`, `event`, `deadline`.  
`event` and `deadline` tasks require a 24 hour format date and time attached while `todo` tasks do not.

Format:  
`todo [description]`  
`event [description] /at [dd/mm/yyy hh:mm]`  
`deadline [description] /by [dd/mm/yyy hh:mm]`

Example(s): 

* `todo buy david's birthday present`  
Creates a `todo` task

* `event david's birthday party /at 13/02/2019 1800`  
Creates an `event` task

* `deadline essay assignment /by 15/09/2019 2359`  
Creates a `deadline` task

### 3.3 Listing all tasks 
Displays the current list of tasks.  

Format: `list`

### 3.4 Marking a task as done
Marks a specified task in the list of tasks as done.  
Task to be marked done is indicated by its index number in the current list of tasks. 

Format: `done [task's list index number]`

Example(s):  
* `done 2`  
Marks the 2nd task in the task list as done.

### 3.5 Deleting a task
Deletes a specified task in the list of tasks.  
Task to be deleted is indicated by its index number in the current list of tasks.   

Format: `delete [task's list index number]` 

Example(s):  
* `delete 3`  
Deletes the 3rd task in the task list.

### 3.6 Searching for a task by keyword
Finds and displays tasks whose description contains the given keyword.  

Format: `find [keyword]`  

Example(s):  
* `find book`  
Displays any task with the keyword "book" in its description.

### 3.7 Archive commands  
Tasks are able to be removed from the current list of tasks and stored into an archive.  
The archive extension has various features that can be used with the following commands.

All archive commands are preceded with the `archive` command word.
#### 3.7.1 Creating an archive  
Creates a new empty archive.  

Format: `archive create [archive name]`

Example(s):  
* `archive create Due assignments`  
A new empty archive named `Due assignments` is created.

#### 3.7.2 Adding a task to an archive
Removes the specified task from the current list of tasks and adds it to the specified archive.  
The task to be archived is indicated by its index number in the current list of tasks and 
the archive to add the task to is indicated by the archive's name.

Format: `archive add [task's list index number] [archive name]`

Example(s):
* `archive add 4 Leisure`  
Archives the 4th task in the current list of tasks into the `Leisure` archive.

#### 3.7.3 Listing archive names
Displays a list of existing archives' names.

Format: `archive list`

#### 3.7.4 Listing tasks in a particular archive
Displays the list of tasks in a specified archive.

Format: `archive view [archive name]`

Example(s):
* `archive view 2018 events`  
Displays the list of tasks in the `2018 events` archive.

#### 3.7.5 Listing tasks in all archives
Displays all archived tasks at once, grouped by archive.

Format: `archive viewall`
 
#### 3.7.6 Deleting a task in an archive
Deletes the specified task in the specified archive.  

the archive to delete the task from is indicated by the archive's name.
The task to be deleted is indicated by its index number in the archive's list of tasks.

Format: `archive delete [task's archive list index number] [archive name]`

Example(s):  
* `archive delete 5 Past assignments`  
Deletes the 5th task in the `Past assignment` archive.

#### 3.7.7 Deleting an entire archive
Deletes the specified archive, including all the tasks in it.

Format: `archive deleteall [archive name]`

Example(s):  
* `archive deleteall March deadlines`  
Deletes the `March deadlines` archive, including all the tasks in it.

#### 3.7.8 Unarchive a task
Removes the specified task from the specified archive and add it to the current list of tasks.

Format: `archive revert [task's archive list index number] [archive name]`

Example(s):  
* `archive revert 7 Leisure`  
Removes the 7th task in the `Leisure` archive and adds it back ot the current list of tasks.

### 3.8 Exiting the program
Displays the exit message and closes the program.

Format: `bye`
