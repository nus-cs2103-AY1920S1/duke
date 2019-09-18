# User Guide

## Features 

### List tasks
List tasks.

## Usage

### `list`

Expected outcome:

```
[D][✓] Submit CS2103T Assignment (by 20 September 2019 11:59PM)
[E][✗] Feed the cat (by 22 September 2019 11:50AM)
```
### Set todo
Set a task to be done

## Usage

### `todo <description>`

Sets a task with description.

Example of usage: 

`todo Take a nap`

Expected outcome:
```
Got it. I've added this task: 
[T][✗] Take a nap
Now you have <number of tasks> tasks in the list
```

### Set deadline
Set a task with a deadline

## Usage

### `deadline <description> /by <date>`

Sets a task with description to be done by a certain date.

Example of usage: 

`deadline Submit CS2103T Assignment /by 20/9/2019 2359`

Expected outcome:

```
Got it. I've added this task:
[D][✗] Submit CS2103T Assignment (by 20 September 2019 11:59PM)
Now you have <number of tasks> tasks in the list
```

### Set event
Set an event at a certain time

## Usage

### `event <description> /by <date>`

Sets a task with description to happen at a certain date.

Example of usage: 

`event CS2103T lecture /at 20/9/2019 1600`

Expected outcome:

```
Got it. I've added this task:
[E][✗] CS2103T lecture (at 20 September 2019 4:00PM)
Now you have <number of tasks> tasks in the list
```

### Done
Mark task as done

## Usage

### `done <index>`

Marks a task specified by its index in the list as done

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
1. [E][✓] CS2103 Team meeting (at 20 September 2019 03:00PM)
```

### Find
Filter tasks by their description

## Usage

### `find <search parameter>`

Sets a task with description containing search parameter.

Example of usage: 

`find tutorial`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D][✗] Finish CS2100 tutorial (by 19 September 2019 01:00PM)
2. [D][✓] Finish CS2103T tutorial (by 19 September 2019 01:00PM)
```

### Sort
Sort tasks by their description or type

## Usage

### `sort <sort type>`

Sort tasks.

Example of usage: 

`sort description`

Possible sort types:
`description, type`

Expected outcome:
`Sorted by type`