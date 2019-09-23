#  Duke

<img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/readmeLogo.jpg">

Duke is a simple and pleasant todolist manager, with its own pre-defined commands. Users type in commands to tell Duke 
what to do for you. 

## Table Of Contents
* [Introduction](#introduction)
    * [About](#about) 
    * [Getting Started](#getting-started)
    * [Credits](#credits)
* [Features](#features)
* [Commands](#commands)
    * [Todo](#todo)
    * [Deadline](#deadline)
    * [Event](#event)
    * [List](#list)
    * [Done](#done)
    * [Delete](#delete)
    * [Find](#find)
    * [Remind](#remind)
    * [Schedule](#schedule)
* [FAQ]()



## Introduction
### About
This is a java project created for CS2103T Software Engineering. This java project was 
developed using Java version 11.0.1 in MacOS Mojave Version 10.14.6

### <img id="find" src="https://raw.githubusercontent.com/bjhoohaha/duke/master/docs/Ui.png"> <br/>


Author: Ong Bing Jue <br/>
Version: 1.0.4 <br />
Release Date: 30 Sepetember 2019 <br />
Github: <a href="http://github.com/bjhoohaha/duke">http://github.com/bjhoohaha/duke</a> <br />

### Getting Started

* Java JDK 11 <br />
Download and install jdk 11 from: <br />
<a href="https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html">
https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html
</a>

* Mac / Linux <br />
    `java -jar Duke-0.1.3.jar`
* Windows <br />
Double click the jar file to run

### Credits

**Natalia Raices**
 * Font designer for the 'Delius Unicase' font used in the graphical user interface

## Features
* Add and delete different types of task items _e.g._ `todo`, `deadline`, `event`
* Displays different task items in list view
* Displays different task items in schedule view for all items or for a particular date
* Sets a task item as done
* Find your task items with keywords
* Sets a reminder for your task
* Platform compatibility with Windows, MacOS, Linux
 

## Commands
A task in Duke can be classified into three main categories: `todo`, `deadline`, `event `
<br />
<br />
<br />
### <img id="todo" src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/todo.png" width="500">
***
A `todo` task is a task item that only has a task description. A todo task does not contain a date field.

`todo <taskDescription>` : creates a new todo task

Example of usage: <br />
`todo borrow books`

Expected Outcome:
<img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/todoDemo.png">
<br />
<br />
<br />
### <img id="deadline" src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/deadline.png" width="500">
***
A `deadline` task is a task item that has a task description and a task date. The task date indicates when the 
`deadline` task is due by. The task date must follow a specified `dd/MM/yy HH:mm` format.

`deadline <taskDescription> /by <taskDate>` : creates a new deadline task

Example of usage: <br />
`deadline homework /by 30/09/2019 1200`

Expected Outcome:
<img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/deadlineDemo.png">


Possible Error: <br />
`InvalidParameters  `: <br />
If either the `<taskDescription>` or `<task date>` is empty or the `taskDate` is not in the valid `dd/MM/yy HH:mm` 
format
<br />
<br />
<br />
### <img id="event" src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/event.png" width="500">
***
A `event` task is a task item that has a task description and a task date. The task date indicates when the 
`event` task is happening at. The task date must follow a specified `dd/MM/yy HH:mm` format.

`event <taskDescription> /at <taskDate>` : creates a new event task

Example of usage: <br />
`event meeting /at 30/09/2019 1200`

Expected Outcome:
<img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/eventDemo.png">


Possible Error: <br />
`InvalidParameters  `: <br />
If either the `<taskDescription>` or `<task date>` is empty or the `taskDate` is not in the valid `dd/MM/yy HH:mm` 
format

### <img id="list" src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/list.png" width="500">

***
Displays all task items in `list` view. The items are displayed in the order they are entered

`list` : view all task items in list view

Example of usage: <br />
`list`

Expected Outcome:
<img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/listDemo.png">
<br />
<br />
<br />

### <img id="done" src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/done.png" width="500">
***
Each task item can be marked as `done`. It marks the task at the specified index in `list` view as done.

`done <index>`: marks a specified task item as done

Example of usage: <br />
`done 1`

Expected Outcome:
<img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/doneDemo.png">


Possible Error: <br />
`InvalidParameters  `: <br />
If the specified `<index>` is not valid
<br />
<br />
<br />

### <img id="delete" src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/delete.png" width="500">
***
Each task item can be deleted. It deletes a task item at the specified index in `list` view.

`delete <index>`: delete a task item

Example of usage: <br />
`delete 1`

Expected Outcome:
<img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/deleteDemo.png">

Possible Error: <br />
`InvalidParameters  `: <br />
If the specified `<index>` is not valid

### <img id="find" src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/find.png" width="500">
***
Find the task with a matching keyword entered by the user

`find <keyword>`: find task item with matching keyword

Example of usage: <br />
`find books`

Expected Outcome:
<img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/findDemo.png">
<br />
<br />
<br />

### <img id="remind" src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/remind.png" width="500">
***
Sets a reminder for a specified task. The reminder is set for the task with the specified index in the list view. The
date set for the reminder must follow the `dd/MM/yy HH:mm` format.  To change the date for a reminder that has been set,
simply enter a new remind command and the date will be automatically overwritten.

`remind <index> <reminderDate>`: sets a reminder for a specified task

Example of usage: <br />
`remind 2 01/09/2019 0000`

Expected Outcome:
<img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/reminderDemo.png">

`InvalidParameters  `: <br />
If the specified `<index>` is not valid or `<reminderDate>` is not in the correct date format
<br />
<br />
<br />

### <img id="schedule" src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/schedule.png" width="500">
***
See all tasks in `schedule` view. The `schedule` view is sorted by date order. 

`schedule`: see all task in schedule view

`schedule <date>`: see all task in schedule view for a specified date

Example of usage: <br />
`schedule`

`schedule 01/09/2019 0000` <br/>

Expected Outcome:

<img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/scheduleDemo.png">

<img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/scheduleDateDemo.png">
<br />
<br />
<br />

### <img id="bye" src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/bye.png" width="500">
***
Exits duke and displays the exit message.

Example of usage: <br/>
`bye`: exits duke and displays the exit message

Expected Outcome:

<img src="https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/byeDemo.png">
<br />
<br />
<br />

## FAQ
**Where does duke store its task?** <br/>
Duke stores its tasks in a text file. The path for the text file can be found in `./data/duke.txt`

**What happens if I do not have `./data` directory in my computer?** <br/>
Duke automatically creates the parent directories if a write operation is required.

**What happens if I accidentally deleted the `.txt`file?** <br />
Don't worry, duke creates a new     `./data/duke.txt` file each time there is any updates to the exisitng list of task 
items

**How do I turn off a reminder set for a task item?**
Duke does not allow users to turn off reminders. However, users can enter a reminder that is overdue, duke automatically
clears all overdue reminders.

**How do I update a reminder set for a task item?**
You can update a reminder set for a task item by setting a new reminder for the task item. A new reminder overwrites the
previous reminder set.

**Why do I get `InvalidCommand` error?** <br />
This may happen if the user enters an invalid command. Please check available [Commands](#commands) for more information

**Why do I get `InvalidParameter` error?** <br />
This may happen if an invalid parameter is entered for the following command. Please check the individual commands in 
[Commands](#commands) for more information.