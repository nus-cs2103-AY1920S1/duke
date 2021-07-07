# User Guide

A personal chatbot which helps users keep track of their todo tasks, events and deadlines in an interactive manner.

## Getting Started

1. Ensure that you have Java Version 11 installed on your computer.
2. Download the latest `DIOke.jar` [here](https://github.com/moziliar/duke/releases).
3. Move the file to the target folder where the app will be stored.
4. Double-click the file to start the app. The GUI should appear in a few seconds as shown below.
![DIOke](https://github.com/moziliar/duke/blob/master/docs/Ui.png?raw=true)
5. Key in your commands in the command box, press `Enter` and wait for the response.
   
   Examples:
   * `list` 
   * `todo read book (needs 2 hours)`
   * `deadline return book /by 26/12/2019 1230`
   * `delete 1`

## Features 

### 1. Add Tasks

#### 1.1 Add Todo Tasks: `todo`
 Add a new Todo task to the list of tasks.
 
 (optional: add (needs [duration]) to add duration to the Todo)
 
 Format: `todo [DESCRIPTION] [optional: DURATION]`
 
 Example: `todo borrow book`

#### 1.2 Add Event Tasks: `event`

 Add a new Event task with time and date to the list of tasks. 
 
 Format: `event [DESCRIPTION] /at [DATEANDTIME]`
 
 > Note: DATEANDTIME has the format DD/MM/YYYY HHmm.
 
 Example: `event inteview /at 18/09/2019 1400`

#### 1.3 Add Deadline Tasks: `deadline`

Add a new Deadline task with date and time to the list of tasks.
 
Format: `deadline [DESCRIPTION] /by [DATEANDTIME]`
 
> Note: DATEANDTIME has the format DD/MM/YYYY HHmm.
 
Example: `deadline return book /by 20/09/2019 1900`
 
### 2. List tasks: `list`

View list of all tasks, with types (e.g. `[T]` for Todo) and progress (Done/Not done).

Format:`list`

### 3. Mark task as done: `done`

Mark a task as done. Status of the task is changed from ✗️ to ✓.

Format: `done [INDEX]`

Example: `done 1`

### 4. Delete task: `delete`

Delete a task from the list.

Format: `delete [INDEX]`

Example: `delete 2`

### 5. Find task: `find`

Find a task in the task list with a keyword.

Format: `find [KEYWORD]`

Example: `find book` (Find task containing "book")

### 7. Saving the data

Data saving is automated after each command that changes the task list.

### 8. Bye: `bye`

Exit from the application.

Format: `bye`