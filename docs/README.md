# User Guide for DUKE

Table of Contents
1. Introduction
2. Quick Start
3. Features
4. Usage guide
    - Help command
    - List task
    - Create task
    - Delete task
    - Mark task as done
    - Find task
    - Exit application

## 1.0 Introduction
Duke is a Task manager CLI app that gives you a list of tasks to do. This app is optimised for those who prefer to work with a command line interface (CLI).

## 2.0 Quick Start
1. Make sure you have Java 11.0 installed in your computer
2. Download the latest Jar release @
3. Navigate to the folder containing the jar file and use java -jar {filename} to run the application

## 3.0 Features 


### Data
Saves your list to your hard disk. 

Your list is saved to a txt file each time you use the app to update your list.

### Support for different tasks:
Each task has different attribute based on their types:
1. Todo task:
Has description name. No date or time present.
2. Deadline task:
Has description name, date and time. (in the format of dd/mm/yyyy HHmm e.g. 03/11/2019 1400)
3. Event task:
Has description name, date and time. (in the format of dd/mm/yyyy HHmm e.g. 15/04/2019 1700) 

## 4.0 Usage
Commands are all case-insensitive e.g. `help` and `Help` works.

#### 4.1 Help command - `Help` 

Command to list all the available commands.

Example of usage: 

e.g. `help`


#### 4.2 List Task - `list`

Describe list all your current task on the list.

Example of usage: 

e.g. `list`


#### 4.3 Create Task - `Todo`, `Deadline` and `Event`

Create task to add into your list in the app.

This command is divided to create three different type of tasks: `Todo`, `Deadline` and `Event`

`Todo`
- Task with only description
    - Format on command line: `Todo` [description]
    - e.g. enter `todo homework`
    - Outcome: `[T][✘] homework` is created

`Deadline`
- Task with description, date and time
    - Format on command line: `Todo` [description] /by [dd/mm/yyyy HHmm]
    - e.g. enter `deadline team project /by 15/05/2019 1700`
    - Outcome: `[D][✘] team project (by: 15th of May 2019, 5pm)` is created
    
`Event`
- Task with description, date and time 
    - Format on command line: `Event` [description] /at [dd/mm/yyyy HHmm]
    - e.g. enter `event dinner /at 27/06/2019 1300`
    - Outcome: `[E][✘] dinner (at: 27th of June 2019, 1pm)` is created
    
#### 4.4 Delete task - `delete`

Delete a task in the list.

Format on command line: `delete` [index]

e.g. `delete 1`

outcome: Task on the index "1" of the list is deleted.


#### 4.5 Mark task as done - `done`

Mark task as Done in list [✘] -> [✓]

e.g. `done 1`

outcome: 

Nice! I've marked this task as done:
`[T][✓] homework`

#### 4.6 Find task - `find`

Find task in list by description

Format on command line: `find` [keyword]

e.g. `find homework`

outcome: 

Here are the matching tasks in your list:
1. `[T][✓] homework`

#### 4,7 Exit Application - `bye`

Exits the application

e.g. `bye`