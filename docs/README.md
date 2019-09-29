# Duke Task Manager User Guide

## ___Features___ 

### `Add`

### `List`

### `Delete`

### `Done`

### `Find`

### `Bye`

## Usage

### `List` 
#####   Lists all the current tasks.
Example of usage: 

    list

Expected outcome:
    
    Here are the tasks in your list:
    
    1.[D][✓] cs2103 (by: 30 September 2019, 11.59PM)
    2.[E][✘] acronis (at: 26 September 2019, 6.00PM)
    3.[T][✘] workout

### `Add`
#####    Adds a task to the current database. Tasks can be a ToDo, Deadline or Event.
Example of usage:
    
    todo midterm paper

Expected outcome:

    Got it. I've added this task:
    [T][✘] midterm paper
    Now you have 4 tasks in the list.

Example of usage:

    deadline finish lab1 /by 01/10/2019, 2359
    
    
Expected outcome:

    Got it. I've added this task:
    [D][✘] finish lab1 (by: 01 October 2019, 11.59PM)
    Now you have 5 tasks in the list.
    

Example of usage:

    event fintech hackathon /at 11/11/2019, 1800
    
Expected outcome:

    Got it. I've added this task:
    [E][✘] fintech hackathon (at: 11 November 2019, 6.00PM)
    Now you have 5 tasks in the list.
         

### `Delete`  
##### Deletes selected task.   
Example of usage:
    
    delete 1
    
Expected outcome:

    Noted. I've removed this task:
    [D][✓] cs2103 (by: 30 September 2019, 11.59PM)
    Now you have 4 tasks in the list.
    

### `Done`
##### Marks the selected task as done.
Example of usage:

    done 1
    
Expected outcome:

    Nice! I've marked this task as done:
    [E][✘] acronis (at: 26 September 2019, 6.00PM)
    

### `Find`
##### Finds tasks that contains the specified keyword.
Example of usage:

    find midterm
    
Expected outcome:

    Here are the matching tasks in your list:
    3.[T][✘] midterm paper
    

### `Bye`
##### Ends interaction with Duke and saves new data to file.
Example of usage:

    bye
    
Expected outcome:

    Bye. Hope to see you again soon!
