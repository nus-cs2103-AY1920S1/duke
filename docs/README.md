# User Guide

## Introduction
Welcome to Duke User Guide. This application allows user to manage their daily tasks via 
**Command Line Interface (CLI)** and through a friendly **Graphical User Interface (GUI)**. 
The application comes with the feature to add tasks, mark task as done and much more!

## How to get Started
1. Ensure your computer has `Java 11` version for compatibility to run the program. Otherwise, you can download it from
 [here](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html).
1. After download the latest release version of the `Duke.jar` [here](https://github.com/ArgVampir/duke/releases).
1. Open terminal and `cd` to the path of the downloaded jar. Execute the command `java -jar duke.jar`.

## Features

### 1. View all tasks: **`list`**
Type `list` to view the list

### 2. Add a new task: **`todo`**, **`event`**, **`deadline`**

#### There are 3 types of tasks you can add into Duke:
1. `todo` task - A to-do task that only need description
  Type `todo <description>` to add todo task
2. `deadline` task - A deadline to meet which requires a description and datetime.
  Type `deadline <description> /by <date & time>` to add deadline task
3. `event` task - An event requires the description and datetime
  Type `event <description> /at <date & time>` to event task
  
**Examples:**
- `todo borrow book`
- `deadline Assignment#2 /by 23/11/2019 2359`
- `event Career Fair /at 15/3/2020 1800`

### 3. Find task using a keyword: **`find`**
Type `find <keyword>` to find task related to keyword

**Examples**
1. `find homework`
2. `find art exhibition`

### 4. Mark task as done: **`done`**
Type `done <index of task on list>`to mark particular task as finish


### 5. Delete task: **`delete`**
Type `delete <index of task on list>` to delete particular task from the task list


### 6. Sort the tasks: **`sort`**

#### There are 3 types of sorts:

1. `sort todo` sorts the task list displaying the todo first

2. `sort deadline` sort the task list displaying the deadline first

3. `sort event` sort the task list displaying the event first
  
  
### 7. Farewell to Duke: **`bye`**
type `bye` to make a farewell


## Additional Notes

- Keep in mind that the commands are  **case-sensitive** 
- Date format needs to be follow strictly
