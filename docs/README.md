# Duke - User Guide
## 1. Introduction
Duke is for those who prefer to use a desktop app for managing tasks. Duke can make your life so much more manageable by organising your tasks efficiently. Interested? Jump to Section 2, Quick Start to get started. Enjoy!

## 2. Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest duke.jar here.
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Double-click the file to start the app. The GUI should appear in a few seconds.
![Image of Duke] 
(https://github.com/bernicechio/duke/blob/master/docs/Ui.png)
5. Type the command in the command box and press 'Enter' to execute it.
e.g. typing 'todo read book' and pressing 'Enter' will add the todo task read book to your Duke.
6. Some example commands you can try:

* *`list`*: lists all tasks
* *`deadline return book /by 06/06/2006 0606`*: adds a deadline task return book by 06 June 2006 at 0606 hrs.
* *`delete 1`*: deletes the 1st task shown in the current list
* *`bye`*: exits Duke
7. Refer to Section 3, "Features" for details of each command.

## 3. Features 

### Adding a todo task: `todo`
Adds a todo task. +
Format: `todo read book`

### Adding a deadline task: `deadline`
Adds a deadline task. +
Format: `deadline return book /by DD/MM/YYYY hhmm`

### Adding an event task: `event`
Adds an event task. + 
Format: `event project meeting /at DD/MM/YYYY hhmm`

### Marking a task as done: `done` 
Marks the specified task as done. +
Format: `done INDEX`

### Deleting a task: `delete`
Deletes the specified task from Duke. +
Format: `delete INDEX`

### Listing all tasks: `list`
Shows a list of all tasks in Duke. +
Format: `list`

### Tagging a task: `tag`
Tags a task with a specified keyword. A task can have any number of tags (including 0) +
Format: `tag INDEX KEYWORD`

### Finding tasks: `find`
Finds tasks with a related keyword. +
Format: `find KEYWORD`

### Exiting Duke: `bye` 
Exits Duke. +
Format: `bye`

## Usage

### `todo` - Adding a todo task

Adds a todo task to Duke.

Example of usage: 

`todo read book`

Expected outcome:

`Got it. I've added this task:[T][X] read book
 Now you have 1 tasks in the list.`

### `deadline` - Adding a deadline task

Adds a deadline task to Duke.

Example of usage: 

`deadline return book /by 06/06/2006 0606`

Expected outcome:

`Got it. I've added this task:[D][X] return book
 Sun Jun 06 06:06:00 SGT 2006
 Now you have 2 tasks in the list.`
 
### `event` - Adding an event task

Adds an event task to Duke.

Example of usage: 

`event project meeting /at 07/07/2007`

Expected outcome:

`Got it. I've added this task:[E][X] project 
 meeting Sun Jul 07 07:07:00 SGT 2007
 Now you have 3 tasks in the list.`
 
### `done` - Marking a task as done

Marks the specified task as done.

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:
[:heavy_check_mark:] read book`

### `delete` - Deleting a task

Deletes the specified task from Duke.

Example of usage: 

`delete 3`

Expected outcome:

`Noted. I've removed this task:
[E][X] project meeting Sat Jul 07 07:07:00
SGT 2007
Now you have 2 tasks in the list.`

### `list` - Listing all tasks

Shows a list of all tasks in Duke.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:
1. [T][:heavy_check_mark:] read book 
2. [D][X] return book Tue Jun 06 06:06:00 SGT 2006`

### `tag` - Tagging a task

Tags a task with a specified keyword.

Example of usage: 

`tag 1 fun`

Expected outcome:

`Noted. I've tagged this task:[T][:heavy_check_mark:] read 
book#fun
as fun`

### `find` - Finding tasks

Finds tasks with a related keyword.

Example of usage: 

`find book`

Expected outcome:

`Here are the matching tasks in your list:
1. [T][:heavy_check_mark:] read book#fun
2. [D][X] return book Tue Jun 06 06:06:00 SGT 2006`

### `bye` - Exiting Duke

Exits Duke.

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`
