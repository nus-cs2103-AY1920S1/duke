# User Guide

## Features 
1. Add tasks such as todos, events or deadlines to Duke.
1. Mark tasks as completed
1. Undo feature
1. Date parsing for events and deadlines

## Usage

### `todo <description>` - Adds a Todo

Adds a Todo to Duke.

Example of usage: 

`todo sample task`

Expected outcome:

<img src="https://puu.sh/EhtFk.png" width="400" height="650">

### `event <description> /at <date>` - Adds an Event at `<date>`

Adds an event at the date provided to Duke.

Example of usage: 

`event cs2103t lecture /at 20/09/2019 1600`

Expected outcome:

<img src="https://puu.sh/EhtHZ.png" width="400" height="650">

### `deadline <description> /by <date>` - Adds a Deadline by `<date>`

Adds a deadline by the date to Duke.

Example of usage: 

`deadline assignment /by 21/09/2019 2359`

Expected outcome:

<img src="https://puu.sh/EhtIY.png" width="400" height="650">

### `list` - List all tasks

Lists all tasks in Duke.

Example of usage: 

`list`

Expected outcome:

<img src="https://puu.sh/EhtJX.png" width="400" height="650">

### `done <task number>` - Mark as done

Marks the task with the current task number as done.

Example of usage: 

`done 5`

Expected outcome:

<img src="https://puu.sh/EhtKY.png" width="400" height="650">

### `undo <steps to undo>` - Undo

Undo the number of steps given.

Example of usage: 

`undo 1`

Expected outcome:

<img src="https://puu.sh/EhtOZ.png" width="400" height="1150">
