# User Guide 
Taiping is task manager acting like a chatbot

## Features 

### Adding of tasks 
Taiping can add the task to your own task list

### Update the tasks as done
Taiping can mark the tasks as done and give you the status of the task

### Finding of tasks
Taiping can find the tasks you have for you

### Delete tasks
Taiping can delete tasks for you

### Listing of tasks
Taiping can list all the tasks you have

### Duplicates Detection
Taiping will make sure you do not repeat your tasks by accident

## Usage

### `todo` - A task type
You can add a `description` of your `todo` task to add the task

Example of usage: 

`todo borrow book`

Expected outcome:

`Got it. I've added this task:`

`[T][✗] borrow book`
      
`Now you have 5 tasks in the list.`

### `deadline` - A task type
You can add a `description` of your `todo` task to add the task
You must also add a `date` and `time` for this task type

Example of usage: 

`deadline return book /by 12/12/2000 1822`

Expected outcome:

`Got it. I've added this task:`

`[D][✗] return book (by: 12-Dec-2000 18:22)`
      
`Now you have 5 tasks in the list.`

### `event` - A task type
You can add a `description` of your `todo` task to add the task
You must also add a `date` and `time` for this task type

Example of usage: 

`event party /at 12/12/2000 1900`

Expected outcome:

`Got it. I've added this task:`

`[E][✗] party (at: 12-Dec-2000 19:00)`
      
`Now you have 5 tasks in the list.`

### `done` - When you finish a task
You can mark a task as done

Example of usage: 

`done 5`

Expected outcome:

`Congratulations! I've marked this task as done:`

`[E][✓] party (at: 12-Dec-2000 19:00)`
      
`Now you have 4 tasks in the list.`

### `find` - Finding a task
You find a task which you listed before

Example of usage: 

`find borrow book`

Expected outcome:

`Here are the matching tasks in your list:`

`[T][✗] borrow book`

### `delete` - When you want to delete a task
You can delete a task from your task list

Example of usage: 

`delete 1`

Expected outcome:

`Noted. I've removed this task: `

`[E][✓] party (at: 12-Dec-2000 19:00)`
      
`Now you have 4 tasks in the list.`

### `list` - When you want to see all the tasks
You can view all the tasks you have

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:`

`1.[T][✓] read book`

