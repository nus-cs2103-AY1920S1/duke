# User Guide
## 1. Introduction
This is the guide to a product called Duke, a personal assistant chatbot which helps you manage and record your 
daily tasks!

## 2. Features 
> Notes
> * Words in UPPER_CASE are details that need to be given by the user.

### 2.1 Add a task
---
Three types of tasks that can be added into Duke's tasks list, namely *ToDo, Deadline, and Event*.

Command: `[TYPE OF TASK] [DESCRIPTION]`

The **date and time** given to a **Deadline** or **Event** needs to follow the following format:

Format: `DD/MM/YYYY HHMM`, where time is in 24-Hour format.

Example: `15/09/2019 1300`
    
#### 2.1.1 Add ToDo
Command: `todo [TITLE]`

Example: `todo buy grocery`

#### 2.1.2 Add Deadline
Command: `deadline [TITLE] /by [DATE AND TIME]`

Example: `deadline math quiz /by 18/09/2019 1400`

#### 2.1.3 Add Event
Command: `event [TITLE] /at [DATE AND TIME]`

Example: `event workout /at 20/09/2019 1700`


### 2.2 Tentative Schedule 
---
An **event** can be given **tentative dates** which can be confirmed later.

Command: `event title /at [DATE AND TIME 1], [DATE AND TIME 2], ...`

Example: `event running /at 18/09/2019 1700, 20/09/2019 0800, 21/09/2019 0900`

#### 2.2.1 Confirm the date of an event 
Confirm the date of an event, **provided** that it was given tentative dates.

Command: `event setDate [INDEX OF EVENT] [INDEX OF DATE]`

Example: 
* `event running /at 18/09/2019 1700, 20/09/2019 0800, 21/09/2019 0900`

* `event setDate 2 3`

INDEX OF EVENT refers to the index of the event in the tasks list, whereas INDEX OF DATE refers to the index of the date
in the tentative dates of the event. In the example above, the event is the 2nd task in the tasks list, and the 
confirmed date it's the 3rd date in the tentative dates of the event. Thus, the confirmed date of the event is
`21/09/2019 0900`.


### 2.3 Mark a task as done
---
Mark a task in the tasks list as done using the given INDEX of the task.

Command: `done [INDEX]`

Example: `done 1`

Condition: 
Index **must be a positive integer** and within the range of 1~N (inclusive), where N is the current total 
number of tasks in the tasks list. 


### 2.4 Delete a task 
---
Delete a task from the tasks list using the given INDEX of the task.

Command: `delete [INDEX]`

Example: `delete 3`

Condition: 
Index **must be a positive integer** and within the range of 1~N (inclusive), where N is the current total 
number of tasks in the tasks list. 


### 2.5 List all tasks 
---
List all the tasks in the tasks list.

Command: `list`


### 2.6 Find tasks 
---
Find tasks that contain the KEYWORD given in their description and list them out.

Command: `find [KEYWORD]`

Example: `find quiz`


### 2.7 Exit 
---
Exit the program.

Command: `bye`
