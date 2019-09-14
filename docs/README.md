# User Guide for Duke
# Table Of Contents

- [Features](#features)
	- [Task List](#task-list)
- [Usage](#usage)
	- [`todo <description>` - Adds a TODO](#todo-description---adds-a-todo)
	- [`event <description> /at <datetime>` - Adds an event](#event-description-at-datetime---adds-an-event)
	- [`deadline <description> /by <datetime>` - Adds a deadline](#deadline-description-by-datetime---adds-a-deadline)
	- [`list` - Displays Task List](#list---displays-task-list)
	- [`done <index>` - Sets task at index of Task List as Done](#done-index---sets-task-at-index-of-task-list-as-done)
	- [`delete <index>` - Deletes task at index from Task List](#delete-index---deletes-task-at-index-from-task-list)
	- [`find <text>` - Find all Tasks containing given text](#find-text---find-all-tasks-containing-given-text)

# Features 

## Task List

Manages and saves your schedule with Task such as TODO, Event and Deadline.

![](Ui.png "Ui in action")

# Usage

## `todo <description>` - Adds a TODO

Adds a TODO then updates Storage.

Example of usage: 

`todo meet friend`

Expected outcome:

```
Got it. I've added this task:
  [T][✘] borrow book
Now you have <numberOfTasks> tasks in the list.
```

---
---

## `event <description> /at <datetime>` - Adds an event

Adds an event then updates Storage.

Example of usage: 

`event Music Festival /at 8dec`

Expected outcome:

```
Got it. I've added this task:
  [E][✘] Music Festival (at: December 8 <systemYear>, <systemTime>)
Now you have <numberOfTasks> tasks in the list.
```

---
---

## `deadline <description> /by <datetime>` - Adds a deadline

Adds a deadline then updates Storage.

Example of usage: 

`deadline CS3243 | Assignment /by oct9t1234`

Expected outcome:

```
Got it. I've added this task:
  [D][✘] CS3243 | Assignment (at: October 9 <systemYear>, 12:34PM)
Now you have <numberOfTasks> tasks in the list.
```

---
---

## `list` - Displays Task List

Displays Task List.

Example of usage: 

`list`

Expected outcome (e.g after executing [deadline example](#deadline-description-by-datetime---adds-a-deadline) on empty Task List):

```
Here are the tasks in your list:
1.[D][✘] CS3243 | Assignment (at: October 9 <systemYear>, 12:34PM)
```

---
---

## `done <index>` - Sets task at index of Task List as Done

Sets task at index of Task List as Done, then updates Storage.

Example of usage: 

`done 1`

Expected outcome (e.g after executing [deadline example](#deadline-description-by-datetime---adds-a-deadline) on empty Task List):

```
Nice! I've marked this task as done:
[D][✓] CS3243 | Assignment (at: October 9 <systemYear>, 12:34PM)
```

---
---

## `delete <index>` - Deletes task at index from Task List

Deletes task at index from Task List, then updates Storage.

Example of usage: 

`delete 1`

Expected outcome (e.g after executing [deadline example](#deadline-description-by-datetime---adds-a-deadline) on empty Task List):

```
Noted. I've removed this task:
  [D][✘] CS3243 | Assignment (at: October 9 <systemYear>, 12:34PM)
Now you have 0 tasks in the list.
```

---
---

## `find <text>` - Find all Tasks containing given text

Find all Tasks containing given text.

Example of usage: 

`find Oct`

Expected outcome (e.g after executing [deadline example](#deadline-description-by-datetime---adds-a-deadline) on empty Task List):

```
Here are the matching tasks in your list:
1.[D][✘] CS3243 | Assignment (at: October 9 <systemYear>, 12:34PM)
```