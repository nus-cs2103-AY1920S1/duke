# User Guide

## 1. Introduction
Duke is a product for those who prefer to have a simple interface to note down their reminders. Duke is optimised for those who prefer to work with a Command Line Interface(CLI) while still having a basic GUI.

## 2. Quick Start
1.Ensure that you have Java 11 or above installed in your Computer.

2.Download the latest duke.jar [here.](https://github.com/ROHITREDDYBALAM/duke/releases)

3.Copy the file to the folder you want to use as the home folder for your Duke.

4.Double-click the file to start the app. The GUI should appear in a few seconds.

## Features 

###  3.1 Adding a task
Adds task to Duke. There are three types of tasks : 

#### 3.1.1 Todo Task
Format : 'todo {description}'

#### 3.1.2 Deadline Task
Format : 'deadline {description) /by {date}'

#### 3.1.3 Event Task
Format : 'event {description} /at {mm/dd/yyyy hh mm)'
Example : event birthday /at 10/10/2019 18 00

### 3.2 List
Shows the list of all tasks in stored in Duke at that point.
Format : 'list'

### 3.3 Delete
Format : 'delete {index of task}'
Deletes the task corresponding to the index.

### 3.4 Done
Format : 'done {index of the task}'
Marks the task corresponding to the task as done

### 3.5 Find
Format : 'find {word}'
Shows tasks that contain the word.

### 3.6 Archive
Format : 'archive {index of task}'
Archives the task corresponding to the index which can be retrieved from 'data/DukeArchive.txt'

### 3.7 Bye
Format : 'bye'

## Additional Information
1. Duke saves the list after every command.
2. When typing commands with index number, make sure there is a task corresponding to that number in the list of tasks.
