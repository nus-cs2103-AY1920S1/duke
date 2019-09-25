# User Guide

## Features 

### Add todo task: `todo`
Adds a new todo task with given description.

### Add event task: `event`
Adds a new event task with given description and event datetime.

### Add deadline task: `deadline`
Adds a new todo task with given description and deadline.

### Delete task: `delete`
Deletes specified task.

### Mark task as done: `done`
Marks specified task as completed.

### Search through tasks: `find`
Searches through all tasks to find tasks with description matching search phrase.

### List tasks: `list`
Lists all tasks and details in order of addition.

### Reschedule task: `reschedule`
Reschedules an event or deadline to new datetime.

### Exit program: `bye`
Closes program window and exits program.

## Usage

### `todo <TASK_DESCRIPTION>`

Expected outcome:

Adds a new todo task with given description <TASK_DESCRIPTION>.

### `event <TASK_DESCRIPTION> /at <EVENT_DATETIME>`

Expected outcome:

Adds a new event task with given description <TASK_DESCRIPTION> and event datetime <EVENT_TIME> in the format "dd/mm
/yyyy hhmm".

### `deadline <TASK_DESCRIPTION /by <DEADLINE>`

Expected outcome:

Adds a new deadline task with given description <TASK_DESCRIPTION> and deadline <DEADLINE> in the format "dd/mm
/yyyy hhmm".

### `delete <TASK_NUMBER>`

Expected outcome:

Deletes task with task number <TASK_NUMBER>. Alerts user if no tasks exists at given <TASK_NUMBER>.

### `done <TASK_NUMBER>`

Expected outcome:

Marks task with task number <TASK_NUMBER> as done. Alerts user if no tasks exists at given <TASK_NUMBER>.

### `find <SEARCH_PARAMETER>`

Expected outcome:

Lists all tasks found that contain <SEARCH_PARAMETER>.

### `list`

Expected outcome:

Lists all tasks and details in order of addition.

### `reschedule <TASK_NUMBER> <NEW_DATETIME>`

Expected outcome:

Reschedules task (event or deadline task only) with task number <TASK_NUMBER> to have new event datetime or deadline
 respectively from given <NEW_DATETIME>. <NEW_DATETIME> must be in the format "dd/mm/yyyy hhmm".
 
### `bye`

Expected outcome:

Closes program window and exits program.


