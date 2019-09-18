# Duke - User Guide

## Introduction
Duke is a schedule planner targeted at users who prefer Command Line Interface.

## Features 

### Add different task types
You can add and keep track of 3 different types of task.
1. Todo: a task with no deadline
2. Deadline: a task to be completed by a certain date.
3. Event: attend an event at a given date.

### List all task
Show all the tasks with one command.

### Mark task as done
You can enjoy the satisfaction of ticking your task as done after completing them.

### Edit task
You can edit a specific task if you have entered the wrong details.

### Find task
Too many task? You can find them by their descriptions.

### Delete task
Do not want to keep track of a task anymore? You can remove them from the list.

## Usage

### 1. Add different task types
1. Todo

    To add a todo, type `todo <description>`
        
    Example of usage: <br/>
    `todo get a haircut`

    Expected outcome: <br/>
    `Got it. I've added this task:` <br/>
    `[T][✘] get a haircut` <br/>
    `Now you have 1 tasks in the list.`

2. Deadline

    To add a deadline, type `deadline <description> /by <dd/MM/yyyy HHmm>` <br/>
    **Note: HHmm is in 24 hour format. For example 12:30am is 0030 and 11:15pm is 2315.**
    
    Example of usage: <br/>
    `deadline CS2103 IP /by 20/09/2019 2359`
    
    Expected outcome: <br/>
    `Got it. I've added this task:` <br/>
    `[D][✘] CS2103 IP (by: Fri 20/09/2019 2359)` <br/>
    `Now you have 2 tasks in the list.` <br/>

3. Event

    To add an event, type `event <description> /at <dd/MM/yyyy HHmm>` <br/>
    **Note: HHmm is in 24 hour format. For example 12:30am is 0030 and 11:15pm is 2315.**
    
    Example of usage: <br/>
    `event attend seminar /at 30/09/2019 1000`
    
    Expected outcome: <br/>
    `Got it. I've added this task:` <br/>
    `[E][✘] attend seminar (at: Mon 30/09/2019 1000)` <br/>
    `Now you have 3 tasks in the list.`

### 2. Show all the task
To show all task, type `list`

Expected outcome: <br/>
`Here are the tasks in your list` <br/>
`1. [T][✘] get a haircut` <br/>
`2. [D][✘] CS2103 IP (by: Fri 20/09/2019 2359)` <br/>
`3. [E][✘] attend seminar (at: Mon 30/09/2019 1000)`

### 3. Mark task as done
To mark a task as done, type `done <task number>`

Example of usage: <br/>
`done 1`

Expected outcome: <br/>
`Nice! I've marked this task as done:` <br/>
`1. [T][✓] get a haircut`

### 4. Edit task
To edit a task, type `edit <task number> <field to be changed> <new content>` <br/>
**Note: <br/> 
For `event` and `deadline`, `<field to be changed>` are `date` or `description`. <br/>
For `todo`, `<field to be changed>` is only `description`.**

Example of usage: <br/>
`edit 3 date 01/10/2019 1300`

Expected outcome: <br/>
`I've updated this task as:` <br/>
`[E][✗] attend seminar (at: Tue 01/10/2019 1300)`

### 5. Find task
To find a task, type `find <keyword>` <br/>
**Note: find is case sensitive. See usage example 2 below:**

Example of usage 1: <br/>
`find CS`

Expected outcome 1: <br/>
`Here are the matching tasks in your list:` <br/>
`1. [D][✗] CS2103 IP (by: Fri 20/09/2019 2359)`

Example of usage 2: <br/>
`find cs`

Expected outcome 2: <br/>
`Here are the matching tasks in your list:`

### 6. Delete task
To delete a task, type `delete <task number>`

Example of usage: <br/>
`delete 2`

Expected outcome: <br/>
`Noted. I've removed this task:` <br/>
`2. [D][✗] CS2103 IP (by: Fri 20/09/2019 2359)` <br/>
`Now you have 2 tasks in the list.`

### 7. Close Duke
To exit from Duke, type `bye`

**Warning: All the changes you have made in this session will be saved only when you type `bye`**