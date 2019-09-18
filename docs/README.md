# User Guide

## Features 

Duke Skywalker is an experienced task manager (not the Windows one) who uses the force to create, modify, delete, and search through different kinds of Tasks. Duke Skywalker emulates a CLI, though it has a GUI.

### Create Tasks 
User can create 3 different kinds of Tasks: `ToDo`, `Deadline`, and `Event`.
`ToDo`: only has description
`Deadline`: has description, date and time
`Event`: has description, date, startTime and endTime

### Mark Task As Done
User can mark a task as done using its 1-index number.

### Delete a Task
User can delete a single task using its 1-index number.

### Delete All Done Tasks
User can delete all the tasks marked as done in the list.

### List All Tasks
User can list all tasks currently available in the list.

## Usage

### `todo` - to add a ToDo to TaskList

Allows user to create a new `Todo` Task with only a description (no date and time).
The format of the command is: `todo <description>`

Example of usage: 

`todo eat lunch`

Expected outcome:

`Got it. I've added this task:
 [T][-] eat lunch
Now you have 1 tasks in this list.`

### `deadline` - to add a Deadline to TaskList

Allows user to create a new `Deadline` Task with a description, date, and time.
The format of the command is: `deadline <description> /by DD/MM/YYYY <time in 24-hour clock format>`

Example of usage:

`deadline sleep /by 18/09/2019 2355`

Expected outcome:

`Got it. I've added this task:
 [D][-] sleep (by: 18th September, 2019. 11:55pm)
Now you have 2 tasks in the list.`

### `event` - to add an Event to TaskList

Allows user to create a new `Event` Task with a description, date, and startTime and endTime to specify a duration.
The format of the command is: `event <description> /at DD/MM/YYYY <startTime in 24-hour clock format>-<endTime in 24-hour clock format>`

Example of usage:

`event eat lunch /at 19/09/2019 1300-1400`

Expected outcome:

`Got it. I've added this task:
 [E][-] eat lunch (at: 19th September, 2019. 1:00pm - 19th September, 2019. 2:00pm)
Now you have 3 tasks in the list.`

### `done` - to mark a Task as done

Allows user to mark a particular task as done and displays the Task that is done.
The format of the command is: `done <1-index of Task to be marked as done>`

Example of usage:

`done 1`

Expected outcome:

`Nice! I've marked this task as done:
 [T][+] eat lunch`

### `list` - to list out all the Tasks

Allows user to list out all the Tasks in the TaskList.
The format of the command is: `list`

Example of usage:

`list`

Expected outcome:

`Here are the tasks in your list:
1. [T][+] eat lunch
2. [D][-] sleep (by: 18th Spetember, 2019. 11:55pm)
3. [E][-] eat lunch (at: 19th September, 2019. 1:00pm - 19th September, 2019. 2:00pm)`

### `delete` - to delete a task from TaskList

Allows user to delete a particular Task from TaskList and displays the Task that is deleted.
The format of the command is: `delete <1-index of the Task to be deleted>`

Example of usage:

`delete 2`

Expected outcome:

`Noted. I've removed this task:
 [D][-] sleep (by: 18th September, 2019. 11:55pm)
Now you have 2 tasks in this list.`

### `deletedone` - to delete all the Tasks that are done

Allows users to delete all tasks marked as done in a single command and prints the Tasks deleted.
The format of the command is: `deletedone`

Example of usage:

`deletedone`

Expected outcome:

`I have removed all the tasks that were done. Here are the removed tasks:
1. [T][+] eat lunch
Now you have 1 tasks left in the list.`

### `find` - to filter the list of Tasks using a String
 
Allows users to filter out tasks; it only prints out the tasks that have the given String in their description.
The format of the command is: `find <filtering String>`

Example of usage:

`find lunch`

Expected outcome:

`Here are the tasks with 'lunch' in their description:
1. [E][-] eat lunch (at: 19th September, 2019. 1:00pm - 19th September, 2019. 2:00pm)`

### `bye` - bids adieu to the user

Displays a goodbye message and closes the program.

Example of usage:

`bye`

Expected outcome:

`Bye. Hope to see you again soon!
May The Force Be With You!`
