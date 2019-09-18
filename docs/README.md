# User Guide

## What is Duke?
Duke (Keanu Reeves!) is your personal taskbot assistant. He helps you organize your life. 
The application uses a command line interface (CLI) to keep a record of your tasks. 
Your task list is saved automatically, and Duke shows you your list whenever you restart the application.
Duke remembers your tasks, so that you don't have to. 

## Features 
1. Add tasks 
1. Delete tasks
1. Mark tasks as completed
1. Tag tasks
1. Search for tasks 
1. List all tasks
1. Exit application

### Add task
There are three types of tasks you can add. Todos, Deadlines and Events. 

* `todo take out trash` - Adds a todo to the list.
* `deadline CS2103 IP /by 21/09/2019 1900` - Adds a deadline to the list
* `event birthday surprise /at 10/10/2019 2000 - 10/10/2019 2359` - Adds an event to the list


### Delete task
`delete 2` - Removes second task from the list

### Mark task as completed
`done 1` - Marks first task as done

### Tag task
`tag 1 #housework` - Adds the tag 'housework' to the task

### Search for tasks
`find CS2103` - Displays list of tasks that match the keyword 'CS2103'

### List all tasks
`list` - Displays all your tasks


### Exit
`bye` - Saves your tasks and exits the application

## Todo
1. Beautify UI 
1. Write more tests
1. Have a reminder function
