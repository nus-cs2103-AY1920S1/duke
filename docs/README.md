# User Guide

## Features 

### 1. Display help
Display the help page.

### 2. Add a task
Add a task that is either a Todo, Deadline or Event with a given description.
If it is a Deadline or Event task, it is created with the given due date and 
time or event date and time respectively.

### 3. Mark a task as done
Mark an existing task as done, i.e. completed. The cross mark (x) for the task is changed to a check mark (✓).

### 4. Delete a task
Delete an existing task from the task list.

### 5. Find tasks using keywords
Find a task or certain tasks using given keyword(s).

### 6. Display the task list
Display the full list of tasks.

## Usage

### 'help' - Displays the help page

Displays the help page.

Format: `help`

### 'todo' - Adds a Todo task

Adds a Todo task with a description of the task to the task list.

Format: `todo <description>`

Example of usage:

`todo borrow book`

Expected outcome:

```
okie! i added this task:
    [T][x] borrow book
now u haf 1 task in the list
```

### 'deadline' - Adds a Deadline task

Adds a Deadline task with a description of the task and the due date and time to the tasks list. 


Date and time
has to be in the following format: "d/m/yyyy HHmm". Time is in 24-hour format.

Format: `deadline <description> /by <date and time>`

Example of usage:

`deadline 2030 lab assignment /by 20/9/2019 2359`

Expected outcome:

```
okie! i added this task: 
    [D][x] 2030 lab assignment (by: 20/9/2019 2359)
now u haf 2 tasks in the list
```

### 'event' - Adds an Event task
Adds an Event task with a description of the task and the event date and time to the tasks list. 

Date and time
has to be in the following format: "d/m/yyyy HHmm". Time is in 24-hour format.

Format: `event <description> /at <date and time>`

Example of usage:

`event 2103 project meeting /at 18/19/2019 1000`

Expected outcome:

```
okie! i added this task:
    [E][x] project meeting (at: 18/19/2019 1000)
now u haf 3 tasks in the list
```

### 'done' - Marks a task as done
Marks an existing task as done, i.e. changes the cross mark (x) to a check mark (✓).

Format: `done <task number>`

Example of usage:

`done 1`

Expected outcome:

```
naisu! i marked this task as done:
    [T][✓] borrow book
```

### 'delete' - Deletes a task from the tasks list
Deletes an existing task from the tasks list.

Format: `delete <task number>`

Example of usage:

`delete 1`

Expected outcome:

```
okie! i delete this task:
    [T][✓] borrow book
now u haf 2 tasks in the list
```

### 'find' - Finds a task or certain tasks from the tasks list
Finds a task or certain tasks from the task list.

Format: `find <keyword(s)>`

Example of usage:

`find book`

Expected outcome:

```
here ya go, the matchy-matchy tasks in ur list: 
    1. [T][x] borrow book 
    2. [T][x] read book
```

Another example of usage: (multiple keywords)

`find borrow book`

Expected outcome:

```
here ya go, the matchy-matchy tasks in ur list: 
    1. [T][x] borrow book
```

### 'list' - Displays the full list of tasks
Displays the full list of tasks.

Format: `list`

Example of usage:

`list`

Expected outcome:

```
here ya go, the tasks in ur list: 
    1. [T][x] borrow book 
    2. [D][x] 2030 lab assignment (by: 20/9/2019 2359) 
    3. [E][x] project meeting (at: 18/19/2019 1000)
```
