# User Guide

## Features 

### Add and view tasks

Duke allows users to create three types of tasks -- todo, deadline and event. New tasks are automatically saved into a list and user can view the list of tasks.

### Delete tasks

Users can delete a task from the task list.

### Done tasks

Users can mark a task as done.

### Search for tasks base on keywords

Users can search for a task in the task list with a keyword.

### Sort tasks based on categories

Users can sort their task list by time, type, description or status.

## Usage

### `todo [description]`

Add a todo task to the task list.

Example of usage: 

`todo reading`

Expected outcome:

`Got it. I've added this task:`<br/>
`[T][✘] reading`<br/>
`Now you have 1 task(s) in the list.`

### `deadline [description] /by [dd/MM/yyyy HHmm]`

Add a deadline task to the task list.

Example of usage: 

`deadline essay submission /by 10/09/2019 2359`

Expected outcome:

`Got it. I've added this task:`<br/>
`[D][✘] essay submission (by 10th September 2019, 11:59 pm)`<br/>
`Now you have 2 task(s) in the list.`

### `event [description] /at [dd/MM/yyyy HHmm]`

Add a event task to the task list.

Example of usage: 

`event group meeting /at 11/09/2019 1600`

Expected outcome:

`Got it. I've added this task:`<br/>
`[E][✘] essay submission (at 11th September 2019, 04:00 pm)`<br/>
`Now you have 3 task(s) in the list.`

### `delete [index]`

Deletes a task from the task list.

Example of usage: 

`delete 2`

Expected outcome:

`Noted. I've removed this task:`<br/>
`[D][✘] essay submission (by 10th September 2019, 11:59 pm)`<br/>
`Now you have 2 task(s) in the list`

### `done [index]`

Mark a task as done.

Example of usage: 

`done 2`

Expected outcome:

`Nice! I've marked this task as done:`<br/>
`[E][✓] group meeting (at 11th September 2019, 04:00 pm)`

### `list`

List out the tasks in your list.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:`<br/>
`1. [T][✘] reading`<br/>
`2. [E][✓] group meeting (at 11th September 2019, 04:00 pm)`

### `find [keyword]`

Find a task containing a keyword in its description.

Example of usage: 

`find meeting`

Expected outcome:

`Here are the matching tasks in your list:`<br/>
`2. [E][✓] group meeting (at 11th September 2019, 04:00 pm)`

### `bye`

Exits the application

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`

