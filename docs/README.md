# User Guide

## 1. Introduction

Duke is a GUI (Graphical User Interface) application that lets you set reminders for tasks. The user interacts with the application through the Command Line Interface (CLI).  

## 2. Quick Start

1.Ensure that you have Java 11 or above installed in your Computer.

2.Download the latest duke.jar [here](https://github.com/eizon05/duke/releases).

3.Copy the file to the folder you want to use as the home folder for your Duke.

4.Double-click the file to start the app. The GUI should appear in a few seconds.

## Features 

### 3.1 Adding a Task

Adds (and saves) a task to Duke. There are 3 kinds of tasks:

#### 3.1.1 Todo 

Format: "todo {description}"

#### 3.1.1 Deadline 

Format: "deadline {description} /by {date}"

Note: Slash used must be a forward slash

#### 3.1.1 Event 

Format: "event {description} /at {date)"

Note: Slash used must be a forward slash

### 3.2 Listing all Tasks

Format: "list"

Lists all the tasks that are saved in Duke (in order of time saved)

### 3.3 Deleting a Task

Format: "delete {index}"

Deletes a task from the list which is at the index, "{index}"

### 3.4 Marking a Task as Done

Format: "done {index}"

Marks the task at the index, "{index}", (in the list) as done

### 3.5 Finding a Task that contains a specific string

Format: "find {string}"

Shows the tasks that contains a specific string

### 3.6 Archiving a Task

Format: "archive {index}"

Archives the task at the index, "{index}", of the list.

Note: All archived tasks can be seen in 'data/archive.txt'


### 3.7 Bye

Format: "bye"

Terminates the application

## Additional Information

1. Duke saves the entire list of tasks  whenever you add, delete or mark a task as done.
2. When typing commands with index numbers, please make sure to type the index number that correlates to the number in the list of tasks
3. Whenever a task is archived, it will be removed from the list of tasks
4. Archived tasks can be accessed from the data folder which is located in the same directory as the application.
