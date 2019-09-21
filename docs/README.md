# Bertrand's Duke Task App User Guide

## 1. Introduction

Bertrand's Duke app is a **Command-Line-Interface** Desktop-based application which allows you to create a **Tasklist** app which allows you to create, read, update and delete tasks. There are three kinds of tasks available to this application, namely **Todo**, **Deadline** and **Event** tasks. The application stores a local copy `(data/duke.txt)` which will be updated each time a successful command is made.

### *Example Image of Application* : 
![Image of Bertrand's Duke](https://raw.githubusercontent.com/Berttwm/duke/master/docs/Ui.png)

## 2. Quick Start

1. Ensure that you have Java `11` or above installed in your Computer.

2. Download the latest `duke.jar` [here]().

3. Double-click the file to start the app.

4. Try commands under the Features List.

5. Submit Pull Request if there are bugs you would like to report.

## 3. Features 

### 3.1. Shows list of all tasks: `list`

Shows a list of all tasks stored.

Format: `list`

### 3.2. Adding a todo: `todo`

Adds a todo task to Duke.

Format: `todo TODO...`

Examples: 
* `todo help dad`
* `todo clean house`

### 3.3. Adding an event: `event`

Adds an event task to Duke.

Format: `event EVENT... /at DD/MM/YYYY HHMM`

Examples:
* `event Finish Paper 1 /at 4/10/2019 1259`
* `event Playing Badminton Finals /at 10/12/2019 1900`

### 3.4. Adding a deadline: `deadline`

Adds a deadline to Duke.

Format: `deadline DEADLINE... /by DD/MM/YYYY HHMM`

Examples:
* `deadline Math Quiz /by 10/9/2019 2359`
* `deadline Personal Statement for NOC /by 13/2/2019 1159`

### 3.5. Finding a task: `find`

Finds a task in Duke.

Format: `find DESCRIPTION...`

Examples:
* `find quiz`
* `find NOC chat`

### 3.6. Marking a task as done: `done`

Marks a tasks in Duke as done (If said task number exists).

Format: `done ITEM_INDEX`

Examples:
* `done 2`
* `done 30`

### 3.7. Deleting a task: `delete`

Deletes a task in Duke (If said task number exists).

Format: `delete ITEM_INDEX`

Examples:
* `delete 4`
* `delete 23`

### 3.8. Exit Duke App : 'bye'

Closes the program.

Format: `bye`

## Command Summary

* **Todo**: `todo TODO...`
* **Event**: `event EVENT... /at DD/MM/YYYY HHMM`
* **Deadline**: `deadline DEADLINE... /by DD/MM/YYYY HHMM`
* **Find**: `find DESCRIPTION...`
* **Done**: `done ITEM_INDEX`
* **Delete**: `delete ITEM_INDEX`
* **List**: `list`
* **Exit**: `bye`


