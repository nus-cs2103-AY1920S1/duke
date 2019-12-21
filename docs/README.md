# User Guide

## Features 

### Feature 1 
Create todos, events and deadlines
## Usage
Use the text bar in Duke to run commands - for reference, see the in-app help
list by typing 'help'

### `todo/deadline/event` - Create a task

Type 'todo <name>' to create a simple todo. <br />

Type 'deadline <task> /by <dd/mm/yyyy hhmm>' to create a deadline. <br />

Type 'event <task> /at <dd/mm/yyyy hhmm>' to create an event. <br />


Example of usage: <br />
`todo buy groceries` <br />
`event presentation /at 31/03/2020 1400` <br />
`deadline essay for prof tan /by 31/03/2020 1700` <br />

Expected outcome:

`Got it. I've created this task :
 [t][x] buy groceries` <br />
`Got it. I've created this task :
 [e][x] presentation (at 31/03/2020 1700)` <br />
 `Got it. I've created this task :
  [d][x] essay for prof tan (by 31/03/2020 1700)` <br />
 
### Feature 2
Import tasks from an external file
## Usage
Type `import` or `overwrite` to add tasks from an external file to your current task list, or write over your current task list.
<br /> You can also select these 2 options from a **dropdown menu**.
### `import/overwrite` - Import tasks from an external file

Type `import` to open a file chooser. The tasks from this external file will be added on to your existing tasks. <br />
Type `overwrite` to open a file chooser. The tasks from this file will overwrite your task list.


Example of usage: 

`import`

Expected outcome:

A file chooser will open up. When you select a `*.txt` file that is in the correct format, Duke will tell you `Import from *.txt was successful!`



### Feature 3
Flexible delete commands
## Usage
Type `rmdone` or `deleteAll` or `delete <range>` to remove all tasks marked as done, remove 
all tasks, or delete a specific range of tasks. You can select `Delete all tasks` and `Delete all done tasks` from the **dropdown menu**.

### `rmdone/deleteAll/delete <range>` - Delete all done tasks/delete all tasks/delete tasks in a range



Example of usage: 

`delete 1-4,6-9,12"`

Expected outcome:

`Got it. I've deleted these tasks:` <br />
Duke will delete items 1 through 4, 6 through 9, and 12, and display these tasks.

### Feature 4
Expenses Tracker
## Usage
Click on `Expenses Tracker` under `Other tools` in the menubar to open the interface.
### Add an expense/income item to your list using the +/- buttons



Example of usage: 

Type the name of your expense/income, the amount, and then click `+`/`-` to add the item to the correct column.<br />
The total budget amount will automatically update.

Expected outcome:

The columns will automatically update.



