# User Guide

## Features 

### Feature 1
Get a list of commands

`help`

Prints out a list of commands and their parameters.

### Feature 2
Create and save various tasks

`todo` Creates a new task

`deadline <description> /by <DD/MM/YYYY HH/MM>` Creates a task with a deadline

`event <description> /at <DD/MM/YYYY HH/MM>` Creates a task with a date

### Feature 3
Prints out a list of tasks.

`list`

```
Here are the tasks in your list:
1. [T][✓] wash car
2. [T][✓] GEQ project
3. [T][✓] GER project
4. [D][X] CS2103 quiz (by: 19/09/2019 2359)
```

### Feature 4
Mark a task as complete

`done <id>`

Example:
`done 2`

```
Nice! I've marked this task as done:
  [T][✓] GEQ project
```

### Feature 5
Delete a task.

`delete <id>`

Example:
`delete 2`

```
Noted. I've removed this task: [T][✓] GEQ project
Now you have 3 tasks in the list.
```

### Feature 6
Search and displays tasks that match the keyword provided.

`find <keyword>`

Example: `find GER`
```
1. [T][✓] GER project
2. [D][✓] GER quiz (by: 22/09/2019 2359)
```
