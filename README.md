# User Guide

### What is Duke?
Duke is your friendly chatbot which helps to keep track of your daily tasks! It's a To-Do List where you can add and 
modify your tasks.

## Features 

1. Add Task - Add new tasks into your list to keep track of them.
1. Delete Task - Delete old tasks that you have completed or no longer have use for.
1. Modify Task - Update details of tasks.
1. Find Task - Filters the list of tasks based on a keyword.
1. Complete Task - Marks the task as completed.
1. List Task - Lists the tasks you have.
## List of commands

#### `list` : Displays information about your tasks.
Displays the entire list of your tasks.

* Format: `list`

* Example of usage: `list`

* Expected outcome:

```
Here are the tasks in your list: 
    1. [T][x] borrow book 
    2. [D][x] sing song (by: 2019-09-09 0900) 
```

#### `bye` : Quits Duke.
Quits Duke.

* Format: `bye`

* Example of usage: `bye`

* Expected outcome:
```
Bye! Hope to see you again soon!
```

#### `todo` : Adds a To-Do into your list.
Adds a to-do task into your lists with the description of the task.

* Format: `todo <Description>`

* Example of usage: `todo borrow book`

* Expected outcome:
```
Got it! I've added this task:
    [T][x] borrow book
Now you have 3 tasks in the list.
```

#### `deadline` : Adds a Deadline into your list. 
Adds a Deadline into the list of tasks. Deadlines must have time of format of format `Year(yyyy)/Month(mm)/Date(dd) Hour(hh)Minute(mm)`, eg 2019-09-14 1800
* Format: `deadline <Description> /by <Time>`

* Example of usage: `deadline sing song /by 2019-09-09 0900`

* Expected outcome:
```
Got it! I've added this task:
    [D][x] deadline sing song (by: 2019-09-09 0900)
Now you have 3 tasks in the list.
```

#### `event` : Adds an Event into your list.
Adds a Event into the list of tasks. Events must have time of format of format `Year(yyyy)/Month(mm)/Date(dd) Hour(hh)Minute(mm)`, eg 2019-09-14 1800
* Format: `event <Description> /at <Time>`

* Example of usage: `event sing song /at 2019-09-09 0900`

* Expected outcome:
```
Got it! I've added this task:
    [E][x] event sing song (at: 2019-09-09 0900)
Now you have 3 tasks in the list.
```

#### `find` : Obtains task with the keyword in it.
Filters the task list by the keyword to find the corresponding tasks with the keyword in it's description.
* Format: `find <Keyword>`.

* Example of usage: `find book`

* Expected outcome:
```
Here are the matching tasks in your list:
    1. [E][x] book party (at: 2019-09-09 0900)
    2. [T][x] todo borrow book
```

#### `done` : Mark the task in the list as done.
Mark the task at `Number` in the list as done.
* Format: `done <Number in the list>`.

* Example of usage: `done 2`

* Expected outcome:
```
Nice! I've marked this task as done:
    [T][✓] todo borrow book
```

#### `delete` : Deletes the task from the list.
Deletes the task at `Number` in the list from the list.
* Format: `delete <Number in the list>`.

* Example of usage: `delete 2`

* Expected outcome:
```
Noted. I've removed this task:
    [T][✓] todo borrow book
Now you have 3 tasks in the list.
```

#### `update` : Updates the task at `Number` in the list.
Updates the Description or Time of the task at `Number`'s in the list.

* Format: `update <Number in the list> descrip <New Description>` : Updates the task at `Number`'s Description.

* Format : `update <Number in the list> time <New Time>` : Updates the task at `Number`'s Time.

* Example of usage: `update 2 descrip sleep`

* Expected outcome:
```
Got it. I've updated this task:
    [T][x] sleep
Now you have 3 tasks in the list.
```
