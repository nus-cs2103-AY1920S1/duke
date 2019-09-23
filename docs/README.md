# User Guide

## Features 

### Add and keep track of tasks 
Store different types of tasks (todo, event, deadline) and track its status.

### Saves tasks locally
Changes to tasks are saved locally. The same task list from your current session will be loaded the next time you open Duke.

### Mark task as done
Tasks are marked as undone when created by default. You can mark them as done whenever you wish.

### List all tasks
All existing tasks will be listed in order of creation, showing their index, completion status and description.

### Delete tasks
Tasks can be deleted.

### Search for specific tasks
Search for task by entering keyword in the task description.

### Snooze task
Change the time associated with a task.

## Usage

### `todo`/`event`/`deadline` - creates and adds a task object

Creates and adds task depending on keyword:

####`todo (description)` - creates and adds a basic `todo` task with the given `(description)`.

Example of usage:

`todo example`

Expected outcome:
```
>>Task added:
       [T][✘]example
     There are 2 tasks in the list.
```

####`event (description) /at (event time)` - creates and adds an `event` type task.
`(event time)` must be given in the format `DD/MM/YYYY HHMM-HHMM`.

Example of usage:

`event example event /at 2/9/2019 1000-1400`

Expected outcome:
```
>>Task added:
    [E][✘]example event (at: 2/9/2019 1000-1400)
  There are 3 tasks in the list.
```

####`deadline (description) /by (task deadline)` - creates and adds a `deadline` type task
`(task deadline)` must be given in the format `DD/MM/YYYY HHMM`.

Example of usage: 

`deadline do this /by 1/9/2019 1200`

Expected outcome:
```
>>Task added:
     [D][✘]do this (by: 1/9/2019 1200)
   There are (current number of tasks) tasks in the list.
```

### `list`

Shows all tracked tasks and lists them in order of creation.

Example of usage: 

`list`

Expected outcome:
```
>>List:
     1.[D][✘]do this (by: 1/9/2019 1200)
     2.[T][✘]example
     3.[E][✘]example event (at: 2/9/2019 1000-1400)

```

### `done`
Marks the specified task as done. Task index should correspond to the number shown when `list` is used. 

Example below will correspond to the same session used in above in `list`.

Example of usage:

`done 1`

Expected outcome:
```
>>The following task has been marked as done:
       [D][✓]do this (by: 1/9/2019 1200)
```

### `delete`
Deletes specified task. Task index should correspond to the number shown when `list` is used.

Example below will correspond to the same session used in above in `list`.

Example of usage:

`delete 2`

Expected outcome:
```
>>Following task yeeted:
    [T][✘]example
  2 tasks left in the list
```

### `find`
Finds and lists tasks containing the keyword given in this input. List is ordered to the same order as `list`.

Example below will correspond to the same session used in above in `list`.

Example of usage:

`find example`

Expected outcome:

```
>>Matching tasks:
  1.[T][✘]example
  2.[E][✘]example event (at: 2/9/2019 1000-1400)
```

### `snooze`
Reschedules the date and time associated with the task.

New date and time provided must correspond to the task type. i.e. if the task is a `deadline`, date time provided should be in `DD/MM/YYYY HHMM` format.

Example of usage:

`snooze 3 2/9/2019 1200-1400`

Expected outcome:

```
>>Following task has been rescheduled:
  [E][✘]example event (at: 2/9/2019 1200-1400)
```

### `bye`
Prints exit message and closes the program.

When used in GUI, program prints a different exit message and closes after a 1.5 seconds.

Example of usage:

`bye`

Expected outcome:

```
>>Goodbye. Hope to see you again UwU
- Program exits -
```

Expected outcome in GUI:

```
Goodbye. This window will close soon. Hope to see you again UwU
- Window closes and program exits after short delay -
```

