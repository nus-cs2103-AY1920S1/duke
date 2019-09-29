# User Guide

## Was ist das?
This is a todo/deadline management system.
![screenshot](Ui.png)

### Feature 1 
Manage your tasks very well! You can add these tasks:

- To Dos
- Deadlines
- Events

Wow! Never forget anything again!

## Usage

### `todo <name>`

Example of usage: 

`todo Cook dinner for lazy kids`

Expected outcome:
Adds an todo to your list with whatever name you gave it!

`[T][X] Cook dinner for lazy kids`

### `deadline <name> /by <date>`

Example of usage: 

`deadline Cook dinner for lazy kids /by 29/09/2019 2359`

Expected outcome:
Adds an deadline to your list with whatever name and time you gave it!

`[D][X] Cook dinner for lazy kids (by: 29 Sep 2019, 23:59 PM)`

### `event <name> /at <date>`

Example of usage: 

`event Dinner for lazy kids /at 29/09/2019 2359`

Expected outcome:
Adds an event to your list with whatever name and time you gave it!

`[E][X] Cook dinner for lazy kids (at: 29 Sep 2019, 23:59 PM)`

### `help` 

Lists help page.

### `list`

Lists all tasks in your list.

### `delete <number>`

Deletes task number `<number>` as shown on the `list` page.

### `done <number>`

Marks task number `<number>` (as shown on the `list` page) as done.

### `search <text>`

Searches for tasks containing `<text>` in their descriptions and prints them
out.
