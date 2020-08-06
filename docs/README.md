# Jermi User Guide

<img src="Ui.png">

## Introduction
Jermi is a task tracking application that is targeted to help users
keep track of the tasks they have. It includes a GUI that interacts
with the end-users.

## Features

### 1. Viewing help : `help`
Format: `help`

### 2. Adding a task : `todo`, `deadline`, `event`
#### There are 3 types of task JERMI can track:
- `todo` - a task with only a description.
	- Format: `todo <description>`
- `deadline` - a task with a description and the deadline.
	- Format: `deadline <description> /by <deadline>`
- `event` - a task with a description, the date and time of the event.
	- Format: `event <description> /at <date and time>`

#### Examples of usage:
- `todo read book`
- `deadline homework /by next monday`
- `event meeting /at 17/09/2019 1730`

### 3. Viewing all the tasks : `list`
Format: `list`

### 4. Marking a task as done : `done`
Format: `done <task index>`

#### Example of usage:
- `done 1`

### 5. Deleting a task : `delete`
Format: `delete <task index>`

#### Example of usage:
- `delete 2`

### 6. Finding tasks with keyword(s) : `find`
Finds all tasks that partially/exactly match any of the keywords.  
  
Format: `find <keyword 1> <other keywords>...`

#### Example of usage:
- `find meeting`
- `find meeting homework book read`
- `find me rea`

### 7. Deleting all tasks : `clear`
Format: `clear`

### 8. Exiting the program : `bye`
Format: `bye`

 
