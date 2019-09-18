# User Guide
![UI screenshot](Ui.png)

## Features

### Add tasks
The user can add Deadline, Event and Todo tasks to a list.

### Mark tasks
The user can mark tasks as done.

### Remove tasks
The user can delete tasks from the list.

### List tasks
The user can view tasks currently in the list.

### Find tasks
The user can search for a task in the list by a keyword.

### Set data source
The user can set the data source for reading or writing.

### View commands
The user can view available commands.

## Usage

### `deadline` - Adds a new Deadline task

Adds a new task that needs to be done by a specific date/time.

Example of usage: 

`deadline <subject> /by <date/time>`

Expected outcome:

`Creates a new deadline task with the given subject description`

### `event` - Adds a new Event task

Adds a new task that starts and ends at a specific date/time.

Example of usage: 

`event <subject> /at <date/time>`

Expected outcome:

`Creates a new event task with the given subject description`

### `todo` - Adds a new Todo task

Adds a new task without any date/time attached to it.

Example of usage: 

`todo <subject>`

Expected outcome:

`Creates a new todo task with the given subject description`

### `done` - Completes a task

Marks a task as done.

Example of usage: 

`done <number>`

Expected outcome:

`Updates specific numbered task as done`

### `delete` - Removes a task

Removes a task.

Example of usage: 

`delete <number>`

Expected outcome:

`Removes specific numbered task from list`

### `List` - Displays current tasks

Displays current tasks.

Example of usage: 

`list`

Expected outcome:

`Displays current tasks in the list`

### `find` - Searches for task by keyword

Searches for task by keyword.

Example of usage: 

`find <keyword>`

Expected outcome:

`Creates a new deadline task with the given description`

### `read` - Changes data source for reading

Changes data source for reading, replacing default source of `data/tasks.txt`.

Example of usage: 

`read <filename>`

Expected outcome:

`Sets specified file as data source for reading`

### `write` - Changes data source for writing

Changes data source for writing, replacing default source of `data/tasks.txt`.

Example of usage: 

`write <filename>`

Expected outcome:

`Sets specified file as data source for writing`

### `help` - Displays available commands

Displays available commands.

Example of usage: 

`help`

Expected outcome:

`Displays help list of available commands`