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



# Features

## 1. Adding and Creating a Task: Todo, Deadline, Event
Allows users to add a new Task into Duke.

### Todo Task
	
	 todo [Task_Description] - e.g. "todo MA1101R Tutorial 2"


Expected outcome:
```
__________________________________
Got it. I've added this task:
  [T][X] MA1101R Tutorial 2
Now you have 1 tasks in the list.
__________________________________
```

### Event Task
	
	 event [Task_Description] /at dd/mm/yyyy HHMM - e.g. "event Attend Wedding /at 02/12/2019 1800"

Expected outcome:
```
__________________________________
Got it. I've added this task:
  [E][X] Attend Wedding (at: 02/12/2019 1800)
Now you have 2 tasks in the list.
__________________________________
```

### Deadline Task
	
	 deadline [Task_Description] /by dd/mm/yyyy HHMM - e.g. "deadline Submit Project /by 12/09/2019 1500"

Expected outcome:
```
__________________________________
Got it. I've added this task:
  [D][X] Submit Project (by: 12/09/2019 1500)
Now you have 3 tasks in the list.
__________________________________
```
Note : *Duke* does not allow any duplicate [Task_Description], so do remember to input a unique [Task_Description] when
creating new Task.


## 2. List and Display all Task in the current list: List
Shows and Displays the whole list of task in the current list, together with their descriptions and date/time information. 

### List
	
	 list - e.g. "list"

Expected outcome:
```
__________________________________
Here are the tasks in your list:
  1.[T][X] MA1101R Tutorial 2
  2.[E][X] Attending Wedding (at: 02/12/2019 1800)
  3.[D][X] Submit Project (by: 12/09/2019 1500)
Now you have 3 tasks in the list.
__________________________________
```

## 3. Find and Display Task by keyword: Find
Finds and displays tasks with descriptions that corresponds to the input keyword.

### Find
	
	 find [keyword] - e.g. "find tutorial"   

Expected outcome:
```
__________________________________
Here are the tasks in your list:
  1.[T][X] MA1101R Tutorial 2
__________________________________
```

## 4. Deleting a task on the list: Delete
Locate a task which corresponds to the input index and remove/delete it from the current list.

### Delete
	
	 delete [index] - e.g. "delete 1"

Expected outcome:
```
__________________________________
Noted. I've removed this task:
  [T][X] MA1101R Tutorial 2
Now you have 2 tasks in the list.
__________________________________
```

## 5. Marks task as "done": Done
Locate a task which corresponds to the input index and mark it as done.

### Done
	
	 done [index] - e.g. "done 1" 

Expected outcome:
```
__________________________________
Here are the tasks in your list:
  1.[T][X] MA1101R Tutorial 2
  2.[E][X] Attending Wedding (at: 02/12/2019 1800)
  3.[D][X] Submit Project (by: 12/09/2019 1500)
Now you have 3 tasks in the list.
__________________________________
```


## 6. Sorts the current list: Sort
Sorts the current list of Task according to the different Task Types in order of
Todo -> Deadline -> Event.

### Sort
	
	 sort - e.g. "sort" 

Expected outcome:
```
__________________________________
Here are the tasks in your list:
  1.[T][X] MA1101R Tutorial 2
  2.[D][X] Submit Project (by: 12/09/2019 1500)
  3.[E][X] Attending Wedding (at: 02/12/2019 1800)
Now you have 3 tasks in the list.
__________________________________
```

## 7. Exiting the program: Bye
Exits and Closes the program

### Bye
	
	 bye - e.g. "bye"

Expected outcome:
```
Closes Program
```


## Usage

### `Keyword` - Duke Functions

1) Todo [Task_Name]

2) Deadline [Task_Name] [/by] [DD/MM/YYYY HH:MM]

3) Event [Task_Name] [/at] [DD/MM/YYYY HH:MM]

4) List

5) Done [Index]

6) Delete [Index]

7) Find [Key_Word]

8) Sort

9) Bye



