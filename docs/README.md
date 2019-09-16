# User Guide



## What is Duke?
**Duke** is a task management application. This desktop application enables users to
create different types of tasks (Deadline, Event, Todo) and store them into a list; 
display all the existing tasks in the list; locate/find specific tasks using keywords;
and management the different task (deleting and marking as done). This application uses
a command line interface; this means that users can operate the application by typing
commands into a Command Box.



## Quick Setup
1. To run **Duke**, please Ensure that you have [Java 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html) or above installed.

1. Download the lastest Duke.jar file at [here](https://github.com/shihaoyap/duke/releases)



## Features

### 1. Adding and Creating a Task: Todo, Deadline, Event
Allows users to add a new Task into Duke.

Todo Task
	
	 todo [Task_Description] - e.g. "todo MA1101R Tutorial 2"


Expected outcome:
```
Got it. I've added this task:
  [T][X] MA1101R Tutorial 2
Now you have 1 tasks in the list.
```

Event Task
	
	 event [Task_Description] /at dd/mm/yyyy HHMM - e.g. "event Attend Wedding /at 02/12/2019 1800"

Deadline Task
	
	 deadline [Task_Description] /by dd/mm/yyyy HHMM - e.g. "deadline Submit Project /by 12/09/2019 1500"

Note : *Duke* does not allow any duplicate [Task_Description], so do remember to input a unique [Task_Description] when
creating new Task.


### 2. List and Display all Task in the current list: List
Shows and Displays the whole list of task in the current list, together with their descriptions and date/time information. 

List
	
	 list - e.g. "list"


### 3. Find and Display Task by keyword: Find
Finds and displays tasks with descriptions that corresponds to the input keyword.

Find
	
	 find [keyword] - e.g. "find tutorial"   


### 4. Deleting a task on the list: Delete
Locate a task which corresponds to the input index and remove/delete it from the current list.

Delete
	
	 delete [index] - e.g. "delete 1"


### 5. Marks task as "done": Done
Locate a task which corresponds to the input index and mark it as done.

Done
	
	 done [index] - e.g. "done 1" 

### 6. Exiting the program: Bye
Exits and Closes the program

Bye
	
	 bye - e.g. "bye"

##