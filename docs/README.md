# User Guide

Welcome to Doke's User Guide. You'll find everything you need to know about Doke here.

## What is Doke?

**Doke** is a chatbot designed to help you manage tasks, while being fun and interactive.

![Doke image](Ui.png)

View [Features](#features) for a comprehensive list of features.

## Quick Start

1. Verify that you have **Java 11** installed.

2. Download the latest `.jar` version from [Releases](https://github.com/Nanosync/duke/releases).

3. Copy or move the file to a desired location.

4. Double-click to start the application. A GUI will appear in a few seconds.

5. Type a message in the box and press `Enter` to execute it.
    - e.g. `help` displays the list of available commands.

6. Some example commands are listed below:
    - `list`: shows all available tasks
    - `todo Task 1`: creates a todo called `Task 1`
    - `done 1`: marks `Task 1` as done
    - `deadline Project Milestone 1 /by 21/10/2019 1200`: creates a deadline called `Project Milestone 1`, due by `21/10/2019 1200`
    - `event Presentation /at 26/10/2019 1200`: creates an event called `Presentation`, at `26/10/2019 1200`
    - `find Pr`: finds all tasks that contain the word `Pr`
    - `bye`: closes the application

7. Refer to [Usage](#usage) for details of each command.

## Table of Contents
- [Features](#features)
    - [Manage tasks](#manage-tasks)
    - [Categorise tasks](#categorise-tasks)
    - [Search for tasks](#search-for-tasks)
    - [Load and save tasks](#load-and-save-tasks)
- [Usage](#usage)
    - [Add and categorise tasks](#adding-and-categorising-tasks)
        - [deadline - Add a deadline task](#deadline---add-a-deadline-task)
        - [event - Add an event task](#deadline---add-a-deadline-task)
        - [todo - Add a todo task](#todo---add-a-todo-task)
    - [Delete and mark a task as done](#delete-and-mark-a-task-as-done)
        - [delete - Delete a task](#delete---delete-a-task)
        - [done - Mark a task as done](#done---mark-a-task-as-done)
    - [Filter and view your tasks](#filter-and-view-your-tasks)
        - [find - Find a task](#find---find-a-task)
        - [list - List all tasks](#list---list-all-tasks)
    - [Miscellaneous](#miscellaneous)
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

Doke saves your tasks to: `data/tasks.txt`. Corrupt files will be discarded by the application and re-generated on launch.

The format of each task type is defined below.

---

Deadline Format:
```
D|[Done: true/false]|Description|[By date]
```

Event Format:
```
E|[Done: true/false]|Description|[At date]
```

Todo Format:
```
T|[Done: true/false]|Description
```

---

For example, given a save file with the following content:
```
D|false|complete The Project|20/9/2019 1800
E|false|Attend the funfair|25/12/2019 1200
T|true|homework
```

The following output is returned:
```
Here are the tasks in your list:
1. [D][ ] complete The Project (by: Fri Sep 20 18:00:00 SGT 2019)
2. [E][ ] Attend the funfair (at: Wed Dec 25 12:00:00 SGT 2019)
3. [T][x] homework
```

## Usage

The following commands below have been sorted by use, for your convenience.

### Adding and categorising tasks

---

#### `deadline` - Add a deadline task

Creates a deadline.

**Example of usage:**

`deadline my homework /by 13/1/2019 2100`

**Expected outcome:**

```
Got it. I've added this task: 
[D][ ] my homework (by: Sun Jan 13 21:00:00 SGT 2019)
Now you have 1 tasks in the list.
```

A new deadline called `my homework` is created and has a due date by `13 Jan 2019, 21:00`. The date format is `dd/MM/YYYY HHmm`.

---

#### `event` - Add an event task

Creates an event.

**Example of usage:**

`event meeting /at 17/9/2019 1200`

**Expected outcome:**

```
Got it. I've added this task: 
[E][ ] meeting (at: Tue Sep 17 12:00:00 SGT 2019)
Now you have 1 tasks in the list.
```

A new event called `meeting` is created and is set at `17 Sep 2019, 12:00`. The date format is `dd/MM/YYYY HHmm`.

---

#### `todo` - Add a todo task

Creates a todo.

**Example of usage:**

`todo school project`

**Expected outcome:**

```
Got it. I've added this task: 
[T][ ] school project
Now you have 1 tasks in the list.
```

A new todo called `school project` is created.

---

### Delete and mark a task as done

---

#### `delete` - Delete a task

Deletes a task.

**Example of usage:**

`delete 1`

**Expected outcome:**

```
Noted. I've removed this task:
[D][ ] my homework (by: Sun Jan 13 21:00:00 SGT 2019)
Now you have 0 tasks in the list.
```

Assuming there is a single task called `my homework`, task number `1` from the task list is deleted. It can range from 1 to the number of tasks in the list.

---

#### `done` - Mark a task as done

Marks a task as done.

**Example of usage:**

`done 1`

**Expected outcome:**

```
Nice! I've marked this task as done:
[T][x] homework
```

Task number `1`, labelled `homework` from the task list is marked as done, as indicated by `[x]`. Task number `1` can range from 1 to the number of tasks in the list.

---

### Filter and view your tasks

---

#### `find` - Find a task

Finds a task with given keywords (case-sensitive).

**Example of usage:**

`find pre`

**Expected outcome:**

```
Here are the matching tasks in your list:
1. [T][ ] prepare materials
2. [T][ ] prepare content
```

Assuming the list has 3 tasks (`draw props`, `prepare materials`, `prepare content`), the tasks containing the word `pre` is returned.

---

#### `list` - List all tasks

Lists all tasks.

**Example of usage:**

`list`

**Expected outcome:**

```
Here are the tasks in your list:
1. [T][ ] draw props
2. [T][ ] prepare materials
3. [T][ ] prepare content
```

A list of recorded tasks is shown.

---

### Miscellaneous

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

```
You may use the following commands:
bye - Exit the program
deadline [description] /by [date: dd/MM/YYYY] - Add deadline
delete [task number] - Delete a task
done [task number] - Marks a task as done
event [description] /at [date: dd/MM/YYYY] - Add event
find [keywords] - Finds a task based on its keywords
help - Show help
list - List all tasks
todo [description] - Add a todo
```

All available commands are shown.
