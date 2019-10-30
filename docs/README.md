# User Guide
The Duke application helps users manage their tasks like todo, deadlines, as well as events. 
This user guide will list some of the features, keywords and usage for the application.

## Features 
* Manage various types of tasks, including todo, deadlines, and events.
* Mark a task as done.
* Delete an unwanted task.
* List all the tasks added to the Duke program.
* Find tasks with a specified keyword.
* More readable date-time format.
* Get a motivational quote.

## Usage

### `todo` - Creates a new ToDo task
Creates a new todo task with the given description.

Format: `todo [Description]`

Example Usage: `todo wash the laundry`

Expected outcome:
```
Got it! I've added this task:
 [T][X] wash the laundry
Now you have 1 task in the list.
```

### `deadline` - Creates a new Deadline task
Creates a new deadline task with the given description.

Format: `deadline [Description] /by [dd/MM/yyyy] [hhmm]`

Example Usage: `deadline CS2103 iP 30/09/2019 1200`

Expected outcome:
```
Got it! I've added this task:
 [D][X] CS2103 iP (by: 30th September 2019, 12PM)
Now you have 1 task in the list.
```
### `event` - Creates a new Event task
Creates a new event task with the given description.

Format: `event [Description] /at [dd/MM/yyyy] [hhmm]`

Example Usage: `event Mum's birthday party 2/10/2019 1700`

Expected outcome:
```
Got it! I've added this task:
 [E][X] Mum's birthday party (at: 2nd Ocotber 2019, 5PM)
Now you have 1 task in the list.
```

### `done` - Marks a task as done
Marks the given task number as done.

Format: `done [TaskNumber]`

Example Usage: `done 2`

Expected outcome:
```
Nice! I've marked this task as done:
 [T][O] Buy a gift for Cassandra's 21st birthday
```

### `delete` - Deletes a task 
Deletes the given task from the task list.

Format: `delete [TaskNumber]`

Example Usage: `delete 3`

Expected outcome:
```
Noted. I've removed this task:
 [T][O] wash the laundry
Now you have 2 task in the list.
```

### `find` - Find keyword specific tasks
Lists out the tasks that match the given keyword

Format: `find [Keyword]`

Example Usage: `find CS2103`

Expected outcome:
```
Here are the tasks in your list:
 1. [D][X] CS2103 iP (by: 30th September 2019, 12PM)
```

### `list` - Lists out all the tasks
Lists out all the task that Duke has been handling for the user.

Format: `list`

Example Usage: `list`

Expected outcome:
```
Here are the tasks in your list:
 1. [T][O] wash the laundry
 2. [D][X] CS2103 iP (by: 30th September 2019, 12PM)
 3. [E][X] Mum's birthday party (at: 2nd Ocotber 2019, 5PM)
```

### `data` - Lists out all tasks stored in local disc
Lists out all the task that Duke has stored in the user's local storage disc.

Format: `data`

Example Usage: `data`

Expected outcome:
```
This is the list of tasks stored in your local storage disc:
 1. T | O | wash the laundry
 2. D | X | CS2103 iP (by: 30th September 2019, 12PM)
 3. E | X | Mum's birthday party (at: 2nd Ocotber 2019, 5PM)
```

### `quote` - Gives a motivational life quote
A randomised motivational quote will be shared to the user.

Format: `quote`

Example Usage: `quote`

Expected outcome:
```
Have enough courage to start and enough heart to finish.
 - Jessica N. S. Yourko
```

### `bye` - Exits the Duke Application
Exits the Duke Application by bidding the user farewell.

Format: `bye`

Example Usage: `bye`

Expected outcome:
```
GoodBye! Hope to see you again soon!
```