# User Guide

## Features 

## Tasks 
Allows to save, edit & follow up on daily tasks.  

### Commands

### `todo` 

Adds a todo task.
<br />Input in the format: __todo < task >__

Example of usage: 

`todo read book`

Expected outcome:

    Got it. I've added this task:
    [T][:(] read book
    Now you have 1 tasks in the list.
    
### `deadline` 

Adds a task with deadline.
<br />Input text in the format: __deadline < task > /by < DD/MM/YYYY HHMM>__

Example of usage: 

`deadline submission /by 02/03/2019 1800`

Expected outcome:

    Got it. I've added this task:
    [D][:(] submission (by: 02/03/2019 1800)
    Now you have 2 tasks in the list.
    
### `event` 

Adds a event task.
<br />Input text in the format: __event < task > /at < DD/MM/YYYY HHMM>__

Example of usage: 

`event party /at 5/6/2012 1400`

Expected outcome:

    Got it. I've added this task:
    [E][:(] party (at: 05/06/2012 1400)
    Now you have 3 tasks in the list.

### `list` 

shows all tasks 
<br />Input text: __list__

Example of usage: 

`list`

Expected outcome:

    1. [T][:(] read book
    2. [D][:(] submission (by: 02/03/2019 1800)
    3. [E][:(] party (at: 05/06/2012 1400)
    
### `done` 

marks task as done 
<br />Input text: __done < digit of task in list >__

Example of usage: 

`done 1`

Expected outcome:

    Nice! I've marked this task as done:
    [T][:)] read book

### `delete` 

deletes the task from the list of tasks 
<br />Input text: __delete < digit of task in list >__

Example of usage: 

`delete 2`

Expected outcome:

    Noted. I've removed this task:
    [D][:(] submission (by: 02/03/2019 1800)
    Now you have 2 tasks in the list.
    
### `find` 

finds tasks that contain user's input and returns a list of results
<br />Input text: __find < word to find >__

Example of usage: 

`find book`

Expected outcome:

    Here are the matching tasks in your list:
    1. [T][:)] read book
    
## Expenses
Allows to save & follow up on daily expenses.  

### Commands

### `spent` 

allows user to input expenditures and tag it if required
<br />Input text: __spent < item > /for < cost > # < tag name >__

Example of usage: 

`spent eggs /for 8.99 #food`

Expected outcome:

    Your expense has been added to the list: 
    (ID: 1) eggs | 8.99
    
### `expenses` 

states all expenses in a list format
<br />Input text: __expenses__

Example of usage: 

`expenses`

Expected outcome:

    #food
    1. (ID: 1) eggs | 8.99
    
    #studies
    1. (ID: 2) pencils | 0.99
    
    Total: 9.98

### `deleteExpense` 

allows user to delete an expense from the list by using its ID
<br />Input text: __deleteExpense < ID of the expense >__

Example of usage: 

`deleteExpense 2`

Expected outcome:

    Noted. I've removed this expense:
    (ID: 2) pencils | 0.99
    Now you have 1 expenses in the list.
    
### `bye` 

close the app bye typing in 'bye'

Example of usage: 
`bye`

#That's All! Have Fun Now!!!