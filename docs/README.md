# User Guide

![App Screenshot](./Ui.png)

## Features 
### Create tasks

You can create Todo, Event and Deadline tasks and add them to the task list.

### Delete tasks

You can delete tasks that have been added from the task list.

### Load and save tasks

The tasks that you have added will be saved when you close Duke. The tasks would also be loaded when Duke is reopened.

### Update task status

You can mark tasks as done once you have completed them.

### View tasks

You can view the tasks as a list with both their done status, type and description.

### Search via keyword(s)

You can search for tasks with specific keyword(s) in their description and view all of them in a list.

### Add alias for commands

You can add aliases for commands that are hard for you to remember. The aliases would be retained even after you close
reopen Duke.

## Usage
### `todo` - Adds a new Todo task

Adds a new task to be done.

Example of usage: 

Command: `todo <description>`

Expected outcome:

`Creates a new todo task with the given description`
---
### `deadline` - Adds a new Deadline task

Adds a new task with a deadline to be met.

Example of usage:

Command: `deadline <description> /by <ddmmyyyy HHHH>`

Expected outcome:

`Creates a new deadline task based on the specified description and deadline`
---
### `event` - Adds a new Event task

Adds a new event and when it will happen.

Example of usage:

Command: `event <description> /at <ddmmyyyy HHHH>`

Expected outcome:

`Creates a new event task based on the specified description and date`
---
### `delete` - Deletes a task

Deletes a task given its index.

Example of usage:

Command: `delete <index>`

Expected outcome:

`Deletes the task at the specified index`
---
### `done` - Marks a task as done

Marks a task as done given its index.

Example of usage:

Command: `done <index>`

Expected outcome:

`Marks the specified task as done`
---
### `list` - List all tasks

List all tasks added into the task list.

Example of usage:

Command: `list`

Expected outcome:

`A list view of all tasks`
---
### `find` - Finds tasks containing keywords

Lists and finds all tasks with matching keywords.

Example of usage:

Command: `find <keyword(s)>`

Expected outcome:

`A list view of all tasks matching the keyword(s)`
---
### `alias` - Associates command to alias

Create a new alias for an existing command.

Example of usage:

Command: `alias <existing command> <alias>`

Expected outcome:

`The alias serves the same function as the command it is associated to`
---
### `bye` - Exit Duke

Saves the aliases and task list.

Example of usage:

Command: `bye`

Expected outcome:

`Duke terminates`