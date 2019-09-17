# User Guide

Welcome to Duke! Duke is an amazing task tracker — get started now!

## Features 

### `todo` - Add a Todo

Add a Todo to be tracked by Duke.

A new Todo will not be added if there is an existing tracked Todo with the same description.

Format: `todo <DESCRIPTION>`

Example of usage: 

`todo Buy groceries`

Expected outcome:

`[T][X] Buy groceries`

A new Todo with the specified description is added to Duke.

<br>

### `event` - Add an Event

Add an Event to be tracked by Duke.

A new Event will not be added if there is an existing tracked Event with the same description and time.


Format: `event <DESCRIPTION> /at <DD/MM/YYYY HHMM>`

Example of usage: 

`event John's birthday party /at 22/11/2019 1800`

Expected outcome:

`[E][X] John's birthday party (at: 22/11/2019 1800)`

A new Event with the specified description and time is added to Duke. 

<br>

### `deadline` - Add a Deadline

Add a Deadline to be tracked by Duke.

A new Deadline will not be added if there is an existing tracked Deadline with the same description and time.

Format: `deadline <DESCRIPTION> /by <DD/MM/YYYY HHMM>`

Example of usage: 

`deadline CS2101 Submission /by 20/10/2019 2359`

Expected outcome:

`[D][X] CS2101 Submission (by: 20/10/2019 2359)`

A new Deadline with the specified description and time is added to Duke. 

<br>

### `list` - List all tasks

Lists all tasks tracked by Duke.

Format: `list`

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][X] Buy groceries
2. [E][X] John's birthday party (at: 22/11/2019 1800)
3. [D][X] CS2101 Submission (by: 20/10/2019 2359)
```

<br>

### `done` - Mark a task as done

Marks the task with the associated task number as done.

Format: `done <TASK_NUMBER>`

Example of usage: 

`done 3`

Expected outcome:

`[D][O] CS2101 Submission (by: 20/10/2019 2359)`

<br>

### `delete` - Delete a task

Deletes the task with the associated task number.

Format: `delete <TASK_NUMBER>`

Example of usage: 

`delete 3`

Expected outcome: Task is removed and no longer tracked by Duke.

<br>

### `find` - Find tasks

Returns a list of found tasks with description containing the keyword.

Format: `find <KEYWORD>`

Example of usage: 

`find groceries`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][X] Buy groceries
```

<br>

### `bye` - Exit from Duke

Saves the latest task list and exit from Duke.

Format: `bye`

Example of usage: 

`bye`

Expected outcome: Tasks are saved and application closes.