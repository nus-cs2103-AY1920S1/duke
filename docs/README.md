# Duke - User Guide

== Introduction
Duke is a schedule planner targeted at users who prefer Command Line Interface.

== Quick Start

## Features 

### Add different task types
You can add 3 different types of task.
1. Todo
2. Deadline
3. Event

### Find task
You can find a task by it's description.

### Edit task
You can edit a specific task if you have entered the wrong details.

## Usage

### `Keyword` - Describe action

### 1. Add different task types
1. Todo
To add a todo type, `todo <description>`

Example of usage:
`todo get a haircut'

Expected outcome:
`Got it. I've added this task:
[T][\u2718] get a haircut
Now you have 1 tasks in the list.`

2. Deadline
To add a deadline, type `deadline <description> /by <mm/dd/yyyy HHMM>`
Note: HHMM is in 24 hour format. For example 12:30am is 0030 and 11:15pm is 2315.

Example of usage:
`deadline CS2103 IP /by 20/09/2019 2359`

Expected outcome:
`Got it. I've added this task:
[D][\u2718] CS2103 IP (by: Fri 20/09/2019 2359)
Now you have 2 tasks in the list.`

3. Event
To add an event, type `event <description> /at <mm/dd/yyyy HHMM>`
Note: HHMM is in 24 hour format. For example 12:30am is 0030 and 11:15pm is 2315.

Example of usage:
`event attend seminar /at 30/09/2019 1000`

Expected outcome:
`Got it. I've added this task:
[E][\u2718] attend seminar (at: Mon 30/09/2019 1000)
Now you have 3 tasks in the list.`

### 2. Show all the task
To show all task, type `list`

Expected outcome:
`Here are the tasks in your list
1. [T][\u2718] get a haircut
2. [D][\u2718] CS2103 IP (by: Fri 20/09/2019 2359)
3. [E][\u2718] attend seminar (at: Mon 30/09/2019 1000)`

### 3. Mark task as done
To mark a task as done, type `done <task number>'

Example of usage:
`done 1`

Expected outcome:
`Nice! I've marked this task as done:
1. [T][\u2713] get a haircut`

### 4. Edit task
To edit a task, type `edit <task number> <field to be changed> <new content>`
Note: `<field to be changed>` for `event` and `deadline` are `date` or `description`. For `todo`, `<field to be changed>` is only `description`.

Example of usage:
`edit 3 description date 01/10/2019 1300`

Expected outcome:
`I've updated this task as:
[E][\u2718] date 01/10/2019 1300 (at: Mon 30/09/2019 1000)`

### 5. Find task
To find a task, type `find <keyword>`
Note: find is case sensitive so make. See usage example below:

Example of usage:
`find CS`

Expected outcome:
`Here are the matching tasks in your list:
1. [D][\u2718] CS2103 IP (by: Fri 20/09/2019 2359)`

Example of usage:
`find cs`

Expected outcome:
`Here are the matching tasks in your list:`

### 6. Delete task
To delete a task, type `delete <task number>`

Example of usage:
`delete 2`

Expected outcome:
`Noted. I've removed this task:
2. [D][\u2718] CS2103 IP (by: Fri 20/09/2019 2359)
Now you have 2 tasks in the list.`

``` 7. Close Duke
To exit from Duke, type `bye`
__Warning: All the changes you have made in this session is save only when you type `bye`__