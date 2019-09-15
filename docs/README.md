# User Guide

## Features 

### Add tasks to a to-do list.
Allows you to track tasks. 

### Add deadlines to a list.
Remind you of your upcoming deadlines. 

### Add events to a list.
Remind you of your upcoming events. 

### (New) Record notes. 
Allows you to scribble some important notes for future references.

## Usage


### `todo` - Add a new Todo

Add a new todo in your to-do list.

Example of usage: 

`todo buy bread`

Expected outcome:

Task `buy bread` added to to-do list.


### `event` - Add a new Event

Add a new Event in your list.

Example of usage: 

`event badminton /at 3/5/2019 1500`

Expected outcome:

Event `badminton (at: 3 May 2019 3:00pm)` added to event list.


### `deadline` - Add a new Deadline

Add a new Deadline in your list.

Example of usage: 

`deadline homework /by 3/5/2019 1500`

Expected outcome:

Event `homework (by: 3 May 2019 3:00pm)` added to deadline list.


### `find` - Find a task, event or deadline. 

Find all tasks, events or deadlines from your list that matches the query.

Example of usage: 

`find buy`

Expected outcome:

Tasks, events or deadlines containing the word `buy` will be returned.


### `delete task` - Delete a task, event or deadline. 

Delete a task, event or deadline from your list.

Example of usage: 

`delete task 2`

Expected outcome:

Task, event or deadline of index `2` will be deleted from list.


### `done` - Mark a task, deadline or event completed. 

Mark a task, deadline or event from your list completed.
Does not apply to notes.

Example of usage: 

`done 2`

Expected outcome:

Task, Event or Deadline of index `2` will be marked completed from list. 

### `bye` - Close the application. 

Closes the application.

Example of usage: 

`bye`

Expected outcome:

Application quits.
