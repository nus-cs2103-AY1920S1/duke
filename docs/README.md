# User Guide

## Features 

### Managing tasks
User can categorise task in three ways:
1) To-Do: Task is a general to-do task.
2) Deadline: Task has a deadline
3) Event: Task is an event with a venue.  

User is able to mark a task as done/undone

### Memorize Commands
User can explicitly instruct duke to memorise any given command.

## Usage

### `todo` - Creates To-Do task.

Example of usage: 

`todo Math Homework 01`

### `event` - Creates Event task

Format: event (event name) /at (venue)

Example of usage: 

`event dinner /at East Coast Park`

### `deadline` - Creates Deadline task

Format: deadline (deadline item) /by (Date and Time in the format: DD/MM/YYYY HHmm)

Example of usage: 

`deadline Submit Math Homework /by 20/09/2019 1800`

### `list` - Lists all the tasks

Example of usage:

`list`

Expected outcome:  

`Here are the tasks in your list:`  
`1.[E][x] Hacker Event (at: NUS)`  
`2.[T][Done] Math Homework 01`  
`3.[D][x] Submit Math Homework (by: 20 Sep 2019, 6:00PM)`  

### `find` - Finds tasks by keyword filter

Example of usage:  

`find 6:00`

Expected outcome:  

`Here are the matching tasks in your list:`  
`[D][x] Submit Math Homework (by: 20 Sep 2019, 6:00PM)`  

### `done` - Marks a task as done based on task position

Example of usage:

`done 1`

### `delete` - Deletes a task based based on task position

Example of usage:

`delete 1`

### `bye` - Command to exit the program  

Example of usage:

`bye`

### `Trivia` - User keyword dictionary feature

#### Trivia guide:

`trivia`:  

Gives list of trivia commands available  

`trivia list`:  

Gives list of all memorised words  

`trivia memorise`:  

Memorises given user keyword in the format:  
trivia memorise (user_keyword) (keyword representation)

Example of usage:  

`trivia memorise home myhome`  

To use the keyword, wrap the keyword inside square brackets [] 
for Duke to recognise this keyword  

Example of usage:  

`event party /at [home]`  - Equivalent to event party /at myhome

`trivia reset`:  

Clears memory of all existing memorised keyword

`trivia erase`:  

Erases a particular keyword from memory in the format:  
trivia erase (keyword)  

Example of usage:  

`trivia erase randomKeyWord`  
