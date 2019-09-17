# User Guide
## Introduction
Duke is a desktop personal assistant application. It is **optimised for users who prefer to work
with a Command Line Interface (CLI)**.

## Quick start
1. Ensure you have `Java 11` or above installed on your computer.
2. Download the latest jar file [here](https://github.com/C-likethis123/duke/releases/tag/A-Release).
3. Copy the jar file to a desired folder. In the folder, create a folder `/data`. In `/data`, add a file called `duke.txt`.
3. Start a terminal from the folder where the jar file is. Type `java -jar duke-0.1.3.jar`. The GUI should appear in a few seconds.
4. After starting up, Duke greets its users upon starting up.
//put an image here
5. Type commands in the text box and press `Enter` to execute it.
6. Some example commands to try:
    1. `list`: lists all tasks in the task list.
    2. `todo some task`: adds a `ToDo` with a task description `some task` to the task list.
    3. `bye`: exits the application
7. Refer to Features for details on each command.    
     
## Features 

### Adding a task 
There are three types of tasks:
* Todos: Tasks that only has a task description.
* Deadlines: Tasks with a task description and a deadline.
* Events: Tasks that represents events. It has a task description and an event date.

To add a ToDo, type `todo DESCRIPTION`. 
`DESCRIPTION` refers to the task description. 
For example:   `todo read book` creates a ToDo with description 'read book'.


To add a Deadline, type `deadline DESCRIPTION /by DATE`.
`DESCRIPTION` refers to the task description. 
`DATE` refers to the deadline of the date, which has a format DD/MM/YYYY HHMM.
For example, `deadline do CS2100 tutorial /by 19/09/2019 1400`.

To add an Event, type `event DESCRIPTION /at DATE`.
`DESCRIPTION` refers to the task description. 
`DATE` refers to the deadline of the date, which has a format DD/MM/YYYY HHMM.
For example, `event Connect with Facebook Engineers /at 20/09/2019 1700`.

### Deleting a task
To delete a task, the format: `delete INDEX`.

This deletes a task at a specified index. 

`INDEX` refers to the index number shown in the displayed task list.

### Listing all tasks
To see all the tasks, type `list`. 
It returns a list of all the tasks in the task list.

### Finding tasks by name
To find a task by name, type `find KEYWORD`.
It returns a list of tasks with descriptions that contain the keywords.

Example:
`find read`


Note: 
* The search is case-sensitive.
* Only the task description is searched.

### Updating a task
To update a task, type `update INDEX DESCRIPTION DATE`.

`INDEX` refers to the index number shown in the displayed task list.

`DESCRIPTION` refers to the updated task description to update 
a task with. 

`DATE` refers to the date of the task.

![alt text](https://github.com/C-likethis123/duke/blob/master/docs/TaskList.png?raw=true "Task List")
**Referring to the above screenshot:**

1. To update task 8, a ToDo task, type `update 8 read 2103T example user guide`.
The task description will be updated to `read 2103T example user guide`.
2. To update task 1, a Deadline task, type `update 1 do CS2100 tutorial /by 17/09/2018 1700`.
3. To update task 7, an Event task, type `update 7 AngelHack 2019: Singapore /at 03/06/2019`.

### Checking off tasks
To mark a task as done, type `done INDEX`. 

This deletes a task at a specified index. 

The index refers to the index number shown in the displayed task list.

### Exiting the application
To exit the application, type `bye`.

The GUI window closes. 

### Saving data of tasks
Data of tasks are saved in the hard disk automatically after any 
command that changes the data. 

There is no need to save manually. 
