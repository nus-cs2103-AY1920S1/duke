# User Guide

## Feature 1 
Able to add a todo to Duke.

### Usage
To store something that you have to do in duke.

### Keyword `todo` 
todo (task you have to do)

Example of usage: 

todo meet family

Expected outcome:

Duke stores a todo task in your list, and updates if successfully done.

## Feature 2
Able to add a event to Duke.

### Usage
To store an event in duke.

### Keyword `event` 
event (task you have to do) /at (dd/mm/yyyy hhhh)

Example of usage: 

event attend workshop /at 10/10/2019 2000

Expected outcome:

Duke stores an event task in your list, and updates if successfully done.

## Feature 3
Able to add a deadline to Duke.

### Usage
To store a deadline in duke.

#### Keyword `deadline` 
deadline (task you have to do) /by (dd/mm/yyyy hhhh)

Example of usage: 

deadline finish homework /by 10/10/2019 2100

Expected outcome:

Duke stores a deadline task in your list, and updates if successfully done.

## Feature 4
Able to show list of tasks in duke.

### Usage
View list of tasks in duke.

#### Keyword `list` 

Expected outcome:

Duke shows you your list of tasks.

## Feature 5
Able to remove tasks from duke.

#### Keyword `remove` 
remove (task no.)

Example of usage: 

remove 5

Expected outcome:

Duke removes task number 5 from your list if it exists, otherwise throws an error message.

## Feature 6
Able to complete task in duke.

#### Keyword `done` 

Example of usage: 

done 4

Expected outcome:

Duke marks task number 4 as complete if it exists, otherwise gives an error message.

## Feature 7
Able to find tasks with keyword in duke.

#### Keyword `find` 
find (keyword)

Example of usage: 

find homework

Expected outcome:

Duke returns you a list of all the tasks that contain the word "homework", and returns you an empty list of no tasks match the keyword.

## Feature 3
Able to exit duke.

#### Keyword `bye` 

Expected outcome:

Duke exits.