# User Guide

# Chen Sheng's Duke
This is a simple organiser to keep track of the tasks that is being entered by you! You are able to input tasks, remove tasks, search tasks as well as to mark task as done with this organiser. This organiser can be used by typing in comands into the console and this organiser application will execute it for you!

## Features

### Feature 1 

## Usage
You are able to add tasks by using the keywords `event`, `deadline` or `todo`. Type the the keywords to allow the organiser to add the task.

### `Keyword` - Describe action

Type the command `event`, `deadline` or `todo` followed by the description of the task into the console. For `event` and `deadline`, date and time is required to be keyed in, `/by dd/mm/yyyy hhmm` for deadline and `/by dd/mm/yyyy hhmm` for event are the formats following the description command. `todo` command is not required to have date and time.

Example of usage: 

`deadline finish science assignment /by 23/04/2019 1300`

Expected outcome:

![Adding task](/images/AddTask.png)

### Feature 2 

## Usage
You are able to mark task as done by using `done` command. When you have finish a task, you are able to mark task as completed. 

### `Keyword` - Describe action

Type the command `done` followed by the task number to prompt the organiser to mark task as done.

Example of usage: 

`done 3`

Expected outcome:
![Done task](/images/Done.png)

### Feature 3 

## Usage
You are able to delete task using the `delete` command. You can delete a task if you want to remove unwanted entries of task.

### `Keyword` - Describe action

Type the command `delete` followed by the task number to prompt the organiser to mark task as done.

Example of usage: 

`delete 1`

Expected outcome:
![Delete task](/images/Delete.png)

### Feature 4 

## Usage
You are able to list down the task by using `list` command. You are able to have a list of tasks you've entered into the organiser by the order you typed your tasks in.

### `Keyword` - Describe action

Type the command `list` to display a list of tasks.

Example of usage: 

`list`

Expected outcome:
![List task](/images/List.png)

### Feature 5 

## Usage
You are able to find tasks with a keyword. When you are trying to search for tasks with similar keyword, `find` command can be used to search for the tasks related to that keyword.

### `Keyword` - Describe action

Type the command `find` followed by the description.

Example of usage: 

`find finish`

Expected outcome:
![Find task](/images/Find.png)

### Feature 6 
You are able to view schedule of the day.

## Usage
When you want to find out the tasks for the day, you are able to use this feature to have an overview of tasks to do for the day.

### `Keyword` - Describe action

Type the command `view` followed by the date.

Example of usage: 

`view 23/04/2019`

Expected outcome:
![Viewtask](/images/View.png)

