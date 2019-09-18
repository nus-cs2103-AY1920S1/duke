# User Guide

## Features 

### Feature 1 
Create todos, events and deadlines
## Usage
Use the text bar in Duke to run commands - for reference, see the in-app help
list by typing 'help'

### `todo/deadline/event` - Create a task

Type 'todo <name>' to create a simple todo.
Type 'deadline <task> /by <dd/mm/yyyy hhmm>' to create a deadline.
Type 'event <task> /at <dd/mm/yyyy hhmm>' to create an event.


Example of usage: 

`deadline essay for prof tan /by 31/03/2020 1700`

Expected outcome:

`Got it. I've created this task :
 [d][x] essay for prof tan (by 31/03/2020 1700)
 
### Feature 2
Import tasks from an external file
## Usage
Type `import` or `overwrite` to add tasks from an external file to your current task list, or write over your current task list.

### `import/overwrite` - Import tasks from an external file

Type `import` to open a file chooser. The tasks from this external file will be added on to your existing tasks. 
Type `overwrite` to open a file chooser. The tasks from this file will overwrite your task list.


Example of usage: 

`import`

Expected outcome:

A file chooser will open up.


### Feature 3
Flexible delete commands
## Usage
Type `rmdone` or `deleteAll` or `delete <range>` to remove all tasks marked as done, remove 
all tasks, or delete a specific range of tasks.

### `rmdone/deleteAll/delete <range>` - Import tasks from an external file



Example of usage: 

`delete 1-4,6-9,12"`

Expected outcome:

`Got it. I've deleted these tasks:`
Duke will delete items 1 through 4, 6 through 9, and 12, and display these tasks.




