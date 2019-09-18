# Duke User Guide

1. Introduction
1. Features
    1. Types of Tasks
    1. Add Task
    1. Delete Task
    1. Delete All Tasks
    1. Mark Task Complete
    1. Find Task
    1. Show Task List
    1. Exit Duke
1. Command Summary    

## Introduction
Duke is a personal chat bot for users to manage their tasks. Duke is optimized for Command Line Interface (CLI) usage while also providing a visual Graphical User Interface (GUI) to provide users an easy and simple platform to organize their tasks.

![Image of Duke](https://github.com/choonx99/duke/tree/master/docs/Ui.png)

## Usage
To communicate with Duke, users are required to input commands in the input box located beside the "send" button. The commands accepted by Duke (features of Duke) are show in their respective feature.

## Features
### Types of Tasks
Duke allows the creation of 3 different types of task.

`todo` A basic task with only the task description. No time and date is required.

`deadline` A task that includes description and specifies a date/time for the task to be done by.

`event` A task that includes description and specifies a date/time of when the task will occur.

Each task is shown in this format `[task type][task complete] "description" ("by/at: date time")`

    Example of a todo task: [T][1] cs2103 increments
    Example of a deadline task: [D][0] cs2103 project (by: 13th September 2019, 4:00pm)
    
    Task complete will be 0 if task is not done, 1 if task is done.
    time can be a specific time or a period e.g 1500-1800 will be 3:00pm-6:00pm

### Show Task List
To display the tasks currently in the task list, use the command `list`

    Example: list
    Outcome: Here are the tasks in your list:
             1.[T][0] cs2103 increments
             2.[T][1] cs2103 user guide
             3.[D][0] cs2103 project (by: 11th November 2019, 1:00pm)
             4.[E][1] cs2103 week 3 meeting (at: 2nd February 2019, 1:15pm)

### Add Task
To add a `todo` task, use the command `todo "description"`

    Example: todo CS2103 User Guide
    Outcome: Got it. I've added this task:
             [T][0] CS2103 User Guide
             Now you have 5 tasks in the list.
    
To add a `deadline` task, use the command `deadline "description" /by "dd/mm/yyyy hhmm-hhmm"`

    Example: deadline CS2103 iP increments /by 21/03/2019 1800-1840
    Outcome: Got it. I've added this task:
             [D][0] CS2103 iP increments (by: 21st March 2019, 6:00pm-6:40pm)
             Now you have 6 tasks in the list.

To add a `event` task, use the command `event "description" /at "dd/mm/yyyy hhmm"`
    
    Example: event CS2103 project meeting /at 23/5/2019 1315
    Outcome: Got it. I've added this task:
             [E][0] CS2103 project meeting (at: 23rd May 2019, 1:15pm)
             Now you have 7 tasks in the list.

### Delete Task
To delete a task, use the command `delete "task number*"`

    Example: delete 3
    Outcome: Noted. I've removed this task:
             [D][0] cs2103 project (by: 11th November 2019, 1:00pm)
             Now you have 6 tasks in the list.
          
 *If unsure of task number, you may use the Show Task List function to see the task number of the task you want to delete.
 
### Delete All Tasks
To delete all tasks, use the command `delete all`

    Example: delete all
    Outcome: All tasks have been deleted. Your task list is now empty.

### Mark Task Complete
To mark a task as completed, use the command `done "task number*"`

    Example: done 1
    Outcome: Nice! I've marked this task as done:
             [T][1] cs2103 increments
    
*If unsure of task number, you may use the Show Task List function to see the task number of the task you want to delete.

### Find Task
To find tasks by description, use the command `find "description*"`

    Example: find increments
    Outcome: Here are the matching tasks in your list:
             1.[T][1] cs2103 increments
             2.[D][0] CS2103 iP increments (by: 21st March 2019, 6:00pm-6:40pm)
    
*Input description is non-case-sensitive

### Exit Duke
To exit Duke, use the command `bye`.
Duke will exit immediately after that.

## Command Summary
* Show Task List: `list`
* Add todo: `todo "description"`
* Add deadline: `deadline "description" /by "dd/mm/yyyy hhmm-hhmm"`
* Add event: `event "description" /at "dd/mm/yyyy hhmm"`
* Delete Task: `delete "task number"`
* Delete All Task: `delete all`
* Mark Task Complete: `done "task number"`
* Find Task: `find "description"`
* Exit Duke: `bye`

