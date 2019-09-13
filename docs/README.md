# User Guide

## Features 

### Create Tasks
Create tasks of 3 different types: Todo, Deadline, Event.
- Todo tasks possess just a description and no time constraints.
- Deadline tasks must be completed by a stipulated end time.
- Event tasks take place at a stipulated start time.

### List Tasks
See a list of all your created tasks.

### Mark Tasks as Done
Mark a task as done. It will still appear in your list.

### Search for Tasks
Search for tasks using one or more search terms.

### Delete Tasks
Delete tasks from your list.

### Auto-Saver
Tasks are automatically saved to your computer between sessions.

### History of Changes
See a list of all the changes you've made for the current session.

### Undo, Redo
Undo a change you've made, or redo your undo.
You can keep undoing your changes till you reach the start of the current session.

### Ask for Help
See a list of all the commands that Duke understands.

## Usage

### `todo [description]` - Creates Todo task
Creates a new Todo task with the given description.

Example usage: 

`todo read book`

Expected outcome:
```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task(s) in your list.
```

### `deadline [description] /by [date/time]` - Creates Deadline task
Creates a new Deadline task with the given description and deadline.
The deadline should be in the format shown in the example below.

Example usage:

`deadline return book /by 15/01/2020, 6:02pm`

Expected outcome:
```
Got it. I've added this task:
  [D][ ] return book (by: 15/01/2020, 6:02PM)
Now you have 2 task(s) in your list.
```

### `event [description] /at [date/time]` - Creates Event task
Creates a new Event task with the given description and start time.
The time should be in the format shown in the example below.

Example usage:

`event birthday party /at 23/02/2021, 12:01am`

Expected outcome:
```
Got it. I've added this task:
  [E][ ] birthday party (at: 23/02/2021, 12:01AM)
Now you have 3 task(s) in your list.
```

### `list` - List all tasks
Shows a list of all your tasks.

Example usage:

`list`

Expected outcome:
```
Here are the task(s) in your list:
1. [T][ ] read book
2. [D][ ] return book (by: 15/01/2020, 6:02PM)
3. [E][ ] birthday parth (at: 23/02/2021, 12:01AM)
```

### `done [task number]` - Mark a task as done
Marks a task as done in your list.
The task number provided is the task's position in your list.

Example usage:

`done 1`

Expected outcome:
```
Nice! I've marked this task as done:
  [T][+] read book
```

### `find [search term 1] [search term 2] ...` - Search for tasks
Searches for tasks in your list using at least 1 search term.

Example Usage:

`search book`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][+] read book
2. [D][ ] return book (by: 15/01/2020, 6:02PM)
```

### `delete [task number]` - Delete a task
Deletes a task from your list.

Example usage:

`delete 1`

Expected outcome:
```
Noted. I've removed this task:
  [T][+] read book
Now you have 2 task(s) in your list.
```

### `history` - See all changes
Shows a list of all changes made this session.

Example usage:

`history`

Expected outcome:

```
Here is your history of changes from oldest to newest:

todo read book
deadline return book /by 15/01/2020, 6:02pm
event birthday party /at 23/02/2021, 12:01am
done 1
delete 1
-- you are here --
 
```

### `undo` - Undoes the last change
Undoes the last change made, as listed in the change history.

Example usage:

`undo`

Expected outcome:

`'delete 1' undone.`

### `redo` - Redoes the last undone change
Redoes an undone change. Use `history` to see what change will be redone.

Example usage:

`redo`

Expected outcome:

`'delete 1' redone.`

### `help` - See all commands
Shows a list of all possible commands.

### `bye` - Exits Duke
Exits the app after a two-second delay.

Example usage:

`bye`

Expected outcome:

```
Bye! Hope to see you again soon :)

Exiting...
```