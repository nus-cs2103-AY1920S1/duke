! [Duke](./Ui.png)
# User Guide

## Features

### Feature - Todo
Adds a new Todo task. Todo tasks are tasks that do not have any
date associated them.

### Feature - Deadline
Adds a new Deadline task. Deadline tasks are associated with a date, typically indicating the deadline of the task.

### Feature - Event
Adds a new Event task. An event task is associated with the date that it occurs.

### Feature - Remind
Allows you to create a reminder.

## Usage

### `todo` - Adds a new Todo task.

Command:

`todo <Task description>`

Example of usage:

`todo iP Week 6`

Expected outcome:

New Todo is added to the task list and Duke states that task has been added.


### `deadline` - Adds a new Deadline task.

Command:

`deadline <Task description> /by <dd/mm/yyyy hh:mm>`

Example of usage:

`deadline Submit CS2103T Quiz /by 19/09/2019 23:59`

Expected outcome:

New deadline is added to the task list and Duke states that task has been added.

### `event` - Adds a new event task.

Command:

`event <Task description> /at <dd/mm/yyyy hh:mm>`

Example of usage:

`event CS2103T tutorial /at 19/09/2019 10:00`

Expected outcome:

New event is added to the task list and Duke states that task has been added.

### `remind` - Creates a reminder.

Command:

`remind <description> /at <dd/mm/yyyy hh:mm>`

Example of usage:
`remind CS2103T UG Draft Deadline /at 18/09/2019 23:59 `

Expected outcome:

A reminder is scheduled. Duke will remind the user of the mentioned task description at the specified time. (In this case, at 18/09/2019 23:59)