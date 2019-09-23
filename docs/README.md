# Virgil User Guide

## Features 

### Feature 1 
Creates a ToDo Task in tasks list.

## Usage

### `todo` - Adds a ToDo Task.

Todo creates a todo task from the following String and adds it into the TaskList.

Example of usage: 

`todo Do Laundry`

Expected outcome:

`Got it. I've added this task:
  [T][X] Do Laundry
Now you have X tasks in this list.`

### Feature 2
Creates a Deadline Task in tasks list.

## Usage

### `deadline` - Adds a Deadline Task.

Deadline creates a deadline task from the following String with a by date into the TaskList.

Example of usage: 

`deadline Do Laundry /by 20/09/2019 2300`

Expected outcome:

`Got it. I've added this task:
  [D][X] Do Laundry (by: 20th of September 2019, 11.00pm)
Now you have X tasks in this list.`

### Feature 3
Creates an Event Task in tasks list.

## Usage

### `event` - Adds an Event Task.

Event creates an event task from the following String with an at time into the TaskList.

Example of usage: 

`event Do Laundry /at 20/09/2019 2300`

Expected outcome:

`Got it. I've added this task:
  [E][X] Do Laundry (at: 20th of September 2019, 11.00pm)
Now you have 2 tasks in this list.`

### Feature 4
Shows all the tasks in the list

## Usage

### `list` - Lists all tasks in task list.

Prints all the tasks in the tasklist.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:
1. [T][✓] Read a book
2. [D][x] Do Laundry (by: 20 of September 2019, 11.00pm)
3. [E][x] Gaming Convention (at: 20 of September 2019, 12.00pm)`

### Feature 5
Mark a task as completed.

## Usage

### `done` - Marks a task as done.

Find and marks the task at the provided index as completed.

Example of usage: 

`done 2`

Expected outcome:

`Nice! I've marked this task as done:
  [E][✓] College Welcome Tea (at: 18th of September 2019, 6.00pm)`

### Feature 6
Deletes a task from the task list.

## Usage

### `delete` - Deletes task from the task list.

Find and deletes the task at the provided index from the task list.

Example of usage: 

`delete 1`

Expected outcome:

`Noted. I've removed this task:
  [E][✓] College Welcome Tea (at: 18th of September 2019, 6.00pm)
 Now you have 2 tasks in the list.`

### Feature 7
Find tasks from the task list with a specified keyword.

## Usage

### `find` - Finds tasks with the keyword.

Find and prints a list of tasks the task list that contains the specified keyword.

Example of usage: 

`find College`

Expected outcome:

`Here are the tasks in your list:
  [E][✓] College Welcome Tea (at: 18th of September 2019, 6.00pm)
  [T][X] Move out of Tembusu College`

### Feature 8
Changes the timing of a deadline or event task to a new one.

## Usage

### `snooze` - Changes timing of a timed task.

Finds a deadline or event of a specified index in the task list and changes its timing.

Example of usage: 

`snooze 1 18/09/2019 2000`

Expected outcome:

`Nice! I've changed the timing of this task:
  [E][✓] College Welcome Tea (at: 18th of September 2019, 8.00pm)`

### Feature 9
Prints a goodbye message and closes Virgil.

## Usage

### `bye` - Ends the program.

Prints a goodbye message and closes the Virgil chatbot.

Example of usage: 

`bye`

Expected outcome:

`Bye! Hope to see you again soon!`

### Feature 10
Prints a guide on the available commands of Virgil

## Usage

### `help` - Prints a user guide

Prints a user guide explaining how to use Virgil as well as all the commands
available.

Example of usage: 

`help`

Expected outcome:

`Bye! Hope to see you again soon!`





