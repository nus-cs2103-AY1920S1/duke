# User Guide

## What is Do(d)gy Duke? 
Do(d)gy Duke is a Journal application, allowing users to store, 
retrieve and view various types of tasks at a later date. 

## Features 

### Feature 1: Adding tasks
Add a new `Todo`, `Event` or `Deadline` to the current Task List. 
### Feature 1.1: Adding `Todo`
`Todo` task are tasks with a description. 
### Feature 1.2: Adding `Event`
`Event` task are tasks with a description and a event time(e.g. at: 2pm today)
### Feature 1.3: Adding `Deadline`
`Deadline` task are tasks with a description and a deadline time(e.g. by: 12pm tomorrow)
###Feature 1.4: `Tag`
When adding a task to the list, tags can be included as well. (optional)

### Feature 2: `List`
List and prints out all the stored tasks. 

### Feature 3: `Find`
Search for a keyword in the current Task List.

### Feature 4: `Delete`
Delete a `Task`, `Event` or `Deadline` from the current Task List.

### Feature 5: `Done`
Set a particular task as completed

## Usage 

### Adding a todo: `todo` 
Add a `Todo` task to the journal

Example of usage: 
`todo buy milk`

Expected outcome: 

`Got it. I've added this task:`

`  [T][0]buy milk`

`Now you have 1 tasks in the list.`

### Adding a event: `event` 
Add a `Event` task to the journal

Example of usage: 
`event watch movie /at: 2020-01-10T12:30:00`

Expected outcome: 

`Got it. I've added this task:`

`  [E][0] event watch movie (at: 2020-01-10T12:30:00)`

`Now you have 2 tasks in the list.`
##
### Adding a deadline: `deadline` 
Add a `Deadline` task to the journal

Example of usage: 
`deadline study test /by: 2019-10-05T23:59:59`

Expected outcome: 

`Got it. I've added this task:`

`  [D][0] study test (by: 2019-10-05T23:59:59)`

`Now you have 3 tasks in the list.`

### Including a tag when adding tasks: `/t` 
(Optional) Include a single `tag` when adding a task to the journal. 
All types of tasks can accept a `tag`. Should be included at the end.  

Example of usage: 
`todo fix phone /t Urgent!`

Expected outcome: 

`Got it. I've added this task:`

`  [T][0]<Urgent!> fix phone `

`Now you have 4 tasks in the list.`

Another example of usage: 
`deadline complete art project /by: 2019-12-05T23:59:59 /t blueclues`

Expected outcome: 

`Got it. I've added this task:`

`  [D][0]<blueclues> complete art project (by: 2019-12-05T23:59:59)`

`Now you have 5 tasks in the list.`

### Listing tasks: `list` 
Lists out all stored tasks.

Example of usage: 
`list`

Expected outcome: 

`Here are the tasks in your list:`

`  [T][0]buy milk`

`  [E][0] event watch movie (at: 2020-01-10T12:30:00)`

`  [D][0] study test (by: 2019-10-05T23:59:59)`

`  [T][0]<Urgent!> fix phone `

`  [D][0]<blueclues> complete art project (by: 2019-12-05T23:59:59)`


### Finding all tasks with the containing keyword in their description: `find KEYWORD` 
Searches through all tasks(and tags) for the specified keyword. If found, the task will be returned. 

Example of usage: 
`find test`

Expected outcome: 

`Here are the matching tasks in your list:`

`  [D][0] study test (by: 2019-10-05T23:59:59)`


### Deleting a task: `delete INDEX` 
Delete a task from the journal at the specified `INDEX`.

Example of usage: 
`delete 2`

Expected outcome: 

`Noted. I've removed this task:`

`  [E][0] event watch movie (at: 2020-01-10T12:30:00)`

`Now you have 4 tasks in the list.`

### Exiting the program: 'bye'
Exit the program by closing the window or using the keyword `bye`


### Saving the data
Do(d)gy Duke automatically saves the data in file 'tasks.txt', 
located in project root folder. 