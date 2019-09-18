# DukeBot User Guide [![Status](https://travis-ci.org/jiayushe/duke.svg?branch=master)](https://travis-ci.org/jiayushe/duke)

## User Interface
![UI](Ui.png)

## Features 

### Task Manager
DukeBot is a personal task manager. It allows users to add, delete, search and tag tasks.

## Usage

### 1. `bye` - Exits application

Exits DukeBot application.

Example of usage: 

```
bye
```

Expected outcome:

```
☀ Bye! Hope to see you again soon!
```

### 2. `deadline` - Adds a deadline

Adds a Deadline Task to task list.

Example of usage: 

```
deadline hello world /by 01/01/2019 1800
```

Expected outcome:

```
Got it! I've added this task:
[D][✘] hello world (by 1 Jan 2019 18:00)
Now you have 1 task in the list!
```

### 3. `delete` - Deletes a task

Deletes a task from task list.

Example of usage: 

```
delete 1
```

Expected outcome:

```
Noted! I've removed this task:
[D][✘] hello world (by 1 Jan 2019 18:00)
Now you have 0 tasks in the list!
```

### 4. `done` - Marks a task as done

Marks a task as done in task list.

Example of usage: 

```
done 1
```

Expected outcome:

```
Nice! I've marked this task as done:
[D][✘] hello world (by 1 Jan 2019 18:00)
```

### 5. `event` - Adds an event

Adds an Event Task to task list.

Example of usage: 

```
event hello world /at 01/01/2019 1800
```

Expected outcome:

```
Got it! I've added this task:
[E][✘] hello world (by 1 Jan 2019 18:00)
Now you have 1 task in the list!
```

### 6. `find` - Searches tasks

Searches tasks by keyword in the task list.

Example of usage: 

```
find world
```

Expected outcome:

```
Here are the matching tasks in your list:
1.[E][✘] hello world (by 1 Jan 2019 18:00)
```

### 7. `list` - Lists tasks

Lists all tasks in the task list.

Example of usage: 

```
list
```

Expected outcome:

```
Here are the tasks in your list:
1.[E][✘] hello world (by 1 Jan 2019 18:00)
```

### 8. `tag` - Adds a tag to a task

Adds a tag to a task in the task list.

Example of usage: 

```
tag 1 /as life
```

Expected outcome:

```
Tag life added to this task:
[E][✘][#life] hello world (by 1 Jan 2019 18:00)
```

### 9. `todo` - Adds a Todo Task

Adds a Todo Task to task list.

Example of usage: 

```
todo hello world
```

Expected outcome:

```
Got it! I've added this task:
[T][✘] hello world
Now you have 2 tasks in the list!
```
