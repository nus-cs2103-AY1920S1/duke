# User Guide

## About
Duke is a task manager application that uses **Command Line Interface(CLI)** and is suitable for those who like to manage their tasks by typing.

## How to Use
1. Ensure that your computer has `Java 11` or above, if not download it from [here](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html).
2. Download the latest release of the application [here]().
3. After downloading the jar file, run your terminal and go to the directory in which the jar file is located, then run `java -jar duke.jar`. The application will start after running the command.
4. Now, you should be able to type in some commands in the application. Refer to the below section to find out different commands that you can use in the application.

## Features

### Add task
Use the keywords `todo`, `deadline`, and `event` to add different types of task into your todo list. All types of task must contain `task description`. For `deadline` and `event` must contain `date/time` information.

##### Usage
`todo [task description]` - will add a new `todo` task with `task description`.

`deadline [task description] /by [dd/mm/yyyy HHmm]` - will add a new `deadline` that should be finished by the specified date and time.

`event [task description] /at [dd/mm/yyyy HHmm]` - will add a new `event` that will happen at the specifed date and time.

##### Example of usage:
`todo borrow book` - will add a new `todo` task with task description of `borrow book` into your todo list application.

`deadline return book /by 02/02/2019 1900` - will add a new `deadline` with task description `return book` and has a deadline on 2nd
February 2019, 7pm.

`event social gathering /at 02/02/2019 1900` - will add a new `event` with task description `social gathering` that will happen on 2nd February 2019, 7pm.

### Delete Task
Use the keyword `delete` to delete a task from your list of tasks.

##### Usage
`delete [task number]` - will delete a task associated with the `task number` that is specified.

##### Example of usage:
`delete 2` - will delete the 2nd task in the list of tasks. If there is no task number 2, an error will be thrown.

### Find Task
Use the keyword `find` to find a certain task by it's `task description`.

##### Usage
`find [task description]` - will find all the tasks that have a keyword that is specified in `task description`.

##### Example of usage:
`find borrow` - will find all the tasks that have a `task description` relating to 'borrow'.

### Mark a task as done
Use the keyword `done` to update the status of the task to `done`

##### Usage
`done [task number]` - will mark the task that is associated with `task number` to done.

##### Example of usage:
`done 2` - will mark the task number 2 to `done`. If there is no task number 2, an error will be thrown.

### Get all the tasks
Use the keyword `list` to get all the tasks that you have in your list.

##### Usage
`list` - will show all the tasks the list.

### Sort the task:
Use the keyword `sortby` to sort the tasks in the list according to the category. Currently, only `deadline` is available as the category.

##### Usage
`sortby [category]` - will sort the tasks based on the `category` that is specified.

##### Example of usage:
`sortby deadline` - will sort the tasks based on their deadline.

### Exiting the application
Use the keyword `bye` to exit the application.

### Saving and loading
Currently saving and loading is automatic whenever you specify a command.