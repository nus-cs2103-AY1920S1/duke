# User Guide

## Features 

### Task Management 
You can ask Maid Chan to manage your tasks to be done, including todos, events and deadlines. You can add tasks, delete tasks, mark tasks as done, and archive tasks.

## Usage

### `list` - List tasks

List all tasks in the task list. You can specify a particular type of tasks to be listed.

Example of usage: 

`list (optional argument)`

`optional arugment` can be `todo`, `event` or `deadline`.

Expected outcome:

```
Here are the tasks in your list:
1.[task type][status] task description
2.[task type][status] task description
...
```

### `todo` - Add todo tasks
Add a todo task to the task list. Todo tasks do not have a specific time or due date.

Example of usage:

`todo task description`

Expected outcome:

```
Got it. I've added this task:
  [T][✘] task description
Now you have n tasks in your list.
```

### `event` - Add events
Add an event to the task list. Events have a specific time.

Example of usage:

`event task description /at DD/MM/YYYY HHMM`

Expected outcome:

```
Got it. I've added this task:
  [E][✘] task description (at: DD/MM/YYYY HHMM)
Now you have n tasks in your list.
```

### `deadline` - Add deadlines
Add a deadline to the task list. Deadlines have a specific due date.

Example of usage:

`deadline task description /by DD/MM/YYYY HHMM`

Expected outcome:

```
Got it. I've added this task:
  [D][✘] task description (by: DD/MM/YYYY HHMM)
Now you have n tasks in your list.
```

### `done` - Mark tasks as done
Mark a specific task as done.

Example of usage:

`done index_number` or `done task description`

Expected outcome:

```
Got it. I've marked this task as done:
  [task type][✓] task description
```

### `delete` - Delete tasks
Delete a specific task.

Example of usage:

`delete index_number` or `delete task description`

Expected outcome:

```
Noted. I've removed this task:
  [task type][status] task description
Now you have n tasks in your list.
```

### `archive` - Archive tasks
Archive a specific task. Archived tasks will be saved at a separate file.

Example of usage:

`archive index_number`

Expected outcome:

```
Noted. I've archived this task:
  [task type][status] task description
Now you have n tasks in your list.
```

### `find` - Search for tasks
Search for tasks using the search keyword.

Example of usage:

`find search_keyword`

Expected outcome:

```
Here are the matching tasks in your list:
1.[task type][status] task description
2.[task type][status] task description
...
```

### `bye` - Exit the program
Exit the program.

Example of usage:

`bye`

Expected outcome:

Program exits after 2 seconds of delay.