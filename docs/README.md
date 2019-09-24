#### Table of contents

<!---
Summary of the conversion rules:

punctuation marks will be dropped
leading white spaces will be dropped
upper case will be converted to lower
spaces between letters will be converted to '-'

-->

1. [Introduction](#introduction)
2. [Setting up](#setting-up)
3. [Features](#features)
    1. [Adding a new to-do task](#1-adding-a-new-to-do-task)
    2. [Adding a new event task](#2-adding-a-new-event-task)
    3. [Adding a new deadline task](#3-adding-a-new-deadline-task)
    4. [Viewing task-related statistics](#4-viewing-task-related-statistics)
    5. [Viewing all statistics](#5-viewing-all-statistics)
    6. [Resetting global statistics](#6-resetting-global-statistics)
    7. [Exiting the application](#7-exiting-the-application)
  
    
## Introduction
Obot Wan Kenobi is a chatbot based on the persona of Jedi Master Obi-Wan Kenobi. He can handle basic tasks such as recording to-do, deadline and event tasks; as well as output relevant statistics based on the user's history.

![Image of Ui screenshot](https://ahmadhatziq.github.io/duke/Ui.png)

Fig. 1. Screenshot of Obot

## Setting up 
1. Ensure you have Java version `"11.0.4" 2019-07-16 LTS` or later installed in your Computer.
You can download it [here](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
2. Verify that the correct version exists by typing `java -version` into the terminal/CMD. 
3. Download the jar file from [here](https://github.com/AhmadHatziq/duke/releases/tag/A-Release)
4. Execute the jar file from the command line by running the following
   1. Change the working directory to the folder containing the .jar file ie `cd "Dowloads"`
   2. Execute the .jar file by running `java -jar obot-0.1.4.jar`

## Features 

### 1. Adding a new to-do task 
A to-do task is the most simplest form of a task.

To-do task can be added via the following format:
todo [task_name]

<br/>

Sample command:

`todo Laundry`

<br/>

Expected outcome:

`Got it. I've added this task:`

`[T][x] Laundry`

`Now you have X tasks in the list`

<br/>

### 2. Adding a new event task 
An event task is similar to a to-do task, escept that it is associated with a particular location, the event location.

Event task can be added via the following format:
event [event_name] /at [event_location]

<br/>

Sample command:

`event Exam /at MPSH1-C`

<br/>

Expected outcome:

`Got it. I've added this task:`

`[E][X] Exam (at: MPSH1-C)`

`Now you have X tasks in the list`

<br/>

### 3. Adding a new deadline task 
A deadine task is similar to a to-do task, except that it is associated with a particular cut-off time, the due deadline.

Deadline task can be added via the following format:

deadline [deadline_name] /by [deadline_date]

[deadline_date] must be of the following format:
__DD/MM/YYYY HHMM__

<br/>

Sample command:

`deadline CS Quiz /by 31/12/2019 2359`

<br/>

Expected outcome:

`Got it. I've added this task:`

`[D][X] CS Quiz (by: 31st of December 2019, 11.59pm)`

`Now you have X tasks in the list`

<br/>

### 4. Viewing task-related statistics 
Obot can also give the statistics regarding the number of tasks completed __in the current day.__
Tasks completed more than one day ago will not be counted.

To view statistics pertaining to each task (to-do, event or deadline), enter the following command:

stats [event_type]

[event_type] is either: 
* `deadline`
* `todo`
* `event`

<br/>

Sample command:

`stats event`

<br/>

Possible outcome 1:

`Events completed today: 1`

`Well Done!`

<br/>

Possible outcome 2:

`Events completed today: 0`

`You can do better! :)`

<br/>

### 5. Viewing all statistics 
Aside from task-related statistics, Obot can also give statistics regarding the __total commands entered, total tasks deleted__ and __total tasks completed__. These statistics will consider tasks that have previously been marked completed but deleted by the user. 

To view all statistics (which also included the tasks completed in the current day), enter the following sample command:

<br/>

Sample command:

`stats all`

<br/>

Possible outcome:

`Listing all statistics`

`Total Commands Executed: 150`

`Total Tasks Deleted: 20`

<br/>

`Total To-Dos Completed: 12`

`Total Todos Completed TODAY: 2`

<br/>

`Total Deadlines Completed: 7`

`Total Deadlines Completed TODAY: 1`

<br/>

`Total Events Completed: 4`

`Total Events Completed TODAY: 2`

<br/>

### 6. Resetting global statistics
Global statistic values can be reset. However, dynamic statistic values (Feature 4) cannot be reset.

To reset global statistic values, enter the following sample code: 

<br/>

`stats reset`

<br/>

To view the outcome, view all statistics with:
`stats all`

<br/>

Expected outcome:

`Listing all statistics`

`Total Commands Executed: 1` - Number of total commands executed will be 1 as `stats all` has been executed.

`Total Tasks Deleted: 0`

<br/>

`Total To-Dos Completed: 0`

`Total Todos Completed TODAY: 2` - Dynamic statistic based on when a `todo` was marked completed.

<br/>

`Total Deadlines Completed: 0`

`Total Deadlines Completed TODAY: 1` - Dynamic statistic based on when a `deadline` was marked completed.

<br/>

`Total Events Completed: 0`

`Total Events Completed TODAY: 2` - Dynamic statistic based on when aa `event` was marked completed.

<br/>

### 7. Exiting the application 
Yes, this is a feature.

Obot can be exited by entering the following command:

`bye`

<br/>

Expected outcome:

`The Force will be with you, always`








