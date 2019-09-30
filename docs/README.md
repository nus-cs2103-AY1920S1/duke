# User Guide

## Features 

### 1. List all tasks: `list`
Shows a list of all tasks (todos, events and deadlines) in Duke.\
Format: `list`

### 2. Adding a todo: `todo`
Adds a new todo task to Duke.\
Format: `todo DESCRIPTION`\
Example:
* `todo Grocery Shopping` adds a todo task with description "Grocery Shopping"

### 3. Adding a deadline: `deadline`
Adds a new deadline task to Duke.\
Format: `deadline DESCRIPTION /by DD/MM/YYYY HHMM`\
Example:
* `deadline CS2103 Quiz /by 18/09/2019 2359` adds a deadline task with description "CS2103 Quiz" and is due on 18 September 2019, 23:59p

### 4. Adding an event: `event`
Adds a new event task to Duke.\
Format: `event DESCRIPTION /at DD/MM/YYYY HHMM`\
Example:
* `event CS2103 Team Meeting /at 23/09/2019 1200` adds an event task with description "CS2103 Team Meeting" that happens on 23rd September 2019, 12:00pm.

### 5. Deleting a task: `task`
Deletes a task based on its task number.\
Format: `delete INDEX`\
Example:
* `delete 3` deletes the 3rd task displayed when the `list` command is run

### 6. Marking a task as done: `done`
Marks a task as done based on its task number.\
Format: `done INDEX`\
Example:
* `done 2` marks the 2nd task displayed when the `list` command is run as done

### 7. Finding a task: `find`
Returns a list of tasks that contains the given keyword.\
Format: `find STRING_TO_FIND`\
Example:
* `find CS2103` returns a list of all tasks that contains the keyword CS2103

### 8. Adding priorities to the tasks: `priority`
Adds a priority (high, medium or low) to the task based on its task number.\
Format: `priority INDEX PRIORITY`\
Example:
* `priority 1 high` marks the 1st task displayed when the `list` command as high priority.

### 9. Exiting the programme: `bye`
Exits the program.\
Format: `bye`

