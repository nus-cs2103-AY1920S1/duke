# Quack - User Guide 

A personal chatbot which helps users keep track of their todo tasks, events and deadlines in an interactive manner.

## Getting Started

1. Ensure that you have Java Version 11 installed on your computer.
2. Download the latest `quack.jar` [here](https://github.com/choongyx/duke/releases).
3. Copy the file to the folder you want to use as the home folder for your Quack.
4. Double-click the file to start the app. The GUI should appear in a few seconds as shown below.
![Screenshot of Quack](https://github.com/choongyx/duke/blob/master/docs/Ui.png?raw=true)
5. Type a command in the command box and press `Enter` to execute it.
   e.g. typing `todo buy coffee` and pressing Enter will add a new Todo task: buy coffee.
   
   Some example commands you can try:
   
   `list` : lists all tasks
   
   `delete 1` : deletes first task of list

   `bye` : exits the app
6. Refer to Features for the detailed list of features and commands.   

## Features 

### 1. Add Tasks

#### 1.1 Add Todo Tasks: `todo`
 You can add a new Todo task to the list of tasks.
 
 Format: `todo [DESCRIPTION]`
 
 Example: `todo buy coffee`

#### 1.2 Add Event Tasks: `event`

You can add a new Event task to the list of tasks. Event tasks have a start date and time associated with it.
 
 Format: `event [DESCRIPTION] /at [DATEANDTIME]`
 
 > Note: DATEANDTIME is to be specified in DD/MM/YYYY HHmm.
 
 Example: `event project meeting /at 18/09/2019 1400`

#### 1.3 Add Deadline Tasks: `dateline`

You can add a new Deadline task to the list of tasks. Deadline tasks have a deadline associated with it.
 
Format: `dateline [DESCRIPTION] /by [DATEANDTIME]`
 
> Note: DATEANDTIME are to be specified in DD/MM/YYYY HHmm.
 
Example: `dateline post lecture quiz /at 19/09/2019 2359`
 
### 2. List tasks

View list of all tasks, their type and status.

Format:`list`

### 3. Mark task as done

Mark a task as done. Status changes from 0 to 1.

Format `done [TASKNUMBER]`

Example: `done 1` (Marks the first task as done)

### 4. Delete task

Delete a task from list.

Format : `delete [TASKNUMBER]`

Example: `delete 2` (Deletes the second task in the list)

### 5. Find a task

Find a task among all the tasks by searching for a keyword.

Format: `find [KEYWORD]`

Example: `find book`

### 6. View statistics

View the total number of tasks, number of each type of tasks, total number of task completed and the number of each type of task completed.

Format: `stats`

### 7. Saving the data

Data is saved in the hard disk `data/tasks.txt` automatically after any command that changes the data.
There is no need to save manually.

### 8. Bye

Exit from the application.

Format: `bye`

