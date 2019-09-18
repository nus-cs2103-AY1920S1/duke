# User Guide for [Duke](https://github.com/victorvic54/duke)
![Duke](Ui.png)

## Overview
Duke, a Personal Assistant chat bot that helps a person to keep 
track of their tasks. You can ask duke to do something through a command line from the application.

## Features 
### Add task
Users are able to add the following type of task:
- Todo (a task that need to be done in the future)
- Event (a task that will be happening in a certain date and time)
- Deadline (a task that needs to be done before a certain date and time)

### Delete task
Users are able to delete task that are no longer valid.

### Mark task as done
Users are able to mark a particular task that has happened or done.

### List tasks
Users are able to list down the list of tasks that they have created.

### Find tasks
Users are able to find tasks from the list of tasks based on the keyword they provide.
Duke will also help to match your incomplete or typo text as precise as possible.

### Save and load tasks
Automatic save and load that whenever the user add task or load task when they open the application.

## Usage

### `todo` - Adds a todo task
Commands: 

`todo <description>` - add a todo task with a `description` into the list of tasks.

`event <description> /at <dd/mm/yyyy HHmm>` - add an event task with a `description` at a specific date and time into the list of tasks.

`deadline <description> /by <dd/mm/yyyy HHmm>` - add a deadline task with a `description` that need to be done by a specific date and time into the list of tasks.

Example of usage:

`todo watch lecture` - add a todo task with a `description` of `watch lecture` into the list of tasks.

`event IFG competition /at 12/12/2019 1800` - add an event task with a `description` of `IFG competition` at 12th of December 2019, 6pm.

`deadline cs2103 assignment /by 19/9/2019 2330` - add a deadline task with a `description` of `cs2103 assignment` that need to be done by 19th of September 2019 11.30pm.

### `delete` - Delete task

Command:
`delete <task number>` - delete a task with a `task number` from the list of tasks.

Example of usage:

`delete 1` - delete a task assigned with the number 1 from the list of tasks.

### `done` - Mark task as done
Command:
`done <task number>` - mark a particular `task number` as done from the list of tasks.

Example of usage:

`done 1` - mark task number 1 as done from the list of tasks.

### `list` - List tasks
Command: `list` - to show or list down the list of tasks that they have created.

### `find` - Search for tasks
Command: `find <keyword>` - find the tasks with a `keyword` from the description of the list of tasks.

Example of usage:

`find book` - find the tasks with a `book` keyword from the list of tasks.

`find bok` or `find bk`  - find the tasks with an incomplete keyword `bok` or `bk` and return the 
result that contains a word starts with `b` and ends with `k` from the list of tasks.

### `bye` - Exit application
Command:
`bye` - Exit duke.