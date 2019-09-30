# A Simple User Guide

## Features 

### Feature 1 - List

## Usage

### `list`

Prints the list containing all tasks.

Example of usage: 

`list`

Expected outcome:

`1.[T][+] test`

### Feature 2 - Todo

## Usage

### `todo`

Adds a todo task to the list, a todo has no specific date or time.

Example of usage: 

`todo test`

Expected outcome:

`Got it. I've added this task:`

`[T][-] test`

`Now you have 1 task in the list.`


### Feature 3 - Deadline

## Usage

### `deadline`

Adds a deadline task to the list, a deadline has a specific due date and time.

Example of usage: 

`deadline return book /by 2/12/2019 1800`

Expected outcome:

`Got it. I've added this task:`

`[D][-] return book (by: 2/12/2019 1800)`

`Now you have 2 tasks in the list.`


### Feature 4 - Event

## Usage

### `event`

Adds an event task to the list, a event will take place at specific date and time.

Example of usage: 

`event project meeting /at 12/12/2019 1800`

Expected outcome:

`Got it. I've added this task:`

`[E][-] project meeting (at: 12/12/2019 1800)`

`Now you have 3 tasks in the list.`


### Feature 5 - Done

## Usage

### `done`

Marks the task at the specified index as completed.

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`

`[T][+] test`


### Feature 6 - Find

## Usage

### `find`

Finds any tasks containing the given keyword.

Example of usage: 

`find book`

Expected outcome:

`Here are the matching tasks in your list:`

`1.[D][-] return book (by: 2/12/2019 1800)`


### Feature 7 - Delete

## Usage

### `delete`

Removes the task at the specified index from the list.

Example of usage: 

`delete 3`

Expected outcome:

`Noted. I've removed this task:`

`[E][-] project meeting (at: 12/12/2019 1800)`

`Now you have 2 tasks in the list.`


### Feature 8 - Bye

## Usage

### `bye`

Exits the program.

Example of usage: 

`bye`


### Feature 9 - Help

## Usage

### `help`

Provides a summary of available commands to the user.

Example of usage: 

`help`

Expected outcome:

`Welcome to Help Page`
...
