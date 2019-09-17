# User Guide
Duke is for those who prefer to use a desktop app for managing tasks.

## Features
1. List tasks
2. Mark task as done
3. Delete task
4. Find task
5. Add task
6. Bye

## Usage

### `todo (description)` - Add a Todo task
A todo task will be added with the task name`(description)`.

Example of usage: 

`todo read books`

Expected outcome:

`Got it. I've added this task:`  
 `[T][✗] read books`  
`Now you have 1 task in the list.`

### `event (description) /at (period)` - Add an Event task
An event task will be added with the task name `(description)` and `(period)`.

Example of usage: 

`event project meeting /at 03/03/2019 1400-1600`

Expected outcome:

`Got it. I've added this task:`   
 `[E][✗] project meeting (at: 3rd of March 2019, 2:00pm to 4:00pm)`  
`Now you have 2 tasks in the list.`

### `deadline (description) /by (deadline)` - Add a Deadline task
A deadline task will be added with the task name `(description)` and `(deadline)`.

Example of usage: 

`deadline return books /by 03/03/2019 1400`

Expected outcome:

`Got it. I've added this task:`  
 `[D][✗] return books (by: 3rd of March 2019, 2:00pm)`  
`Now you have 3 tasks in the list.`

### `(description) /after (afterWhen)` - Add a DoAfter task
A DoAfter task will be added with the specified `(description)` and `(afterWhen)`.  

Example of usage:

`return books /after 01/02/2013 1400`

Expected outcome:

`Got it. I've added this task:`  
 `[DA][✗] return books (after: 1st of February 2013, 2:00pm)`  
`Now you have 1 task in the list.`

### `list` - List all the tasks
All the tasks in the list will be shown.

Example of usage:

`list`

Expected outcome:

`Here are the tasks in your list:`  
`1.[T][✗] read books`  
`2.[E][✗] project meeting (at: 3rd of March 2019, 2:00pm to 4:00pm)`  
`3.[D][✗] return books (by: 3rd of March 2019, 2:00pm)`

### `done (task number)` - Mark task as done
The task with that particular task number will be marked as done.

Example of usage:

`done 2`

Expected outcome:

`Nice! I've marked this task as done:`  
 `[E][✓] project meeting (at: 3rd of March 2019, 2:00pm to 4:00pm)`
 
### `delete (task number)` - Delete a task
The task with that particular task number will be deleted.
 
 Example of usage:
 
 `delete 2`
 
 Expected outcome:
 
`Noted. I've removed this task:`  
 `[E][✓] project meeting (at: 3rd of March 2019, 2:00pm to 4:00pm)`  
 `Now you have 2 tasks in the list.`
 
### `find (keyword)` - Find a task
Find a task by searching for that particular `(keyword)`.
 
 Example of usage:
 
 `find books`
 
 Expected outcome:
 
 `Here are the matching tasks in your list:`  
`1.[DA][✗] return books (after: 1st of February 2013, 2:00pm)`  
`2.[T][✗] read books`

### `bye` - Exit from the application
A goodbye message will be shown and the application will close.

Example of usage:

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`
