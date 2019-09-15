# **Duke ChatBot User Guide** 


## **Table of Contents**

1. [Introduction](#UserGuide)  
    1.1 [What is Duke ChatBot](#what)  
    1.2 [Who Should Use It](#who)  
    1.3 [Typographical Conventions](#how)  
    
2. [Features](#features)  
    2.1 Types of Tasks(#types)  
        2.1.1 Adding of Tasks(#add)  
        2.1.2 Handling of Tasks(#handle)  
    2.2 Finding Tasks(#find)  
    2.3 Sorting Tasks(#sort)  

3. [Viewing of Statistics](#stats)  
    3.1 Viewing of Statistics by Time   
    
4. [Glossary](#glossary)  


## **Introduction** <a name="UserGuide"></a>


### What is Duke ChatBot <a name="what"></a>

Duke ChatBot is a task managing chat bot which allows users to input the tasks to do and manipulate them. It tracks tasks that are marked as done over a period of time and displays relevant statistics.

This is what Duke ChatBot looks like:
![](/docs/Ui.png)

### Who Should Use It <a name="who"></a>

This application aims to be beginner friendly and is intended for users who wants to keep track of the list of tasks to do and enhance their productivity.

### Typographical Conventions <a name="how"></a>

This document uses the following typographical conventions:

`sort`  A grey highlight (called a mark-up) indicates that this is a command that can be type into the command line and executed by the application.

:exclamation:   An exclamation sign indicates important information


## **Features** <a name="features"></a>


### Types of Tasks <a name="types"></a>

This application stores 3 different types of tasks:

`todo`                  A Todo task represents a task to be done without any time limit.
    
`deadline`         A Deadline task represents a task to be done by a certain date and time.
    
`event`               An Event task represents an event to attend at a certain date and time.

#### Adding of Tasks <a name="add"></a>
This section contains the commands to be keyed in to add different type of task.

##### Todo Task

To key in a `todo` task: `todo` [description of task]
    
    Example of usage: todo math homework
    Expected Outcome: Got it. Ive added this task:
                      [T][✗] math homework
                      Now you have 1 task in the list.
                       

##### Deadline Task

To key in a `deadline` task: `deadline` [description of task] /by [date in the format of dd/MM/yyyy] [time in the format of HHmm]
    
    Example of usage: deadline math project /by 6/4/2022 0123
    Expected Outcome: Got it. Ive added this task:
                      [D][✗] math project (by: 6/4/2022 01.23AM)
                      Now you have 1 task in the list.
    
:exclamation: `by` must be included in the command.

##### Todo Task

To key in a `event` task: `event` [description of task] /at [date in the format of dd/MM/yyyy] [time in the format of HHmm]
    
    Example of usage: event music festival /at 6/4/2022 0123
    Expected Outcome: Got it. Ive added this task:
                      [E][✗] music festival (at: 6/4/2022 01.23AM)
                      Now you have 1 task in the list.

:exclamation: `at` must be included in the command.
    
    
:exclamation: Both Deadline and Todo task must include a valid timestamp in [dd/MM/yyyy] [HHmm] format. 


#### Handling of Tasks <a name="handle"></a>
This section contains the commands to be keyed in to handle tasks.

There are two commands to manipulate the tasks: 

##### Done Command

To mark the task as done:



##### Delete Command




## **Viewing of Statistics** <a name="stats"></a>

## **Glossary** <a name="glossary"></a>

### Feature 1 
Description of feature.

## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
