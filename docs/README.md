# User Guide
**Doko** is a Personal Assistant Chatbot that helps you remember your upcoming **Tasks**.

A Task is something you have to do or take note of. A Task can either be a Todo, Deadline or Event, and are defined below:

### Todo
>A Todo has no due date.
>
>Example: Find a Job

### Deadline
>A Deadline has a due date.
>
>Example: CS2105 Assignment 1

### Event
>An Event has a start date and an end date.
>
>Example: CS2103T Lecture from 4pm - 6pm

<br>
<br>
<br>

## Running
Requirements: Java 11 Runtime Environment

Navigate [here](https://github.com/WeomuCat/duke/releases/latest) and download the **Doko.jar** file. Run the jar file in your favourite terminal emulator with `java -jar Doko.jar`.

<br>
<br>
<br>

## Features

### Adding Tasks
Doko adds a task of your choice to the task list.

[Image](docs/media/AddingTasks.png)

### Completing Tasks
Doko marks a task that you select from the task list as done.

[Image](docs/media/CompletingTasks.png)

### Deleting Tasks
Doko deletes a task that you select from the task list.

[Image](docs/media/DeletingTasks.png)

### Finding Tasks
Doko finds all tasks whose description matches your search keyword.

[Image](docs/media/FindingTasks.png)

### Saving Tasks
Doko saves your tasks automatically whenever you add/change/delete a task.

### Tentative Scheduling of Events
If you are planning an event that can be held on at multiple schedules, Doko can help you note down all schedules. You can then tell Doko your preferred schedule at a later date.

[Image](docs/media/TentativeScheduling.png)

### Snoozing Deadlines
Doko can help you to postpone your deadline tasks by a given duration.

[Image](docs/media/Snoozing.png)

### Recurring Tasks
Doko can take note of tasks that are recurring, and will populate the task list accordingly.

[Image](docs/media/RecurringTasks1.png)
[Image](docs/media/RecurringTasks2.png)

### Help Pages
Doko is able to provide detailed information for all commands.

[Image](docs/media/HelpPage.png)

### Flexible User Input
Doko accepts user input from either it's graphical user interface (GUI) or it's command line interface (CLI).

### Command History
Doko remembers commands that you have entered previously into it's GUI. Press the up and down keys to scroll through the history.

[Image](docs/media/CommandHistory.gif)

<br>
<br>
<br>

## Commands

1. [bye](#1-bye)
2. [deadline](#2-deadline)
3. [delete](#3-delete)
4. [done](#4-done)
5. [event](#5-event)
6. [event_at](#6-event_at)
7. [find](#7-find)
8. [help](#8-help)
9. [list](#9-list)
10. [snooze](#10-snooze)
11. [todo](#11-todo)

<br>

### 1. [bye](#1-bye)
---
##### Usage:

>`bye`

##### Description:

>Exits Doko.

<br>

### 2. [deadline](#2-deadline)
---
##### Usage:

>`deadline <description> /by <due> [/every <recurrence>]`

##### Description:

>Creates a deadline task. A deadline is a task that has a due date.

##### Parameters:

>`<description>`
>
>Required: Yes
>
>Type: String
>
>Description: The description of this deadline.

>`/by <due>`
>
>Required: Yes
>
>Type: ISO-8601 Date
>
>Description: The due date of this deadline.

>`/every <recurrence>`
>
>Required: No
>
>Type: ISO-8601 Duration
>
>Description: The recurrence interval of this deadline.

<br>

### 3. [delete](#3-delete)
---
##### Usage:

>`delete <task>`

##### Description:

>Deletes a task forever.

##### Parameters:

>`<task>`
>
>Required: Yes
>
>Type: Index
>
>Description: The index of the task to delete.

<br>

### 4. [done](#4-done)
---
##### Usage:

>`done <task>`

##### Description:

>Marks a task as done. Well done!

##### Parameters:

>`<task>`
>
>Required: Yes
>
>Type: Index
>
>Description: The index of the task to mark as done.

<br>

### 5. [event](#5-event)
---
##### Usage:

>`event <description> /at <schedule> [/every <recurrence>]`

##### Description:

>Creates an event task. An event has a start date and an end date.

##### Parameters:

>`<description>`
>
>Required: Yes
>
>Type: String
>
>Description: The description of this event.

>`/at <schedule>`
>
>Required: Yes
>
>Type: ISO-8601 Date Range(s)
>
>Description: The schedule(s) of this event.

>`/every <recurrence>`
>
>Required: No
>
>Type: ISO-8601 Duration
>
>Description: The recurrence interval of this event.

<br>

### 6. [event_at](#6-event_at)
---
##### Usage:

>`event_at <task> /at <schedule>`

##### Description:

>Confirms a schedule from a list of tentative schedules in an event.

##### Parameters:

>`<task>`
>
>Required: Yes
>
>Type: Index
>
>Description: The index of the event.

>`/at <schedule>`
>
>Required: Yes
>
>Type: Index
>
>Description: The index of the tentative schedule within the event to choose.

<br>

### 7. [find](#7-find)
---
##### Usage:

>`find <keyword>`

##### Description:

>Searches for all tasks whose description matches a keyword.

##### Parameters:

>`<keyword>`
>
>Required: Yes
>
>Type: String
>
>Description: The needle for any partially matching task description.

<br>

### 8. [help](#8-help)
---
##### Usage:

>`help [<command>] [/all]`

##### Description:

>Explains how a command works.

##### Parameters:

>`<command>`
>
>Required: No
>
>Type: Command Name
>
>Description: The command name to find help for.

>`/all`
>
>Required: No
>
>Type: Flag
>
>Description: If this flag is set, list all available commands.

<br>

### 9. [list](#9-list)
---
##### Usage:

>`list [/all]`

##### Description:

>Lists tasks which are upcoming and not done.

##### Parameters:

>`/all`
>
>Required: No
>
>Type: Flag
>
>Description: If this flag is set, tasks which are overdue or done will be listed.

<br>

### 10. [snooze](#10-snooze)
---
##### Usage:

>`snooze <task> /by <duration>`

##### Description:

>Pushes back the due date of a deadline by a given duration.

##### Parameters:

>`<task>`
>
>Required: Yes
>
>Type: Index
>
>Description: The index of the task to snooze.

>`/by <duration>`
>
>Required: Yes
>
>Type: ISO-8601 Duration
>
>Description: The duration to snooze the task.

<br>

### 11. [todo](#11-todo)
---
##### Usage:

>`todo <description>`

##### Description:

>Creates a todo task. A todo has no due date.

##### Parameters:

>`<description>`
>
>Required: Yes
>
>Type: String
>
>Description: The description of this todo task.
