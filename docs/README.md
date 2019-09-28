# User Guide

[1. Introduction](README.md#Introduction)
[2. Quick Start](README.md#Quick-Start)  
[3. Features](README.md#Features)    
[4. FAQ](README.md#FAQ)   
[5. Command Summary](README.md#Command-Summary) 

## 1. Introduction 

Duke is for those who prefer to use a desktop app for managing tasks. 
More importantly, DukeBot is optimized for those who prefer to work with a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). Enjoy!


## 2. Quick Start 
1. Ensure you have Java 11 or above installed in your Computer.  

2. Download the latest duke-0.2.0.jar [here](https://github.com/qweiping31415/duke/releases)

![Ui.png](Ui.png)

## 3. Features 

### 3.1. Viewing help
Shows a simple user guide to command formats.  
Format: `help`


### 3.2. Adding a task: `todo`, `deadline`, `event`
Adds a task to the list


##### 3.2.1. Adding a ToDo Task: `todo` 
Adds a ToDo task to the list
Format: `todo TASK_DESCRIPTION` 

Examples:
* `todo homework`
* `todo read book`

##### 3.2.2. Adding a Deadline Task: `deadline` 
Adds a Deadline task to the list
Format: `deadline TASK_DESCRIPTION /by DUE_DATETIME`

Examples:
* `deadline do homework /by 3pm`
* `deadline do homework /by Monday`

> ###### **Formatted date and time**:
> If `DUE_DATETIME` is entered in the `DD/MM/YYYY HHMM` format, it will automatically be converted to a more readable format.
>
>Example:
>`deadline do homework /by 12/12/1212 1212`

##### 3.2.3. Adding a Event Task: `event` 
Adds an Event task to Duke
Format: `event TASK_DESCRIPTION /by DUE_DATETIME`

Example:
* `event consultation /at 3pm`
* `event consultation /at Monday`

> ###### **Formatted date and time**:
> If `DUE_DATETIME` is entered in the `DD/MM/YYYY HHMM` format, it will automatically be converted to a more readable format.
>
>Example:
>`event consultation /at 12/12/1212 1212`


### 3.3. Finding tasks by keyword: `find`
Finds tasks whose descriptions contain an exact match of the given keyword.

Format: `find KEYWORD` 
Example:
* `find homework`


### 3.4. Marking a task as done: `done`  
Marks the specified task in the list as done.  

Format: `done INDEX`
* Marks the tasks at the specified INDEX.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …​


### 3.5. Deleting a task: `delete`
Deletes the specified task from the list.  
Format: `delete INDEX`
* Deletes the tasks at the specified INDEX.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …​


### 3.6. Listing all tasks : `list`
Shows a list of all tasks in the list.  
Format: `list`


### 3.7. Exiting the program: `bye`  
Exits the program.  
Format: `bye`


## 4. FAQ
* None


## 5. Command Summary 
* Add : `CODE_NAME TASK_DESCRIPTION [ADDITIONAL_KEYWORD] [DUE_DATETIME]`  
e.g. `todo homework`
e.g. `deadline do homework /by 3pm`
e.g. `event consultation /at 12/12/1212 1212`

* Find : `find KEYWORD`   
e.g. `find homework`

* Delete : `delete INDEX`
e.g. `delete 3`

* Done : `done INDEX`
e.g. `done 3`

* List : `list`

* Help : `help`

* Exit : `bye`
