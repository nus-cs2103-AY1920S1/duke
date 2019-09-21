# User Guide

## About
Duke is a command line based tool for keeping
track of your tasks. Tasks are saved whenever
you need to change your tasks.

## Features 

### Adding tasks
Add tasks you have yet to complete.
 
The types of tasks are *todo*, *event* and *deadline*
tasks. Since event and deadline tasks are time sensitive, you
can add the relevant date when creating the task.

### List tasks
List the tasks you have in your current list 
of tasks.

### Finding tasks
Duke let's you enter tasks that you have entered previously
based on the keyword in your search.

### Mark tasks as completed
When you finish a task, you can indicate that 
you have completed it.

### Deleting tasks
Delete any of the tasks in your list.

### Undo an action
Duke allows you to undo your **most recent** action.
This includes all actions which change your list
of tasks.


## Usage

### `todo <description of task>` - Adding a *todo* task

Add a todo task with a description to your task list. Duke will
give feedback on the task, as well as the 
number of tasks in your list currently.

Example of usage: 

`todo 5km run`

Expected outcome:

```
Got it. I've added this task:
[T][✗] 5km run
Now you have 1 tasks in your list.
```


### `event <description of task> /at <dd-mm-yyyy hh:mm>` - Adding an *event* task

Add an event task with a description and date (in the
specified format) to your task list. Duke will
give feedback on the task, as well as the 
number of tasks in your list currently.

Example of usage: 

`event project meeting /at 10-10-2019 18:00`

Expected outcome:

```
Got it. I've added this task:
[E][✗] project meeting (at: 10-10-2019 18:00)
Now you have 2 tasks in your list.
```

### `deadline <description of task> /by <dd-mm-yyyy hh:mm>` - Adding a *deadline* task

Add a deadline task with a description and date (in the
specified format) to your task list. Duke will
give feedback on the task, as well as the 
number of tasks in your list currently.

Example of usage: 

`deadline project meeting /by 11-10-2019 18:00`

Expected outcome:

```
Got it. I've added this task:
[D][✗] programming assignment (by: 11-10-2019 18:00)
Now you have 3 tasks in your list.
```

### `list` - Get the list of tasks you have

List out all the tasks you have. Todo tasks are given as `[T]`, event tasks as `[E]` and deadline as `[D]`. 
Incomplete tasks are displayed with a `[✗]`, while completed tasks are displayed as `[✓]`.

Example of usage:

`list`

Expected outcome:

```$xslt
Here are the tasks in your list:
1.[T][✗] 5km run
2.[E][✗] project meeting (at: 10-10-2019 18:00)
3.[D][✗] programming assignment (by: 11-10-2019 18:00)
```
### `find <search keyword>` - Search for the task

Finds matching tasks from your list, if any, based on your search
keyword.

Example of usage:

`find programming`

Expected outcome:

```$xslt
Here are the matching tasks in your list:
3.[D][✗] programming assignment (by: 11-10-2019 18:00)
```

### `done <task id>` - Mark a task as completed

Mark a given task as completed. In order to mark the right task, remember to use `list` first to check the id
of the task you completed.

Example of usage:

`done 1`

Expected outcome:

```$xslt
Here are the tasks in your list:
1.[T][✓] 5km run
2.[E][✗] project meeting (at: 10-10-2019 18:00)
3.[D][✗] programming assignment (by: 11-10-2019 18:00)
```
### `delete <task id>` - Delete a task

Delete the given task from your list. In order to mark the right task, remember to use `list` first to check the id
of the task you completed.

Example of usage:

`done 1`

Expected outcome:

```$xslt
Noted. I've removed this task:
[T][✓] 5km run
Now you have 2 tasks in the list.
```
### `undo` - Undo the most recent action

You can undo the most recent addition, deletion of tasks as well as marking of said tasks as completed.
Remember not to press undo **consecutively**!

Example of usage:

`undo`

Expected outcome:

```$xslt
Got it, last action was undone.
Please do not press undo consecutively!
Here are the tasks in your list:
1.[T][✓] 5km run
2.[E][✗] project meeting (at: 10-10-2019 18:00)
3.[D][✗] programming assignment (by: 11-10-2019 18:00)
```
