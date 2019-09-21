# User Guide
Obot Wan Kenobi is a chatbot based on the persona of Jedi Master Obi-Wan Kenobi. He can handle basic tasks such as recording to-do, deadline and event tasks; as well as output relevant statistics based on the user's history.

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
__DD/MM/YYYY HHMM__

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
Aside from task-related statistics, Obot can also give statistics regarding the __total commands entered, total tasks deleted__ and __total tasks completed__. These statistics will consider tasks that have previously been marked completed but deleted by the user. 

To view all statistics (which also included the tasks completed in the current day), enter the following sample command:

Sample command:

`stats all`

Possible outcome:

`Listing all statistics`\
`Total Commands Executed: 150`\
`Total Tasks Deleted: 20`


`Total To-Dos Completed: 12`\
`Total Todos Completed TODAY: 2`


`Total Deadlines Completed: 7`\
`Total Deadlines Completed TODAY: 1`


`Total Events Completed: 4`\
`Total Events Completed TODAY: 2`

### 6. Resetting global statistics
Global statistic values can be reset. However, dynamic statistic values (Feature 4) cannot be reset.

To reset global statistic values, enter the following sample code: 

`stats reset`

To view the outcome, view all statistics with:
`stats all`

Expected outcome:

`Listing all statistics`\
`Total Commands Executed: 1` - Number of total commands executed will be 1 as `stats all` has been executed.


`Total Tasks Deleted: 0`


`Total To-Dos Completed: 0`\
`Total Todos Completed TODAY: 2` - Dynamic statistic based on when a `todo` was marked completed.


`Total Deadlines Completed: 0`\
`Total Deadlines Completed TODAY: 1` - Dynamic statistic based on when a `deadline` was marked completed.


`Total Events Completed: 0`\
`Total Events Completed TODAY: 2` - Dynamic statistic based on when aa `event` was marked completed.

### 7. Exiting the application
Obot can be exited by entering the following command:

`bye`

Expected outcome:

`The Force will be with you, always`







