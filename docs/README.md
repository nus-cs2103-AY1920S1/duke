# User Guide

## Features 

### Feature 1 
Description of feature.

## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
#  Duke

<img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/readmeLogo.jpg">

Duke is a simple and pleasant todolist manager, with its own pre-defined commands. Users type in commands to tell Duke 
what to do for you. 

## Table Of Contents

## Introduction
### About
This is a java projecte created for CS2103T Software Engineering. This java project was 
developed using Java version 11.0.1 in MacOS Mojave Version 10.14.6<br /><br />
Author: Ong Bing Jue <br/>
Version: 1.0.4 <br />
Release Date: 30 Sepetember 2019 <br />
Github: http://github.com/bjhoohaha/duke <br />

### Getting Started

* Java JDK 11 <br />
Download and install jdk 11 from: <br />
https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html

* Mac / Linux <br />
    `java -jar Duke-0.1.3.jar`
* Windows <br />
Double click the jar file to run

### Credits

**Natalia Raices**
 * Font designer for the 'Delius Unicase' font used in the graphical user interface

## Features

A task in Duke can be classified into three main categories: `todo`, `deadline`, `event `

### <img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/todo.png" width="500">

A `todo` task is a task item that only has a task description. A todo task does not contain a date field.

`todo <taskDescription>` : creates a new todo task

Example of usage: <br />
`todo borrow books`

Expected Outcome:

### <img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/deadline.png" width="500">

A `deadline` task is a task item that has a task description and a task date. The task date indicates when the 
`deadline` task is due by. The task date must follow a specified `dd/MM/yy HH:mm` format.

`deadline <taskDescription> /by <taskDate>` : creates a new deadline task

Example of usage: <br />
`deadline homework /by 30/09/2019 1200`

Expected Outcome:

Possible Error: <br />
`InvalidParameters: `: <br />
If either the `<taskDescription>` or `<task date>` is empty or the `taskDate` is not in the valid `dd/MM/yy HH:mm` 
format

### <img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/event.png" width="500">

A `event` task is a task item that has a task description and a task date. The task date indicates when the 
`event` task is happening at. The task date must follow a specified `dd/MM/yy HH:mm` format.

`event <taskDescription> /at <taskDate>` : creates a new event task

Example of usage: <br />
`event meeting /by 30/09/2019 1200`

Expected Outcome:

Possible Error: <br />
`InvalidParameters: `: <br />
If either the `<taskDescription>` or `<task date>` is empty or the `taskDate` is not in the valid `dd/MM/yy HH:mm` 
format

### <img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/list.png" width="500">


Displays all task items in `list` view. The items are displayed in the order they are entered

`list` : view all task items in list view

Example of usage: <br />
`list`

Expected Outcome:

### <img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/done.png" width="500">

Each task item can be marked as `done`. It marks the task at the specified index in `list` view as done.

`done <index>`: marks a specified task item as done

Example of usage: <br />
`done 1`

Expected Outcome:

Possible Error: <br />
`InvalidParameters: `: <br />
If the specified `<index>` is not valid

### <img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/delete.png" width="500">

Each task item can be deleted. It deletes a task item at the specified index in `list` view.

`delete <index>`: delete a task item

Example of usage: <br />
`delete 1`

Expected Outcome:

Possible Error: <br />
`InvalidParameters: `: <br />
If the specified `<index>` is not valid

### <img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/find.png" width="500">

Find the task with a matching keyword entered by the user

`find <keyword>`: find task item with matching keyword

Example of usage: <br />
`find books`

Expected Outcome:


### <img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/remind.png" width="500">

Sets a reminder for a specified task. The reminder is set for the task with the specified index in the list view. The
date set for the reminder must follow the `dd/MM/yy HH:mm` format.  To change the date for a reminder that has been set,
simply enter a new remind command and the date will be automatically overwritten.

`remind <index> <reminderDate>`: sets a reminder for a specified task

Example of usage: <br />
`remind 2 01/09/2019 0000`

Expected Outcome:

`InvalidParameters: `: <br />
If the specified `<index>` is not valid or `<reminderDate>` is not in the correct date format

### <img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/schedule.png" width="500">

See all tasks in `schedule` view. The `schedule` view is sorted by date order. 

`schedule`: see all task in schedule view

`schedule <date>`: see all task in schedule view for a specified date

Example of usage: <br />
`schedule`

`schedule 01/09/2019 0000` <br/>

Expected Outcome:

`InvalidParameters: `: <br />
If the specified `<index>` is not valid or `<reminderDate>` is not in the correct date format