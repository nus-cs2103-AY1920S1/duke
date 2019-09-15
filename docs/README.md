# User Guide

## Features 

### 

Duke is a personal chat bot that uses the CLI to manage daily tasks. It is a case insensitve CLI application.



## Usage

### `list` - List out all tasks 

A list of all the To Do, Deadline and Events are shown to the user.
For all To Do, Deadline and Events, a task description is shown in chronological order in which they were added. The classification of the To Do, Deadline and Events are denoted by a task icon, as shown in table below.

Task | Task icon
------------ | -------------
To Do | T
Deadline | D
Event | E

The completion status is also denoted by a completion icon, as shown in the table below.

Completion Status | Completion icon
------------ | -------------
Completed | Y
Incomplete | N

For Deadlines, the date and time in which the task is due is included in the same line after the task description.
For Events, the date and time in which the task is happening is included in the same line after the task description.

Example of usage: 

`list`

Example of expected outcome:

```
Here are the tasks in your list:
1.[E][N] project meeting (by: 02/12/2019 1420)
2.[D][N] return book (by: 02/12/2019 1420)
3.[T][N] read book
```

### `help` - Show help page to assist user in commands available

Shows the various commands that the Duke chat bot uses in the application. 

Example of usage: 
`help`
Example of expected outcome:
```
Welcome to help page!
Here are some commands you can use:
list, todo, event, deadline, done, delete, find, bye
```
The user can further check the syntax required of each command to use it, by supplying the command interested.

Example of usage: 
`help done`
Example of expected outcome:
```
Here is what I found!
delete <task number>
```
### `todo` - Adds a To Do task to the task list.

The user can add a To Do task that has no deadline to the task list.

Example of usage: 
`todo Homework`
Example of expected outcome:
```
Got it. I've added this task:
  [T] Homework
Now you have 5 tasks in the list.
```

