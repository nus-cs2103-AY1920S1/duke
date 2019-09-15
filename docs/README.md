# User Guide

## Features 

### Task Management
Allows for creation, deletion, and display of tasks.

### Task Completion
Allows for marking of tasks as completed.

### Task Search
Allows for keyword search to be performed for tasks.

### Place Management
Allows for creation and display of places.

### Persistent Data
Data is saved upon proper exit of application and reloaded upon subsequent run.

## Usage

### `todo` - Creates a Todo Task

This command creates a simple task based on subsequent user input

Example of usage: 

`todo eat`

Expected outcome:

`eat` Task created

### `event` - Creates an Event Task

This command creates a simple event based on subsequent user input. Requires proper date format after /at (dd-MM-yyyy HH:mm).

Example of usage: 

`event party /at 22-09-1992 2230`

Expected outcome:

`party` Task created

### `deadline` - Creates a Deadline Task

This command creates a simple deadline based on subsequent user input. Requires proper date format after /by (dd-MM-yyyy HH:mm).

Example of usage: 

`event essay /by 22-09-1992 2230`

Expected outcome:

`essay` Task created

### `list` - Displays a list of created tasks

This command displays all created tasks with a sequential value from oldest to most recent.

Example of usage: 

`list`

Expected outcome:

`1. xxx` First task created

### `done` - Marks a task from the task list as done

Used in conjunction with list command (see above). Marks as done a task from the list based on list index.

Example of usage: 

`done 1`

Expected outcome:

Item 1 (from list) marked as done.

### `delete` - Deletes a task from the task list

Used in conjunction with list command (see above). Deletes a task from the list based on list index.

Example of usage: 

`delete 1`

Expected outcome:

Item 1 (from list) deleted.

### `find` - Finds tasks based on keyword

This command displays all tasks that match the user provided keyword.

Example of usage: 

`find eat`

Expected outcome:

Task list based on keyword hit

### `place` - Creates a Place

This command creates a simple place based on subsequent user input

Example of usage: 

`place home`

Expected outcome:

`home` Place created

### `list places` - Displays a list of Places

This command displays all created places with a sequential value from oldest to most recent.

Example of usage: 

`list places`

Expected outcome:

`1. xxx` First place created

### `bye` - Exits program and Stores Data

This command stores all data prior to exiting the program, so that it can be loaded back in at next programme execution.

Example of usage: 

`bye`

Expected outcome:

Program exits
