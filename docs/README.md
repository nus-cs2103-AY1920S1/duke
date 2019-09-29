# User Guide

## Features 

### Add Task
Supports 3 types of tasks: ToDos, Deadline, Event.

### Update Task
Set task as done.

### Delete Task
Delete task from list.

### Find Task
Find task using keywords.

### List Task
List all tasks in the list.

### Remind Task
Get reminders on tasks expiring within 3 days.

## Usage

#### `todo [task description]` 
- add a new `ToDos` task.

#### `deadline [task description] /by [dd/mm/yyyy HH:mm]` 
- add a new `Deadline` task with its specified date and time.

#### `event [task description] /at [dd/mm/yyyy HH:mm]` 
- add a new `Event` task with its specified date and time.

####### Example of usage: 

event important meeting /at 16/03/2020 10:00

####### Expected outcome:

creates an `event` with description `important meeting` at the specified date and time `16 March 2020, 10am`.

#### `done [task number]` 
- updates the current task with the specified number on the list as done.

####### Example of usage:

done 3

####### Expected outcome:

Mark the third task on the list as done.

#### `delete [task number]` 
- deletes the current task with the specified number on the list.

####### Example of usage:

delete 3

####### Expected outcome:

Delete the third task on the list.

#### `find [keyword]` 
- Finds all task which contains the specified keyword.

####### Example of usage:

find meeting

####### Example of outcome:

A list of task which contains keyword "meeting".

#### `list` 
- Show all tasks in the list.

#### `reminder` 
- Show all tasks which expires within 3 days.

#### `bye` 
- Exits the application.