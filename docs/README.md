# duke by Lawnce

## Features 

# Feature 1 - Add ToDos
Adds any todos to the list 

## Usage

1. When user inputs command of "todo", the remaining words behind such a command will be added into the .txt file that is in also stored in the repo. The writing of data is done real-time
2. Will be tagged as T in the list

Example of usage: 

`todo borrow book`

Expected outcome:

`Got it. I've added this task:
[T][x] return book
"Now you have " + tasks.getTaskArrayList().size() + " tasks in the list."`

# Feature 2 - Add Deadlines
Adds any deadlines to the list 

## Usage 

1. When user inputs command of "deadline", the remaining words behind such a command will be added into the .txt file that is in also stored in the repo. The writing of data is done real-time.
2. Additional feature is the inclusion of a deadline time at the end of the user input, this date and time will be parsed and converted to a date object in java terms and added to the output
3. Will be tagged as D in the list

Example of usage: 

`deadline return book /by 21/7/2019 1800`

Expected outcome:

`Got it. I've added this task:
[D][x] return book (by: 21st of July 2019, 6:00pm)
"Now you have " + tasks.getTaskArrayList().size() + " tasks in the list."`

# Feature 3 - Add Events
Adds any events to the list 

## Usage 

1. When user inputs command of "event", the remaining words behind such a command will be added into the .txt file that is in also stored in the repo. The writing of data is done real-time.
2. Additional feature is the inclusion of an event time at the end of the user input, this date and time will be parsed and converted to a date object in java terms and added to the output
3. Will be tagged as E in the list

Example of usage: 

`event fly to Egypt /at 22/05/2019 1655`

Expected outcome:

`Got it. I've added this task:
[E][x] fly to Egypt (at: 22nd of May 2019, 4:55pm)
"Now you have " + tasks.getTaskArrayList().size() + " tasks in the list."`

# Feature 4 - List
Shows the list of tasks

## Usage 

1. Displays the list of tasks that is according to the .txt file that acts as the database file

Example of usage: 

`list`

Expected outcome:

`1. [T][x] return book`
`2. [D][x] return book (by: 21st of July 2019, 6:00pm)`
`3. [E][x] fly to Egypt (at: 22nd of May 2019, 4:55pm)`

# Feature 5 - Delete
Deletes a specific task in the list

## Usage 

1. Scans for the integer after this command and delete the corresponding task in the list

Example of usage: 

`delete 1`

Expected outcome:

`1. [D][x] return book (by: 21st of July 2019, 6:00pm)`
`2. [E][x] fly to Egypt (at: 22nd of May 2019, 4:55pm)`

# Feature 5 - Done
Marks a specific task in the list as done

## Usage 

1. Scans for the integer after this command and mark the corresponding task as done with a tick in the list

Example of usage: 

`done 1`

Expected outcome:

`1. [D][✓] return book (by: 21st of July 2019, 6:00pm)`
`2. [E][x] fly to Egypt (at: 22nd of May 2019, 4:55pm)`

# Feature 6 - Find
Finds a specific task in the list 

## Usage 

1. Finds the tasks based on the keywords that are in the remaining words of the user's input

Example of usage: 

`find return`

Expected outcome:

`1. [D][✓] return book (by: 21st of July 2019, 6:00pm)`

# Feature 7 - Exit
Exits the program

## Usage 

1. Upon scanning of the exit command of "bye", the program will terminate

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`




