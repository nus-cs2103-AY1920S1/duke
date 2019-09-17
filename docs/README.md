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

### `help` - Displays all commands supported by Duke

Displays all commands supported by Duke. Command keyword, command format and command description will be stated clearly for the user to easily refer.

Example of usage: 

`help`

Expected outcome:

`Sure, let me help you out. Here are all the commands you can enter:`<br/>
`bye - terminates the application`<br/>
`list - displays all tasks in the task list`<br/>
`delete <n> - deletes the nth task in the task list. Note that n is a positive integer.`<br/>
`done <n> - marks the nth task in the task list as done. Note that n is a positive integer.`<br/>
`todo <description> - adds a to-do task into the task list`<br/>
`event <description> /at <dd/MM/yyyy HHmm> - adds an event into the task list`<br/>
`deadline <description> /by <dd/MM/yyyy HHmm> - adds a deadline into the task list`<br/>
`find <keyword> - finds tasks(s) with description matching the keyword`

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

`Here are the tasks in your list:`<br/>`1.[T][✗] lawsuit research`<br/>`2.[T][✗] hire intern`

### `delete <n>` - Deletes a specific task in the task list

The nth task in the task list will be deleted.

Example of usage: 

`delete 1`

Expected outcome:

`Noted. I've removed ths task:`<br/>`[T][✗] lawsuit research`<br/>`Now you have 0 tasks in the list.`

### `done <n>` - Marks a specific task in the task list as done

The nth task in the task list will be marked as done.

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`<br/>`[T][✓] hire intern`

### `todo <desciption>` - Adds a todo into the task list

A todo of the specified description will be appended to the current task list.

Example of usage: 

`todo sign documents`

Expected outcome:

`Got it. I've added this task:`<br/>`[T][✗] sign documents`<br/>`Now you have 2 tasks in the list.`

### `event <desciption> /at <dd/MM/yyyy HHmm>` - Adds a event into the task list

An event of the specified description and date time will be appended to the current task list.

Example of usage: 

`event gala dinner /at 20/10/2019 1900`

Expected outcome:

`Got it. I've added this task:`<br/>`[E][✗] gala dinner (at: 20/10/2019 1900)`<br/>`Now you have 3 tasks in the list.`

### `deadline <desciption> /by <dd/MM/yyyy HHmm>` - Adds a deadline into the task list

An deadline of the specified description and date time will be appended to the current task list.

Example of usage: 

`deadline submission of lawyer annual report /by 25/11/2019 1200`

Expected outcome:

`Got it. I've added this task:`<br/>`[D][✗] submission of lawyer annual report (by: 25/11/2019 1200)`<br/>`Now you have 4 tasks in the list.`

### `find <keyword>` - Finds task(s) with description matching the keyword

Show all tasks that matches the specified keyword. Partial matching is also supported.

Example of usage: 

`find doc`

Expected outcome:

`Here are the matching tasks in your list:`<br/>`1.[T][✗] sign documents`
