# User Guide

## Features 

### Feature 1 
Show you your current list of tasks.

## Usage

### `list` - Display current list of tasks.

Example of usage: 

`list`

### Feature 2
Add a task into the list of tasks. Dates and times are to be input as the following format:
DD/MM/YYYY HHMM

## Usage

### `todo [description]` - Adds a todo task into the list with the description.

### `event [description] /at [date time]` - Adds an event task into the list with the description, date and time.
 
### deadline [description] /by [date time]` - Adds a deadline task into the list with the description, date and time.

Example of usage: 

`todo read book`

`event project meeting /at 20/12/2019 1600`

`deadline homework /by 15/10/2019 2359`

### Feature 3
Deletes a task from the list of tasks.

## Usage

### `delete [index]` - Removes a task from the list at the given index.

Example of usage:

`delete 2` - Deletes the 2nd task in the list.

### Feature 4
Find a task from the list of tasks with a given keyword.

## Usage

### `find [keyword]` - Displays a list of tasks with the given keyword from the current list of tasks.

Examples of usage:

`find book` - Returns a list of tasks with the word 'book'.

### Feature 4
Mark a task as done.

##Usage

### `done [index]` - Changes the cross beside the description to a tick of the task at the given index.


Examples of usage:

`done 1` - Marks the first task in the current list of tasks as done.

### Feature 5
Update the description or date and time of a task. Dates and times are to be input as the following format:
DD/MM/YYYY HHMM


##Usage

### `update [description/date] of [task description] /to [new description/date time]`

Examples of usage:

`update description of borrow book /to borrow a Chinese book`

`update date of project meeting /to 12/12/2012 1200`
