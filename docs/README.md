# User Guide

## Getting Started
 1. Ensure that you have Java Version 11 installed on your computer.
 2. Download the latest jar file [here](https://github.com/cutieprobe/duke/releases).
 
## Working Screenshot

![working screenshot](https://raw.githubusercontent.com/cutieprobe/duke/master/docs/Ui.png)

## Features 
1. Manage tasks
2. Customize commands

### Feature 1: Manage Tasks
Add, remove, search and view various types of tasks to manage your daily life.

### Feature 2: Customize Commands
Add customized shorter aliases for commands to enjoy more efficient typing.

## Usage

### `todo` - Add a todo

Default alias: `t`

Add a new todo task.

Example of usage: 

`todo kfc interview`

Expected outcome:

```
     Got it. I've added this task:
       [T][✘] kfc interview
```

### `deadline` - Add a deadline

Default alias: `dd`

Add a new deadline task.

Example of usage: 

`deadline Duke project /by 30/09/2019 23:59`

Expected outcome:

```
     Got it. I've added this task:
       [D][✘] duke project (by: 30/09/2019 23:59)
```

### `event` - Add a event

Default alias: `e`

Add a new event task.

Example of usage: 

`event GER1000 meeting /at 02/10/2019 14:30`

Expected outcome:

```
     Got it. I've added this task:
       [E][✘] ger1000 meeting (at: 02/10/2019 14:30)
```

### `list` - List all tasks

Default alias: `l`

List all tasks in the task list.

Example of usage: 

`list`

Expected outcome:

```
     Here are the tasks in your list:
          1.[T][✘] duke project
          2.[D][✓] kfc interview (by: 01/10/2019 23:59)
          3.[E][✘] eat ice cream (at: 01/10/2019 04:00)
          4.[T][✘] gym! gym! gym!
```

### `done` - Mark a task as done

Default alias: `dn`

Mark an existing task as done.

Example of usage: 

`done 1`

Expected outcome:

```
     Nice! I've marked this task as done:
     [T][✓] duke project
```

### `delete` - Delete a task

Default alias: `de`

Delete an existing task.

Example of usage: 

`delete 2`

Expected outcome:

```
     Noted. I've removed this task:
     [D][✓] kfc interview (by: 01/10/2019 23:59)
```

### `find` - Search for tasks

Default alias: `t`

Search for all tasks matching the keyword.

Example of usage: 

`find meeting`

Expected outcome:

```
     Here are the matching tasks in your list:
          1.[E][✘] ger1000 meeting (at: 02/10/2019 14:30)
```

### `bye` - Exit the application

Default alias: `b`, `exit`

Exit the application.

Example of usage: 

`bye`

Expected outcome:

```
     Bye. Hope to see you again soon!
```

### `alias add` - Add an alias to a keyword

Default alias: `aa`

Add an alias to a command keyword.

Example of usage: 

`alias add ls list`

Expected outcome:

```
     "ls" is now mapped to "list".
```

### `alias delete` - Delete an alias

Default alias: `t`

Delete an alias so that it is no longer mapped to its keyword.

Example of usage: 

`alias delete l list`

Expected outcome:

```
     "l" is no longer mapped to "list".
```

### `alias view` - View an alias

Default alias: `t`

View an alias and the keyword mapped it is mapped to.

Example of usage: 

`alias view t`

Expected outcome:

```
     "t" is an alias for "todo".
```

### `alias list` - List all aliases of a keyword

Default alias: `al`

View all aliases mapped to a keyword.

Example of usage: 

`alias list bye`

Expected outcome:

```
     Here are the aliases for "bye":
     b, exit
```

### `alias all` - List all aliases and their keywords

Default alias: `aall`

View all aliases and their respective keywords.

Example of usage: 

`alias all`

Expected outcome:

```
     Here are the available aliases (left) and their mapped keywords (right):
     "aa": "alias add".
     "aall": "alias all".
     "ad": "alias delete".
     "al": "alias list".
     "av": "alias view".
     "b": "bye".
     "dd": "deadline".
     "de": "delete".
     "dn": "done".
     "e": "event".
     "exit": "bye".
     "f": "find".
     "l": "list".
     "t": "todo".
```
