# User Guide

Welcome to Doke's User Guide. You'll find everything you need to know about Doke here.

## What is Doke?

**Doke** is a chatbot designed to help you manage tasks, while being fun and interactive.

![Doke image](Ui.png)

## Prerequisites

To get started, you'll need to have **Java 11** installed.

The application is packaged as a `.jar` file, which can be found in the `Releases` tab.

## Quick Start

An example scenario of commands entered is listed below:

```
todo Task 1
deadline Project Milestone 1 /by 21/10/2019 1200
event Presentation /at 26/10/2019 1200
done 1
list
find Pr
bye
```

## Table of Contents
- [Features](#features)
    - [Manage tasks](#manage-tasks)
    - [Categorise tasks](#categorise-tasks)
    - [Search for tasks](#search-for-tasks)
    - [Load and save tasks](#load-and-save-tasks)
- [Usage](#usage)
    - [Basics: Add and categorise tasks](#basics-adding-and-categorising-tasks)
        - [deadline - Add a deadline task](#deadline---add-a-deadline-task)
        - [event - Add an event task](#deadline---add-a-deadline-task)
        - [todo - Add a todo task](#todo---add-a-todo-task)
    - [Basics: Delete and mark a task as done](#basics-delete-and-mark-a-task-as-done)
        - [delete - Delete a task](#delete---delete-a-task)
        - [done - Mark a task as done](#done---mark-a-task-as-done)
    - [Basics: Filter and view your tasks](#basics-filter-and-view-your-tasks)
        - [find - Find a task](#find---find-a-task)
        - [list - List all tasks](#list---list-all-tasks)
    - [Basics: Miscellaneous](#basics-miscellaneous)
        - [bye - Quit the program](#bye---quit-the-program)
        - [help - View help](#help---view-help)

## Features

Doke comes with a variety of features to help you with productivity.

### Manage tasks

Tasks can be made simple with Doke. You can tell Doke to:

1. Add a task
2. Delete a task
3. Mark a task as done
4. List tasks

### Categorise tasks

When adding tasks, you can tell Doke to categorise your tasks as a/an:

1. Todo
2. Deadline
3. Event

### Search for tasks

You can filter out important tasks by searching for their name as a keyword.

### Load and save tasks

Doke saves your tasks to: `data/tasks.txt`. For advanced users, you may modify it by inferring from the given format. Corrupt files will be discarded by the application and re-generated on launch.

## Usage

The following commands below have been sorted by use, for your convenience.

### Basics: Adding and categorising tasks

---

#### `deadline` - Add a deadline task

Creates a deadline.

**Example of usage:**

`deadline [description] /by [date]`

**Expected outcome:**

A new deadline called `description` is created and has a due date by `date`. The date format is `dd/MM/YYYY HHmm`.

---

#### `event` - Add an event task

Creates an event.

**Example of usage:**

`event [description] /at [date]`

**Expected outcome:**

A new event called `description` is created and is set at `date`. The date format is `dd/MM/YYYY HHmm`.

---

#### `todo` - Add a todo task

Creates a todo.

**Example of usage:**

`todo [description]`

**Expected outcome:**

A new task called `description` is created.

---

### Basics: Delete and mark a task as done

---

#### `delete` - Delete a task

Deletes a task.

**Example of usage:**

`delete [task number]`

**Expected outcome:**

The `task number` from the task list is deleted. It can range from 1 to the number of tasks in the list.

---

#### `done` - Mark a task as done

Marks a task as done.

**Example of usage:**

`done [task number]`

**Expected outcome:**

The `task number` from the task list is marked as done, as indicated by `[x]`. It can range from 1 to the number of tasks in the list.

---

### Basics: Filter and view your tasks

---

#### `find` - Find a task

Finds a task with given keywords.

**Example of usage:**

`find [task name]`

**Expected outcome:**

The task containing `task name` is returned.

---

#### `list` - List all tasks

Lists all tasks.

**Example of usage:**

`list`

**Expected outcome:**

A list of recorded tasks is shown.

---

### Basics: Miscellaneous

---

#### `bye` - Quit the program

Quits the program.

**Example of usage:**

`bye`

**Expected outcome:**

The program closes.

---

#### `help` - View help

Shows all available commands.

**Example of usage:**

`help`

**Expected outcome:**

All available commands are shown.
