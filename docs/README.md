# User Guide for Duke GUI
#### By Dalson Tan

## Features 
### Feature 1: Adding Tasks
Can add various tasks onto task list
### Feature 2: Check current task list
Able to check all your list entries and their status (complete or incomplete)
### Feature 3: Delete, Mark a list entry as Done
Able to manipulate the entries on the list, marking an entry as completed, or delete the entire entry
### Feature 4: Find list entries
Use a keyword to find a particular list entry
### Feature 5: Reminder for active deadlines and events
Able to get a list of upcoming dates to take note of

## Usage

### `todo` - Add a to do task 

Will add a todo task, without a date or time for completion.

Example of usage: 

`todo read a book`

Expected outcome:

`read a book` added as a todo task

### `event` - Add an event task 

Will add a event task, with a date or time of when the event is held.

Example of usage: 

`event read a book /at 2nd Feb`

Expected outcome:

`read a book (at: 2nd Feb)` added as a event task

### `deadline` - Add a deadline task 

Will add a deadline task, with a date or time to complete this task by.

Example of usage: 

`deadline read a book /by 2nd Feb`

Expected outcome:

`read a book (by: 2nd Feb)`` added as a deadline task

### `delete` - Add an event task 

Delete a list entry, using the index of the entries

Example of usage: 

`delete 2`

Expected outcome:

`2. read a book (at: 2nd Feb)` will be deleted from the task list.

