# Radomir User Guide

## Welcome to Radomir!

![User Interface](Ui.png)

To help you understand this application, there a few things 
you would need to set up before proceeding with usage. 

## Contents Page 

* [Prerequisites](#prerequisites)
* [Features](#features)
  * [Feature 1: Adding Tasks](#feature-1-adding-tasks)
  * [Feature 2: Deleting Tasks](#feature-2-deleting-tasks)
  * [Feature 3: Listing Tasks](#feature-3-listing-tasks)
  * [Feature 4: Modifying Tasks](#feature-4-modifying-tasks)
  * [Feature 5: Finding Tasks](#feature-5-finding-tasks)
  * [Feature 6: Tutorial Section](#feature-6-tutorial-section)
  * [Feature 7: Listing Available Commands](#feature-7-listing-available-commands)
* [Commands](#usage)
  * [tutorial](#tutorial---activates-tutorial-mode)
  * [todo](#todo---adds-a-new-todo-task-to-the-list)
  * [event](#event---adds-a-new-event-task-to-the-list)
  * [deadline](#deadline---adds-a-new-deadline-task-to-the-list)
  * [delete](#delete---deletes-a-task-from-the-list)
  * [massdelete](#massdelete---deletes-multiple-tasks-at-once)
  * [clearall](#clearall---deletes-the-whole-list-of-tasks)
  * [done](#done---mark-a-task-as-done)
  * [list](#list---list-down-the-added-tasks)
  * [find](#find---list-down-tasks-that-contains-the-specified-keyword)
  * [bye](#bye---quits-the-app)
    
## Prerequisites
1) Have Java SE Development Kit 11 or higher installed on your computer. 

## Features 

### Feature 1: Adding Tasks
There are 3 times of tasks that you can create :
1) Todo tasks with specified description.
2) Deadline tasks with specified description and deadline.
3) Event tasks with specified description and timing.

### Feature 2: Deleting Tasks
There are 3 ways to delete tasks from the list :
1) Delete a single task.
2) Mass delete multiple tasks.
3) Delete all tasks in the list.

### Feature 3: Listing Tasks
There is a feature to list all added tasks. 

### Feature 4: Modifying Tasks
There is a way to modify a task by marking it as done.

### Feature 5: Finding Tasks
There is a way to find tasks according to specified keyword.

### Feature 6: Tutorial Section
There is a way to activate tutorial mode for new users.

### Feature 7: Listing Available Commands
There is a way to list out all the commands.

## Usage

### `tutorial` - Activates tutorial mode 

This commands enables the tutorial mode which provides guide and examples for new users. 
Warning : This will delete your current list of tasks.

#### Example :

Input : `tutorial`

Outcome : 'The guide on how to use the app and couple of available features'

### `todo` - Adds a new todo task to the list

This command enables you to add a new todo task by providing the description of the task.

#### Example :

Input : 'todo Buy new pair of Jeans'

Outcome : 'A new todo tasks with decription "Buy new pair of jeans" has been added to the list'

### `event` - Adds a new event task to the list

This command enables you to add a new event task by providing the description and timing of the task. 
The keyword for the timing is '/at' and the format for the timing is 'DD/MM/YYYY HHMM'

#### Example :

Input : 'event Jane's birthday party /at 01/06/2019 1800'

Outcome : 'A new event task with decription "Jane's birthday party" has been added to the list at 01/06/2019 6pm'

### `deadline` - Adds a new deadline task to the list

This command enables you to add a new deadline task by providing the description and timing fo the task. 
The keyword for the timing is '/by' and the format for the timing is 'DD/MM/YYYY HHMM'

#### Example :

Input : 'deadline Math homework /by 10/10/2019 2359'

Outcome : 'A new deadline task with description "Math homework" has been added to the list by 10/10/2019 11.59pm'

### 'delete' - Deletes a task from the list

This command enables you to delete a task from the list according to the index specified.

#### Example :

Input : 'delete 3'

Outcome : 'Task number 3 has been deleted from the list'

### 'massdelete' - Deletes multiple tasks at once

This command enables you to delete multiples tasks from the list of added tasks. 
The input index of tasks to be deleted has to be separated by a space.

#### Example :

Input : 'massdelete 1 3 6'

Outcome : 'Task number 1, 3, 6 have been deleted from the list'

### `clearall' - Deletes the whole list of tasks

This command enables you to clear the whole list of tasks.

#### Example : 

Input : 'clearall'

Outcome : 'Deletes the whole list'

### `done` - Mark a task as done

This app enables you to mark a task that you have completed according to the specified index.

#### Example :

Input : 'done 2'

Outcome : 'Task number 2 has been marked as done'

### `list` - List down the added tasks

This command will show you all the added tasks.

#### Example :

Input : `list`

Outcome : 'Shows the list of tasks'

### `find` - List down tasks that contains the specified keyword

This command will show you a list of tasks that contains the specified keyword.

#### Example :

Input : 'find Jeans'

Outcome : 'List of tasks that contains the word Jeans'

### `bye` - Quits the app

This command will set the app into Quit mode. Press ENTER key to close the app.

#### Example :

Input : 'bye'

Outcome : 'The program will enter quit mode, press ENTER to quit the app'

[Back to Top](#user-guide)