# User Guide

 1. [Introduction](#introduction)
 2. [Quickstart](#quickstart)
 3. [Features](#features)
 4. [Usage](#usage)
 
 <br>
 
 ## Introduction
 ![intro](public/intro.png)  
 
 Ever wondered what it would be like to have the *Mad Titan* manage your tasks for you? Wait no more!  
 Duke is simple chat-bot that uses a Command Line Interface (CLI) to allow you to interact with none other than the 
 Warlord Thanos. Enlist Thanos' help in managing your various tasks, including todos, deadlines and events. And, if you
 ever feel overwhelmed, you may be able to persuade Thanos to use the powers of the *Infinity Gauntlet* to help you 
 balance your tasks better.
 
 ## Quickstart
 1. Ensure Java 11 is downloaded on your computer. To check if you have Java installed, type `java  --version` into your
  command line.
 2. Download the `.jar` file from here.
 3. Move the file into your desired folder.
 4. Double click the file to run.

## Features 

### Wide range of task types
Duke contains simple Todo tasks, Deadline tasks where you can specify an end date, as well as Event tasks, allowing you
to specify when the event starts. Manage your complex task needs with great flexibility using all three types.

### Adding tasks
![add task](public/todo.png)

Ask the Mad Titan Thanos to help keep track of tasks for you. With his genius intellect, Thanos will never forget any of
your tasks. (They are saved to disk).
 
### Deleting tasks
![delete task](public/delete.png)

Get Thanos to destroy a task that you no longer want. With his experience destroying Infinity Stones, Thanos will make 
sure not a single trace of your task remains.

### Completing tasks
![done task](public/done.png)

Tell Thanos that you have completed one of your tasks. A man of ambition himself, Thanos will gladly aid you in 
completing your goals.

### Find tasks
![find tasks](public/find.png)

With his years of tracking down the Stones, Thanos is adept at searching and will quickly find your desired tasks for 
you.

### View all tasks
![list tasks](public/list.png)

Thanos will show all your tasks to you. That's pretty much it.

### Undoing and redoing commands
![undo](public/undo.png)

Using the power of the *Time Stone*, the flow of time itself is under Thanos' command. Thanos can thus undo and redo 
command you make, in the event of a mistake, without even breaking a sweat

### Bringing balance to the universe (and your tasks)
![snap](public/snap.png)

Despite helping you manage your tasks, Thanos has never once given up on bringing balance to the universe. Allow him to
practice the monumental task of snapping away half the population of the entire universe by first letting him snap away 
half of your existing tasks.

## Usage

### `todo` - add todo

> Adds a new todo to your tasks list.

Example of usage: 

`todo <description>`

Parameters:  
`description` - description for the todo.


### `deadline` - add deadline

> Adds a new deadline to your tasks list.

Example of usage:

`deadline <description> /by <date>`

Parameters:  
`description` - description for the deadline.  
`date` - date the deadline is due by, in the format `DD/MM/YYYY HHMM`.


### `event` - add event

> Adds a new event to your tasks list.

Example of usage:

`event <description> /at <date>`

Parameters:  
`description` - description for the event.  
`date` - date the event starts at, in the format `DD/MM/YYYY HHMM`.


### `delete` - delete task

> Deletes the task with the specified id.

Example of usage:

`delete <id>`

Parameters:
`id` - id of the task to be deleted.


### `done` - complete task

> Marks the task with the specified id as done.

Example of usage:

`delete <id>`

Parameters:
`id` - id of the task to be marked as done.  


### `find` - find tasks

> Finds tasks based on the given search parameter.  

Example of usage:

`find <search_param>`

Parameters:  
`search_param` - parameter to search for tasks by.


### `list` - lists tasks

> Lists all your tasks.

Example of usage:  

`list`

Parameters:  
none


### `undo` - undoes commands

> Undoes the previous command that affected your task list.

Example of usage:

`undo`

Parameters:  
none


### `redo` - redoes commands

> Redoes any commands that have been undone.

Example of usage:

`redo`

Parameters:  
none


### `snap` - snap

> Snaps away half of your tasks at random.

Example of usage:

`snap`

Parameters:  
none

