# User Guide
Obot Wan Kenobi is a chatbot that helps to store simple tasks, such as deadlines and events.
## Features 

### 1. Adding a new to-do task
A to-do task is the most simplest form of a task.

To-do task can be added via the following format:
todo [task_name]

Sample command:

`todo Laundry`

Expected outcome:

`Got it. I've added this task:`\
`[T][x] Laundry`\
`Now you have X tasks in the list`

### 2. Adding a new event task
An event task is similar to a to-do task, escept that it is associated with a particular location, the event location.

Event task can be added via the following format:
event [event_name] /at [event_location]

Sample command:

`event Exam /at MPSH1-C`

Expected outcome:

`Got it. I've added this task:`\
`[E][X] Exam (at: MPSH1-C)`\
`Now you have X tasks in the list`

### 3. Adding a new deadline task
A deadine task is similar to a to-do task, except that it is associated with a particular cut-off time, the due deadline.

Deadline task can be added via the following format:
deadline [deadline_name] /by [deadline_date]

[deadline_date] must be of the following format:
_DD/MM/YYYY HHMM_

Sample command:

`deadline CS Quiz /by 31/12/2019 2359`

Expected outcome:

`Got it. I've added this task:`\
`[D][X] CS Quiz (by: 31st of December 2019, 11.59pm)`\
`Now you have X tasks in the list`

### 4. Viewing task-related statistics
Obot can also give the statistics regarding the number of tasks completed _in the current day._
Tasks completed more than one day ago will not be counted.

To view statistics pertaining to each task (to-do, event or deadline), enter the following command:

stats [event_type]

[event_type] is either: 
* `deadline`
* `todo`
* `event`

Sample command:
`stats event`

Possible outcome 1:

`Events completed today: 1`\
`Well Done!`

Possible outcome 2:

`Events completed today: 0`\
`You can do better! :)`

### 5. Viewing all statistics
Aside from task-related statistics, Obot can also give statistics regarding the _total commmands entered, total tasks deleted and total tasks completed_. These statistics will consider tasks that have previously been marked completed but deleted by the user. 

To view all statistics (which also included the tasks completed in the current day), enter the following sample command:

Sample command:

`stats all`

Possible outcome:

`Listing all statistics`\
`Total Commands Executed: 150`\
`Total Tasks Deleted: 20`


`Total To-Dos Completed: 12`\
`Total Todos Completed TODAY: 2`\


`Total Deadlines Completed: 7`\
`Total Deadlines Completed TODAY: 1`


`Total Events Completed: 4`\
`Total Events Completed TODAY: 2`








