# Duke User Guide

Duke is a chat bot that helps you manage your tasks. 

![Duke screenshot] (/docs/Ui.png)

## Features 

Duke manages a task list that can store:
1. Todos
2. Events (at a location)
3. Deadlines (by a certain date and time)

For managing tasks, Duke can:
1. Mark a task as done
2. Delete a specific task
3. Replace a specific task with another one
4. Find task by keyword

## Usage

### `list`

Duke will output the current list of tasks

### `todo`

Usage: `todo args`

Duke will add a todo with description `args` to the task list

Example: `todo write user guide`

### `event`

Usage: `event args /at place`

Duke will add an event with description `args` and location `place` to the task list

Example: `event hackathon /at school of computing`

### `deadline`

Usage: `deadline args /by date time`

Duke will add a deadline with description `args`, date `date` and time `time`.

`date` must be in format `d/M/yyyy`

`time` must be in format `HHmm`

Example: `deadline CS2103 project /by 31/10/2019 2359`  

### `done`

Usage: `done num`

Duke will mark the task at index `num` as done.

Example: `done 4`

### `delete`

Usage: `delete num`

Duke will delete the task at index `num`.

Example: `delete 1` 

### `find`

Usage: `find args`

Duke returns a sub-list of all tasks that contain `args` in the task description.

Example: `find coding assignment`

### `update`

Usage: `update num command`

Duke replaces the task at index `num` with a new task created by `command`.

Example: `update 3 todo add new functionality`