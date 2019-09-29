# User Guide DIS DUKE

Table of contents
1.  Introduction
2.  Quick Start
3.  Features
4.  Usage guide
    -   List Task
    -   Create Task
    -   Delete Task
    -   Mark Task as Done
    -   Find Task
    -   Edit Task
    -   Exit Application

## 1.0 Introduction 
Dis Duke is a Task tracker CLI app that gives you a to-do list. This app is optimised for those who prefer to work with a command line interface (CLI).

## 2.0 Quick Start 
1. Make sure you have Java 11.0 installed on your computer
2. Download the latest Jar release @ https://github.com/EugeneTeu/duke/releases
3. Navigate to the foldder containing the jar file and use java -jar {filename} to run the application


## 3.0 Features 

### Persistent  Data
Save your list to your hard disk. Each time you use the app to update your list, your list is saved to a txt file.

### Support for different tasks: 
Each task has different attributes based on their types: 
1. Todo task:
    *   Only has description name. No date or time present.
2. Deadline task:
    *   Has description name, date and time. (in the format dd/mm/yy hhmm e.g 05/09/18 1300) 
3. Event task:
    *   Has description name, date and time  (in the format dd/mm/yy hhmm-hhmm e.g 05/09/18 1300-1500) 

## 4.0 Usage Guide
Commands are all case-insensitive e.g `list` and `List` works.

### 4.1 `List Task` 

command to list all your current tasks in the list

Format on command line: list

e.g `list` 

### 4.2 `Create Task` 

Create task to add into your list in the app.

This command is divided to create the three different task: `ToDo` , `Deadline` and `Event`

###### `ToDo`

* Task with only description name
    * Format on command line: todo [task description]
    * e.g enter `todo change tyres`
    * Outcome: `[T][✘] change tyres` is created
    
###### `Deadline`

* Task with description name, date and time
    * Format on command line: deadline [task description] /by [dd/mm/yy hhmm]
    * e.g enter `deadline change tyres /by 13/09/18 1500`
    * Outcome: `[D][✘] change tyres (by: 13th of September 18, 3pm)` is created
    
###### `Event`

* Task with description name, date and duration
    * Format on command line: event [task description] /by [dd/mm/yy hhmm-hhmm]
    * e.g enter `event change tyres /at 13/09/18 0800-1500`
    * Outcome: `[E][✘] change tyres (at: 13th of September 18, 8am-3pm)` is created


### 4.3 `Delete Task` 

Delete task in list

Format on command line: delete [index]

e.g `delete 1`

index is the number of the task when `list` is called

### 4.4 `Mark Task as Done` 

Mark task as Done in list [✘]  -> [✓]

Format on command line: done [index]

e.g `done 1`

index is the number of the task when `list` is called

### 4.5 `Find Task` 

Find task in list by task description

Format on command line: find [query]

e.g `find change tyres`

Any partial match or full match with the tasks in the list will cause that task to be returned

### 4.6 `Edit Task` 

update task in list. Note that you cannot change the type of task but only the contents 

e.g `task name` , `date`, `time`

There are three modes of changing: 

1. changing `task name`, `date` and `time`
   * Format on command Line: 
     * For this command, match the type of task in the list to the type here E.g if the task is 'Event' then follow 'Event'
     * Any deviation from the above will cause bugs
     * For `Event` tasks: edit [index]  [task name] t: [dd/mm/yy hhmm-hhmm] 
     * For `Deadline` tasks: edit [index]  [task name] t: [dd/mm/yy hhmm] 
     * For `Todo` tasks: edit [task name] t:
     * index is the number of the task when `list` is called
   
2. changing `task name`
   * Format on command Line:
     * edit [index] desc [task name]
     * index is the number of the task when `list` is called

3. changing `date` and `time`
   * Format on command Line:
     * edit [index] time [dd/mm/yy hhmm]
     * index is the number of the task when `list` is called

### 4.7 `Exit Application` 

Exits the application 

e.g `bye` 
