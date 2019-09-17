# Guide to using Duke


## Table of Contents

 - [Introduction](#introduction)
    - [Duke](#duke)
    - [Features](#features)
- [Commands in Duke](#commands-in-duke)
    - [`list`](#list)
    - [`todo <name>` ](#todo-name)
    - [`event <Description>' /at <Date in DD/MM/YYYY> <Time in HHMM>` ](#event-description-at-date-in-ddmmyyyy-time-in-hhmm)
    - [`deadline <Description> /by <Date in DD/MM/YYYY> <Time in HHMM>`](#deadline-description-by-date-in-ddmmyyyy-time-in-hhmm)
    - [`done <task index>`](done-task-index)  
    - [`delete <task index>`](#delete-task-index)
    - [`find <name>`](#find-name)
    - [`bye` ](#bye)
  - [Acknowledgements](#acknowledgements)

## Introduction
Welcome to Duke's friendly user guide. Here we will discuss the features available in Duke, as well as how to use Duke's full functionality.

### Duke
 **Duke** is a friendly chatbot TodoList application that tracks down your tasks for you.
![Image of Duke](docs\Ui.png)
 
### Features
List of features
 1. Differentiates your task by deadline, event and todo.
 2. Spellcheck capabilities.
 3. Simple chatbox interface.
 4. Search functionality.
 5. Typing based.

How Duke works revolves around **commands**. Each command handles a different aspect of Duke. Entering the command name into the text box works as the command for Duke. Knowing the different commands and typing them is essential to fully utilizing Duke.

## Commands in Duke

### `list`   
- Shows all saved tasks. This command will be used most often and it handles showing all the current task in your list.

**Usage Example:**

`list`

**Expected outcome:**

```
Here are the tasks in your list:

1. [T][✘] Complete Geography assignment
2. [T][✘] Buy milk
```

---

### `todo <name>` 
- Adds a Todo task into your saved list. This command adds a Todo task marked by the `T` signifier. A Todo task lacks any time or date attached.

**Usage Example:** 

`todo finish Neon Genesis Evangelion`

**Expected outcome:**

```
Got it. I've added this task:
    [T][✘] finish Neon Genesis Evangelion
Now you have 3 tasks in the list.
```
---

### `deadline <name> /by <Date in DD/MM/YYYY> <Time in HHMM>`

- This command adds a deadline task with the time attached as the  deadline into the saved tasks. Deadlines tasks are marked by a `D` signifier. Date and time must follow the DD/MM/YYYY and HHMM format.

**Usage example:** 

`deadline finish game development /by 29/09/2020 1100`

**Expected outcome:**

```
Got it. I've added this task:
    [D][✘] finish game development (by: 29 September 2020, 11:00 AM)
Now you have 4 tasks in the list.
```

---

### `event <name> /at <Date in DD/MM/YYYY> <Time in HHMM>` 
- This command adds an event with the time attached as the date of the event. Event tasks are marked by a `E` signifier. Date and time must follow the DD/MM/YYYY and HHMM format.

**Usage example:**

`event party at work /at 30/01/2019 1800`

**Expected outcome:**

```
Got it. I've added this task:
    [E][✘] party at work (at: 30 January 2019, 6:00PM)
Now you have 5 tasks in the list.
```
---

### `done <task index>` 
- Marks a task as completed. The index number must match the index number given by the `list` command.

**Usage example:** 

`done 1`

**Expected outcome:**

```
Nice! I've marked this task as done:
    [T][✓] complete Mario Odyssey
```

---

### `delete <task index>` 
- Deletes a task specified by the index given. The particular index given must match the task index given by the `list` command.

**Usage example:**

`delete 1`

**Expected outcome:**

```
Noted. I've removed this task:
    [T][✓] read Sapiens
Now you have 4 tasks in the list.
```
---

### `find <name>` 
- Finds all tasks with the name listed. 

**Usage example:**

`find meaning`

**Expected outcome:**

```
Here are the matching tasks in your list:

1. [D][✘] search for the meaning of life (by: 31 December 9999, 11:59PM)
```

---

### `bye` 
- Exits Duke, closing the application.

**Usage example:** 

`bye`

**Expected outcome:**
```
Bye. Hope to see you again soon!
```

## Acknowledgements
- Me
- Myself
- I
- Adithyajoshi5 in his blog for the led computation algorithm

Expected outcome:

```
Bye. Hope to see you again soon!
```
---