# User Guide - Duke

Duke is an interactive task manager that can keep track of deadlines, events and other general tasks. You can mark tasks as done and delete them when they turn redundant, all in an immersive chat-bot like environment.

## Features 

### Add To-dos
Conveniently schedules tasks to be done in no particular time or order!

### Keep Track of Deadlines
Add tasks that you have to complete by a particular deadline.

### Schedule Events
Plan and attend events scheduled in the future.

### Keep Track of Completion
Mark tasks as done as and when you complete them.

### Remove Tasks
Delete completed and redundant tasks from task list!

## Usage

This section documents the entire set of commands supported by Duke. The commands follow a common format. The user must enter the name of the command followed by the parameter(s).

`command [parameters]`

### Help

This command lists all the commands supported by Duke and their respective formats.

*Usage:*

`help`

*Output:*

```
Here is a list of commands I can respond to: -
 (*) list
  ...
```

### List

Lists all the tasks stored by Duke. Includes completed tasks.

*Usage:*

`list`

*Output:*
```
1. [Todo] [✔] Buy book
2. [Deadline] [✘] Return Book (by: 28-09-2019 2359)
```

### Todo `[description]`

Adds a general task to be done. To-dos have no specified completion time.

*Usage:*

`todo buy book`

*Output:*

```
Nice! I've added this task to the list:-
  [Todo] [✘] buy book
You now have 1 task in the list.
```
