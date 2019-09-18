# User Guide
Duke is a task management tool which helps you to keep track of various task. It is a command line interface (CLI) which allows you to input your tasks by entering commands.
## Features 
1. Add tasks
2. Delete task
3. Find task
4. List tasks
5. Mark tasks as 'Done'
6. View the number of task for each type
7. Bye

### Add tasks 
You can create 3 types of task which are `deadline`, `event` and `todo`. 

### Delete task
Delete a task that is in the tasklist.

###Find task
Find a task in the tasklist.

###List task
List out all the task in the tasklist.

###Mark task as 'Done'
Mark the task as 'Done' to show that it has been completed.

###View the number of tasks for each type
Shows the number of tasks for a certain type of task and the number of tasks that are completed and not completed.

###Bye
Exit the application.

## Usage

### `deadline` - To add a deadline task in the tasklist.

Creates a deadline task that will be added to the tasklist with the format of `deadline <description> /by <format of date in dd/mm/yyyy> and <format of time in hhmm>.`

Example of usage: 

`deadline homework /by 12/12/2019 1230 tommorrow`

Expected outcome:

`>Got it. I've added this task:
>[D][]homework (by:12th of December 2019 12:30pm tomorrow)
>Now you have 1 tasks in the list.`

### `event` - To add a event task in the tasklist.

Creates an event task that will be added to the tasklist with the format of `event <description> /at <format of date dd/mm/yyyy> and <format of time in hhmm> respectively.`

Example of usage: 

`event birthday /at 12/12/2019 1230 tommorrow`

Expected outcome:

`>Got it. I've added this task:
>[E][]birthday (at:12th of December 2019 12:30pm tomorrow)
>Now you have 1 tasks in the list.`

### `todo` - To add a todo task in the tasklist.

Creates a todo task that will be added to the tasklist with the format of `event <description>`.

Example of usage: 

`todo swim`

Expected outcome:

`>Got it. I've added this task:
>[T][]swim
>Now you have 1 tasks in the list.`

### `delete` - To delete a task in the tasklist.

Deletes a task in the tasklist with the corresponding index number with the format in `delete <index number>`.

Example of usage: 

`delete 1`

Expected outcome:

`>Noted. I've removed this task:
>[E][]birthday (at:12th of December 2019 12:30pm tomorrow)
>Now you have 2 tasks in the list.`

### `find` - To find a task in the tasklist.

Finds a task in the tasklist using a keyword with the format in `find <keyword>`.

Example of usage: 

`find birthday`

Expected outcome:

`>Here are the matching tasks in your list:
>1.[E][]birthday (at:12th of December 2019 12:30pm tomorrow)`

### `list` - List out all the tasks in the tasklist.

Displays all the tasks in the tasklist  with the format in `list`.

Example of usage: 

`list`

Expected outcome:

`>Here are the tasks in your list:
>1.[E][]birthday (at:12th of December 2019 12:30pm tomorrow)
>2.[T][]swim`

### `done` - Marks a task as 'Done'.

Marks a task in the tasklist as 'Done' with the corresponding index number with the format in `done <index number>`.

Example of usage: 

`done 1`

Expected outcome:

`>Nice!. I've marked this task as done:
>[E][x]birthday (at:12th of December 2019 12:30pm tomorrow)`

### `stats` - Displays the number of tasks for each type.

Displays the number of tasks for the respective type and the number of tasks that has been completed or not completed with the format in `stats`.

Example of usage: 

`stats`

Expected outcome:

`>Number of deadline tasks: 0
>Number of event tasks: 1
>Number of todo tasks: 2
>Number of tasks done: 1
>Number of tasks not done: 2`

### `bye` - Exit the application.

Ends the application after you have finished using it.

Example of usage: 

`bye`

Expected outcome:

`Bye! Hope to see you again soon!`