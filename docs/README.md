# Keud - Yet another to-do manager
This is a NUS Computer Science Software Engineering course project.

## Features 

Keud supports:
* adding a to-do, deadline or event
* moving a task to archive  
* and more

## Usage

### `list` - list the tasks

`list` will list all the current tasks in the list, and `list archive` will 
show the archived tasks.

### `done` - set a task to complete

`done [number]` will mark the specified task to complete.

### `archive` - archive a task

`archive [number]` will send the task to archive list.
### `unarchive` - unarchive a task

In the `list archive` screen, calling `unarchive [number]` will move the task from archive to task list.

### `todo`, `deadline`, `event` - creating a task
* `todo [description]` will add a new todo item with the specified description.

* `deadline [description] /by [dd/MM/YYYY hhmm]` will add a deadline with the specified details.

    Example: 
    ```
    deadline return book /by 2/12/2019 1800
    ```
    
* 
    






