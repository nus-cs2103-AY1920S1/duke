# User Guide

## Features 

### Addition
Add a task to todo list.
### Deletion
Deletes one or more task from todo list.
### List
Lists all tasks in todo list.
### Marking
Marks one or more tasks as done.
### Search
Searches for tasks containing input keyword.
### Exit
Exits from program.

## Usage

### `todo` - Add a todo task

Adds a todo task to todo list and shows whether the addition is successful.

Example of usage: 

`todo read book`

Expected outcome:

`Got it. I've added this task: `

`[T][×] read book`
       
`Now you have 1 task in the list.`

### `event` - Add an event task

Adds an event task to todo list and shows whether the addition is successful.

Example of usage: 

`event project meeting /at 6/8/2019 2015`

Expected outcome:

`Got it. I've added this task:`

`[E][×] project meeting (at: Tue Aug 06 20:15:00 SGT 2019)`
       
`Now you have 2 tasks in the list.`

### `deadline` - Add a deadline task

Adds a deadline task to todo list and shows whether the addition is successful.

Example of usage: 

`deadline return book /by 6/6/2019 0000`

Expected outcome:

`Got it. I've added this task:`

`[D][×] return book (by: Thu Jun 06 00:00:00 SGT 2019)`
       
`Now you have 3 tasks in the list.`

### `delete` - delete a task

Deletes one or more tasks by specifying their numbers and shows deleted tasks.

Example of usage: 

`delete 1 2`

Expected outcome:

`Noted. I've removed these tasks:`

`[T][×] read book`

`[E][×] project meeting (at: Tue Aug 06 20:15:00 SGT 2019)`

`Now you have 1 task in the list.`

Example of usage: 

`delete 1`

Expected outcome:

`Noted. I've removed these tasks:`

`[T][×] read book`

`Now you have 2 tasks in the list.`

### `list` - list all tasks

Shows all the task in todo list in the order of addition time.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:`

`1.[T][×] read book`

`2.[E][×] project meeting (at: Tue Aug 06 20:15:00 SGT 2019)`

### `done` - mark as done

Marks one or more tasks as done by specifying their numbers and shows whether the action is successful.

Example of usage: 

`done 1 2`

Expected outcome:

`Nice! I've marked these tasks as done:`

`[T][√] read book`

`[E][√] project meeting (at: Tue Aug 06 20:15:00 SGT 2019)`

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked these tasks as done:`

`[T][√] read book`

### `find` - search a task

Searches for all tasks with given keyword and shows the matching tasks.

Example of usage: 

`find book`

Expected outcome:

`Here are the matching tasks in your list:`

`1.[T][√] read book`

### `bye` - Exit

Exits program and closes the window.

Example of usage: 

`bye`

Expected outcome:

nil.
