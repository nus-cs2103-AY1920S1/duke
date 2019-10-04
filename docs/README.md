# User Guide

## Features 

### Feature 1: "Create Tasks"
Able to create tasks to be added to a task list. Supported tasks are TODO, DEADLINE and EVENT tasks.

## Usage - a (TODO)

### `todo [name]` - Creates a TODO Task with a specified name.

Example of usage: 

`todo buy groceries`

Expected outcome:

`Got it. I've added this task: 
    [T][✗] buy groceries
Now you have 1 tasks in the list.`

## Usage - b (DEADLINE)

### `deadline [name] [date]` - Creates a DEADLINE task with a specified name and date.

Example of usage: 

`deadline groceries expire /by 11/11/2019 1800`

date of the format: '/by dd/MM/yyyy HHmm'

Expected outcome:

`Got it. I've added this task: 
     [D][✗] groceries expire (by 11 Nov 2019, 0600PM)
 Now you have 2 tasks in the list.`

## Usage - c (EVENT)

### `event [name] [date]` - Creates a EVENT task with a specified name and date.

Example of usage: 

`event cook groceries /at 11/11/2019 1800`

date of the format: '/at dd/MM/yyyy HHmm'

Expected outcome:

`Got it. I've added this task: 
     [E][✗] cook groceries (by 11 Nov 2019, 0600PM)
 Now you have 3 tasks in the list.`

### Feature 2: "List Tasks" 
Lists out all the current tasks in the task list.

## Usage

### `list` - Lists out all the tasks in order of addition to the list.

Example of usage: 

`list`

Expected outcome:

`1. [T][✗] buy groceries
2. [D][✗] groceries expire (by 11 Nov 2019, 0600PM)
3. [E][✗] cook groceries (by 11 Nov 2019, 0600PM)`



### Feature 3: "Mark a Task as done"
Marks a task in the task list as DONE.

## Usage

### `done [integer]` - Marks the task in the task list with the specified index as done (index is 1-based).

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done: 
 [T][✓] buy groceries`

### Feature 4: "Delete a Task" 
Deletes a task from the task list (regardless of whether the task is done).

## Usage

### `delete [integer]` - Deletes the task in the task list with the specified index(index is 1-based).

Describe action and its outcome.

Example of usage: 

`delete 1`

Expected outcome:

` Noted. I've removed this task: 
 [T][✓] buy groceries
 Now you have 2 tasks in the list.`

### Feature 5: "Find a task" 
Finds a task in the task list by searching for a keyword.

## Usage

### `find [keyword]` - Finds a task in the task list by searching for a keyword.

Example of usage: 

`find groceries`

Expected outcome:

`     Here are the matching tasks in your list: 
      1.[D][✗] groceries expire (by 11 Nov 2019, 0600PM)
      2.[E][✗] cook groceries (by 11 Nov 2019, 0600PM)
`

### Feature 6: "Undo"
Undos the creation of the last task in the task list.

## Usage

### `Undo` - Undos the creation of the last task in the task list.

Example of usage: 

`undo`

Expected outcome:

`The last created task has been deleted: 
[E][✗] cook groceries (by 11 Nov 2019, 0600PM)
`