# User Guide for Duke

## Your Personal Assistant Chat Bot!
<img src="https://raw.githubusercontent.com/Q-gabe/duke/master/docs/Ui.png" alt = "Duke Preview" width="500" />

## Features 

### Track your tasks hassle-free!
Duke helps you track your tasks from your day-to-day life easily using an interactive interface!

### Keeping tabs on deadlines and events? No problem!
Duke is also able to help you keep track of deadlines and events with specific timing parameters.

### Prefer CLI?
Duke is designed for users who prefer using CLI instead of traditional mouse users.

## Usage

### Adding a To-do Task:

Creates a new to-do task.

##### Example of usage:
	todo Get groceries from Woolworths

##### Expected outcome:
	Got it. I've added this task:
	  [T][✘] Get groceries from Woolworths
	You now have 1 task in the list.


### Adding an Event:

Creates a new event task with a specified date and time.

##### Example of usage:
	event Meet family at QV /at 25/09/2019 2000

##### Expected outcome:
	Got it. I've added this task:
	  [E][✘] Meet family at QV (at: 25/09/2019 2000)
	You now have 2 task in the list.


### Adding a Deadline

Creates a new deadline task with a specified date and time.

##### Example of usage:
	deadline Submit iP for CS2103T /by 30/09/2019 2359

##### Expected outcome:
	Got it. I've added this task:
	  [D][✘] Submit iP for CS2103T (by: 30/09/2019 2359)
	You now have 3 task in the list.


### List all tasks

List all the tasks currently tracked by Duke.

##### Example of usage:
	list

##### Expected outcome:
	Here are the tasks in your list:
	  1.[T][✘] Get groceries from Woolworths
	  2.[E][✘] Meet family at QV (at: 25/09/2019 2000)
	  3.[D][✘] Submit iP for CS2103T (by: 30/09/2019 2359)`


### Find tasks

Finds all tasks that match or contain the search query.

##### Example of usage:
	find groceries

##### Expected outcome:
	Here are the matching tasks in your list:
	  1.[T][✘] Get groceries from Woolworths


### Mark Task as Done

Marks a task status as Done.

##### Example of usage:
	done 1

##### Expected outcome:
	Nice! I've marked this task as done:
	  1.[T][✓] Get groceries from Woolworths


### Delete Task

Delete task from the task list.

##### Example of usage:
	delete 1

##### Expected outcome:
	Noted. I've removed this task:
	  1.[T][✓] Get groceries from Woolworths
	Now you have 2 tasks in the list.


### Closing Duke

Closes Duke.

##### Example of usage:

	bye
