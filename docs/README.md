# User Guide

## What is Duke
Duke is a task management system. This desktop application allows a single user to add and remove tasks; save/load tasks into/from a separate file; find tasks by input string; update priorities of items; list all tasks the user has saved; list all tasks by order of priority. This application uses the command line interface; this means you operate the application by typing commands in to the Command Box.

This is what Duke looks like:
![Duke Screenshot](Ui.png)

## About this document
This document shows all the features of **Duke**

Note the following symbols and formatting used in this document: 

`list` A grey highlight (called a mark-up) indicates that this is a command that can be typed into the command line and executed by the application.

## Basic commands
This section contains basic commands for **Duke**.

### Adding tasks
This section contains instructions on how to add different tasks into a list. There are 3 types of tasks:

1. Todo
2. Deadline
3. Event

### Adding Todo tasks: `todo`
This command adds a todo task into the list of tasks. A todo task contains only a descripton of itself.

To add a **Todo** task:

1. Type `todo {description}` and press enter or click **Send** to execute it
2. The result box will display the todo task added and the total tasks left in the list

Expected outcome:
![Add Todo](addTodo.PNG)

### Adding Deadline tasks: `deadline`
This command adds a deadline task into the list of tasks. A todo task contains a description and the date and time to complete the task by. The format to enter the date is dd/MM/yyyy HHmm. 

Format | Meaning
------------ | -------------
dd | Day of the month represented by 2 digits (e.g. 01 for th first day of the month)
MM | Month of the year represented by 2 digits (e.g. 06 represent June)
yyyy | Year represented by 4 digits (e.g. 2019)
HH | Hours represented by 2 digits
mm | Minutes represented by 2 digits

To add a **Deadline** task:

1. Type `deadline {description} /by {dd/MM/yyyy HHmm}` and press enter or click **Send** to execute it
2. The result box will display the deadline task added and the total tasks left in the list

Expeceted outcome:
![Add Deadline](addDeadline.PNG)

### Adding Event tasks: `event`
This command adds a event task into the list of tasks. An event task contains a descripton of itself and two dates (The starting time and date of the event and the ending time and date of the event). The format to enter the time and date is dd/MM/yyyy HHmm. The start and ending times are separated by a dash.

Format | Meaning
------------ | -------------
dd | Day of the month represented by 2 digits (e.g. 01 for th first day of the month)
MM | Month of the year represented by 2 digits (e.g. 06 represent June)
yyyy | Year represented by 4 digits (e.g. 2019)
HH | Hours represented by 2 digits
mm | Minutes represented by 2 digits

To add a **Event** task:

1. Type `todo {description} /at {dd/MM/yyyy HHmm} - {dd/MM/yyyy HHmm}` and press enter or click **Send** to execute it
2. The result box will display the event task added and the total tasks left in the list

Expected outcome:
![Add Event](addEvent.PNG)

## Listing all Events
Events can be listed in two ways:
1. By order of input (chronological order)
2. By order of priority

### Listing by order of input: `list`
This command lists the events in order of input by the user. Events that were input first will be higher on the list.

To list all tasks:

1. Type `list` and press enter or click **Send** to execute it

Expected outcome:
![List](list.PNG)

###Listing by order of priority: `prioritylist`
This command lists the events by order of priority defined by the users, tasks without any priority assigned will not be displayed in the list. Events assigned and smaller priority value will be higher on the list as compared to larger values. (e.g. priority:1 will be listed higher than priority:5)

To list tasks by priority:

1. Type `prioritylist` and press enter or click **Send** to execute it

Expected outcome:
![Priority List](priorityList.PNG)

## Deleting events: `delete`
This command deletes a **task** from the list according to the index given in the `list` command (index > 0). The deleted task is displayed after deletion.

To delete a task:

1. Type `delete {index}` and press enter or click **Send** to execute it

Expected outcome:
![Delete](delete.PNG)

## Marking events as done: `done`
This commands marks a **task** from the list according to the index given in the `list` command (index > 0). A task orginally left undone will be marked with a tick after the command is executed and displayed.

To mark a task as done:

1. Type `done {index}` and press enter of click **Send** to execute it
Expected outcome:
![Done](done.PNG)

##Find task: `find` 
This command searches for tasks in the list that contain the input given by the user. The command then lists the tasks which description contains the user's input string by chronological order (similar to the order of `list`).

To find tasks which contain user's input:

1. Type `find {user input string}` and press enter of click **Send** to execute it
Expected outcome:
![Find](find.PNG)

## Update task priority: `setpriority`
This command updates the priority of a task in the list according to the index given the in the `list` command. The task's priority is set to the user's input integer (priority > 0). The lower the value of the priority, the higher its priority (e.g. Tasks with priority: 1 is of higher priority as compared to priority: 2).

1. Type `setpriority {index} {priority}` and press enter of click **Send** to execute it
Expected outcome:
![Update Priority](setPriority.PNG)

## Exit: `bye`
This commands exits the program.

1. Type `bye` and press enter of click **Send** to execute it