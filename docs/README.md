# User Guide


## About

<p style="text-align:center;"><img src="Ui.png" alt="Ui" width= "512" height= "734"></p>

Duke is a simple task tracker that helps users to keep track of
their todo tasks, events and deadlines.

## Features 


### Feature 1 - Add/Delete Tasks

In Duke, you can add 3 types of tasks:
- To-do
- Event
- Deadline

Set them as done after you have completed it.
Set priority (1-4) for the task after adding it.

### Feature 2 - Find

Duke has a simple search function that allows you to search by keywords.
Helpful if you have a very long list.

## Commands

### 1. `todo` - Add a new todo task

You can add a new todo task and 
it will be automatically added to your task list.
`todo {taskName}`


Example of usage: 

`todo Buy Nike's shoes`

Expected outcome:

<img src="images/ug-todo.png" width= "512" height= "734" />


### 2. `event` - Add a new event task

`event {taskName} /at {d MMM yyyy HHmm}`

Example:

`event JJ Lin's concert /at 12 Dec 2019 1900`

Expected outcome:


<img src = "images/ug-event.png" width = "512" height = "734"/>



### 3. `deadline` - Add a new deadline task

`deadline {taskName} /by {d MMM yyyy HHmm}`

Example:

`deadline Online Quiz /by 10 Oct 2019 2359`

Expected outcome:


<img src = "images/ug-deadline.png" width = "512" height = "734" />


### 4. `done` - Mark a task as done 

`done {taskNumber}`

Example:

`done 9`

Expected outcome:


<img src = "images/ug-done.png" width = "512" height = "734" />


### 5. `list` - View your current list

Example:

`list`

Expected outcome:


<img src = "images/ug-list.png" width = "512" height = "734" />


### 6. `find` - Search list

Search list with a keyword

`find {keyword}`

Example:

`find concert`

Expected outcome:


<img src = "images/ug-find.png" width = "512" height = "734" />



### 7. `priority` - Set priority

Set priority of task according to 1/2/3/4.
1. Normal
1. Standard
1. Urgent
1. Emergency

`priority {task_number} {1/2/3/4}`

Example:

`priority 3 1`

Expected outcome:


<img src ="images/ug-priority.png" width = "512" height = "734" />


### 8. `delete` - Delete task

`delete {task_number}`

Example:

`delete 3`

Expected outcome:


<img src = "images/ug-delete.png" width = "512" height = "734" />


### 9. `bye` - Exit

Example:

`exit`

Expected outcome:


<img src = "images/ug-bye.png" width = "362" height = "205" />


