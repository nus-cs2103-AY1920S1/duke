# Duke - User Guide
![Image of Task Manager](Ui.png)

## Table of Content
1. [Introduction](#introduction)
2. [Get Started](#get-started)
3. [Features](#features)
4. [FAQ](#faq)
5. [Command Summary](#command-summary)

## Introduction
Duke is a desktop application that helps users to manage their day-to-day tasks.
It has a Graphical User Interface (GUI) that allows user-friendly interaction 
and uses Command Line Interface (CLI) for data entries.

## Get Started

1. Download Java Runtime environment to run `duke.jar` file [here](https://www.java.com/en/download/).
2. Download the latest release of `duke.jar` [here](https://github.com/junnbang/duke/releases).
3. To start the application, double-click on `duke.jar`

## Features
*Command Format 🌟🌟🌟*
- *`<action> <parameter1> <parameter2> <parameter3>`* <br />
E.g. event SkillsFuture Seminar /at 15/9/2019 1400
- **Order of parameter** is **IMPORTANT**
- Commands are **CASE-SENSITIVE**
- Date format **MUST** be followed strictly.

### 1. View user commands
Format: `help`

### 2. View all tasks: **`list`**
Format: `list`
### 3. Add a new task: **`todo`**, **`event`**, **`deadline`**
Adds a new task into the task manager. <br />
#### There are 3 types of tasks you can add into Duke:
1. `todo` task - A to-do task which requires description only.
    - Format: `todo <description>`
2. `deadline` task - A deadline to meet which requires a description and datetime.
    - Format: `deadline <description> /by <date & time>`
3. `event` task - An event which requires a description and datetime.
    - Format: `event <description> /at <date & time`
    
#### Examples:
1. `todo return storybook`
2. `deadline Submission of Maths Worksheet /by 29/9/2019 1300`
3. `event John's birthday /at 1/8/2020 1800`

### 4. Find task using a keyword: **`find`**
Format: `find <keyword>`

#### Examples:
1. `find workshop`
2. `find Project work`

### 5. Mark a task as done: **`done`**
Format: `done <index of task on list>`

#### Example:
1. done 3

### 6. Delete a task: **`delete`**
Format: `delete <index of task on list>`

#### Example:
1. delete 2

### 7. View your schedule for a specific date: **`view`**
Format: `view <date>` (date format: dd/mm/yyyy)

#### Examples:
1. `view 29/10/2019`
2. `view 1/2/2019`

### 8. Exit the program: **`bye`**
Format: `bye`

## FAQ
*Q*: Where is the tasks file saved at? <br />
*A*: Go to the directory where you put your `duke.jar` at. 
There is a `data` folder created and it contains your `tasks.txt` file.

## Command Summary
1. View user commands: `help`
2. View tasks: `list`
3. Add a new task:
	- `todo <description>`
	- `deadline <description> /by <date & time>`
	- `event <description> /at <date & time>`
4. Find: `find <keyword>`
5. Mark task as done: `done <index of task on list>`
6. Delete task: `delete <index of task on list>`
7. View schedule by date: `view <date>`
8. Exit the program: `bye`
