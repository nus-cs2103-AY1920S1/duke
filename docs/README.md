# User Guide

## About Duke
Duke is an interactive chatbot that serves as a personal task list.

## Features

### Add / Delete a task to task list
In Duke, you can add or delete tasks belonging to one of the three predefined categories - Todo, Event, Deadline.
For example, if you want to take note of an assignment deadline, you may add a deadline task to the task list.
You may also remove that deadline task afterwards if it is no longer of concern.

### Mark a task on the task list as done
In Duke, every task is set to be not done by default, and a cross symbol [✗] will be reflected on the task list. Once a task is cleared by you, you may mark it as done, and a tick symbol [✓] will be reflected instead.

### Display task list
View all the tasks in your task list. Each task will have a representative symbol to represent its status - done or not done.

### Auto-save functionality
In Duke, all tasks are automatically saved. No manual saving is needed.

## Usage

### `bye` - Terminates the application

Duke program will exit, and its GUI window will close.

Example of usage: 

`bye`

Expected outcome:

GUI window closed.

### `list` - Displays all tasks in the task list

Shows the task list in full.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:\n1.[T][✗] lawsuit research\n2.[T][✗] hire intern`
`Test`

### `delete <n>` - Deletes a specific task in the task list

The nth task in the task list will be deleted.

Example of usage: 

`delete 1`

Expected outcome:

`Noted. I've removed ths task:\n[T][✗] lawsuit research\nNow you have 0 tasks in the list.`

### `done <n>` - Marks a specific task in the task list as done

The nth task in the task list will be marked as done.

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:\n[T][✓] hire intern`

### `todo <desciption>` - Adds a todo task into the task list

A todo task of the specified description will be appended to the current task list.

Example of usage: 

`todo sign documents`

Expected outcome:

`Got it. I've added this task:`
`[T][✗] sign documents`
`Now you have 2 tasks in the list`

