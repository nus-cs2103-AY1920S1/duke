# User Guide

## Features 

### Feature 1 
Displaying your list of tasks.

## Usage

### `list` - Lists out your tasks.

`list` is a command that helps to display the current list of tasks you have stored.

Example of usage: 

`list`

Expected outcome:

![Image of list usage](https://github.com/jerryk1997/duke/blob/master/docs/list_example.png)

### Feature 2
Adding a todo item.

## Usage

### `todo [description]` - Adds a todo item to your list of tasks. A todo item requires a non-empty description, but does not require a date.

Example of usage: 

`todo read book`

Expected outcome:

![Image of adding todo](https://github.com/jerryk1997/duke/blob/master/docs/Todo_example.png)

### Feature 3 
Adding an event.

## Usage

### `event [description /at DD/MM/YYYY HHMM]` - Adds an event to your list of tasks. An event is a task with a date at which it is happening. 

Example of usage:

`event Project Meeting /at 02/10/2019 1530`

Expected outcome:

![Image of adding event](https://github.com/jerryk1997/duke/blob/master/docs/Event_example.png)

### Feature 4
Adding a deadline.

## Usage

### `deadline [description /by DD/MM/YYYY HHMM]` - Adds a deadline to your list of tasks. A deadline is a task which must be done by a certain date.

Example of usage:

`deadline Project submission /by 03/10/2019 1000`

Expected outcome:

![Image of adding deadline](https://github.com/jerryk1997/duke/blob/master/docs/Deadline_example.png)

### Feature 5
Doing a task.

## Usage

### `done [Index of task]` - Changes the status of the task at the given index to done. This results in the status icon changing from an X to a tick.

Example of usage:

`done 2`

Expected outcome:

![Image of doing a task](https://github.com/jerryk1997/duke/blob/master/docs/Done_example.png)

### Feature 6
Deleting a task.

## Usage

### `delete [Index of task]` - Removes the task at the given index from the list.

Example of usage:

`delete 2`

Expected outcome:

![Image of deleting a task](https://github.com/jerryk1997/duke/blob/master/docs/Delete_example.png)

### Feature 7
Assinging priority to the tasks.

## Usage

###  `high todo [description]`
###  `medium event [description /at DD/MM/YYYY HHMM]`
###  `low deadline Project submission /by 03/10/2019 1000`

Priority can be high/medium/low. Priority can be assigned to the task by typing 'high', 'medium', or 'low' before the task. If no priority is assigned, the priority of the task is assigned to low by default.

Example of usage:

`high todo return book`

Expected outcome:

![Image of assigning priority example](https://github.com/jerryk1997/duke/blob/master/docs/Priority_example.png)

### Feature 8
Shows the list of tasks sorted by priority.

## Usage

### `priority` - Lists out tasks by priority.

Example of usage:

`priority`

Expecte outcome:

![Image of priority list example](https://github.com/jerryk1997/duke/blob/master/docs/PriorityList_example.png)






