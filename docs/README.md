# User Guide

## 1 Introduction
Project Duke is an interactive program designed to help users manage their 
everyday simple task list. With an easy-to-use command line interface, Duke 
supports many features to help users keep track of their everyday tasks, be it 
something the user wants to do, an event to attend, or a deadline to meet.

## 2 Quick Start
To launch the Duke application, follow these steps:
1. Ensure you have Java version 11 or later installed on your computer.
1. Download the latest version of Duke [here](https://github.com/zhangxuan97/duke/releases).
1. To run the application:
    * For Mac OS users: Navigate to the folder which contains the jar file (latest `v0.2.jar`) 
    in terminal and enter the command `java -jar v0.2.jar`.
    * For Windows users: Run the application by double clicking the `v0.2.jar` file.

The application should be open in a few seconds.

## 3 Features and Usages

### 3.1 Task Operations

#### 3.1.1 Adding a Task: `todo`|`deadline`|`event`

Adds a particular type of task to the task list. The types of tasks include `Todo`, 
`Deadline`, and `Event`.

Syntax:

`todo TASKNAME`: Adds a new todo task in the task list.

`deadline TASKNAME / DATE TIME`: Adds a new deadline task in the task list 
which expires on the `DATE` (specified in the format `dd/MM/yyyy`) and `TIME` 
(specified in the format `HHmm`).

`event TASKNAME / DATE TIME`: Adds a new event task in the task list 
which expires on the `DATE` (specified in the format `dd/MM/yyyy`) and `TIME` 
(specified in the format `HHmm`).

Example Usage | Expected Output
------------- | -------------- 
`todo Buy a new T-shirt` | `[T][☓] Buy a new T-shirt`
`deadline Lecture Quiz / 12/09/2019 2359` | `[D][☓] Lecture Quiz (by: September 12 2019 11:59pm)`
`event Dental / 17/10/2019 1600` | `[E][☓] Dental (at: October 17 2019 04:00pm`

The default completion status of a newly added task is set as incomplete.

#### 3.1.2 Deleting a Task: `delete`

Deletes a particular task identified by its task ID. Note: To check the list of task IDs, 
use the `list` command.

Syntax:

`delete TASK_ID`: Deletes the task with ID `TASK_ID`, if such a task exists.

Example Usage | Expected Output
------------- | -------------- 
`delete 2` | Acknowledgement that Duke has removed task 2, and the number of remaining tasks.

#### 3.1.3 Marking a Task as Complete: `done`

To indicate that the user has completed a particular task.

Syntax:

`done TASK_ID`: Marks the task as complete, if such a task exists.

Example Usage | Expected Output
------------- | -------------- 
`done 2` | Acknowledgement that Duke has marked task as complete.

#### 3.1.4 Finding a Task by Keyword: `find`

Searches through all the names of the tasks and outputs the list of tasks which contains 
the specified keyword. It is not case-sensitive.

Syntax:

`find KEYWORD`: Outputs the list of tasks whose names partially (or fully) contains the 
keyword `KEYWORD`.

Example Usage | Expected Output
------------- | -------------- 
`find report` | A list of tasks and their details whose task names contains the word `report`.

### 3.2 Task List Operations

#### 3.2.1 Listing All Tasks: `list`

Lists out all current tasks in the format `[TASK_ID]. [TASKTYPE][STATUS] TASK_NAME`. If the task is an event 
task or a deadline task, the respective date would be printed as well.

Syntax:

`list`: Lists out all existing tasks in user's task list.

Example Usage | Expected Output
------------- | -------------- 
`list` | Complete list of tasks.

#### 3.2.2 Archiving Tasks: `archive`

The user can choose to archive a set of tasks from the current task list. Once archived, it will 
be deleted form the current task list and saved in a file `archive.txt` - the task name, details, 
as well as its date of archive can be seen in `archive.txt`.

Syntax:

`archive done`: Archives all tasks whose status has been mark as completed.

`archive today`: Archives all expired tasks, i.e. date associated with the task is before today. 
For example, if I have a task `[E][☓] Concert (at: September 14 2019 10:00pm)`, using the command 
`archive today` on September 15 2019 will archive the task.

`archive TASK_ID`: Archives a task with the ID given by `TASK_ID`.

`archive all`: Archives all existing tasks in current task list.

Example Usage | Expected Output
------------- | -------------- 
`archive done` | Acknowledgement from Duke that all completed tasks have been archived.
`archive today` | Acknowledgement from Duke that all expired tasks have been archived.
`archive 2` | Acknowledgement from Duke that task 2 has been archived, if task 2 exists.
`archive all` | Acknowledgement from Duke that all tasks have been archived.

#### 3.2.3 Clearing Tasks: `clear`

Removes all tasks from the task list.

Syntax:

`clear`: Empties the task list.

Example Usage | Expected Output
------------- | -------------- 
`clear` | Acknowledgement from Duke that task list is cleared.

### 3.3 Global Operations

#### 3.3.1 Viewing Help: `help`

Outputs the list of all commands and their corresponding syntax. Users can choose to seek 
a more in-depth help option by entering the particular command behind help.

Syntax:

`help`: Shows the user a list of all possible commands.

`help [SPECIFIC_COMMAND]`: Explains to the user what the command does and the syntax of the 
command.

Example Usage | Expected Output
------------- | -------------- 
`help` | A list of all possible commands is displayed
`help find` | `Finds tasks in your task list by a specific keyword. Syntax: find <keyword>`

#### 3.3.2 Exiting the Program: `bye`

Exits the program.

Syntax:

`bye`: Upon entering "bye", the program will exit.
