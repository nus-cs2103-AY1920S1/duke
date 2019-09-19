# User Guide
Welcome from Duke User Guide. Here, you will find how to use Duke Application.

## Table of Contents

- [What is Duke?](#what-is-duke)
    - [Image of Duke](#image-of-duke)
    
- [Commands](#commands)
    - [`list` - Lists down all the task](#list---lists-down-all-the-task)
    - [`todo <Description>` - to add a todo task](#todo-description---to-add-a-todo-task)
    - [`deadline <Description> /by <Date in DD/MM/YYYY> <Time in HHMM>`- to add a deadline](#deadline-description-by-date-in-ddmmyyyy-time-in-hhmm--to-add-a-deadline)
    - [`event <Description>' /at <Date in DD/MM/YYYY> <Time in HHMM>` - to add an event](#event-description-at-date-in-ddmmyyyy-time-in-hhmm---to-add-an-event)
    - [`delete <Task Number>` - to delete a task](#delete-task-number---to-delete-a-task)
    - [`done <Task Number>` - to mark a task as done](#done-task-number---to-mark-a-task-as-done)
    - [`undo` - to undo previous actions](#undo---to-undo-previous-actions)
    - [`find <keyword>` - to list down tasks with particular keyword](#find-keyword---to-list-down-tasks-with-particular-keyword)
    - [`bye` - to exit Duke](#bye---to-exit-duke)

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