# **Duke ChatBot User Guide** 

<br>

## **Table of Contents**

1. [Introduction](#UserGuide)  
    1.1 [What is Duke ChatBot](#what)  
    1.2 [Who Should Use It](#who)  
    1.3 [Typographical Conventions](#how)  
    
2. [Features](#features)  
    2.1 [Types of Tasks](#types)  
    2.2 [Adding of Tasks](#add)  
    2.3 [Listing Tasks](#list)  
    2.4 [Handling of Tasks](#handle)  
    2.5 [Finding Tasks](#find)  
    2.6 [Sorting Tasks](#sort)  
    2.7 [Mass Operation of Tasks](#massOp)

3. [Viewing of Statistics](#stats)  
    3.1 [Viewing of Statistics by Time](#time)  
    
4. [Glossary](#glossary)  

<br>
<br>

## **Introduction** <a name="UserGuide"></a>


### **What is Duke ChatBot** <a name="what"></a>

Duke ChatBot is a task managing chat bot which allows users to input the tasks to do and organise them. It tracks tasks that are marked as done over a period of time and displays relevant statistics.

This is what Duke ChatBot looks like:  
<img src="https://github.com/EvonDong/duke/blob/master/docs/Ui.png" width="480">

<br>

### **Who Should Use It** <a name="who"></a>

This application aims to be beginner friendly and is intended for users who wants to keep track of the list of tasks to do and enhance their productivity.

<br>

### **Typographical Conventions** <a name="how"></a>

This document uses the following typographical conventions:

`sort`  | A mark-up indicates that this is a command that can be type into the command line and executed by the application.

:exclamation:  | An exclamation sign indicates important information.

<br>
<br>

## **Features** <a name="features"></a>


### **Types of Tasks** <a name="types"></a>
This application stores 3 different types of tasks:

`todo`                  A Todo task represents a task to be done without any time limit.
    
`deadline`         A Deadline task represents a task to be done by a certain date and time.
    
`event`               An Event task represents an event to attend at a certain date and time.

<br>

### **Adding of Tasks** <a name="add"></a>
This section contains the commands to be keyed in to add different type of task.

#### **Todo Task**

To key in a `todo` task: `todo` [_description of task_]
    
    Example of usage: todo music homework
    Expected Outcome: Got it. Ive added this task:
                      [T][✗] music homework
                      Now you have 1 task in the list.
                       

#### **Deadline Task**

To key in a `deadline` task: `deadline` [_description of task_] /by [_dd/MM/yyyy_] [_HHmm_]
    
    Example of usage: deadline music project /by 6/4/2023 0123
    Expected Outcome: Got it. Ive added this task:
                      [D][✗] music project (by: 6/4/2023 01.23AM)
                      Now you have 2 tasks in the list.
    
:exclamation: /`by` must be included in the command.

#### **Event Task**

To key in an `event` task: `event` [_description of task_] /at [_dd/MM/yyyy_] [_HHmm_]
    
    Example of usage: event music festival /at 6/4/2022 0123
    Expected Outcome: Got it. Ive added this task:
                      [E][✗] music festival (at: 6/4/2022 01.23AM)
                      Now you have 3 tasks in the list.


:exclamation: `/at` must be included in the command.
    
    
:exclamation: Both Deadline and Todo task must include a valid timestamp in [_dd/MM/yyyy_] [_HHmm_] format. 

<br>

### **Listing Tasks** <a name="list"></a>

To `list` all tasks recorded: `list`

    Example of usage: list
    Expected Outcome: Here are the tasks in your list:
                      1.[T][✗] music homework
                      2.[D][✗] music project (by: 6/4/2023 01.23AM)
                      3.[E][✗] music festival (at: 6/4/2022 01.23AM)

<br>

### **Handling of Tasks** <a name="handle"></a>
This section contains the commands to be keyed in to handle tasks.
To execute the following commands, `list` out the list of tasks as shown in the [previous section](#list) and note the index of the task to operate on.

1. **Done Command**

To mark the task as `done`: `done` [_index of the task in the list_]

    Example of usage: done 1
    Expected Outcome: Nice! I've marked this task as done:
                      [T][✓] music homework


2. **Delete Command**

To `delete` the task: `delete` [_index of the task in the list_]

    Example of usage: delete 1
    Expected Outcome: Noted. I've removed this task:
                      [T][✓] music homework
                      Now you have 2 tasks in the list.

<br>

### **Finding of Tasks** <a name="find"></a>

To `find` tasks using a keyword: `find` [_keyword_]

    Example of usage: find music
    Expected Outcome: Here are the matching task in your list:
                      1.[D][✗] music project (by: 6/4/2023 01.23AM)
                      2.[E][✗] music festival (at: 6/4/2022 01.23AM)
 

:exclamation: The keyword must only consists of one word.

<br>

### **Sorting of Tasks** <a name="sort"></a>

To `sort` tasks according to the dates and time in a chronological order: `sort` 

    Example of usage: sort
    Expected Outcome: Here are the tasks in your list:
                      1.[E][✗] music festival (at: 6/4/2022 01.23AM)
                      2.[D][✗] music project (by: 6/4/2023 01.23AM)


:exclamation: If the list contains a `deadline` or `event` task, it will take priority over a `todo` task as it is assumed that a `todo` task is of lower priority.  
:exclamation: If two `deadline` or `event` tasks have the same timestamp, the tasks would be sorted according to their descriptions in a alphabetical order. The same rule also applies for `todo` task.  



<br>

### **Mass Operation of Tasks** <a name="massOp"></a>

To execute the same command to multiple tasks at once, add `, ` between the indexes of the tasks.

    Example of usage: delete 1, 2
    Expected Outcome: Noted. I've removed these task:
                      [T][✓] music homework
                      [D][✗] music project (by: 6/4/2023 01.23AM)
                      Now you have 1 task in the list.

:exclamation: This only applies to `done`, `delete`, `find` commands.

<br> 
<br> 

## **Viewing of Statistics** <a name="stats"></a>

To view an overview of the tasks done over time, there are two ways:

1. Click on the **Show Statistics** button on the top of the application as shown below.
<img src="https://github.com/EvonDong/duke/blob/master/docs/showStats1.png" width="350">

<br>

2. Key in `show stats` in the command line: `show stats`

This is an example of an expected chart:  
<br>
    <img src="https://github.com/EvonDong/duke/blob/master/docs/BarChart1.png" width="350">

<br>
<br>

## **Glossary** <a name="glossary"></a>

Term | Description  
------ | -----------  
Command Line Interface | The horizontal pale yellow box on bottom left corner of the application
Mark-up | A grey highlight over the words
Timestamp | A sequence of characters or encoded information identifying when a certain event occurred, usually giving date and time of day




