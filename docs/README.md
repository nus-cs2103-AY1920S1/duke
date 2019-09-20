# User Guide
Welcome from Duke User Guide. Here, you will find how to use Duke Application.

## Table of Contents

- [What is Duke?](#what-is-duke)
    - [Image of Duke](#image-of-duke)
    

## What is Duke?
 **Duke** is a friendly chatbot application which allow you to manage your daily tasks by keeping track of them.
 
### Image of Duke
![Image of Duke](Ui.png)

## Features 
**_NOTE_** : Words in `UPPER_CASE` are the parameters to be supplied by the user. e.g in `todo TASKNAME, TASKNAME` is a parameter which can be used as `todo return books`. 
### Add a Todo Task
Adds a todo task to the task list.  
  
Format: `todo TASKNAME`  

Examples:
* `todo Buy milk`
* `todo Pay phone bill`  

### Add a Deadline Task  
Adds a deadline task to the task list.  

Format: `deadline TASKNAME /by DATETIME`  
**_NOTE_** : DATETIME have to be in the format of `dd/mm/yyyy hhmm`  

Examples:
* `deadline Maths homework /by 25/12/2019 1000`
* `deadline Physics Lab homework /by 20/11/2019 1400` 

### Add an Event Task  
Adds an event task to the task list.  

Examples:

### Lists down all the task in the Tasklist

This command will list down all the task being tracked by Duke

Format: `list`  
Examples:

* `list`

### Delete a task

This command will delete a task located at a particular index number.

Example:
* `delete 1'


### Find a task

This command will find a task in the tasklist according to the keyword.  

Example:
* `find homework`

### Bye

This command will send a bye message.  

Example:
* `bye`