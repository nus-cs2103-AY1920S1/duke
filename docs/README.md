# User Guide

## Features 

### Manage Tasks
Add, remove and interact with your tasks to easily keep track of things you need to do. 

## Usage

### `todo` - Add a todo

Adds a new todo.

Example of usage: 

`todo buy chocolate`

Expected outcome:

```
Done! I have successfully added the following task:
[T][x] buy chocolate
```

### `deadline` - Add a deadline

Adds a new deadline.

Example of usage: 

`deadline do homework /by 18/09/2019 1800`

Expected outcome:

```
Done! I have successfully added the following task:
[D][x] do homework (by: 18/09/2019 1800)
```

### `event` - Add an event

Adds a new event.

Example of usage: 

`event birthday party /at 12/10/2019 1500`

Expected outcome:

```
Done! I have successfully added the following task:
[E][x] birthday party (at: 18/09/2019 1800)
```

### `list` - List all tasks

Lists all tasks.

Example of usage: 

`list`

Expected outcome:

```
Here are your tasks:
1. [T][x] buy chocolate
2. [D][x] do homework (by: 18/09/2019 1800)
3. [E][x] birthday party (at: 18/09/2019 1800)
```

### `done` - Mark a task as done

Marks a task as done or completed.

Example of usage: 

`done 2`

Expected outcome:

```
Great! I have marked the following task as done:
[D][✓] do homework (by: 18/09/2019 1800)
```

### `delete` - Delete a task

Deletes a task.

Example of usage: 

`delete 1`

Expected outcome:

```
Done! I have deleted the following task:
[T][x] buy chocolate
```

### `find` - Search for a task

Search for a task matching a keyword.

Example of usage: 

`find homework`

Expected outcome:

```
Done! I have found the following tasks matching your query:
1. [D][✓] do homework (by: 18/09/2019 1800)
```

### `savefile` - Change save file.

Changes the save file from the default `duke.txt` to a user-specified one.

Example of usage: 

`savefile john.txt`

Expected outcome:

```
Done! I have switched to the following save file:
john.txt
```

### `bye` - Exit the application.

Exits the application.

Example of usage: 

`bye`

Expected outcome:

```
Application closes.
```
