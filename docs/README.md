# User Guide

## Features 

### Feature 1 
Adding a **Task** to your list
* A todo Task : No date involved
* A deadline Task : Which has to be done *by* a certain date
* An event Task: : Which has to done *at* a certain date

### Feature 2
Deleting a **Task** from your list

### Feature 3
Marking a **Task** as done

### Feature 4
Displaying the list of **all Tasks** you have

### Feature 5
Postponing a **Task**

### Feature 6
Find a **Task** using *keywords* 


## Usage

### `todo` - adding a todo to the list
adds a todo without any date.

Example of usage: 

`todo cycle`

Expected outcome:

	Got it. I've added this task:
	[T][✘] cycle
	Now you have 1 task in the list.

-----

### `deadline` - adding a deadline to the list
adds a deadline and the date that it should done by

Example of usage: 

`deadline drop CS /by 30/09/2019 2359`

Expected outcome:

	Got it. I've added this task:
	[D][✘] drop CS (by 30/09/2019 2359)
	Now you have 2 tasks in the list.

-----

### `event` - adding an event to the list
adds an event and the date that it is at

Example of usage: 

`event McDonald's interview /at 1/10/2019 1300`

Expected outcome:

	Got it. I've added this task:
	[E][✘] McDonald's interview (at: 1/10/2019 1300)
	Now you have 3 tasks in the list.

-----

### 'list' - list all your tasks
show the list of tasks and their status

Example of usage:

`list`

Expected outcome:

	Here are the tasks in your list:
	1. [T][✘] cycle
	2. [D][✘] drop CS (by 30/09/2019 2359)
	3. [E][✘] McDonald's interview (at: 1/10/2019 1300)

-----

### 'done' - mark you task as done
mark a task as completed on your list, using its index.

Example of usage:

`done 2`

Expected outcome:

	Nice! I've marked this task as done:
	[D][✔] drop CS (by 30/09/2019 2359)

-----

### 'postpone' - change the date of your task
change the date of your deadline or event on the list, using its index.

Example of usage:

`postpone 3 2/10/2019 1000`

Expected outcome:

	Noted. I've postponed this task:
	[E][✘] McDonald's interview (at: 2/10/2019 1000)

-----

### 'delete' - delete a task
delete a task from the list, using its index

Example of usage:

`delete 1`

Expected outcome:

	Noted. I've removed this task:
	[T][✘] cycle

-----

### `find` - find a task
find a task using keywords. A list of tasks containing the keywords will be displayed.

Example of usage:

`find CS`

Expected outcome:

	Here are the matching tasks in the list:
	1. [D][✘] drop CS (by 30/09/2019 2359)

-----


### `bye` - exit the program
exit and close the program

Example of usage:

`bye`

Expected outcome:

	Bye. Hope to see you again soon!

-----