# User Guide

## Features 

### Recording and Saving Tasks

Duke allows you to note down important tasks that you need to complete in your daily life. There are three kinds of tasks in Duke: **Todo**, **Deadline** and **Event**. After recording your tasks down in Duke, your changes will be saved to a local file, so that you can re-open and update your list of tasks! 

### Marking Tasks as Completed

When you finish your tasks, you can mark them as completed in Duke. 

### Loading Tasks

My implementation of Duke allows you to open different lists of tasks at different file paths when you first start up the Duke program. 

## Usage

### `list` - Shows the current list of tasks

Example of usage: 

`list`

Expected outcome:

`1.[T][✓] read book
2.[D][✕] return book (by: 6th of June 2019, 6pm)
3.[E][✓] project meeting (at: 6th of August 2019, 12am)
4.[T][✕] join sports club`

### `done` - Marks a specified task as completed. 

Example of usage: 

`done 4`

Expected outcome:

`Nice! I've marked this task as done:
[T][✓] Cook Noodles`

### `todo` - Creates a task to be completed with label "T". (Todo)

Example of usage: 

`todo Buy Groceries`

Expected outcome:

`Got it. I've added this task:
 [T][✕] Buy Groceries
Now you have 6 tasks in the list.`

### `event` - Creates a task to be completed with label "E". (Event)

Example of usage: 

`event Music Festival /at 29/10/2019 1930`

Expected outcome:

`Got it. I've added this task:
 [E][✕] Music Festival (at: 29th of October 2019, 7.30pm)
Now you have 3 tasks in the list.`

### `deadline` - Creates a task to be completed with label "D". (Deadline)

Example of usage: 

`deadline Submit Essay /by 20/9/2019 2359`

Expected outcome:

`Got it. I've added this task:
 [D][✕] Submit Essay (by: 20th of September 2019, 11.59pm)
Now you have 2 tasks in the list.`

### `find` - Search for tasks in your list that match a given keyword.

Example of usage: 

`find math`

Expected outcome:

`Here are your matching tasks in your list
1.[T][✓] Revise Math
2.[D][✓] Finish Math Quiz (by: 11th of November 2020, 11.59pm)
3.[E][✕] Math for AI Symposium (at: 6th of December 2020, 10.30am)`

### `delete` - Deletes a specified task from your list. 

Example of usage: 

`delete 2`

Expected outcome:

`Noted. I've removed this task:
[T][✓] Cook Noodles
Now you have 10 tasks in the list.`

### `bye` - Exits the program and saves any changes made. 

Example of usage: 

`bye`

Expected outcome:

`Writing new changes done!
Bye. Hope to see you again!`


