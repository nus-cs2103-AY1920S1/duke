# User Guide
Duke is a chatbot to help you manage your tasks. 

## Features 

### Adding a todo tasks: 'todo'
Adds a todo task. 

Example: 
'todo cs2103T homework'

### Adding a deadline task: 'deadline'
Adds a task with the specified deadline in DD/MM/YYYY HHmm format. 

Example: 

'deadline buy textbooks /by 12/10/2019 1700'

### Adding a event task: 'event'
Adds a task with a specified  date and time of event in DD/MM/YYYY HHmm format. 

Example: 
'event meet friend /at 11/12/2019 1412'

### Mark a task as done: 'done' 
Sets a specified task as done. 

Example: 
'done 2' : Changes from cross to a tick to the 2nd task in the task list, provided it exists. 

### Deleting a task: 'delete'
Deletes a specified task from the task list. 

Example: 
'delete 4' : Deletes the 4th task in the task list, provided it exists. 

### Listing all tasks: 'list'
Lists all the tasks stored into task list. 

Example: 
'list'

Expected outcome:
1. T][✘]water plants 
2. [T][✘]eat lunch
3. [D][✘]watch webcasts (by: 27/09/2019 1800) 
4. [E][✘]team meeting (at: 24/09/2019 1600) 
5. [T][✘]run later 
 
### Finding a task by using a keyword: 'find'
Finds a task which contains the specified phrase of keyword. 

Example: 
'find books'

### Exiting the program: 'bye'
Exits the program. 

Example: 
'bye'

