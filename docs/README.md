# User Guide

## Features 
### Add Task
Adds a task (todo/deadline/event) to your task list.

### Delete Task
Deletes the indicated task from your task list.

### Mark Task As Done
Marks the indicated task as done.

### Edit Task
Edits the description of a specified task.

### Find Task
Finds the tasks containing a specified keyword 


## Usage

### `todo (description)` - Adds a new task.

Adds a todo into the task list.

Example of usage: 

`todo CS2103T User Guide`

Expected outcome:

`Got it. I've added this task:
  [T][✘] CS2103T User Guide
Now you have 1 task in the list.`


### `event (description)/at DD/MM/YYYY HHmm` - Adds a new event.

Adds the event with the specified time into the task list.

Example of usage: 

`event AGM/at 20/09/2019 1830`

Expected outcome:

`Got it. I've added this task:
  [E][✘] AGM (at: 20/09/2019 1830)
Now you have 1 task in the list.`


### `deadline (description)/at DD/MM/YYYY HHmm` - Adds a new deadline.

Adds the deadline with the specified due date into the task list.

Example of usage: 

`deadline find a wife/by 17/07/2090 1200`

Expected outcome:

`Got it. I've added this task:
  [D][✘] find a wife (by: 17/07/2090 1200)
Now you have 1 task in the list.`


### `find` - Searches for tasks.

Returns the list of tasks with the specified keyword.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`


### `done` - Marks a task.

Marks the task with the indicated index as done.

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:
  [✓] lunch`
  
  
### `list` - Displays tasks.

Displays all tasks in the task list.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:
  1.[E][✓] lunch (at 20/09/2019 1200)
  2.[D][✘] find a wife (by: 17/07/2090 1200)`


### `delete` - Deletes a task.

Removes the specified task from the task list.

Example of usage: 

`delete 2`

Expected outcome:

`Noted. I have removed this task:
  [D][✘] find a wife (by: 17/07/2090 1200)
  Now you have 1 task in the list.`
  
### `edit` - Edits a task's descripton.

Updates the description of a specified task.

Example of usage: 

`edit 1 dinner`

Expected outcome:

`Task 1 has been updated to dinner`
