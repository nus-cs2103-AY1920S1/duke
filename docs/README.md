# User Guide - Duke


## Features 

### Add tasks to track them
You can let Duke keep track of your tasks by providing the _description_ and _time_ (if applicable).
Duke supports three types of tasks:
* _Normal Task_: A task to be done, without a specific time.
* _Deadline_: A task that is due by a specific time.
* _Event_: An event that will occur at a specific time.

### Update an existing task
You can update the _description_ or _time_ (if applicable) of an existing task.

### Mark a task as done
You can mark an existing task in the list as done. 

### Display all the tasks
You can let Duke display all current tasks stored in the list, regardless of the status of completion,
in the order of their creation. 

### Search by keyword (or phrase)
You can find a task by searching a keyword (or phrase) in the current task list, and get all matching results 
(i.e. tasks that contain the keyword or phrase) displayed in order of their appearance in the list.

### Delete an existing task
If you no longer wants a task to be tracked, you can delete it from the task list.

### Save current tasks in a local file
After every single modification to your task list, your latest tasks are automatically
saved in a local file on your hard drive, presented in an easily understandable format. 
 
### Load past tasks upon launch
Every time Duke is launched, it automatically loads your past tasks from the local file.
If loading fails, it creates a new task list to be stored in the same location.

### Exit the app
You can exit Duke by typing a single word _"bye"_, and the app will be automatically terminated.


## Usage 

### `todo` - add a normal task

To add a normal task, type `todo` followed by the _description_ of the task, and it will be recorded by Duke. 
By default, every newly added task is marked as undone.

Example of usage: 

`todo read books`

Expected outcome:

```
 Got it. I've added this task: 
   [T][✘] read books
 Now you have 1 task(s) in the list.
```
 
### `deadline` - add a task with a deadline
 
To add a task with a specific deadline, type `deadline` followed by: (i) the _description_ of the task, 
(ii) `/by`, (iii) the _due time_, in `d/MM.yyyy HHmm` format. It will be recorded by Duke. 
By default, every newly added task is marked as undone.
 
Example of usage: 
 
`deadline project submission /by 2/10/2019 2359`
 
Expected outcome:
 
```
 Got it. I've added this task: 
   [D][✘] project submission (by: Wed, 2 Oct 2019, 11:59PM)
 Now you have 2 task(s) in the list.
```
   
### `event` - add an event with a time of occurrence
To add an event with a specific time of occurrence, type `event` followed by: (i) the _description_ of the event, 
(ii) `/at`, (iii) the _time of occurrence_, in `d/MM.yyyy HHmm` format. It will be recorded by Duke. 
By default, every newly added task is marked as undone.

Example of usage: 
 
`event team meeting /at 18/09/2019 1600`
 
Expected outcome:
 
```
 Got it. I've added this task: 
   [E][✘] team meeting (at: Wed, 18 Sep 2019, 4:00PM)
 Now you have 3 task(s) in the list.
```
 
### `update` - update an existing task
To update an existing task, type `update` followed by: (i) the task index, 
(ii) the attribute which you want to update, i.e. `description` or `time` (if applicable), 
(iii) the new value of the attribute. The task will be updated without changing its position
in the list.
 
Example of usage #1: 
  
`update 1 description read five books`
  
Expected outcome:
  
```
 Ok! I've updated this task: 
   [T][✘] read five books
 Now you have 3 task(s) in the list.
```
 
Example of usage #2: 
  
`update 2 time 19/09/2019 2359`
  
Expected outcome:
  
```
 Ok! I've updated this task: 
   [D][✘] project submission (by: Thu, 19 Sep 2019, 11:59PM)
 Now you have 3 task(s) in the list.
```
 
### `done` - mark a task as done
To mark an existing task as completed, type `done` followed by the index of the task.
Its status in Duke will change from a cross (uncompleted) to a tick (completed).  
 
Example of usage: 
  
`done 1`
  
Expected outcome:
  
```
 Nice! I've marked this task as done: 
   [T][✓] read five books
```

### `list` - display all existing tasks
To have a look at all the tasks in the list, type `list`. 
Duke will display a complete list of existing tasks that have been recorded.
 
Example of usage: 
  
`list`
  
Expected outcome:
  
```
 Here are the tasks in your list:
 1.[T][✓] read five books
 2.[D][✘] project submission (by: Thu, 19 Sep 2019, 11:59PM)
 3.[E][✘] team meeting (at: Wed, 18 Sep 2019, 4:00PM)
```

### `find` - search by keyword (or phrase)
To search for all tasks that contain the specified keyword (or phrase), type `find` 
followed by the keyword (or phrase). Duke will display a list of matching results. 
 
Example of usage: 
  
`find submission`
  
Expected outcome:
  
```
 Here are the matching tasks in your list:
 1.[D][✘] project submission (by: Thu, 19 Sep 2019, 11:59PM)
```

### `delete` - delete an existing task from the list
To delete an existing task, type `delete` followed by the index of the task. 
It will be removed from the task list and cease to be tracked by Duke.
 
Example of usage: 
  
`delete 1`
  
Expected outcome:
  
```
 Noted. I've removed this task: 
   [T][✓] read five books
 Now you have 2 task(s) in the list.
```

### `bye` - exit Duke
To exit Duke, type `bye`. Duke will automatically terminate in one second.
 
Example of usage: 
  
`bye`
  
Expected outcome:
  
```
 Bye. Hope to see you again soon!
 (Exits)
```