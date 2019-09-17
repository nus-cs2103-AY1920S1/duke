# User Guide
Duke is an event scheduler.
## Features 

### Feature 1 
Adding Tasks to Duke

## Usage

### `todo` - To add a todo

A todo task will be added to Duke

Example of usage: 

`todo (description of task)`

Expected outcome:
<br>`Got it. I've added this task:`</br>
<br>`[T][✘] example of todo`</br>
<br>`Now you have 1 tasks in the list`</br>

### `event` - To add an event

An event task will be added to Duke

Example of usage: 

`event (description of event) /at 12/12/12`

Expected outcome:
<br>`Got it. I've added this task:`</br>
<br>`[e][✘] example of event (at: December 12, 2012)`</br>
<br>`Now you have 2 tasks in the list`</br>

### `deadline` - To add a deadline

A deadline will be added to Duke

Example of usage: 

`deadline (description of deadline) /at 12/12/12`

Expected outcome:
<br>`Got it. I've added this task:`</br>
<br>`[D][✘] example of deadline (at: December 12, 2012)`</br>
<br>`Now you have 3 tasks in the list`</br>

### Feature 2
Modifying Tasks in Duke

## Usage

### `delete` - To delete a task

The numbered task will be deleted

Example of usage: 

`delete 3`

Expected outcome:
<br>`Noted. I've removed this task:`</br>
<br>`[T][✘] example of deleted task`</br>
<br>`"Now you have 2 tasks in the list.`</br>

### `done` - To mark a task as done

Task will be marked as done

Example of usage: 

`done 2`

Expected outcome:
<br>`Nice! I've marked this task as done:`</br>
<br>`[T][✓] example of todo`</br>
<br>`Now you have 2 tasks in the list`</br>

### Feature 3
Utilities
## Usage

### `list` - To show list of tasks

A list of all tasks will be shown

Example of usage: 

`list`

Expected outcome:
<br>`Here are the tasks in your list:`</br>
<br>`1.[T][✘] example of task`</br>
<br>`2.[E][✓] example of task`</br>
<br>`3.[D][✘] example of task`</br>


### `find` - To find tasks 

List of tasks with key word will be shown

Example of usage: 

`find exam`

Expected outcome:
<br>`Here are the matching tasks in your list:`</br>
<br>`1.[T][✘] physics exam`</br>
<br>`2.[T][✓] cs exam tmr`</br>
<br>`3.[T][✘] exam ends today`</br>

### `bye` - To exit Duke

List of tasks with key word will be shown

Example of usage: 

`bye`

Expected outcome:
<br>`Bye. Hope to see you again soon!`</br>
<br>Find your saved file in the data folder</br>


