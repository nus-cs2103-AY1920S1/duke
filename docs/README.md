# Duke - User Guide

## Introduction
Duke is a task manager that helps you keep track of your tasks.

## Command Format
* Words in `UPPER_CASE` are the parameters to be supplied by the user.
* Items in round brackets are optional.

## Features 

### Feature 1: Add a to-do task
You can add a *Todo* task to the list. 
This task type is only associated with a description.
There are no time constraints for such tasks.

### Feature 2: Add a tasks with a deadline
You can add a *Deadline* task to the list.
This task type is associated with a description 
and a deadline by which the task has to be completed.

### Feature 3: Add an event task
You can add an *Event* task to the list.
This task type is associated with a starting date and
optionally, a starting time, ending date and time.

### Feature 4: Delete a task
You can delete a task from the list.

### Feature 5: Mark a task as done
You can mark a task as done once you have completed it.

### Feature 6: List tasks
You can view all tasks that are currently in the list.

### Feature 7: Find a task
You can find a task that is in the list using keywords.

### Feature 8: Sort tasks
You can sort the tasks in the list in the order *Todo*, *Deadline* and *Event*.
*Deadline* and *Event* tasks are sorted in chronological order.

### Feature 9: Exit Duke
You can exit the application when you have finished managing your tasks.

## Usage

### `todo` - Adds *Todo* task

Adds a *Todo* task to the list.

Format: `todo TASK_DESCRIPTION`

Example of usage: 

`todo read book`

Expected outcome:

```
Got it. I've added this task:
    [T][Not Done] read book
Now you have 1 task in the list.
```

### `deadline` - Adds *Deadline* task

Adds a *Deadline* task to the list. A date for the deadline must be provided
but a time is optional.
The date must be in the form `dd/mm/yyyy` 
and the time, if given, must be in the 24-hour time notation `hhmm`.


Format: `deadline TASK_DESCRIPTION \by DATE (TIME)`

Example of usage: 

`deadline complete assignment \by 15/09/2019 1300`

Expected outcome:

```
Got it. I've added this task:
    [D][Not Done] complete assignment (by: 15th of SEPTEMBER 2019, 1pm)
Now you have 2 tasks in the list.
```

### `event` - Adds *Event* task

Adds a *Event* task to the list. A starting date for the event must be provided
but a starting time, ending date and ending time is optional. The date must be in the form `dd/mm/yyyy` 
and the time must be in the 24-hour time notation `hhmm`.


Format: `event TASK_DESCRIPTION /at START_DATE (START_TIME /to END_DATE END_TIME)`

Example of usage: 

`event camp /at 16/09/2019 1300 /to 17/09/2019 0800`

Expected outcome:

```
Got it. I've added this task:
    [E][Not Done] camp (at: 16th of SEPTEMBER 2019, 1pm to 17th of SEPTEMBER 2019, 8am)
Now you have 3 tasks in the list.
```

### `delete` - Deletes a task

Deletes a task from the list. The index of the task that you want to delete
must be provided.


Format: `delete TASK_INDEX`

Example of usage: 

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
    [D][Not Done] complete assignment (by: 15th of SEPTEMBER 2019, 1pm)
Now you have 2 tasks in the list.
```

### `done` - Marks as done

Marks a task from the list as completed. The index of the task that you want to mark as done
must be provided.


Format: `done TASK_INDEX`

Example of usage: 

`done 2`

Expected outcome:

```
Nice! I've marked this task as done:
    [E][Done] camp (at: 16th of SEPTEMBER 2019, 1pm to 17th of SEPTEMBER 2019, 8am)
```

### `list` - Lists all tasks 

Lists all tasks that are in the list. 

Format: `list`

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
    1.[T][Not Done] read book
    2.[E][Done] camp (at: 16th of SEPTEMBER 2019, 1pm to 17th of SEPTEMBER 2019, 8am)
```

### `find` - Finds tasks

Finds all tasks that are in the list that contains the given keyword. 

Format: `find KEYWORD`

Example of usage: 

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
    1.[T][Not Done] read book
    2.[D][Not Done] return book (by: 15th of SEPTEMBER 2019)
```

### `sort` - Sorts tasks

Find all tasks that are in the list that contains the given keyword. 

Format: `sort`

Example of usage: 

`sort`

Expected outcome:

```
Here are the tasks in your list:
    1.[T][Not Done] read book
    2.[T][Done] make poster
    3.[D][Not Done] return book (by: 15th of SEPTEMBER 2019, 1pm)
    4.[D][Done] assignment (by: 15th of SEPTEMBER 2019, 6pm)
    5.[D][Not Done] study for test (by: 18th of SEPTEMBER 2019)
    6.[E][Not Done] carnival (at: 17th of SEPTEMBER, 8pm to 17th of SEPTEMBER, 10pm)
    7.[E][Not Done] presentation (at: 18th of SEPTEMBER, 9am)
```

### `bye` - Exits Duke

Exits the task manager.

Format: `bye`

Example of usage: 

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```