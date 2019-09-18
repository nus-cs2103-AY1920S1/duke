# User Guide

<br>
<br>
<br>

## Features

### Feature 1
Description of feature.

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

>Exits Duke.

<br>

### 2. deadline
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

### 3. delete
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

### 4. done
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

### 5. event
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

### 6. event_at
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

### 7. find
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

### 8. help
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

### 9. list
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

### 10. snooze
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

### 11. todo
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
