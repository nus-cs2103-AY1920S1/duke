# User Guide

## Features

### Feature - Todo
Adds a new Todo task. Todo tasks are simple reminders that does not have any
date associated.

### Feature - Deadline
Adds a new Deadline task. Deadline tasks comes with an ending date.

### Feature - Event
Adds a new Event task. Event tasks comes with an starting date.

### Feature - Sort
Allows you to sort the list by ascending/descending name or time.

## Usage

### `todo` - Adds a new Todo task.

Command:

`todo (Description of task)`

Example of usage:

`todo Complete 2103T quiz`

Expected outcome:

New Todo is added to the task list, with a message confirming it.


### `deadline` - Adds a new Deadline task.

Command:

`deadline (Description of task) /by (dd/mm/yyyy hhmm)`

Example of usage:

`deadline Complete 2103T Quiz /by 19/09/2019 2359`

Expected outcome:

New deadline is added to the task list, with a message confirming it.

### `event` - Adds a new event task.

Command:

`event (Description of task) /at (dd/mm/yyyy hhmm)`

Example of usage:

`event 2103T tutorial /at 18/09/2019 1000`

Expected outcome:

New event is added to the task list, with a message confirming it.

### `sort` - Sorts the list by specified parameters.

Command:

`sort (name/date) (ascending/descending)`

Example of usage:
`sort name ascending`

Expected outcome:

Task list is sorted in ascending/descending order by name/date, depending on
parameters specified.
