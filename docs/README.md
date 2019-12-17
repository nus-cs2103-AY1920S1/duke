# User Guide

[1. Introduction](#1-introduction)

>[1.1 Overview](#11-overview)

>[1.2 Command format](#12-command-format)

>[1.3 Important note](#13-important-note)

[2. Features](#2-features)

>[2.1 Adding a general task](#21-adding-a-general-task-todo)

>[2.2 Adding a task with deadline](#22-adding-a-task-with-deadline-deadline)

>[2.3 Adding an event](#23-adding-an-event-event)

>[2.4 Listing all tasks](#24-listing-all-tasks-list)

>[2.5 Marking done](#25-marking-done-done)

>[2.6 Removing a task](#26-removing-a-task-delete)

>[2.7 Finding a text](#27-finding-a-text-find)

>[2.8 Undoing last command](#28-undoing-last-command-undo)

>[2.9 Exiting](#29-exiting-bye)


## 1. Introduction
### 1.1 Overview
Duke is a personal assistant chatbot that helps a person to keep track of tasks.
It is optimized for those who prefer to work with a Command Line Interface (CLI) while still having the benefits of a
Graphical User Interface (GUI).

### 1.2 Command format
    < a > means a can be replaced by user input. < > should be removed from user input.

    [ a ] means a can be replaced by user input but with exactly the same format. [ ] should be removed from user input.

    ( a ) means a is optional. ( ) should be removed from user input.

    a ^ b means user input can be either a or b but not both.
    
### 1.3 Important note
Unexpected text format in duke.txt may cause exceptions and hence failure to start the application. To ensure normal
functioning of Duke, please delete your `duke.txt` file (if any) before you launch Duke for the first time. You do not
need to delete `duke.txt` from next launching onwards. To prevent unexpected encoding, please do not modify `duke.txt`
with any external editor. You can only modify `duke.txt` within the application. 

## 2. Features
### 2.1 Adding a general task: `todo`
Adds a general task to the task list.

Format: `todo <task>`

Example of usage:

`todo read book 1`

Expected outcome:

`Got it. I've added this task:`

`[T][N] read book 1`

`Now you have 1 tasks in the list.`

### 2.2 Adding a task with deadline: *deadline*
Adds a task with deadline to the task list.

Format: `deadline <task> /by [dd/MM/yyyy] ([HHmm])`

Example of usage:

`deadline return book 1 /by 20/09/2019 1500`

Expected outcome:

`Got it. I've added this task:`

`[D][N] return book 1 by: 20 September 2019 15:00`

`Now you have 2 tasks in the list.`

### 2.3 Adding an event: *event*
Adds an event happening at particular time (or time period) to the task list.

Format: `event <task> /at [dd/MM/yyyy] ([HHmm] ^ [HHmm HHmm])`

Example of usage:

`event project meeting /at 21/09/2019 1500 1600`

Expected outcome:

`Got it. I've added this task:`

`[E][N] project meeting at: 21 September 2019 15:00 to 16:00`

`Now you have 3 tasks in the list.`

### 2.4 Listing all tasks: *list*
Lists all tasks, sorted according to the sequence of being added, with the first added task shown first.

Format: `list`

Example of usage:

`list`

Expected outcome:

`Here are the tasks in your list:`

`1.[T][N] read book 1`

`2.[D][N] return book 1 by: 20 September 2019 15:00`

`3.[E][N] project meeting at: 21 September 2019 15:00 to 16:00`

### 2.5 Marking done: *done*
Marks a task as done.

Format: `done <index>`

Example of usage:

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`

`[T][Y] read book 1`

### 2.6 Removing a task: *delete*
Removes a task from the task list.

Format: `delete <index>`

Example of usage:

`delete 3`

Expected outcome:

`Noted! I've removed this task:`

`[E][N] project meeting at: 21 September 2019 15:00 to 16:00`

`Now you have 3 tasks in the list.`

### 2.7 Finding a text: *find*
Searches for a text in the task list. Returns all tasks with the specified text.

Format: `find <text>`

Example of usage:

`find return`

Expected outcome:

`Here are the matching tasks in your list:`

`1.[D][N] return book 1 by: 20 September 2019 15:00`

### 2.8 Undoing last command: *undo*
Revokes last command.

Format: `undo`

Example of usage:

After mark task 2 as done, type:

`undo`

Expected outcome:

`Undo last command: done 2`

And status of task 2 should be [ N ]

### 2.9 Exiting: *bye*
Exits the program.

Format: `bye`
