# User Guide

## Features 

### Feature 1 - Add Tasks
Keep track of the list of things you are required to do. 

### Feature 2 - Statistics
Take a look at the number of tasks you have completed. 


## Usage

### `todo` - Add a todo task

The command `todo` allows user to add a todo task.

Example of usage: 

    Start the command with todo, followed by the description of the task

`todo buy apples`

Expected outcome:
    
    Got it. I've added this task:
    1.[T][✗] todo buy apples
    Now you have 1 tasks in the list.
    

### `event` - allows user to add an event task.

The command `event` allows user to add an event and the date and time of it. 

Example of usage: 

    Start the command with `event`, followed by the name of the event. Then `\at`
    the event's time and date. 

`event Mary's party \at 19/09/2019 1900`

Expected outcome:

    Got it. I've added this task:
    2.[E][✗] event Mary's party at 19 September 2019, 7.00p.m.
    Now you have 2 tasks in the list.
    
### `deadline` - allows user to add a deadline task.

The command `deadline` allows user to add a deadline and the date and time of it. 

Example of usage: 

    Start the command with `deadline`, followed by the name of the task. Then `\by`
    the task's time and date. 

`deadline math homework \by 18/09/2019 2359`

Expected outcome:

    Got it. I've added this task:
    3.[D][✗] deadline math homework by 18 September 2019, 11.59p.m.
    Now you have 3 tasks in the list.

### `list` - list the task added

The command `list` shows the complete list of task added by user.

Example of usage: 

    Start the command with `list`

`list`

Expected outcome:

    Here are the tasks in your list:
    1.[T][✗] todo buy apples
    2.[E][✗] event Mary's party at 19 September 2019, 7.00p.m.
    3.[D][✗] deadline math homework by 18 September 2019, 11.59p.m.
    
    To view the full list of task added, open the text file generated.
    
### `done` - mark a task as done

The command `done` allows user to mark task as complete. 

Example of usage: 

    Start the command with `done`, followed by the task number.

`done 1`

Expected outcome:

    Nice! I've marked this task as done:
    [✓] todo buy apples
    
### `delete` - remove a task from the list

The command `delete` allows user to remove a task.

Example of usage: 

    Start the command with `delete`, followed by the task number.

`delete 1`

Expected outcome:

    Noted. I've removed this task:
    1.[T][✓] todo buy apples
    Now you have 2 tasks in the list.
    
### `stats` - shows the number of done task

The command `stats` allows user to see how many tasks are done.

Example of usage: 

    Start the command with `stats`.

`stats`

Expected outcome:

    Since 2019-09-06
    You have done:
    1 Todo tasks
    0 Event tasks
    0 Deadline tasks
    
### `reset` - reset the statistics

The command `reset` allows user to reset the statistics.

Example of usage: 

    Start the command with `reset`.

`reset`

Expected outcome:

    Statistics has been reset.


   

    
