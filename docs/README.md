# User Guide

## Features 

### `todo` - Creates a Todo task

Example of usage: 

`todo borrow book`

Expected outcome:

`Got it. I've added this task:
[T][✘] borrow book
Now you have 1 tasks in the list`

### `event` - Creates an Event task

Example of usage: 

`event book club /at 02/10/2019 2000`

Expected outcome:

`Got it. I've added this task:
[E][✘] book club (by: 12/10/2019 2000)
Now you have 2 tasks in the list`

### `deadline` - Creates a Deadline task

Example of usage: 

`deadline do book report /by 02/10/2019 1800`

Expected outcome:

`Got it. I've added this task:
[D][✘] do book report (by: 12/10/2019 1800)
Now you have 2 tasks in the list`

### `list` - Retrieves complete list of tasks

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:
1.[T][✘] read book
2.[D][✘] do book report (by: 12/10/2019 1800)
3.[E][✘] attend book club (at: 12/10/2019 2000)`

### `delete` - Deletes task at specified index

Example of usage: 

`delete 2`

Expected outcome:

`Noted. I've removed this task: 
[E][✘] book club (at: 12/10/2019 1800)
Now you have 2 tasks in the list.`

Task at the number 2 position is deleted

### `done` - Marks task at specified index as completed

Example of usage: 

`done 2`

Expected outcome:

`Nice! I've marked this task as done:
[T][✓] do report`

Task at the number 2 position is marked as done

### `bye` - Exits Duke application

Example of usage: 

`bye`

Expected outcome:

Duke window is closed and tasks are saved
