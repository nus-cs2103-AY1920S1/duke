# User Guide
Obot Wan Kenobi is a chatbot that helps to store simple tasks, such as deadlines and events.
## Features 

### Adding a new to-do task
A to-do task is the most simplest form of a task.

To-do task can be added via the following format:
todo [task_name]

Sample command:

`todo Laundry`

Expected outcome:

`Got it. I've added this task:`\
`[T][x] Laundry`\
`Now you have X tasks in the list`

### Adding a new event task
An event task is similar to a to-do task, escept that it is associated with a particular location, the event location.

Event task can be added via the following format:
event [event_name] /at [event_location]

Sample command:

`event Exam /at MPSH1-C`

Expected outcome:

`Got it. I've added this task:`\
`[E][X] Exam (at: MPSH1-C)`\
`Now you have X tasks in the list`

### Adding a new deadline task
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

### Viewing all statistics




