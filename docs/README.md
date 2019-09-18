#Duke - User Guide
By: Xu Tunan Since: 18/09/2019
## 1. Introduction
Duke is for users to manage their upcoming tasks. You can store the tasks that 
you need to do in Duke and check any time you like. Try and enjoy!

## 2. Features 

* Words in UPPER_CASE are the parameters to be supplied by the user 

  e.g. in todo DESCRIPTION, DESCRIPTION is a parameter which can be 
  used as add dinner.
* DD/MM/YYYY HH:mm is the format of time
  
   e.g. 31/12/2019 23:59


### 2.1 Adding a new task without time restriction: `todo`

Adds the task you want to do that without a time limit.

Format: `todo DESCRIPTION`

Example of usage: 
`todo Buy Drink`
<br/><br/><br/>
### 2.2 Adding a new task with deadlines: `deadline`
Adds the task with a deadline.

Format: `deadline DESCRIPTION /by DD/MM/YYYY HH:mm`

Example of usage: `deadline CS2100 Assignment /by 20/09/2019 23:59`
<br/><br/><br/>
### 2.3 Adding a new event: `event`
Adds the event in a specific time.

Format: `event DESCRIPTION /at DD/MM/YYYY HH:mm`

Example of usage: `event Dinner /at 21/09/2019 18:00`
<br/><br/><br/>
### 2.4 Listing all tasks: `list`
Show a list of all tasks in Duke.

Format: `list`

Expected outcome: `Here are the tasks in your list: ...`
<br/><br/><br/>
### 2.5 Finishing a task: `done`
Marks a task as done.

Format: `done INDEX`

Example of usage: `done 1`
<br/><br/><br/>
### 2.6 Deleting a task: `delete`
Deletes a task in Duke list.

Format: `delete INDEX`

Example of usage: `delete 1`
<br/><br/><br/>
### 2.7 Finding tasks: `find`
Finds the tasks with specific key word.

Format: `find KEY_WORD`

Example of usage: `find Dinner`
<br/><br/><br/>
### 2.8 Updating task time: `update`
Updates the time of a task with deadline or a event.

Format: `update INDEX DD/MM/YYYY HH:mm`

Example of usage: `update Dinner 21/09/2019 17:30`
<br/><br/><br/>
### 2.9 Exiting Duke: `bye`
Format: `bye`

Expected outcome: Exit Duke
<br/><br/><br/>

