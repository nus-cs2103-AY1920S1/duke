# User Guide
## 1. Introduction
Duke is a java application that allows the user to keep track with all their daily tasks by interacting with 
a virtual chatbot.

## 2. Features 
> Note: Commands are all in small-case letters.
### 2.1 Add a task
-
There are 3 types of tasks that can be added into Duke's tasks list, namely *To Dos, Deadlines, and Events*.

Command: `[TYPE OF TASK] [DESCRIPTION]`

The **date and time** given to a **Deadline** or **Event** needs to follow the following format:

Format: `DD/MM/YYYY HHMM`, where time is in 24 hours.

Example: `19/09/2019 1400`
    
#### 2.1.1 Add ToDo
Command: `todo [Task Details]`

Example: `todo destroy the avengers`

#### 2.1.2 Add Deadline
Command: `deadline [Task Details] /by [DD/MM/YYYY HHMM]`

Example: `deadline submit CS2103 assignment /by 19/09/2019 1400`

#### 2.1.3 Add Event
Command: `event [Task Details] /at [DD/MM/YYYY HHMM]`

Example: `event party /at 19/09/2019 1400`

### 2.2 List all tasks 
-
List all the tasks in the tasks list.

Command: `list`


### 2.3 Mark a task as done
-
Mark a task in the tasks list as "Done" using the given index of the task.
> You can check the index of the task by running a `list` command.

Command: `done [INDEX]`

Example: `done 1`

Condition: 
Index **must be a positive integer** and must be within the range of the task list.


### 2.4 Delete a task 
-
Delete a task from the tasks list using the given INDEX of the task.
> You can check the index of the task by running a `list` command.

Command: `delete [INDEX]`

Example: `delete 1`

Condition: 
Index **must be a positive integer** and must be within the range of the task list.


### 2.5 Find tasks 
-
Find tasks that contain the KEYWORD given in their description and list them out.

Command: `find [KEYWORD]`

Example: `find assignment`

### 2.6 Find free time
-
Find the next available free time to schedule an event.
>Note: Only existing *Events* in the task list will be taken into consideration to avoid conflict. 
>(ToDOs and Deadlines will be ignored)

The user is able to specify the duration of the event he wants to find a time slot for.
>Each event in the task list is assumed to be 4 hours long. 
>
>E.g. Current Date and Time: 19/09/2019 14:00
>
>Date and Time of next event in list: 19/09/2019 14:00  
>
>`when 3` returns 19/09/2019 18:00

Command: `when [DURATION OF EVENT]`

Example: `when 3` 

Finds the next available 3 hour time slot from now. If current Date and Time is 19/09/2019 14:00 and 
Date and Time of next event in list is 19/09/2019 14:00 then `when 3` returns 19/09/2019 18:00.


### 2.7 Exit 
-
Exit the program.

Command: `bye`
