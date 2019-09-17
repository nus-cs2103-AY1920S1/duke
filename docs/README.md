# User Guide

## Features 

### 1. Adding a todo task: `todo` 

Adds a todo task to the task list.

Format: `todo DESCRIPTION`

Example of usage: 

`todo read book`

Expected outcome:
```
Got it. I've added this task:
   [T][✘] read book
Now you have 1 task in the list.
```

### 2. Adding a deadline task: `deadline` 

Adds a deadline task to the task list. The time of the task is optional.
The date follows `dd/mm/yyyy` format and the time follows `HHMM` format (24-hour).
The time of the deadline is optional but the date must be present.

Format: `deadline DESCRIPTION /by DATE [TIME]`

Example of usage: 

`deadline return book /by 06/06/2019 0800`

Expected outcome:
```
Got it. I've added this task:
  [D][✘] return book (by: Thu, 6 June 2019, 08:00AM)
Now you have 2 tasks in the list.
```

### 3. Adding a event task: `event` 

Adds a event task to the task list. All the dates follows 
`dd/mm/yyyy` format and all the time follows `HHMM` format (24-hour).

Format 1: `event DESCRIPTION /at START_DATE START_TIME END_DATE END_TIME`

If the start date and the end date of the event to be added is on the same day, format 2 can be used.

Format 2: `event DESCRIPTION /at DATE START_TIME END_TIME`

Example of usage: 

`event project meeting /at 06/08/2019 1400 1600`

Expected outcome:

```
Got it. I've added this task:
  [E][✘] project meeting (at: 6 August 2019, 02:00PM - 04:00PM)
Now you have 3 tasks in the list.
```

### 4. Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

Example of usage: 

`list`

Expected outcome:
```
Here are the tasks in your list:
1.[T][✘] read book
2.[D][✘] return book (by: Thu, 6 June 2019, 08:00AM)
3.[E][✘] project meeting (at: 6 August 2019, 02:00PM - 04:00PM)
```

### 5. Marking a task as done: `done`

Marks the specified task as done.

The index refers to the index number shown in the displayed task list.

The index must be a positive integer 1, 2, 3, …​

Format: `done INDEX`

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
  [D][✓] return book (by: Thu, 6 June 2019, 08:00AM)
```

### 6. Deleting a task: `delete`

Deletes the specified task from the task list.

The index refers to the index number shown in the displayed task list.

The index must be a positive integer 1, 2, 3, …​

Format: `delete INDEX`

Example of usage: 

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
  [D][✓] return book (by: Thu, 6 June 2019, 08:00AM)
Now you have 2 tasks in the list.
```

### 7. Searching for a task: `find`

Finds tasks whose descriptions contain the given keyword.

The search is case sensitive. (e.g book will not match Book.)

Only the description is searched. Keyword can be matched 
partially. (e.g. car will match carpet)

Format: `find KEYWORD`

Example of usage: 

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
1.[T][✘] read book
2.[D][✘] return book (by: Thu, 6 June 2019, 08:00AM)
```

### 8. Exiting the program: `bye`

Exits the program.

Format: `bye`

### 9. Saving the data
Task list data are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

## Command Summary
* Add todo: `todo DESCRIPTION`
* Add deadline: `deadline DESCRIPTION /by DATE [TIME]`
* Add event: `event DESCRIPTION /at START_DATE START_TIME END_DATE END_TIME`
* Add one-day event: `event DESCRIPTION /at DATE START_TIME END_TIME`
* List: `list`
* Done: `done INDEX`
* Delete: `delete INDEX` 
* Find: `find KEYWORD`
* Bye: `bye`