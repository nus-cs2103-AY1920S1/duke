# Bear User Guide

## Features 

Bear is an interactive, error-resistant and lightweight task manager with a clean and minimalist interface that helps you to get things done.

Bear supports seamless data storage and transfer between devices so you can access your task list from anywhere. Changes are automatically saved. 

## Usage

Commands are **case-insensitive**.

### `todo` - Add a To-Do

A To-Do is a task without any date/time attached to it.

You can use this command to add a To-Do to your task list.

Command format: `todo <todo description>`

Example of usage: 

`todo revise CS2103T content`

Expected outcome:

```
Got it. I've added this task:
[T][✗] revise CS2103T content
Now you have 1 task in the list.
```

### `event` - Add an Event

An Event is a task that starts at a specific time.

You can use this command to add an Event to your task list.

Command format: `event <event description> /at <event date/time>`

Bear understands and automatically converts dates and times in the format `DD/MM/YYYY HHMM`.

Example of usage: 

`event attend CS2103T lecture /at 20/9/2019 1600`

Expected outcome:

```
Got it. I've added this task:
[E][✗] attend CS2103T lecture (at: 20th of September 2019, 4:00 PM)
Now you have 2 tasks in the list.
```

### `deadline` - Add a Deadline

A Deadline is a task that needs to be done before a specific date/time.

You can use this command to add a Deadline to your task list.

Command format: `deadline <deadline description> /by <deadline date/time>`

Bear understands and automatically converts dates and times in the format `DD/MM/YYYY HHMM`.

Example of usage: 

`deadline finish CS2103T iP /by 17/9/2019 2359`

Expected outcome:

```
Got it. I've added this task:
[D][✗] finish CS2103T iP (by: 17th of September 2019, 11:59 PM)
Now you have 3 tasks in the list.
```

### `done` - Mark a task as done

You can use this command to mark a task in your task list as done.

Command format: `done <task index>`

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][✓] revise CS2103T content
```

### `delete` - Delete a task

You can use this command to delete a task from your task list.

Command format: `delete <task index>`

Example of usage: 

`delete 3`

Expected outcome:

```
Noted. I've removed this task:
[D][✗] finish CS2103T iP (by: 17th of September 2019, 11:59 PM)
Now you have 2 tasks in the list.
```

### `list` - List all tasks

You can use this command to list all tasks in your task list.
The generated list will be in chronological order.

Expected outcome:

```
Here are the tasks in your list:
1.[T][✓] revise CS2103T content
2.[E][✗] attend CS2103T lecture (at: 20th of September 2019, 4:00 PM)
```

### `find` - Find a task

You can use this command to find a task by searching for a keyword.

Search results are **case-insensitive**.

Command format: `find <search keyword>`

Example of usage: 

`find CS2103T`

Expected outcome:

```
Here are the matching tasks in your list:
1.[T][✓] revise CS2103T content
2.[E][✗] attend CS2103T lecture (at: 20th of September 2019, 4:00 PM)
```

### `load` - Load tasks

You can use this command to load a task list from an existing data source.

File names are **case-insensitive**.

Command format: `load <file name>`

Example of usage: 

`load myBear.txt`

Expected outcome:

`Your saved data has been successfully loaded from myBear.txt.`

If no data source is loaded, your task list is automatically saved in `bear.txt`.