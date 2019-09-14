# User Guide for Duke

---

# Table Of Contents

- [Features](#features)
	- [Places](#places)
	- [Task List](#task-list)
- [Usage](#usage)
	- [`todo <description>` - Adds a TODO](#todo-description---adds-a-todo)
	- [---](#---)
	- [`event <description> /at <datetime>` - Adds an event](#event-description-at-datetime---adds-an-event)
	- [---](#----1)
	- [`deadline <description> /by <datetime>` - Adds a deadline](#deadline-description-by-datetime---adds-a-deadline)
	- [---](#----2)
	- [`list` - Displays Task List](#list---displays-task-list)
	- [---](#----3)
	- [`done <index>` - Sets task at index of Task List as Done](#done-index---sets-task-at-index-of-task-list-as-done)
	- [---](#----4)
	- [`delete <index>` - Deletes task at index from Task List](#delete-index---deletes-task-at-index-from-task-list)
	- [---](#----5)
	- [`find <text>` - Find all Tasks containing given text](#find-text---find-all-tasks-containing-given-text)
	- [---](#----6)
	- [`place <alias>|<latitude longitude>` - Sets a Place with given alias and coordinates](#place-aliaslatitude-longitude---sets-a-place-with-given-alias-and-coordinates)
	- [---](#----7)
	- [`alias <alias>|<latitude longitude>` - Displays aliases of an alias or coordinate](#alias-aliaslatitude-longitude---displays-aliases-of-an-alias-or-coordinate)


# Features 

---

## Places

Manages and saves your places using aliases and coordinates.

## Task List

Manages and saves your schedule with Task such as TODO, Event and Deadline.

![](Ui.png "Ui in action")

# Usage

---
---

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

---
---

## `place <alias>|<latitude longitude>` - Sets a Place with given alias and coordinates

Sets a Place with given alias and coordinates, then updates Storage.

Example of usage: 

`place home 1 2`

Expected outcome:

```
Noted. I've recorded is at 1.0, 2.0.
```

---
---

## `alias <alias>|<latitude longitude>` - Displays aliases of an alias or coordinate

Sets a Place with given alias and coordinates, then updates Storage.

Example of usage: 

```
place krMRT 1 2

place kentridgeMRT 1 2

alias 1 2

alias KRmrt
```

Expected outcome:

```
Noted. I've recorded krMRT is at 1.0, 2.0.

Noted. I've recorded krMRT is at 1.0, 2.0.

Alias found:
krMRT
kentridgeMRT

Alias found:
krMRT
kentridgeMRT
```