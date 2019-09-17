# [Duke](https://github.com/geshuming/duke) User Guide

![UI](UI.png)

---

## Features 

### Track tasks
Ability to track the following tasks:
- Todo task (generic tasks)
- Event task (tasks that start at a certain time)
- Deadline task (tasks that end at a certain time)

### Mark tasks as complete
Ability to mark tasks as completed.

### Search for specific tasks
Search function to find certain tasks using keywords.

### Delete tasks
Ability to delete tasks regardless of task status.

### Undo feature
Made a mistake? Don't worry. Duke can undo your commands.

---

## Usage

### bye - Exits Duke

Say bye to Duke and closes the program.

Usage: `bye`

### todo - Adds a todo task

Adds a todo task with a given description.

Usage: `todo <description>`

### event - Adds an event task

Adds an event task with a given description and datetime that the task starts.

Usage: `event <description> /at <datetime>`

`<datetime>` strictly follows the given format `dd/MM/yyyy HHmm`, e.g. 17/09/2019 2359

### deadline - Adds a deadline task

Adds a deadline task with a given description and datetime that the task ends.

Usage: `deadline <description> /by <datetime>`

`<datetime>` strictly follows the given format `dd/MM/yyyy HHmm`, e.g. 17/09/2019 2359

### list - Lists all the tasks

Lists all the tasks given to Duke so far.

Usage: `list`

### done - Marks a task as done

Marks the task with the given task id as done.

Usage: `done <id>`

### delete - Deletes a task

Deletes the task with the given task id as done.

Usage: `delete <id>`

### find - Search for specific tasks

Searches the list of tasks with the given keyword.

Usage: `find <keyword>` 

### undo - Undos the last effective command

Undos the last effective command. Effective commands does not include `list` and `find`.

Usage: `undo`