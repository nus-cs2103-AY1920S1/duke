# User Guide

## Introduction
Duke is an offline application for managing various tasks. It is optimised for those 
who prefer to work with a Command Line Interface (CLI) while still having the benefits of
a Graphical User Interface (GUI).

## Features 

### Command Format
* `DES` is short for description

* Words in `UPPER CASE` are the parameters to be supplied by the user
e.g. in `todo TASK DES`, `TASK DES` is the parameter,
which can be used as `todo borrow book`

* In the examples, words in the diamond brackets (<>) are user inputs, and Duke 
responses are have a '__________' preceding and succeeding it. 

### Add a task
Duke can track three types of tasks:
* *Todos*: tasks without any date/time attached to it
* *Deadlines*: tasks that need to be done before a specific date/time
* *Events*: tasks that start at a specific time

    | Task Type | Command |
    | --------- | ------- |
    | Todo | `todo TASK DES` |
    | Deadline | `deadline TASK DES /by DATE TIME` |
    | Event | `event TASK DES /at DATE TIME` |

Examples:
    
    <todo borrow book>
    __________
    Got it. I've added this task: 
           [T][✗] borrow book
         Now you have 5 tasks in the list.
    __________
    <deadline CS2106 assignment /by 18/08/2019 2359>
    __________
    Got it. I've added this task: 
           [D][✗] return book (by: 18/08/2019 2359hrs)
         Now you have 6 tasks in the list.
    
    
### List tasks
To list all the tasks in Duke:

    list

### Complete a task
The default status of a task added on Duke is *incomplete*: [✘]
To mark a task as done:
    
    done INDEX
This would subsequently update the status of the task to *completed*: [✓].

Example:

    <list>
    __________
    Here are the tasks in your list:
        1. [T][✘] read book
        2. [D][✘] return book (by: 18/07/2019 1800hrs)
    __________
    <done 2>
    __________
    Nice! I've marked this task as done: 
           [✓] return book
    __________
    <list>
    ___________
    Here are the tasks in your list:
        1. [T][✓] read book
        2. [D][✘] return book (by: 18/07/2019 1800hrs)
    
### Delete a task
To delete a task in Duke:
    
    delete INDEX

Example:

    <list>
    __________
    Here are the tasks in your list:
         1.[T][✓] read book
         2.[D][✓] return book (by: 6/06/2019 1500hrs)
         3.[E][✗] project meeting (at: 9/12/2020 1330hrs)
         4.[T][✓] join sports club
         5.[T][✗] borrow book
    __________
    <delete 3>
    __________
    Noted. I've removed this task: 
           [E][✗] project meeting (at: 9/12/2020 1330hrs)
    Now you have 4 tasks in the list.

### Save a task
Duke saves tasks in a hard disk automatically whenever the task list changes.
It loads the data from the hard disk when it starts up.

### Find a task
To look for a task by keyword in Duke:

    find KEYWORD
    
Example:
    
    <find book>
    __________
    Here are the matching tasks in your list:
         1.[T][✓] read book
         2.[D][✓] return book (by: 18/09/2018 1735hrs)
    
### Edit a task
The editable attributes of a task are:
  1. `des` : the description of a task
  2. `date` : the date of `event` and `deadline` tasks 

    edit INDEX/ATTRIBUTE/NEW ATTRIBUTE
 
 Examples:
 
    <list>
    __________
    Here are the tasks in your list:
             1.[T][✓] read book
             2.[D][✓] return book (by: 6/06/2019 1500hrs)
             3.[E][✗] project meeting (at: 9/12/2020 1330hrs)
             4.[T][✓] join sports club
             5.[T][✗] borrow book
    __________
    <edit 2/date/18/09/2019 1800>
    __________
    Success! I've edited this task from this:
    [D][✓] return book (by: 6/06/2019 1500hrs)
    to this:
    [D][✓] return book (by: 18/09/2019 1800hrs)
     __________
     <edit 4/des/join arts club>
     __________
     Success! I've edited this task from this:
     [T][✓] join sports club
     to this:
     [T][✓] join arts club

### Help
To view a help page with a list of all commands:

    help

### Exit Duke
To exit Duke and close the programme:
    
    bye

