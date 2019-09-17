# Duke - User Guide

1. [Introduction](#1-introduction)

2. [Quick Start](#2-quick-start)

3. [Features](#3-features)

  3.1. [Listing all tasks: `list`](#31-listing-all-tasks-list)
  
  3.2. [Adding a todo: `todo`](#32-adding-a-todo-todo)
  
  3.3. [Adding an event: `event`](#33-adding-an-event-event)
  
  3.4. [Adding a deadline: `deadline`](#34-adding-a-deadline-deadline)
  
  3.5. [Finding a task: `find`](#35-finding-a-task-find)
  
  3.6. [Marking a task as done: `done`](#36-marking-a-task-as-done-done)
  
  3.7. [Deleting a task: `delete`](#37-deleting-a-task-delete)
  
  3.8. [Exiting the program: `bye`](#38-exiting-the-program-bye)
  
4. [Command Summary](#4-command-summary)

## 1. Introduction

Duke is a desktop app to **track your tasks** such as to-dos, events and deadlines. **Prefer to use the Command Line Interface** (CLI) with **Graphical User Interface** (GUI)? Duke is the one for you.

## 2. Quick Start

1. Ensure that you have Java `11` or above installed in your Computer.

2. Download the latest `duke.jar` [here](https://github.com/joloong/duke/releases/tag/A-Release).

3. Double-click the file to start the app.

4. Play around with it.

## 3. Features

### 3.1. Listing all tasks: `list`

Shows a list of all tasks in Duke.

Format: `list`

### 3.2. Adding a todo: `todo`

Adds a todo task to Duke.

Format: `todo TODO... /p PRIORITY`

Examples: 
* `todo sleep /p 1`
* `todo code /p 3`

### 3.3. Adding an event: `event`

Adds an event to Duke.

Format: `event EVENT... /at DD/MM/YYYY HHMM-HHMM /p PRIORITY`

Examples:
* `event CS2105 MidTerm /at 7/10/2019 1400-1600 /p 1`
* `event NOC chat /at 12/9/2019 1300-1400 /p 2`

### 3.4. Adding a deadline: `deadline`

Adds a deadline to Duke.

Format: `deadline DEADLINE... /by DD/MM/YYYY HHMM /p PRIORITY`

Examples:
* `deadline CS2103T iP /by 17/9/2019 2359 /p 1`
* `deadline GEQ1000 quiz /by 20/9/2019 2359 /p 2`

### 3.5. Finding a task: `find`

Finds a task in Duke.

Format: `find DESCRIPTION...`

Examples:
* `find quiz`
* `find NOC chat`

### 3.6. Marking a task as done: `done`

Marks a tasks in Duke as done.

Format: `done INDEX`

Examples:
* `done 1`
* `done 27`

### 3.7. Deleting a task: `delete`

Deletes a task in Duke.

Format: `delete INDEX`

Examples:
* `delete 1`
* `delete 72`

### 3.8. Exiting the program: `bye`

Exits the program.

Format: `bye`

## 4. Command Summary

* **Todo**: `todo TODO... /p PRIORITY`
* **Event**: `event EVENT... /at DD/MM/YYYY HHMM-HHMM /p PRIORITY`
* **Deadline**: `deadline DEADLINE... /by DD/MM/YYYY HHMM /p PRIORITY`
* **Find**: `find DESCRIPTION...`
* **Done**: `done INDEX`
* **Delete**: `delete INDEX`
* **List**: `list`
* **Exit**: `bye`
