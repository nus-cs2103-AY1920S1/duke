# User Guide

Duke is a simple task tracker.

## Features 

### Adding, deleting and viewing tasks
You can add tasks to Duke and it will store them for you to view. These tasks can be simple todo tasks, or timed tasks such as deadlines or events.
And of course, you can delete these tasks too.

### Completing tasks
You can mark tasks on your task list as complete. Tasks are incomplete by default.

### Finding tasks
You can filter your tasks by key words/phrases.

### Automated reminders
Duke will remind you of your upcoming incomplete tasks every time you launch the app, and when you ask it to.

## Usage

### `list`
Typing `list` will print the task list.

Describe action and its outcome.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][✗] read book
2. [D][✗] return book (by: 20/09/2019 16:30)
3. [E][✗] CS2103 meeting (at: 23/09/2019 12:00)
```

### `bye`
Typing `bye` will exit the program.

### `reminders`
Typing `reminders` will show all reminders. Duke will remind you of all incomplete tasks with timestamps less than 7 days from now. Tasks that have already gone past their scheduled time are also mentioned.

Example of usage:

`reminders`

Expected outcome:

```
Here are your upcoming tasks:
The task, return book, has already gone past its scheduled time by 1 days, 18 hours and 3 minutes!
You have 3 days, 11 hours and 55 minutes left to complete the task: CS2103 meeting!
```

### `done (list index)`
Typing `done n` will mark the nᵗʰ task in the list as complete. Complete tasks will no longer show on reminders.

Example of usage:

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][✓] read book
```

### `delete (list index)`
Typing `delete n` will delete the nᵗʰ task in the list.

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][✓] read book
Now you have 2 tasks in the list.
```

### `find (keywords)`
Typing `find keywords` will show all tasks containing `keywords` in the task list.

Example of usage:

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][✓] read book
2. [D][✗] return book (by: 20/09/2019 16:30)
```

### `todo (task)`
Typing `todo (task)`, where (task) is the task you want Duke to keep track of, will add a simple Todo task to your task list.

Example of usage:

`todo read book`

Expected outcome:

```
Got it. I've added this task:
[T][✗] read book
Now you have 2 tasks in your list.
```

### `deadline (task) /by (dd/mm/yyyy hh:mm)`
Typing `deadline (task) /by (dd/mm/yyyy hh:mm)`, where (task) is the task you want Duke to keep track of and (dd/mm/yyyy hh:mm) is the task's timestamp, will add a timed Deadline task to your task list.

Example of usage:

`deadline return book /by 20/09/2019 16:30`

Expected outcome:

```
Got it. I've added this task:
[D][✗] return book (by: 20/09/2019 16:30)
Now you have 3 tasks in your list.
```

### `event (task) /at (dd/mm/yyyy hh:mm)`
Typing `event (task) /at (dd/mm/yyyy hh:mm)`, where (task) is the task you want Duke to keep track of and (dd/mm/yyyy hh:mm) is the task's timestamp, will add a timed Event task to your task list.

Example of usage:

`event CS2103 meeting /at 23/09/2019 12:00`

Expected outcome:

```
Got it. I've added this task:
[E][✗] CS2103 meeting (at: 23/09/2019 12:00)
Now you have 4 tasks in your list.
```


