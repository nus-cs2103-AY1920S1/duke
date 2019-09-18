# [Duke User Guide](http://{icesiolz}.github.io/duke/)

![Ui](Ui.png)

## Features 

### Track tasks
Able to keep track of 3 types of tasks:
- To do tasks (Tasks without a time associated)
- Event tasks (Tasks that will happen at a time)
- Deadline tasks (Tasks that needs to be completed by a certain date)

### Complete tasks
Able to mark tasks as done.

### Search tasks
Able to filter tasks with a certain keyword.

### Delete tasks
Able to delete tasks from the task list.

### Statistics
Able to tell you how many tasks you completed already.

## Usage

### bye - Exits Duke
Closes the program.

Usage 'bye'

### todo - Adds a to do task
Adds a to do task into the task list.

Usage 'todo <description>'
  
### event - Adds an event task
Adds an event task with a given description and date.

Usage: `event <description> /at <date>`

### deadline - Adds a deadline task
Adds a deadline task with a given description and date.

Usage: `deadline <description> /by <date>`

### list - Lists all the tasks
Lists all the tasks in task list.

Usage: `list`

### done - Marks a task as done
Marks the task of the id as done.

Usage: `done <id>`

### delete - Deletes a task
Deletes the task of the id.

Usage: `delete <id>`

### find - Filters task
Filters the task list with the given keyword.

Usage: `find <keyword>` 

### statistics - Display statistics
Displays the number of tasks completed so far.

Usage: 'statistics'
