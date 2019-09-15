# User Guide
## Quick Start
0. Ensure that you have Java on your system.
1. Download `jar` file from release.
2. Execute `jar` file. GUI should appear in a few seconds
3. Type in commands and press `enter` on the keyboard or the "Send" button to execute them.

## Screenshot
![Image of UI](/docs/Ui.png)

## Features 

### Task Management
Manage all your todos, deadlines, events in one place

### Command Line Interface
Add and update tasks with ease

## Usage

### `todo` - add a todo to your task list

type `todo` followed by a description of the todo to add an undone task to the list

Example of usage: 

`todo <name of todo>`

Expected outcome:

```
Got it. I've added this task:
    [T][x] <your todo>
Now you have - tasks in the list.
```

### `event` - add an event to your task list

type `event` followed by the title of your event, `/at`, followed by 2 dates separated by `-`. 
Dates must be in `dd/MM/yyyy HHmm` format.

Example usage:

`event my event /at 19/09/2019 2349 - 20/09/2019 2200`

Expected outcome:

```
Got it. I've added this task:
    [E][x] my event (at: Thu Sep 19 23:49:00 SGT 2019 - Fri Sep 20 22:00:00 SGT 2019)
Now you have 6 tasks in the list.
```

### `deadline` - add a deadline to your task list

enter `deadline` followed by description of the deadline, `/by`, followed by 1 date. 
Dates must be in `dd/MM/yyyy HHmm` format.

Example usage:

`deadline my deadline /by 23/09/2019 2103`

Expected outcome:

```
Got it. I've added this task:
    [D][x] my deadline (by: Mon Sep 23 21:03:00 SGT 2019)
Now you have 7 tasks in the list.
```

### `list` - see all tasks in your task list

type `list`

Example usage:
`list`

Expected outcome:
```
______________________________
1. [T][✘] somthing
2. [E][✘] my event (at: Thu Sep 19 23:49:00 SGT 2019 - Fri Sep 20 22:00:00 SGT 2019 )
3. [D][✘] my deadline (by: Mon Sep 23 21:03:00 SGT 2019)
______________________________
```

### `find` - search for a task that has matching word

input `find` followed by keyword to search for.

Example usage:
`find my`

Expected outcome:
```
______________________________
Here are the matching tasks in your list: 
	[E][✘] my event (at: Thu Sep 19 23:49:00 SGT 2019 - Fri Sep 20 22:00:00 SGT 2019 )
	[D][✘] my deadline (by: Mon Sep 23 21:03:00 SGT 2019)
______________________________
```

### `delete` - remove a task

input `delete` followed by index of task

Example usage:
`delete 1`

Expected outcome:
```
______________________________
Noted. I've removed this task: 
	[T][✘] somthing
Now you have 2 tasks in the list
______________________________
```

### `done` - mark a task as done

input `done` followed by index of task

Example usage:
`done 1`

Expected outcome:
```
______________________________
Nice! I've marked this task as done:
  [T][✓] something
______________________________

```

### `update` - replace a task with a new one

input `update` followed by index of task and declare a new task as you would normally

Example usage:
`update 1 todo something`

Expected outcome:
```
______________________________
Updated task from:
[E][✘] some event (at: Thu Sep 19 23:58:00 SGT 2019 - Thu Sep 19 23:59:00 SGT 2019 )
To:
[T][✘] something
______________________________
```

### `bye` - exit the app

input `bye` to close the application

Example usage:
`bye`

Expected outcome:
```
______________________________
Bye. Hope to see you again soon!
______________________________
```
