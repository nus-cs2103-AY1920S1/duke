# *Duke*: User Guide

## Description
Duke is a friendly dog who helps remind you of your future tasks, so that you keep on track!

## Features 

### *Adding Tasks* 
Duke is able to remember your tasks! There are 3 types of tasks available to add:
1) Normal To-do tasks
2) Tasks with deadlines
3) Events with location

### *Marking Tasks*
Duke is able to mark a task as completed! Stay on top with your task list with it!

### *Find Tasks*
Tell Duke what you wish to search and Duke can tell you the tasks that matches it!

## Usage

### `todo <Task Name>` - Adds a default To-do task

Adds a *task* into the list.

Example of usage: 

`todo Thing to do`

Expected outcome:

`[T][✘] Thing to do`

### `deadline <Task Name> /by <Date and Time>` - Adds a To-do task with a deadline

Adds a task with a *deadline* into the list.
Format of inputted date and time is D/MM/YYYY HHmm.

Example of usage:

`deadline Thing to do /by 2/09/2019 1249`

Expected outcome:

`[D][✘] Thing to do (by: 2 September 2019, 12:39PM)`

### `event <Event Name> /at <Venue>` - Adds a Event task with location

Adds a *event* with a location into the list.

Example of usage:

`event Event /at place`

Expected outcome:

`[E][✘] Event (at: place)`

### `delete <Task Number>` - Deletes task at selected index

Deletes the task at the selected index from the list.

Example of usage:

`delete 5`

Expected outcome:

`I've removed this task: [T][✘] example`

### `done <Task Number>` - Marks a task at selected index as completed

Marks the task at the selected index as completed, with a tick, from the list.

Example of usage:

`done 2`

Expected outcome:

`I've marked this task as done: [T][✓] example`

### `find <Task Number>` - Finds tasks that matches the search term

Finds any tasks from the list that matches the search term.

Example of usage:

`find exam`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][✘] exam
2. [T][✘] example
```

### `list` - Displays the task list

Shows the user their task list.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][✘] example
2. [D][✓] example 2 (by: 15 September 2018, 12:39PM)
```

### `help` - Displays a help message

Ask Duke for the list of commands you can use if you are forgetful or new to the system.

Example of usage:

`help`

### `exit` - Closes the chat bot

Say goodbye to Duke as it goes to sleep. The application window does not
close so that the user may reference the chat log.

Example of usage:

`exit`
