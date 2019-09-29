# Jarvis Taskbot

**Prerequisites**

* CLI
* MacOS, Windows, Linux operating systems.

# 1. Introduction

Jarvis is a Command Line Interface (CLI) task manager system that can be used to track various tasks. Jarvis aims to increase the productivity of its user. The tasks are saved in a local file that can be actively retrived (unless deleted) once Jarvis is activated. Jarvis has the ability to read time and dates so feel free to include due dates for your task. 

**Types of supported tasks**
1.) ToDo 
2.) Deadline
3.) Event

# Snapshot of Jarvis

![Homepage of Jarvis](doc/Home.png)

![Working Jarvis](doc/UI.png)

# 2. Quick Start
Ensure that java 11 or above is installed in your computer. Otherwise, you can download Java 11 [here](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html).
Download Jarvis [here](https://github.com/tuandingwei/duke/releases/tag/v0.2)!
Double click of the .jar file to activate Jarvis. A pop-up window should appear soon. 

# 3. Features

Function | Subject | Example | Syntax | 
---------------|---------------|---------------|---------------
Hi | | 'hi' | 
Add Todo | a Todo task | 'todo read book' | todo [description]
Add Deadline | a task with deadline |'deadline return book /by 2/12/2019 1800' | deadline [description] /by [dd/mm/yyyy hh:mm]
Add Event | an event | 'event party /at 2/12/2019 2000-0300' | event [description] /at [dd/mm/yyyy hh:mm - hh:mm]
List | all task | 'list' |
Done | any existing task | 'done 2' | done [index of task in the list]
Delete | an existing task | 'delete 1' | delete [index of task in the list]
Find | keyword | 'find book' | find [keyword]
Clear | clears all tasks permanently | 'clear' | 
Bye | | 'bye' |

# Looking forward
I hope to improve Jarvis with a better UI and provide support for more tasks in the future. 
Feel free to send any recommendations to tuandingwei@u.nus.edu.

