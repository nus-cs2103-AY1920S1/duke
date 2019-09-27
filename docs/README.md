# User Guide
Duke is a todo manager application that is primarily command-line-based. 

## Commands
* Todo
* Deadline
* Event
* Delete
* Done
* List
* Find 
* Undo

## Features 

### Adding a task
Adds a new task into Duke. Types of tasks are:
* Todo - format: todo [TASK_NAME]
* Deadline - format: deadline [TASK_NAME] /by [DATE]
* Event - format: event [TASK_NAME] /by [DATE]

[DATE] format is `[DAY]/[MONTH]/[YEAR] [HOUR][MIN]`

Example of usage: 

`deadline CS2100 assignment /by 18/09/2019 2359`

Expected outcome:

`Got it. I've added this task:`\
`[D][✘] CS2100 assignment (by: Wed Sep 18 23:59:00 SGT 2019)`\
`Now you have 1 tasks in the list.`

### Deleting a task
Deletes a task in the tasklist as specified by its item id.

### Usage

`delete [TASK_INDEX]`

Example of usage: 

`delete 1`

Expected outcome:

`Noted. I've removed this task:`\
` [D][✘] CS2100 assignment (by: Wed Sep 18 23:59:00 SGT 2019)`\
`Now you have 0 tasks in the list.`

### List
Lists all tasks in the tasklist.

### Usage

`list`

Expected outcome:

`Here are the tasks in your list:`\
`1.[D][✘] CS2100 assignment (by: Wed Sep 18 23:59:00 SGT 2019)`

### Mark as done
Marks a task in the tasklist as done as specified by its item id.

### Usage

`done [TASK_INDEX]`

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`\
`  [D][✓] CS2100 assignment (by: Wed Sep 18 23:59:00 SGT 2019)`

### Find task
Lists out all the task that completely or partially match a given keyword.

### Usage

`find [KEYWORD]`

Example of usage: 

`find CS`

Expected outcome:

`Here are the matching tasks in your list:`\
`  1.[D][✓] CS2100 assignment (by: Wed Sep 18 23:59:00 SGT 2019)`

### Sort tasks
Sorts tasks in the tasklist based on the specified field in the tasks.

### Usage

`sort [FIELD]`

[FIELD] can take values `type`, `description`, `date`. 

Example of usage:

`sort date`

Expected outcome:

`Here is the sorted list:`\
`     1.[E][✘] Kennedy's visit to Texas (at: Tue Nov 22 12:30:00 SGT 63)`\
`     2.[D][✓] CS2100 assignment (by: Wed Sep 18 23:59:00 SGT 2019)`

## Exit program
Exits the Duke application.

### Usage

`bye`

Example of usage:

`bye`

Expected outcome:

Program exits.

