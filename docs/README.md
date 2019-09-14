# User Guide
Duke is a Chatbot that helps a person to keep track of tasks.
## Features

### Manage different types of task
There are 3 types of task:
1. Todo
    - A task without any date/time attached to it.
    - E.g. Visit musuem
2. Deadline
    - A task that needs to be done before a specific date/time.
    - E.g. Finish assignment by 20/10/2019 2359
3. Event
    - A task that starts at specific time.
    - E.g. Lunch with friend 21/10/2019 1200

Task can be added, marked as done, or deleted if not needed anymore.

### Find task using keyword
You can search task using keyword and duke will list out all task with keyword.

### Detect duplicates
If you have entered a task before, duke will inform you that it has been added.

### Save list of task to .txt file
All your tasks will be save to a .txt file.

## Usage

### `todo DESCRIPTION` - Add a todo task

A todo will be added to your list with the `description`.

Example of usage:

`todo visit musuem`

Expected outcome:

```
Got it. I've added this task:
  [T][✘] visit musuem
Now you have 1 tasks in the list.
```
### `deadline DESCRIPTION /by DD/MM/YYYY HHmm` - Add a deadline task

A deadline will be added to your list with the `description`, `date` and `time`.

Example of usage:

`deadline finish assignment /by 20/10/2019 2359`

Expected outcome:

```
Got it. I've added this task:
  [D][✘] finish assignment (by: 20-Oct-2019 11:59PM)
Now you have 2 tasks in the list.
```
### `event DESCRIPTION /at DD/MM/YYYY HHmm` - Add a event task

An event will be added to your list with the `description`, `date` and `time`.

Example of usage:

`event lunch with friend /at 21/10/2019 1200`

Expected outcome:

```
Got it. I've added this task:
  [E][✘] lunch with friend (by: 21-Oct-2019 12:00PM)
Now you have 3 tasks in the list.
```
### `list` - List all the tasks

All tasks in your list will be listed out.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][✘] visit musuem
2. [D][✘] finish assignment (by: 20-Oct-2019 11:59PM)
3. [E][✘] lunch with friend (by: 21-Oct-2019 12:00PM)
```
### `done INDEX` - Mark task as done

Mark the task as done at specified `INDEX`.
The index must be a positive integer 1, 2, 3...

Example of usage:

`done 2`

Expected outcome:

```
Nice! I've marked this task as done:
2. [D][✔] finish assignment (by: 20-Oct-2019 11:59PM)
```

### `delete INDEX` - Delete task

Deletes the task at specified `INDEX`
The index must be a positive integer 1, 2, 3...

Example of usage:

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
  [D][✔] finish assignment (by: 20-Oct-2019 11:59PM)
Now you have 2 tasks in the list.
```
### `find KEYWORD` - Find tasks using a keyword

Finds tasks which contains given `KEYWORD`

Example of usage:

`find friend`

Expected outcome:

```
Here are the matching tasks in your list:
1. [E][✘] lunch with friend (by: 21-Oct-2019 12:00PM)
```