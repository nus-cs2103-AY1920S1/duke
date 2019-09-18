# User Guide

**Duke** is created for simple and easy **management of tasks**, using text commands in the command line. It supports various tasks like **DoAfters, Deadlines, Events** and **ToDos** and tracks them by displaying a neat overview of all tasks in a Graphical User Interface (GUI).

![User Interface](/docs/Ui.png)

## Features

### Bye
**Exits** Duke.

#### Usage

#### `bye`

Saves all current data and **exits** Duke.

Example of usage:

`bye`

### DoAfter
Adds a **DoAfter** task to Duke.

#### Usage

#### `doafter NAME_OF_DOAFTER /after DATE_TIME`

Add **DoAfter** task with *DATE_TIME* (format DD/MM/YYYY HH/MM) to Duke.

Example of usage:

`doafter Feed Bruno /after 18/09/2019 1800`

### Done
Marks a task as **done** in Duke.

#### Usage

#### `done INDEX_OF_TASK`

Marks a task at index *INDEX_OF_TASK* as **done** in Duke.

Example of usage:

`done 2`

### Deadline
Adds a **Deadline** task to Duke.

#### Usage

#### `deadline NAME_OF_DEADLINE /by DATE_TIME`

Add **Deadline** with *DATE_TIME* (format DD/MM/YYYY HH/MM) to Duke.

Example of usage:

`deadline CS2103 Post Lecture Quiz /by 19/09/2019 2359`

### Delete
**Deletes** a task from Duke.

#### Usage

#### `delete INDEX_OF_TASK`

**Deletes** task at *INDEX_OF_TASK* in Duke.

Example of usage:

`delete 4`

### Event
Adds an **Event** task to Duke.

#### Usage

#### `event NAME_OF_EVENT /at DATE_TIME`

Add **Event** with *DATE_TIME* (format DD/MM/YYYY HH/MM) to Duke.

Example of usage:

`event Rachel's birthday /at 04/07/2019 0000`

### Find
**Finds** a task in Duke.

#### Usage

#### `find STRING`

**Finds** all occurrences of *STRING* in tasks of Duke and prints them out.

Example of usage:

`find CS2103`

### List
**Prints** out all tasks in Duke.

#### Usage

#### `list`

**Prints** out all tasks in Duke.

Example of usage:

`list`

### ToDo
Adds an **ToDo** task to Duke.

#### Usage

#### `event NAME_OF_TODO`

Add **ToDo** to Duke.

Example of usage:

`todo Finish Kingdom Hearts 3 DLC`
