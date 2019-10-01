# User Guide
[1. Introduction](#Introduction)  
[2. Quick Start](#Quick Start)    
[3. Features](#Features)  
[4. FAQ](#FAQ)   
[5. Command Summary](#Command Summary) << click here if you are reviewing the app :)

## 1. Introduction <a name="Introduction"></a>
The DukeBot is for those who prefer to use a desktop app for managing tasks. 
More importantly, DukeBot is optimized for those who prefer to work with a Command 
Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).  
Have fun!

## 2. Quick Start <a name="Quick Start"></a>
1. Ensure you have Java 11 or above installed in your Computer.  

1. Download the latest dukebot.jar [here](https://github.com/calvincxz/duke/releases)

## 3. Features <a name="Features"></a>

Command Format
* Words in UPPER_CASE are the parameters to be supplied by the user 
e.g. in add n/NAME, NAME is a parameter which can be used as add n/John Doe.
* Items in square brackets are optional e.g `deadline TASK_NAME  /by ddmmyyyy [hhmm]`
can be used as `deadline TASK_NAME  /by ddmmyyyy`


### 3.1 Viewing help
Input "help" to view a simple guide to command formats.  
Format: help

### 3.2. Adding a task: todo, deadline or event
* Adds a task to the DukeBot.
    * todo TASK_NAME 
    * event TASK_NAME /at ddmmyyyy hhmm hhmm  
    * deadline TASK_NAME /by ddmmyyyy [hhmm]
* e.g. `event party /at 01012011 0800 1900`
* e.g. `t read book`

### 3.3. Listing all tasks : list
Shows a list of all tasks in the list.  
Format: list

### 3.4. Finding a task by keyword: find
Finds tasks whose description on the list matches exactly that of the given keywords.  
Format: find KEYWORD 
* `find [D]  `  
`Returns all Deadline tasks`

### 3.5. Deleting a task: delete
Deletes the specified task from the list.  
Format: delete INDEX
* Deletes the tasks at the specified INDEX.
* The index refers to the index number shown in the displayed person list.
* The index must be a positive integer 1, 2, 3, …​

### 3.6. Marking a task as done: done  
Marks the specified task in the list as done.  
Format: done INDEX
* Marks the tasks at the specified INDEX.
* The index refers to the index number shown in the displayed person list.
* The index must be a positive integer 1, 2, 3, …​

### 3.7. Postponing a task: postpone  
*Todos cannot be postponed.

* postpone the specified task in the list.  
* Format: postpone INDEX DAYS HOURS MINUTES  

### 3.8. Writing notes for a task: note  
Appends the notes for a task at the end of the description with a #.  
Format: note NOTES

### 3.9. Exiting the program: bye  
Exits the program.  
Format: bye

## 4. FAQ <a name="FAQ"></a>
* None

## 5. Command Summary <a name="Command Summary"></a>
* Add : `TASK_TYPE TASK_NAME DATE [TIME]`  
e.g. `event party /at 01012011 0800 1900`   
e.g. `t read book`

* Delete : `delete INDEX`
e.g. `delete 3`

* Done : `done INDEX`
e.g. `done 3`

* Postpone : `postpone INDEX DAY HOUR MINUTES`  
e.g. `postpone 3 0 4 0`

* Note: `note NOTES`  
e.g. `note blahblahblah`

* Find : `find KEYWORD`   
e.g. `find D`
* List : `list`
* Help : `help`
* Exit : `bye`
