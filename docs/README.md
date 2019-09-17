# Project Duke - User Guide
| By: `Voong Yu Xuan` | 

---

# Table of Contents 
   1. [Introduction](#Introduction)
   2. [Features](#features)
        - [`todo <description>` - Adds a Todo](#todo-description---adds-a-todo)
        - [`event <description> /at <datetime>` - Adds an event](#event-description-at-datetime---adds-an-event)
        - [`deadline <description> /by <datetime>` - Adds a deadline](#deadline-description-by-datetime---adds-a-deadline)
        - [`list` - Displays Task List](#list---displays-tasklist)
        - [`done <index>` - Sets task in Task List as Done](#done---sets-task-at-index-of-task-list-as-done)
        - [`delete <index>` - Deletes task from Task List](#delete---deletes-task-at-index-from-task-list)
        - [`find <text>` - Finds task matching text](#find-text---find tasks matching text)
        - [`undo` - Undoes task](#undo)
        - [`bye` - Greets user farewell](#bye)


# Introduction
Duke is a chatbot that helps a person to keep track of various tasks. 
Duke loads its data on startup from a local save file and saves the current task
list to the save file when the user exits the app using the `bye` command.

Below is a screenshot of Duke in use.
![](Ui.png "Project Duke UI")

# Features 

---

## `todo <description>` - Adds a Todo

### Adds a TODO to the current tasklist.

Example of usage: 

`todo report`

Expected outcome:

```
Got it. I've added this task:
  [T][✘] report
Now you have <numberOfTasks> tasks in the list.
```

---
---

## `event <description> /at <datetime>` - Adds an event

### Adds an event to the tasklist.

Example of usage: 

`event workshop /at 03/09/2019 1300`

Expected outcome:

```
Got it. I've added this task:
  [E][✘] workshop (at: 3 Sep 2019, 1:00:00 PM)
Now you have <numberOfTasks> tasks in the list.
```

---

## `deadline <description> /by <datetime>` - Adds a deadline

### Adds a deadline to the tasklist.

Example of usage: 

`deadline cleaning /by 03/09/2019 1300`

Expected outcome:

```
Got it. I've added this task:
  [D][✘] cleaning (at: 3 Sep 2019, 1:00:00 PM)
Now you have <numberOfTasks> tasks in the list.
```

---

## `list` - Displays tasklist

### Displays the current tasklist.

Example of usage: 

`list`

Expected outcome (e.g after executing [example todo](#todo-description---adds-a-todo) on an empty tasklist):

```
Here are the tasks in your list:
1.[D][✘] report

```

---

## `done <index>` - Sets task in Task List as Done

### Sets task at index of Task List as Done.

Example of usage: 

`done 1`

Expected outcome (e.g after executing [example todo](#todo-description---adds-a-todo) on empty Task List):

```
Nice! I've marked this task as done:
[T][✓] report
```

---

## `delete <index>` - Deletes task from tasklist

### Deletes task from Task List at the given index.

Example of usage: 

`delete 1`

Expected outcome (e.g after executing [example todo](#todo-description---adds-a-todo)):

```
Noted. I've removed this task:
  [T][✘] report
Now you have 0 tasks in the list.
```

---

## `find <text>` - Find all Tasks containing given text

### Find all Tasks containing given text.

Example of usage: 

`find report`

Expected outcome (e.g after executing [example todo](#todo-description---adds-a-todo)):

```
Here are the matching tasks in your list:
1.[T][✘] report
```

---

## `undo` - Undoes task]
### Undoes last executed task

Example of usage: 

`undo`

Expected outcome (e.g after executing [example todo](#todo-description---adds-a-todo)):

```
This command was undone: AddCommand
```

## `bye` - Greets user farewell  
### Says farewell to the user and saves the current tasklist to the local save file.
Example of usage: 

`bye`

Expected outcome:

```
Bye <3 Hope to see you again soon!


