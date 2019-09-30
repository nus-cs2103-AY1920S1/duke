# User Guide

## About 
Duke is a command line tool to help you keep track of events, todos, and deadlines seamlessly! 

## Features 

### Adding tasks 
Add tasks to duke, this will automatically mark them as incomplete. 
Tasks are subdivided into 3 categories - todo, event and deadline.

### List tasks
List all tasks currently saved. Tasks that are deleted will not show up.

### Cloning tasks
Repeat the same tasks based on list index. 

### Mark as done
Mark tasks as done using the list index. 

## Usage

### `todo <description>` - add a todo task

Example of usage: 

`todo homework 1`

Expected outcome:

`Got it. I've added this task:
[T][✗] homework 1
Now you have 1 tasks in your list.`

### `event <description> /at dd-MM-yyyy HH:mm` - add an event task

Example of usage: 

`event concert /at 13-10-2019 18:00`

Expected outcome:

`Got it. I've added this task:
[E][✗] concert (at: 13-10-2019 18:00)
Now you have 2 tasks in your list.`

### `deadline <description> /by dd-MM-yyyy HH:mm` - add a deadline task

Example of usage: 

`deadline submit proposal /by 15-10-2019 23:59`

Expected outcome:

`Got it. I've added this task:
[D][✗] concert (by: 15-10-2019 23:59)
Now you have 3 tasks in your list.`

### `list` - list all tasks

Example of usage: 

`list`

Expected outcome:

`1. [T][✗] homework 1
2. [E][✗] concert (at: 13-10-2019 18:00)
3. [D][✗] concert (by: 15-10-2019 23:59)`

### `clone <index>` - clone a task
clone a task based on list index.

Example of usage: 

`clone 1`

Expected outcome:

`Got it. I've added this task:
[T][✗] homework 1
Now you have 4 tasks in your list.`

### `delete <index>` - delete a task
clone a task based on list index.

Example of usage: 

`delete 4`

Expected outcome:

Noted. I've removed this task:
[T][✗] homework 1
Now you have 2 tasks in the list.


### `done <index>` - mark task as done
clone a task based on list index.

Example of usage: 

`done 2`

Expected outcome:

`Got it. I've marked this task as done:
[E][✔] concert (at: 13-10-2019 18:00)`