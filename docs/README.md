# User Guide

Duke is a commandline based todolist application.

## Features 

### Feature 1
You can add/remove tasks

### Feature 2
You can mark a task as done

### Feature 3
You can use shortened versions of the commands to add new tasks.
The commands also ignore case sensitivity.

### Feature 4
Find tasks whose name contains a substring.

## Usage

### `clear` - Describe action

clears the chat screen

### `list` - Lists all tasks in the program

displays tasks

### `todo` - Adds a todo task

Example of usage: 

`todo newTask`

### `deadline` - Adds a deadline task

Example of usage: 

`deadline newDeadline \by 1/1/2019 0000`

### `event` - Adds a event task

Example of usage:

`event newEvent \at 1/1/2019 1300 to 1/1/2019 1400`

### `done` - Mark a task as done

argument has to be task index, check index by calling `list`.

Example of usage:

`done 1`

### `delete` - Delete a task

argument has to be task index, check index by calling `list`.

Example of usage:

`delete 1`

### `find` - Finds tasks containing substring

argument is the substring

Example of usage

`find asd`