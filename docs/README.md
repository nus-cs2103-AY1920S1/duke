# User Guide

## Features 

### Adding a task
Adds a new task into duke. Types of tasks are:
* Todo (format: todo TASK_NAME)
* Deadline (format: deadline TASK_NAME /by DATE)
* Event (format: event TASK_NAME /by DATE)

Example of usage: 

`deadline CS2100 assignment /by 7/10/2019 2359`

Expected outcome:

`Got it. I've added this task:`\n
`[D][Not done] CS2100 assignment (by: 7/10/2019 2359)`\n
`Now you have 1 tasks in the list.`

### Deleting a task
Deletes a selected task in the list based on its index.

## Usage

`delete TASK_INDEX`

Example of usage: 

`delete 1`

Expected outcome:

`Noted. I've removed this task:`\n
` [D][Not done] CS2100 assignment (by: 7/10/2019 2359)`\n
`Now you have 0 tasks in the list.`

### List
Lists out all the tasks in the list

## Usage

`list`

Expected outcome:

`Here are the tasks in your list:`\n
`1. [D][Not done] CS2100 assignment (by: 7/10/2019 2359)`\n
`2. [T][Done] Read book`


### Mark as done
Marks a specified task as done based on its index

## Usage

`done TASK_INDEX`

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`\n
`[D][Done] CS2100 assignment (by: 7/10/2019 2359)`

### Find task
Lists out all the task that completely or partially match a given keyword.

## Usage

`find KEYWORD`

Example of usage: 

`find CS`

Expected outcome:

`Here are the matching tasks in your list:`\n
`1. [D][Done] CS2100 assignment (by: 7/10/2019 2359)`

### Undo task
Undos the last modification carried out on the list.

## Usage

`undo`


Expected outcome:

`The last action was undone :)`\n
`Here are the tasks in your list:`\n
`1. [D][Not done] CS2100 assignment (by: 7/10/2019 2359)`
