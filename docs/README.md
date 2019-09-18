# User Guide

#Introduction
Duke is created for simple and easy management of tasks, using text commands in the command line. It supports various tasks like DoAfters, Deadlines, Events and ToDos and tracks them by displaying a neat overview of all tasks in a Graphical User Interface (GUI).

## Features

### Bye
Exits Duke.

## Usage

### `bye` - Describe action

Saves all current Data and exits Duke.

Example of usage:

`bye`

### DoAfter
Adds a DoAfter task to Duke.

## Usage

### `doafter NAME_OF_DOAFTER /after DATE_TIME` - Describe action

Add do after with DATE_TIME (format DD/MM/YYYY HH/MM) to Duke.

Example of usage:

`doafter Feed Bruno /after 18/09/2019 1800`

### Done
Marks a task as done in Duke.

## Usage

### `done INDEX_OF_TASK` - Describe action

Marks a task at index INDEX_OF_TASK as Done in Duke.

Example of usage:

`done 2`

### Deadline
Adds a Deadline task to Duke.

## Usage

### `deadline NAME_OF_DEADLINE /by DATE_TIME` - Describe action

Add deadline with DATE_TIME (format DD/MM/YYYY HH/MM) to Duke.

Example of usage:

`deadline CS2103 Post Lecture Quiz /by 19/09/2019 2359`

### Event
Adds an Event task to Duke.

## Usage

### `event NAME_OF_EVENT /at DATE_TIME` - Describe action

Add event with DATE_TIME (format DD/MM/YYYY HH/MM) to Duke.

Example of usage:

`event Rachel's birthday /at 04/07/2019 0000`

### Find
Finds a task in Duke.

## Usage

### `find string` - Describe action

Finds all occurrences of string in tasks of Duke and prints them out.

Example of usage:

`find CS2103`

### List
Prints out all current tasks in Duke.

## Usage

### `list` - Describe action

Prints out all current tasks in Duke.

Example of usage:

`list`

### ToDo
Adds an ToDo task to Duke.

## Usage

### `event NAME_OF_TODO` - Describe action

Add ToDo to Duke.

Example of usage:

`todo Finish Kingdom Hearts 3 DLC`
