# User Guide

## Features 

### Task tracking

You can keep track of the Todos and tasks with dates such as:
- Deadlines
- Events

### Marking tasks as done

You can mark a task as done upon completion.

### Delete tasks

You can delete unwanted or completed tasks.

### Find tasks

You can search for specific tasks.

### Sort tasks

You can sort tasks by due date, with the earliest due tasks at the top.
		

## Usage

### `bye` - Terminates the program

Example of usage: 

`bye`

Expected outcome:

`Program terminates.`

### `list` - List all saved tasks

Shows the list of saved tasks.

Example of usage: 

list

Expected outcome:

A list of all your tasks is shown.

### `todo` - Adds a todo task

Adds a todo task to current task list.

Example of usage: 

todo <task>

Expected outcome:

Acknowledgement that a todo task is added.

### `Deadline` - Adds a deadline task

Adds a deadline task to current task list.

Example of usage: 

deadline <description> by/<date>
<date> must follow dd/MM/yyyy HHmm format.

Expected outcome:

Acknowledgement that a deadline task is added.

### `event` - Adds an even task

Adds an event task to current task list.

Example of usage: 

event <description> at/<date>
<date> must follow dd/MM/yyyy HHmm format.

Expected outcome:

Acknowledgement that an event task is added.

### `done` - Marks a task as done

Use the list command to see your list of tasks, and mark the index of the desired task as done

Example of usage: 

done <index>

Expected outcome:

Acknowledgement of the done command and the newly marked task is shown.

### `delete` - Deletes a task

Use the list command to see your list of tasks, and delete the index of the task.

Example of usage: 

delete <index>

Expected outcome:

The task at the index is removed from the list.

### `find` - Search for a task

Search for all tasks containing the given keyword.

Example of usage: 

search <keyword>

Expected outcome:

A list containing all the tasks with the given keyword is returned.

### `sort` - Sorts the list

The current list of tasks is sorted according to date in ascending order. Todos are placed below all tasks with dates.

Example of usage: 

sort

Expected outcome:

Acknowledgement of the sort command.


