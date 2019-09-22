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

### <img src = "https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/todo.png" width=300>

A `todo` task is a task item that only has a task description. A todo task does not contain a date field.

`todo <taskDescription>` : creates a new todo task.

Example of usage: <br />
`todo borrow books`

Expected Outcome:

### ![](https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/deadline.png)

A `deadline` task is a task item that has a task description and a task date. The task date indicates when the 
`deadline` task is due by. The task date must follow a specified `dd/MM/yy HH:mm` format.

`deadline <taskDescription> /by <taskDate>` : creates a new deadline task.

Example of usage: <br />
`deadline homework /by 30/09/2019 1200`

Expected Outcome:

### ![](https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/event.png)
### ![](https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/list.png)
### ![](https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/done.png)
### ![](https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/delete.png)
### ![](https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/find.png)
### ![](https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/remind.png)
### ![](https://raw.githubusercontent.com/bjhoohaha/duke/master/src/main/resources/images/schedule.png)