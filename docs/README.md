
# User Guide

## Features 

### Add tasks
You can add certain tasks into Duke to keep track of. In particular the deadline, todo and event tasks.

### Mark tasks as done
You can mark a task as done in Duke if you have completed the task.

### Delete tasks
You can delete specific tasks in Duke.

### Find tasks
You can find certain tasks in Duke by searching for a keyword.

### Prioritise tasks
You can prioritise tasks in Duke either by marking it as a priority when adding the task to Duke 
or by stating its number in the list.

### List tasks
You can get a list of all current tasks added into Duke.

## Usage

### `todo` - Adds a todo task

Adds a todo task into the task list of Duke.

Example of usage: 

`todo borrow book`

Expected outcome:

```
Got it. I've added this task:
[T][ ] borrow book
Now you have 1 tasks in the list.
```

### `deadline` - Adds a deadline task

Adds a deadline task into the task list of Duke.

Example of usage: 

`deadline return book /by 17/09/2019 2234`

Expected outcome:

```
Got it. I've added this task:
[D][ ] return book (by: 17 Sep 2019, 10:34 PM)
Now you have 1 tasks in the list.
```

### `event` - Adds an event task

Adds an event task into the task list of Duke.

Example of usage: 

`event book meeting /at 10/02/2015 0900`

Expected outcome:

```
Got it. I've added this task:
[E][ ] book meeting (by: 10 Feb 2015, 09:00 AM)
Now you have 1 tasks in the list.
``` 

### `done` - Marks a task as done

Marks a task as done in the task list of Duke, this action is irreversible.

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[E][+] book meeting (by: 10 Feb 2015, 09:00 AM)
```
 
### `delete` - Deletes a task

Deletes a task in the task list of Duke.

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[E][+] book meeting (by: 10 Feb 2015, 09:00 AM)
Now you have 0 tasks in the list.
```
  
### `find` - Finds tasks

Finds tasks that are associated with the specific keyword in the task list of Duke.

Example of usage: 

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
1. [E][+] book meeting (by: 10 Feb 2015, 09:00 AM)
2. [D][ ] return book (by: 17 Sep 2019, 10:34 PM)
```

### `prioritise` - Prioritises a task

Prioritise a task in the task list by ranking it first in the list.

Example of usage: 

`prioritise 3`

Expected outcome:

```
Okay! I've marked this as a high priority task:
[D][ ] return book (by: 17 Sep 2019, 10:34 PM)
``` 

### `priority` - Prioritises a task when adding to Duke 

Marks a task as high priority immediately when adding it to Duke.

Example of usage: 

`priority todo read book`

Expected outcome:

```
Got it I have added this task:
[E][ ] read book
Now you have 1 tasks in the list.
```

### `list` - Lists all task in Duke 

Returns the task list in Duke. High priority tasks will be separated from low priority tasks by a line.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [E][+] book meeting (by: 10 Feb 2015, 09:00 AM)
----------------------
2. [D][ ] return book (by: 17 Sep 2019, 10:34 PM)
```

### `bye` - Exits the program 

Closes the window and exits the program.

Example of usage: 

`bye`

Expected outcome:

program closes