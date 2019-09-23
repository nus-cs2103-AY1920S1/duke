# User Guide

## Introduction

Duke is a task manager desktop app that helps you keep track of your tasks. Duke is designed for those that are accustomed to Command Line Interface (CLI) while enjoying the benefits of a Graphical User Interface (GUI).

## Quick Start
* Ensure you have Java `11` or above installed
* Download the latest release of Duke Program [here](https://github.com/yyuanxin/duke/releases/tag/A-Release)
* Double-click the file to start the app. The GUI should appear in a few seconds. 

## Features 

### Feature 1: Task Tracker 
This feature is capable of handling 3 types of tasks.
#### Types of Tasks
1. `Todo`: task with a short description
2. `Deadline`: task that needs to be done before a specific date/time
3. `Event`: task that start and end at a specific time

Date and time are in the format of dd/MM/yyyy HHmm (eg. 31/08/2019 2359)

#### Characteristics of Task Tracker
* `add`: task can be added for task tracking
* `done`: mark task as done once completed
* `delete`: delete task from the list
* `find`: find matching tasks by searching keyword(s)
* `list`: show all the existing task(s)
* `bye`: exit program

### Feature 2: Friendlier Syntax
This feature makes the command syntax more flexible.
1. `add alias`: allow users to define their own aliases
2. `list alias`: show the full command list and its current alias.

Note: Every command can have one alias only and this feature functions at a overwriting basis (i.e. replaces existing with new alias).

## Usage

### 1. Adds a new Todo' task: `todo`
#### Format: `todo <description>`

This command allows user to add a new todo task in Duke.
  * `Todo` tasks will be represented as `[T]` by Duke.

Example of usage: 

`todo make dinner`

Expected outcome:

```
 Got it. I've added this task:
 [T][ ] make dinner
 Now you have 1 task in the list.
```

### 2. Adds a new 'Deadline' task: `deadline`
#### Format: `deadline <description> /by dd/MM/yyyy HHmm`

This command allows user to add a new Deadline task with a specfic date & time in Duke.
  * `Deadline` tasks will be represented as `[D]` by Duke.

Example of usage: 

`deadline complete CS2103T iP /by 18/09/2019 2300`

Expected outcome:

```
 Got it. I've added this task:
 [D][ ] complete CS2103T iP (by: 18th September 2019, 11.00PM)
 Now you have 2 tasks in the list.
```

### 3. Adds a new 'Event' task: `event`
#### Format: `event <description> /at dd/MM/yyyy HHmm`

This command allows user to add a new event task with a date time in Duke.
  * `Event` tasks will be represented as `[E]` by Duke.

Example of usage: 

`event attend CS2103T Lecture /at 20/09/2019 1200`

Expected outcome:

```
 Got it. I've added this task:
 [E][ ] attend CS2103T Lecture (at: 20th September 2019, 12.00PM)
 Now you have 3 tasks in the list.
```

### 4. Mark task as complete: `done`
#### Format: `done <task id>`

This command allows user to mark the task as done in Duke.
  * Tasks marked as done will be represented as `[X]` by Duke.
  * `<task id>` is the index of the task in Duke's Task List.
    * Tip: `list` command shows all the tasks and its corresponding task id.

Example of usage: 

`done 1`

Expected outcome:

```
 Nice! I've marked this task as done:
 [T][X] make dinner
```

### 5. Delete task from list: `delete`
#### Format: `delete <task id>`

This command allows user to delete existing task in Duke.
  * `<task id>` is the index of the task in Duke's Task List.
    * Tip: `list` command shows all the tasks and its corresponding task id.

Example of usage: 

`delete 3`

Expected outcome:

```
 Noted. I've removed this task:
 [E][ ] attend CS2103T Lecture (at: 20th September 2019, 12.00PM)
 Now you have 2 tasks in the list.
```

### 6. Find matching task from list: `find`
#### Format: `find <keyword>`

This command allows user to find all matching tasks with the given keyword in Duke.

Example of usage: 

`find dinner`

Expected outcome:

```
 Here are the matching tasks in your list:
 [T][X] make dinner
```

If there is no matching task in Duke:
```
 0 Matching Results found!
```

### 7. Show all tasks in Duke: `list`
#### Format: `list`

This command allows user to view all existing tasks in Duke.

Example of usage: 

`list`

Expected outcome:

```
 Here are the tasks in your list:
 1. [T][X] make dinner
 2. [D][ ] complete CS2103T iP (by: 18th September 2019, 11.00PM)
 Now you have 2 tasks in the list.
```

### 8. Show the full command list and its alias: `listalias`
#### Format: `listalias`

By default, `listalias` will be loaded with the standard commands.
* Each command type is assigned to one unique alias.
The format shown represents `<COMMAND TYPE>: <alias>` and in alphabetical order
```
 Here are your aliases :)
 
 ADDALIAS: addalias
 EXIT: bye
 DEADLINE: deadline
 DELETE: delete
 DONE: done
 EVENT: event
 FIND: find
 LIST: list
 LISTALIAS: listalias
 TODO: todo
```

### 9. Overwrite existing alias: `addalias`
#### Format: `addalias <COMMAND TYPE> <new alias>`

This command allows user to add their own alias by overwriting the existing. 
* If `<new alias>` has already been attributed to another command type, Duke will inform the user that the overwrite cannot be executed. 
  * This ensures unique alias list is maintained

Example of usage: 

`addalias todo t`

Expected outcome:

```
 Got it. I've overwritten the alias:
 TODO: t
```

Replaces
`todo <description>`
With
`t <description>`

