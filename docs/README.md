# User Guide
## Features
## TaskList
You may add or remove tasks into Duke.

## Deadlines
You may set deadlines to tasks.

## Usage
## list
Lists out all the tasks currently in Duke.

Example of usage:

`list`

Expected outcome:
```
1. [T][✓] buy bread
2. [E][✓] project meeting (at:02/12/2019 1800)
3. [D][✓] return book (by: 05/12/2019 1300)
```

### `todo <taskname>`

Adds a todo task in Duke.

Example of usage: 

`todo buy bread`

Expected outcome:

```
Got it. I've added this task:
[T]✘ buy bread
```

### `event <taskname> /at dd/mm/yyyy HHmm`

Adds an event task in Duke.

Example of usage: 

`event project meeting /at 02/12/2019 1800`

Expected outcome:

```
Got it. I've added this task:
[E]✘ project meeting (at:02/12/2019 1800)
Now you have 1 tasks in the list.
```

### `deadline <taskname> /at dd/mm/yyyy HHmm`

Adds a deadline task in Duke.

Example of usage: 

`deadline return book /at 05/12/2019 1300`

Expected outcome:

```
Got it. I've added this task:
[D]✘ return book (at:05/12/2019 1300)
Now you have 1 tasks in the list.
```

### `done <index>`

Marks a task as done in Duke.

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[E]✓ project meeting (at:02/12/2019 1800)
```

### `delete <index>`

Deletes a task in Duke.

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[E]✓ project meeting (at:02/12/2019 1800)
Now you have 1 tasks in the list.
```

### `done <index>`

Marks a task as done in Duke.

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[E]✓ project meeting (at:02/12/2019 1800)
```

### `find <keyword>`

Finds all tasks with the keyword in Duke.

Example of usage: 

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
[T]✓ buy book
[T]✓ read book
```

### `bye`

Exits Duke.

Example of usage: 

`bye`

Expected outcome:

Program closes.

### `help`

Expected outcome:

todo <taskname> : Adds a todo task to the tasklist
event <taskname> /by <location> : Adds an event task to the task list
deadline <taskname> /at dd/mm/yyyy HHmm : Adds a deadline task to the task list
list : Displays the task list
done <index> : Marks a task at <index> as done
delete <index> : Deletes a task at <index>
find <keyword> : Displays all tasks with <keyword>
bye : Exits the program