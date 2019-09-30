# User Guide - Duke

Duke is an interactive task manager that can keep track of deadlines, events and other general tasks. You can mark tasks as done and delete them when they turn redundant, all in an immersive chat-bot like environment.

Duke automatically saves tasks onto the hard-disk at the file path `/data/Duke.txt`. The tasks from this file are pre-loaded upon opening Duke.

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

### Deadline `[description] [by]`

Adds a task to be completed by a specific deadline. The [by] parameter should be in the DD-MM-YYYY HHMM format.

*Usage:*

`deadline buy book /by 25-09-2019 1500`

*Output:*

```
Nice! I've added this task to the list:-
  [Deadline] [✘] buy book (by: 25-09-2019 1500)
You now have 1 task in the list.
```

### Event `[description] [at]`

Adds an event to attend at a specific time slot. The [at] parameter should be in the DD-MM-YYYY HHMM-HHMM

*Usage:*

`event attend /at 25-09-2019 1500 1700`

*Output:*

```
Nice! I've added this task to the list:-
  [Event] [✘] attend (by: 25-09-2019 1500 1700)
You now have 1 task in the list.
```

### Done `[taskId]/[taskIds]`

Marks a task or a list of tasks as done.

*Usage:*

`done 1 2`

*Output:*

```
Nice! I've marked these tasks as done:-
  [Todo] [✔] buy book
  [Todo] [✔] read book
```

### Find `[description]`

Searches and returns the tasks whose descriptions match the input description.

*Usage:*

'find book'

*Output:*

```
1. [Todo] [✔] buy book
2. [Todo] [✔] read book
```

### Delete `[taskId]`

Deletes the task whose ID matches the entered number.

*Usage:*

'delete 1'

*Output:*

```
Nice! I've removed this task from the list.
  [Todo] [✔] buy book
You now have 3 tasks in the list.
```

### Save

Saves the list of tasks onto the file `/data/Duke.txt`.

*Usage:*

'save'

*Output:*

```
Tasks saved successfully!
```
