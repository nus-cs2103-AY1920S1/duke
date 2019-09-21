# User Guide

## Table Of Contents
1. Introduction
1. Quick Start
1. Features
    1. feature 1
1. FAQ
1. Command Summary


## Introduction

### Duke is a simple **task manager** for the everyday user! Use simple commands like list and todo to keep track of all your tasks. Add or delete tasks, include dates and time and mark them if they are done or not!


## Quick Start

1. Ensure you have Java `11` or above stored in your Computer.

1. Download the latest duke-1.0.3.jar here.

1. Copy the file to the folder you want to use as the home folder for you Task Manager.

1. Double-click the file to start the app. The GUI should appear in a few seconds.

    ![GUI](/UI.png)

5. Type the command in the command box and press Enter.
    eg. typing `help` and pressing Enter will display the commands.

6. Some Example Commands you can try:
* `list` : lists all tasks
* `delete 3` : deletes task 3
* `help` : displays help menu 

7. Refer to Section 3, "Features" for details of each command.


## Features 

1. ### Viewing Help: `help`
Format: `help`

2. ### Adding an item: `todo`/`event`/`deadline`
Adds a task in the Task List.
    2. Adding Todo: `todo`
    Format: `todo DESCRIPTION`
    2. Adding Event: `event`
    Format: `event DESCRIPTION /at DD/MM/YYYY HHMM`
    2. Adding Deadline: `deadline`
    Format: `deadline DESCRIPTION /at DD/MM/YYYY HHMM`

    Examples:
    * `event CS2103T /at 29/09/2019 1830`
    adds event CS2103T with date as 29th September 2019 and time of 6.30pm into the task list. 

    * `todo CS2100 Homework`
    adds todo CS2100 Homework into the task list.

3. ### Listing all Tasks: `list`
Shows a list of all tasks in the task list.
Format: `list`

4. ### Marking Task as Complete: `done`
Marks the task as complete 
Format: `done INDEX`

    Examples:
    * `done 3`
    Marks task number 3 on the list as done.

5. ### Deleting a task: `delete`
Deletes the specified task from the task list.
Format: `delete INDEX`

    Examples:
    * `delete 2`
    Deletes the 2nd task in the task list.

6. ### Finding a task: `find`
Finds tasks whose description contain any of the given keywords.
Format: `find KEYWORD`

    Examples:
    * `find CS2103T`
    Returns all tasks with `CS2103T` as description

7. ### Exiting the Program: `bye`
Exits the program.
Format: `bye`

8. ### Saving and loading the data
Task list data is saved in the jar file automatically after any command that changes the data. 
There is no need to save manually.

    Task list data is loaded from the jar file automatically when opening the program. 
    There is no need to load data manually.

 
## FAQ
**Q**: How do I transfer my data to another Computer?

    **A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Task List folder.


## COMMAND SUMMARY

* **Todo** `todo DESCRIPTION`
e.g. `todo CS2100 Homework`

* **Event** `event DESCRIPTION /at DD/MM/YYYY HHMM`
e.g. `event Tutorial /at 09/08/2019 1500`

* **Deadline** `deadline DESCRIPTION /at DD/MM/YYYY HHMM`
e.g. `deadline Homework /at 09/08/2019 1500`

* **List** `list`

* **Done** `done INDEX`
e.g. `done 3`

* **Delete** `delete INDEX`
e.g. `delete 2`

* **Find** `find KEYWORD`
e.g. `find CS2100`

* **Help** `help`

