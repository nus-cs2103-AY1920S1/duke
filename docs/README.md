# User Guide

A personal chatbot which helps users keep track of their todo tasks, events and deadlines in an interactive manner.

## Getting Started

1. Ensure that you have Java Version 11 installed on your computer.
2. Download the latest `poketask.jar` [here](https://github.com/jonchan51/duke/releases).
3. Move the file to the target folder where the app will be stored.
4. Double-click the file to start the app. The GUI should appear in a few seconds as shown below.
![PokeTask](https://github.com/jonchan51/duke/blob/master/docs/Ui.png?raw=true)
5. Key in your commands in the command box, press `Enter` and wait for the response.
   
   Examples:
   * `list` 
   * `todo study`
   * `deadline return book /by 26/12/2019 1230`
   * `delete 1`

## Features 

### 1. Add Tasks

#### 1.1 Add Todo Task: `todo`
Add a new Todo task to the list of tasks.
 
Format: `todo [DESCRIPTION]`
 
Example: `todo return book`

#### 1.2 Add Event Task: `event`

Add a new Event task with date and time to the list of tasks. 
 
Format: `event [DESCRIPTION] /at [DATETIME]`
 
> Note: DATETIME has the format DD/MM/YYYY HH:MM.
 
Example: `event meeting /at 21/10/2019 23:00`

#### 1.3 Add Deadline Task: `deadline`

Add a new Deadline task with date and time to the list of tasks.
 
Format: `deadline [DESCRIPTION] /by [DATETIME]`
 
> Note: DATETIME has the format DD/MM/YYYY HH:MM.
 
Example: `deadline borrow file /by 28/02/2020 12:00`
 
### 2. List tasks: `list`

View list of all tasks in the current task list.

Format:`list`

### 3. Mark task as done: `done`

Marks tasks as done from the task list.

Format: `done [INDEX]...`

Example: `done 1 3 5 2`

### 4. Delete task: `delete`

Delete tasks from the task list.

Format: `delete [INDEX]...`

Example: `delete 2 9 1 3`

### 5. Find task: `find`

Find a task in the task list with a keyword in their description.

Format: `find [KEYWORD]`

Example: `find book`

### 6. Help: `help`

Lists the usage instructions for all commands.

Format: `help`

### 7. Saving the data

Data saving is automated after each command that changes the task list.

### 8. Bye: `bye`

Exit from the application.

Format: `bye`