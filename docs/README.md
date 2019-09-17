# User Guide

## Features 

### Basic CRUD Functions

1. Create `Task` of type `Todo`, `Event` or `Deadline`.
2. View list of tasks.
3. Mark a `Task` as done.
4. Mark a `Task` as not done.
5. Delete a `Task`.

### Feature 1 : Find Tasks

Tasks can be filtered by `description`.

### Feature 2 : Sort Tasks

Tasks can be sorted by `description`, `taskType` or `doneStatus`.

### Feature 3 : Clear Tasks

Tasks can be cleared to give an empty task list.

## Usage

### `list` - List all tasks

Shows a list of all tasks.

Example of usage: 

`list`

Expected outcome:

`list all tasks`

### `todo` - Create A New Todo

Create a new todo with description.

Example of usage: 

`todo read book`

Expected outcome:

`todo of description 'read book' created`

### `deadline` - Create A New Deadline

Create a new deadline with description and date.

Example of usage: 

`deadline return book /by 10/10/2019 1200`

Expected outcome:

`deadline created with description 'return book' and date '10/10/2019 1200'`

### `event` - List all tasks

Create a new event with description, start date and end date.
Leave two spaces between the start date and end date.

Example of usage: 

`event project meeting /at 10/10/2019 1200  10/10/2019 1400`

Expected outcome:

`event created with description 'project meeting', start date '10/10/2019 1200' 
and end date '10/10/2019 1400'`

### `done` - Mark Task As Done

Mark a task as done by its index.

Example of usage: 

`done 2`

Expected outcome:

`task at index 2 marked as done`

### `notdone` - Mark Task As Not Done

Mark a task as not done by its index.

Example of usage: 

`notdone 2`

Expected outcome:

`task at index 2 marked as not done`

### `delete` - Delete Task

Shows a list of all tasks.

Example of usage: 

`delete 2`

Expected outcome:

`task at index 2 deleted`

### `find` - Find Tasks

Filter tasks by `description`.

Example of usage: 

`find book`

Expected outcome:

`list tasks that has 'book' in its description`

### `sort` - Sort Tasks

Sort tasks by `description`, `taskType` or `doneStatus`.

Example of usage: 

`sort desc`, `sort tasktype`, `sort done`

Expected outcome:

`sorted list of task`

### `clear` - Clear All Tasks

Clear the task list.

Example of usage: 

`clear`

Expected outcome:

`empty task list`