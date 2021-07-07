# Duke User Guide

## 1. Introduction
Duke is an application that can help you to record your tasks to do and
and track whether these tasks are finished. 

## 2. Definition of Different Task
There are three types of task that can be recorded in Duke.
  1. **Todo** : Anything that you can do without any specification.
  2. **Event** : A Task that you can do with specification of venue.
  3. **Deadline** : A Task that you can do with with specification of time,
  in the format of `dd/MM/YYYY HHmm`. You can also edit the time if there is any update.
  
## 3. Features 

### 3.1. Add Task
  Add any task that haven't done to Duke. 
### 3.2. Delete Task
  Delete the task that is in Duke.
### 3.3. Mark Task as Done
  Mark any undone task in Duke to be done.
### 3.4. Edit Deadline
  Edit the time of Deadline.
### 3.5. Find Tasks with Certain Keyword
  Use keyword to search for tasks in Duke that contain the same word.
### 3.6. List all Task in Duke
  View all the existing tasks in Duke in a list order.
### 3.7. Terminate Duke
  Stop Duke from running and terminate it.

## 4. Usage

### 4.1 `todo` - Add todo task to Duke

format : `todo DESCRIPTION`

Add todo task to Duke, and Duke will show the information of the succeed todo.

Example of usage: 

`todo sleep`

Expected outcome:

`Got it. I've added this task:`

`[T][✘] sleep`
  
`Now you have NUMBER tasks in the list.` 

### 4.2 `event` - Add event task to Duke

format : `event DESCRIPTION /at PLACE`

Add event task to Duke, and Duke will show the information of the succeed todo.

Example of usage: 

`event sleep /at home`

Expected outcome:

`Got it. I've added this task:`

`[E][✘] sleep(at: home)`
  
`Now you have NUMBER tasks in the list.` 

### 4.3 `deadline` - Add deadline task to Duke

format : `deadline DESCRIPTION /by dd/MM/YYYY HHmm`

Add deadline task to Duke, and Duke will show the information of the succeed todo.

Example of usage: 

`deadline sleep /by 06/09/2019 2200`

Expected outcome:

`Got it. I've added this task:`

`[D][✘] sleep(by: WEEKDAY MONTH DATE HH:mm:ss TIMEZONE YEAR)`
  
`Now you have NUMBER tasks in the list.` 

### 4.4 `delete` - Delete an Existing Task in Duke

format : `delete INDEX`

Delete a task in the list.

Example of usage: 

`delete 4`

Expected outcome:

`Noted. I have removed this task:`

`TASK DESCRIPTION`
  
`Now you have NUMBER tasks in the list.`

### 4.5 `done` - Mark an Existing Task as Done

format : `done INDEX`

Mark a task as done in the list.

Example of usage: 

`done 4`

Expected outcome:

`Nice! I have marked this task as done:`

`TASK DESCRIPTION`

### 4.6 `edit` - Edit the Time of a Deadline Task in Duke

format : `edit INDEX dd/MM/YYYY HHmm`

Update the time of a deadline task in the list.

Example of usage: 

`edit 10 02/10/2023 1800`

Expected outcome:

`Got it. I have edited this task:`

`TASK DESCRIPTION`
  
`Now you have NUMBER tasks in the list.`

### 4.7 `find` - Find All Task that Contains Keyword

format : `find KEYWORD`

Find all the tasks that contains the keyword in Duke.

Example of usage: 

`find sleep`

Expected outcome:

`Here are the matching tasks in your list:`

And list all the matching tasks in this format:

`INDEX. TASK DESCRIPTION`

### 4.8 `list` - List All the Tasks in Duke

format : `list`

List all the tasks in Duke.

Example of usage: 

`list`

Expected outcome:

List all the matching tasks in this format:

`INDEX. TASK DESCRIPTION`

### 4.8 `bye` - Terminate Duke

format : `bye`

Terminate Duke and close window after 3 second.

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`

And close the window after 3 seconds.