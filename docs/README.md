# User Guide for Duke Trump

Duke Trump is an application where you can handle your daily tasks in an typing based environment.

## Features of Duke Trump

### Typing based

For computer professionals who can type fast and prefer typing instead of clicking, Duke Trump can be your dream come true, everything is typing based. 

### Able to save tasks

Duke Trump can save the tasks you added and modified each time and reload them each time they starts

### Marking tasks

Duke Trump can help you mark that each of your tasks have been finished or not. So that each time you see your task list, you will know what to do.

## Usage

In Duke Trump, tasks are classified into three types, namely todo, event and deadlines. Todos are tasks needs to be finished but do not have a specific time or deadline. deadlines are tasks with a deadline and events are tasks that needs to be done at a specific time. Commands in duke are used to play around with these three types of tasks.

### `todo` - Add a todo task to the list

The task name should be specified after the `todo` keyword.

Example of usage: 

`todo eat dinner`

Expected outcome:

`Got it master! I have added this task:` <br />
  `[T][X] eat dinner`
  
### `event` - Add an event task to the list

The task name should be specified after the `event` keyword. After that,
add keyword `/at` to specify the event time. The event time must be in the 
form dd/MM/yyyy hh:mm:ss.

Example of usage:

`event mid-autmn festival celebration /at 13/09/2019 20:00:00`

Expected outcome:

`Got it master! I have added this task:` <br />
  `[E][X] mid-autumn festival celebration (at:` <br />
  `13/09/2019 20:00:00)`

### `deadline` - Add a deadline event to the list

The task name should be specified after the `deadline` keyword. After that,
add keyword `/by` to specify the deadline time. The deadline time must be in the 
form dd/MM/yyyy hh:mm:ss.

Example of usage:

`deadline software engineering project /by 20/09/2019 23:00:00`

Expected outcome:

`Got it master! I have added this task:` <br />
  `[D][X] software enginnering project (by:` <br/>
  `20/09/2019 23:00:00)`
  
### `list` - List out all added tasks

Example of usage:

`list`

Expected outcome:

`1. [T][X] eat dinner`<br />
`2. [E][X] mid-autumn festival celebration (at:` <br />
   `13/09/2019 20:00:00)` <br />
`3. [D][X] software enginnering project (by:` <br/>
   `20/09/2019 23:00:00)`
 
### `done` - Mark a task as finished
follow a number behind the keyword `done` to indicate which task in the list do you want to set as finished.

Example usage:

`done 1`

Expected outcome:

`Nice master! I have set this task as done:` <br />
`[T][√] eat dinner`

### `delete` - Remove a task from the list
follow a number behind the keyword `delete` to indicate which task in the list you want to delete.

Example usage:

`delete 1`

Expected outcome:

`Noted master! I have removed this task:` <br />
`[T][√] eat dinner` <br />
`Now you have 2 tasks in the list`

### `find` - search the list for tasks with target strings in the task name
Add the target string behind the keyword `find`.

Example usage:

`find software`

Expected outcome:

`Here are the maching tasks in your list, master:` <br />
`1. [D][X] software enginnering project (by:` <br/>
   `20/09/2019 23:00:00)`

### `bye` - Exit the program and save the modified task list

Example usage:

`bye`

Expected outcome:

The application window closes.

### `slot` - Add a new possible slot to an event task

The task number and the new slot time should be added behind the `slot keyword`.

Example usage:

`slot 1 13/09/2019 21:00:00`

Expected outcome:

`Got it master! I have added this slot to the following event:` <br />
`[E][X] mid-autumn festival celebration (at:` <br />
`13/09/2019 20:00:00` <br />
`13/09/2019 21:00:00)`

### `specify` - specify the task time for an event

The task number and the specified slot should be added behind the keyword `specify`.

Example usage:

`specify 1 13/09/2019 20:00:00`

Expected outcome:

`Got it master! I have specified this slot to the following event:` <br />
`[E][X] mid-autumn festival celebration (at:` <br />
`13/09/2019 20:00:00` <br />
