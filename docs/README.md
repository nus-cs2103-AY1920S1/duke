# User Guide - SerSnapsalot
By: `Ken Yoon` Since: `Sep 2019` 


[1. Introduction](#introduction)   

[2. Quick Start](#quickstart)   

[3. Features](#features)   
[...3.1 Viewing help: **_help_**](#help)   
[...3.2 Adding a ToDo: **_todo_**](#todo)   
[...3.3 Adding a Deadline: **_deadline_**](#deadline)   
[...3.4 Adding an Event: **_event_**](#event)   
[...3.5 Marking a task as done: **_done_**](#done)   
[...3.6 Deleting a task: **_delete_**](#delete)   
[...3.7 Listing all tasks: **_list_**](#list)   
[...3.8 Clearing task list: **_clear_**](#clear)   
[...3.9 Locating task by description: **_find_**](#find)   
[...3.10 Saving the data: **_save_**](#save)   
[...3.11 Saving the data to archive: **_archive save_**](#asave)   
[...3.12 Loading the data from archive: **_archive load_**](#aload)   
[...3.13 Exiting the program: **_bye_**](#exit)
    
[4. FAQ](#faq)

[5. Command Summary](#summary)

---
<a name="introduction"/>

## 1. Introduction

SerSnapsalot(SS) is for those of us who enjoy keeping track of tasks through a desktop. Specifically, SS is designed to
implement a CLI while utilizing a Graphical User Interface (GUI). SS also takes reference from Marvel's Spiderman and Ironman
to keep a light and refreshing atmosphere.

<a name="quickstart"/>

## 2. Quick Start

1. Ensure that Java 11 or above is installed in your Computer.
2. Download the latest SerSnapsalot.jar here.
3. Move the file to the folder you want to set as the home folder for your task list.
4. Double click the file to start the app. The GUI should appear in a few seconds.

<a name="features"/>

## 3. Features

**Command Format**

  * Words in UPPER_CASE are parameters provided by the user e.g. in `todo d/DESCRIPTION`,
  `DESCRIPTION` is the parameter which can be used as `todo d/Task 1`.
  * Parameters must be in the specified order when the command specifies either `/at` or `/by` for Event and Deadlines,
  e.g. `event d/DESCRIPTION /at t/TIME` and `deadline d/DESCRIPTION /at t/TIME`.

<a name="help"/>

### 3.1 Viewing Help: `help`

Displays the full range of keywords to begin commands.

<a name="todo"/>

### 3.2 Creating a Todo: `todo`

Adds a Task subclass, ToDo, to the task list.

Format: `todo d/DESCRIPTION`

Examples:

 * `todo Borrow a book from the library`
 
<a name="deadline"/>

### 3.3 Creating a Deadline: `deadline`

Adds a Task subclass, Deadline, to the task list.

Format: `deadline d/DESCRIPTION /at t/TIME`

 * `TIME` should be in the format DD/MM/YYYY HHMM, which specifies the day, month, year, hour and minutes

Examples:

 * `deadline Return book to the library /by 29/12/2018 2359`

<a name="event"/>

### 3.4 Creating an Event: `event`

Adds a Task subclass, Event, to the task list.

Format: `deadline d/DESCRIPTION /at t/TIME`

 * `TIME` should be in the format DD/MM/YYYY HHMM, which specifies the day, month, year, hour and minutes

Examples:

 * `event Outing at the library /at 29/12/2018 1200`

<a name="done"/>

### 3.5 Marking a task as done: `done`

Marks a task as done on the task list.

Format: `done i/INDEX`

 * Marks the task at the specified `INDEX` as done, changing the symbol from `[X]` to `[O]`
 * The index refers to the i-th task in the task list
 * The index must be a positive integer starting from 1 and smaller than the size of the task list

Examples:

 * `done 1`

<a name="delete"/>

### 3.6 Deleting a task: `delete`

Deletes a task from the task list.

Format: `delete i/INDEX`

 * Deletes the task at the specified `INDEX`
 * The index refers to the i-th task in the task list
 * The index must be a positive integer starting from 1 and smaller than the size of the task list

Examples:

 * `delete 1`
 
<a name="list"/>

### 3.7 Listing all tasks: `list`

format: `list`

<a name="clear"/>

### 3.8 Clearing task list: `clear`

format: `clear`

<a name="find"/>

### 3.9 Locating task by description: `find`

Finds tasks whose description contains the given keyword.

Format: `find k/KEYWORD`

 * The search is case-sensitive e.g. `Task` will not match `task`
 * Only the description of the Task is searched
 * Partial words will also be matched e.g. `Task` will match `Tasks`
 * Tasks with matching keywords will be displayed.

Examples:

 * `find Return book`
 * Returns `2. Return book to the library (by: Sat Dec 29 23:59:00 SGT 2018)`

<a name="save"/>

### 3.10 Saving the task list: `save`

Format: `save`

<a name="asave"/>

### 3.11 Saving to archive: `archive save`

Saves the current task list to the archive and clears the current task list.

Format: `archive save`

<a name="aload"/>

### 3.12 Loading from archive: `archive load`

Loads the task list from the archive and replaces the current task list.

Format: `archive load`

<a name="exit"/>

### 3.13 Exiting the program

Format: `bye`

<a name="faq"/>

## 4. FAQ

Q: Where are the tasklist and archived task lsit files saved?
A: They will be saved in the same folder as the jar file as "tasklist.txt" and "tasklistArchive.txt" respectively.

<a name="summary"/>

## 5. Command Summary
  * Help: `help`
  * Todo: `todo d/DESCRIPTION`
  e.g. `todo Borrow a book from the library`
  * Deadline: `deadline d/DESCRIPTION /by t/TIME`
  e.g. `deadline Return book to the library /by 29/12/2018 2359`
  * Event: `event d/DESCRIPTION /at t/TIME`
  e.g. `event Outing at the library /at 29/12/2018 1200`
  * Done: `done`
  * Delete: `delete i/INDEX`
  e.g. `delete 1`
  * List: `list`
  * Clear: `clear`
  * Find: `find k/KEYWORD`
  e.g. `find Return book`
  * Save: `save`
  * Archive save: `archive save`
  * Archive load: `archive load`
  * Exit: `bye`

