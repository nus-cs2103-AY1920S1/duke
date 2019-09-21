# User Guide

![Screenshot of Duke GUI](Ui.png)

## Features 

### Keep track and manages all your tasks
Duke can help you manage all your todo, deadline and event tasks.

### Saves Data
Duke can save all your tasks so that you can access them later.

### Optimized for the Keyboard
Duke is fully usable with just a keyboard. Ideal for fast typers!

## Usage

### Command Format

* Words in `UPPER_CASE` are the parameters to be supplied by the user e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo borrow book`.
* Items in square brackets are optional e.g `event DESCRIPTION [/at DETAILS]` can be used as `event lecture` or as `event lecture /at LT19`.

### `help` - Help Menu

Displays a list of commands and usage that can be used.

Format:

    `help`
    
Expected outcome:

    Displays a help menu

### `todo` - Adds a Todo Task

Adds a Todo Task to your list of Tasks

Format:

    `todo DESCRIPTION`

Example of usage: 

    `todo borrow a book`

Expected outcome:

    Got it. I've added this task:
    [T][✘] borrow a book
    Now you have 1 tasks in this list
    
### `event` - Adds an Event Task

Adds an Event Task to your list of Tasks

Format:

    `event DESCRIPTION [/at DETAILS]`

Example of usage: 

    `event baking contest`
    `event attend a wedding /at Mandarin Hotel`

Expected outcome:

    Got it. I've added this task:
    [E][✘] baking contest
    Now you have 2 tasks in this list

    Got it. I've added this task:
    [E][✘] attend a wedding (at: Mandarin Hotel)
    Now you have 3 tasks in this list
    
### `deadline` - Adds an Deadline Task

Adds a Deadline Task to your list of Tasks

Format:

    `deadline <DESCRIPTION> [/by DETAILS]`
  *A deadline will understand date and time if DETAILS is formatted as `dd/MM/yyyy HHmm`*


Example of usage: 

    `deadline pay rent`
    `deadline project submission /by Sunday`
    `deadline return library book /by 21/09/2019 2359`

Expected outcome:

    Got it. I've added this task:
    [D][✘] pay rent
    Now you have 4 tasks in this list
    
    Got it. I've added this task:
    [D][✘] project submission (by: Sunday)
    Now you have 5 tasks in this list
    
    Got it. I've added this task:
    [D][✘] return library book (by: 2019-09-21T23:59) 
    Now you have 6 tasks in this list
    
### `list` - Shows all your Tasks

Shows all your Todos, Events and Deadlines together with their description, details and whether they are done or not. It also saves the current state of your list into Duke's storage.

Format:

    `list`

Expected outcome:

    1. [T][✘] borrow a book
    2. [E][✘] baking contest
    3. [E][✘] attend a wedding (at: Mandarin Hotel)
    4. [D][✘] pay rent
    5. [D][✘] project submission (by: Sunday)
    6. [D][✘] return library book (by: 2019-09-21T23:59)
    
### `find` - Find a Task

Find a task based on a keyword supplied by the user

Format:

    `find KEYWORD`

Example of usage: 

    `find book`

Expected outcome:

    1. [T][✘] borrow a book
    6. [D][✘] return library book (by: 2019-09-21T23:59)
    
### `done` - Mark a Task as Done

Marks a task as done by selecting the task number as shown when using the `list` or `find` command

Format:

    `done TASK_NUMBER`

Example of usage: 

    `done 1`

Expected outcome:

    Nice! I've marked this task as done:
    [T][✔] borrow a book
    
### `delete` - Delete a Task

Deletes a task by selecting the task number as shown when using the `list` or `find` command

Format:

    `delete TASK_NUMBER`

Example of usage: 

    `delete 1`

Expected outcome:

    Noted. I've removed this task:
    [T][✔] borrow a book
    You now have 5 tasks in this list
    
 ### `bye` - Exits Duke

Duke will close the application

Format:

    `bye`
    
Expected outcome:

    Duke application closes

    
