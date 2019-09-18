# User Guide

## Features 

### Keep track of tasks
* Add tasks to duke.
* Mark tasks as complete.
* Set varying priorities of tasks.

## Usage

### `todo taskDescription` - todo

Adds a to-do task to duke.

Example of usage: 

`todo duke IP`

Expected outcome:

```
Got it. I've added this task:
[T][✘] duke IP LOW
```

### `deadline taskDescription /by dd/mm/yyyy HHmm` - deadline

Adds a deadline to duke.

Example of usage: 

`deadline duke IP /by 19/09/2019 1900`

Expected outcome:

```
Got it. I've added this task:
[D][✘] duke IP (19 September 2019 07:00PM) LOW
```

### `event taskDescription /at dd/mm/yyyy HHmm` - event

Adds an event to duke.

Example of usage: 

`event duke IP /at 20/09/2019 1200`

Expected outcome:

```
Got it. I've added this task:
[E][✘] duke IP (20 September 2019 12:00PM) LOW
```

### `done taskIndex` - done

Marks a task as completed.

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
taskName
```

### `priority taskIndex PriorityLevel` - priority

Sets the priority of a task to LOW/MEDIUM/HIGH

Example of usage: 

`priority 1 MEDIUM`

Expected outcome:

```
Nice! I've changed the priority of this task to MEDIUM
```

### `list` - list

List all the tasks in duke.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][✘] taskName1 HIGH
2. [D][✘] taskName2 (date) MEDIUM
3. [E][✘] taskName3 (date) LOW
```