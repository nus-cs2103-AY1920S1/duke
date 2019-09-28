# My Tasks - User Guide
## Introduction
My Tasks is a personal task manager that helps user keep track of their todo list, events and deadlines.

## Quick Start
1. Ensure you have Java 11 or above installed in your computer.
1. Download the latest mytasks.jar [here](https://github.com/michelleykw/duke/releases).
1. Copy the file to the folder you want to use as the home folder for My Tasks.
1. Double-click the file to start the app. The GUI should appear in a few seconds.
![My Tasks Page](https://github.com/michelleykw/duke/blob/master/src/main/resources/images/DaDuke.png)
1. Type the command in the command box and press Send to execute it.
1. Some example commands you can try: 
    1. `list`: lists all tasks
    1. `done 3`: marks the third task on the list as done
    1. `bye`: exits the app
1. Refer to Features for details of each command.

## Features 
Command Format
* Words in UPPER_CASE are the parameters to be supplied by the user<br />
    Example: in todo TASKNAME, TASKNAME is a parameter which can be used as todo tutorial.
    
### 1. Viewing help: **`help`**
Format: help

### 2. Adding task
#### 2.1. Adding todo: **`todo`**
Adds a todo to the list of tasks<br />
Format: `todo TASKNAME`<br />
Example: `todo tutorial`
  
#### 2.2. Adding deadline: **`deadline`**
Adds a deadline to the list of tasks<br />
Format: `deadline TASKNAME /by DATE`<br />
Example: `deadline tutorial /by 02 Dec 2019 15:00`
  
#### 2.3. Adding event: **`event`**
Adds an event to the list of tasks<br />
Format: `event TASKNAME /at DATE`<br />
Example: `event meeting /at 13 Oct 2019 09:00`
  
> Note: DATE is in the format "dd MMM yyyy hh:mm"
  
### 3. Viewing all tasks: **`list`**
Shows the list of all tasks in My Tasks<br />
Format: `list`
`
### 4. Marking task as done: **`done`**
Marks the task with task number as in the list as done<br />
Format: `done TASKNUMBER`<br />
Example: `done 4` (marks the task number 4 as done)

### 5. Find task **`find`**
Find a task by searching using a keyword (can be in upper or lower case)<br />
Format: `find KEYWORD`<br />
Example: `find tutorial` (shows any task with they keyword "tutorial")

### 6. Prioritise task **`priority`**
Prioritise the task with task number as in the list as high, medium or low<br />
Format: `priority TASKNUMBER LEVEL`<br />
Example: `priority 3 high` (Marks task number 3 as high priority level)

### 7. Clear all tasks **`clear`**
Deletes all the tasks from the list
Format: `clear`