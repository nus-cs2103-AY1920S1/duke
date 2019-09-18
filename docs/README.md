# User Guide

## Features 

### Help
Type help to receive in-application help tips at any time.

### Todo
Allows you to record a to-do event.

### Deadline
Allows you to set an task with a deadline.

### Event
Allows you to create an event with a date and time.

### Note
Allows you to store a note.

### List
Lists all the tasks and notes currently stored.

### Done
Marks a task as done.

### Delete
Deletes a task or note.

## Usage

### `list` - Lists all tasks
Lists all tasks currently stored.

Example use: 

`list`

Expected outcome:

`Here are the tasks in your list:`
`1. [T][✗] read book`

### `todo <description>` - Adds a new todo
Adds a new todo task with `description` as its description.

Example use: 

`todo read book`

Expected outcome:

`Got it. I've added this task:`
`[T][✗] read book`
`Now you have 1 task in the list.`

### `deadline <description> /by <dd/MM/yyyy HHmm> ` - Adds a new deadline.
Adds a new deadline task with `description` as its description, and the date/time as its deadline.

Example use: 

`deadline return book 30/09/2018 1800`

Expected outcome:

`Got it. I've added this task:`
`[D][✗] return book (by: 30/09/2019 1800)`
`Now you have 2 tasks in the list.`

            + "event <description> /at <dd/MM/yyyy HHmm> - Adds a new event.\n\n"
            + "note <content> - Adds a new note.\n\n"
            + "done <number> - marks the entry with the corresponding number done.\n\n"
            + "delete <number> - deletes the entry with the corresponding number.\n\n"
            + "help - displays this help menu.\n";


### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
