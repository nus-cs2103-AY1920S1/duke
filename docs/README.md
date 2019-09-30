# Duke - User Guide

__By Chen Anqi, Sept 2019__

## 1. Introduction
Duke is a chat bot that help users track their tasks, deadlines and events. 
While Duke has a Graphical User Interface (GUI), it is optimized for those who prefer to work with a Command Line Interface (CLI).
If you can type fast, Duke can assist you in managing your tasks faster than traditional GUI apps.
Interested? Read the "Quick Start" below to get started. Enjoy!

## 2. Quick Start

- Ensure you have Java `11` or above installed in your Computer.
- Download the latest `duke-{version}.jar` [here](https://github.com/anqichen9856/duke/releases).
- Copy the file to the folder you want to use as the home folder for your Duke.
- Double-click the file to start the app. The GUI should appear in a few seconds.
![Ui image](https://github.com/anqichen9856/duke/docs/Ui.png)

- Type the command in the command box and press `Enter` to execute it.
e.g. typing `list` and pressing `Enter` will display a list of your tasks, deadlines and events.
- Some example commands you can try:

- `todo read books` : adds a normal task `read books` to Duke.
- `delete 3` : deletes the 3rd task shown in the current list
- `exit` : exits the app

- Refer to "Features" & "Usage" below for details of each command.

## 3. Features 

### Add tasks to track them
You can let Duke keep track of your tasks by providing the _description_ and _time_ (if applicable).
Duke supports three types of tasks:
* _Normal Task_: A task to be done, without a specific time.
* _Deadline_: A task that is due by a specific time.
* _Event_: An event that starts at a specific time and ends at a specific time.

### Update an existing task
You can update the _description_ or _time_ (only if the task is a deadline or event) of an existing task.

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
 
### Load past tasks from a local file
Every time Duke is launched, it attempts to load past tasks from 
{current directory}/DukeStorage/tasks.txt. If loading fails, it creates a new file to 
be stored in the above mentioned location.

### Exit the app
You can exit Duke by typing a single word _"bye"_, and the app will be automatically terminated.


## 4. Usage 

### `todo` - add a normal task

To add a normal task, type `todo` followed by the _description_ of the task, and it will be recorded by Duke. 
By default, every newly added task is marked as undone (indicated by 'X').

Example of usage: 

```
todo read books
```

Expected outcome:

```
 Got it. I've added this task: 
   [T][X] read books
 Now you have 1 task(s) in the list.
```
 
### `deadline` - add a task with a deadline
 
To add a task with a specific deadline, type `deadline` followed by: (i) the _description_ of the task, 
(ii) `/by`, (iii) the _due time_, in `d/MM/yyyy HHmm` format. It will be recorded by Duke. 
By default, every newly added task is marked as undone (indicated by 'X').
 
Example of usage: 
 
```
deadline project submission /by 2/10/2019 2359
```
 
Expected outcome:
 
```
 Got it. I've added this task: 
   [D][X] project submission (by: Wed, 2 Oct 2019, 11:59PM)
 Now you have 2 task(s) in the list.
```
   
### `event` - add an event with a time of occurrence
To add an event with specific start time and end time, type `event` followed by: (i) the _description_ of the event, 
(ii) `/at`, (iii) the _start time_ and _end time_, in `d/MM/yyyy HHmm-HHmm` format. It will be recorded by Duke. 
By default, every newly added task is marked as undone (indicated by 'X'). 

Example of usage: 
 
```
event team meeting /at 18/09/2019 1600-1800
```
 
Expected outcome:
 
```
 Got it. I've added this task: 
   [E][X] team meeting (at: Wed, 18 Sep 2019, 4:00PM to 6:00PM)
 Now you have 3 task(s) in the list.
```
 
### `update` - update an existing task
To update an existing task, type `update` followed by: (i) the task index, 
(ii) the attribute which you want to update, i.e. `description` or `time` (if the task is a deadline or event), 
(iii) the new value of the attribute. The task will be updated without changing its position in the list.
_Note that the index is with respect to the task list, not the current displayed list._
 
Example of usage #1: 
  
```
update 1 description read five books
```
  
Expected outcome #1:
  
```
 Ok! I've updated this task: 
   [T][X] read five books
 Now you have 3 task(s) in the list.
```
 
Example of usage #2: 
  
```
update 2 time 19/09/2019 2359
```
  
Expected outcome #2:
  
```
 Ok! I've updated this task: 
   [D][X] project submission (by: Thu, 19 Sep 2019, 11:59PM)
 Now you have 3 task(s) in the list.
```
Example of usage #3: 
  
```
update 3 time 25/09/2019 1400-1600
```
  
Expected outcome #3:
  
```
 Ok! I've updated this task: 
   [E][X] team meeting (at: Wed, 25 Sep 2019, 2:00PM to 4:00PM)
 Now you have 3 task(s) in the list.
```
 
### `done` - mark a task as done
To mark an existing task as completed, type `done` followed by the index of the task.
Its status in Duke will change from 'X' (uncompleted) to 'O' (completed).  
_Note that the index is with respect to the task list, not the current displayed list._
 
Example of usage: 
  
```
done 1
```
  
Expected outcome:
  
```
 Nice! I've marked this task as done: 
   [T][O] read five books
```

### `list` - display all existing tasks
To have a look at all the tasks in the list, type `list`. 
Duke will display a complete list of existing tasks that have been recorded.
 
Example of usage: 
  
```
list
```
  
Expected outcome:
  
```
 Here are the tasks in your list:
 1.[T][O] read five books
 2.[D][X] project submission (by: Thu, 19 Sep 2019, 11:59PM)
 3.[E][X] team meeting (at: Wed, 25 Sep 2019, 2:00PM to 4:00PM)
```

### `find` - search by keyword (or phrase)
To search for all tasks that contain the specified keyword (or phrase), type `find` 
followed by the keyword (or phrase). Duke will display a list of matching results. 
_Note that the search is case sensitive._
 
Example of usage: 
  
```
find submission
```
  
Expected outcome:
  
```
 Here are the matching tasks in your list:
 1.[D][X] project submission (by: Thu, 19 Sep 2019, 11:59PM)
```

### `delete` - delete an existing task from the list
To delete an existing task, type `delete` followed by the index of the task. 
It will be removed from the task list and cease to be tracked by Duke.
_Note that the index is with respect to the task list, not the current displayed list._
 
Example of usage: 
  
```
delete 1
```
  
Expected outcome:
  
```
 Noted. I've removed this task: 
   [T][O] read five books
 Now you have 2 task(s) in the list.
```

### `bye` - exit Duke
To exit Duke, type `bye`. Duke will automatically terminate in one second.
 
Example of usage: 
  
```
bye
```
  
Expected outcome:
  
```
 Bye. Hope to see you again soon!
 (Exits)
```

## 5. Command Summary

* *Add a normal task* `todo [description]` 
e.g. `todo read books`
* *Add a deadline* `deadline [description] /by [d/MM/yyyy HHmm]`
e.g. `deadline project submission /by 20/09/2019 2359`
* *Add an event* `event [description] /at [d/MM/yyyy HHmm-HHmm]` 
e.g. `event meeting /at 1/10/2019 1400-1600`
* *Delete a task* : `delete INDEX` 
e.g. `delete 3`
* *Mark a task as done* : `done INDEX` 
e.g. `done 1`
* *Update a task* : `update INDEX [attribute] [new value]` 
e.g. `update 2 time 20/09/2019 2300`
* *Find a task* : `find KEYWORD`
e.g. `find project`
* *List all tasks* : `list`
* *Exit the app* : `exit`