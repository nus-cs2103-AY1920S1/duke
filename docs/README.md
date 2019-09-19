# The Jedi Academy - User Guide
![Yoda](/src/main/resources/images/Yoda.png)

## 1. Introduction
_**The Jedi Academy**_ is a Personal Assistant Chatbot that helps a person to keep track of various things. _**The Jedi Academy**_ is optimized for those who prefer to work with a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). Continue reading to find out more about its features and usage!

## 2. Getting Started
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest _**The Jedi Academy**_ jar file here.
3. Copy the file to the folder you want to use as the home folder. Your tasks will be saved to and loaded from `data/duke.txt` located in the same folder.
4. Double-click the file to start the app. The GUI should appear in a few seconds.
5. Type the command in the command box and press Enter to execute it.
6. Refer to Section 3, “Features” for details of each command.

## 3. Features 

### 3.1. Listing all Tasks: 
### `list` 
Lists all Tasks saved in hard drive currently

Usage Format: 
`list`

### 3.2. Adding Todo Task: 
### `todo` 
Adds a Todo Task

Usage Format: 
`todo {TASK}`

Example Usage:
>todo cartwheel to school

### 3.3. Adding Deadline Task: 
### `deadline` 
Adds a Deadline Task

Usage Format: 
`event {TASK} /by {DATETIME}`

Example Usage:
>deadline assignment /by 14/07/2019 2359

### 3.4. Adding Event Task: 
### `event` 
Adds a Event Task

Usage Format: 
`event {TASK} /at {DATETIME}`

Example Usage:
>event diving competition /at 21/07/2019 1400

### 3.5. Marking Task as Done: 
### `done` 
Marks a Task as Done

Usage Format: 
`done {INDEX}`

Example Usage:
>done 2

### 3.6. Deleting a Task: 
### `delete` 
Deletes a Task

Usage Format: 
`delete {INDEX}`

Example Usage:
>delete 3

### 3.7. Finding a Task: 
### `find` 
Finds a Task using Keyword

Usage Format: 
`find {KEYWORD}`

Example Usage:
>find something

### 3.8. Exiting the Program: 
### `bye` 
Exits the Program

Usage Format: 
`bye`

### 3.9. Saving/Loading Tasks

### 3.10. Detecting duplicate Tasks
Throws an error message if duplicate Task is detected:
>"OOPS!!! Duplicate task already exists!"

## 4. Command Summary
* **List**: `list`
* **Todo**: `todo {TASK}`
* **Deadline**: `deadline {TASK} /by {DATETIME}`
* **Event**: `event {TASK} /at {DATETIME}`
* **Done**: `done {INDEX}`
* **Delete**:`delete {INDEX}`
* **Find**: `find {KEYWORD}`
* **Bye**: `bye`
