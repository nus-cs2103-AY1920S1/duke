# User Guide

## Features 

### Add and View Tasks

Duke allows users to create and manage three types of tasks -- todo, deadline and event. New tasks are automatically saved into a list and user can view the list of tasks.

### Delete tasks

Users can delete any task from the task list.

### Done tasks

Users can mark a task as done when completed.

### Search for tasks based on Keywords

Users can search for a task in the task list with a keyword.

### Sort tasks based on categories

Users can sort their task list by time, type, description or status.

### Get Help when stuck

Users can get help by opening a Help window.

## Usage

### `todo [description]`

Add a todo task (unspecified task) to the task list.

Example of usage: 

`todo playing`

Expected outcome:

`Got it. I've added this task:`<br/>
`[T][✘] playing`<br/>
`Now you have 1 task(s) in the list.`

### `deadline [description] /by [dd/MM/yyyy HHmm]`

Add a deadline task (task with a time deadline) to the task list.

Example of usage: 

`deadline milestone submission /by 1/09/2019 2359`

Expected outcome:

`Got it. I've added this task:`<br/>
`[D][✘] milestone submission (by 1st September 2019, 11:59 pm)`<br/>
`Now you have 2 task(s) in the list.`

### `event [description] /at [dd/MM/yyyy HHmm]`

Add a event task (task with a time-table) to the task list.

Example of usage: 

`event seminar /at 9/09/2019 1600`

Expected outcome:

`Got it. I've added this task:`<br/>
`[E][✘] seminar (at 9th September 2019, 04:00 pm)`<br/>
`Now you have 3 task(s) in the list.`

### `delete [index]`

Deletes a task from the task list.

Example of usage: 

`delete 2`

Expected outcome:

`Noted. I've removed this task:`<br/>
`[D][✘] milestone submission (by 1st September 2019, 11:59 pm)`<br/>
`Now you have 2 task(s) in the list`

### `done [index]`

Mark a task as done.

Example of usage: 

`done 2`

Expected outcome:

`Nice! I've marked this task as done:`<br/>
`[E][✓] seminar (at 9th September 2019, 04:00 pm)`

### `list`

List out the tasks in your list.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:`<br/>
`1. [T][✘] playing`<br/>
`2. [E][✓] seminar (at 9th September 2019, 04:00 pm)`

### `find [keyword]`

Find a task containing a keyword in its description.

Example of usage: 

`find semi`

Expected outcome:

`Here are the matching tasks in your list:`<br/>
`2. [E][✓] seminar (at 9th September 2019, 04:00 pm)`

### `help`

Gives a list of all commands the user can use with their format.

Example of usage: 

`help`

Expected outcome:

### `bye`

Exits the application

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`

