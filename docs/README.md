﻿# User Guide

## Features 

### **1. Add tasks**
*todos* - tasks without specific deadlines or dates  
*deadlines* - tasks with specific deadlines  
*events* - tasks with specific time and dates

**Usage**

`Keywords` - `todo`, `deadline`, `event`

Adds the task to the task list  

`todo [description]`  
`deadline [description] [dd/MM/yyyy HHmm]`  
`event [description] [dd/MM/yyyy HHmm]`

Examples of usage:

1. `todo watch Lord of the Rigs`
2. `deadline return Harry Potter to library /by: 30th of October 2019, 9.30pm`
3. `event attend seminar /at 25/09/2019 1800`

Expected outcomes:

1. `Got it. I've added this task:`  
`[T][✘] watch Lord of the Rigs`  
`Now you have 1 task in the list.`  

2. `Got it. I've added this task:`  
`[D][✘] return Harry Potter to library (by: 30th of October 2019, 9.30pm)`  
`Now you have 2 tasks in the list.`  

3. `Got it. I've added this task:`  
`[E][✘] attend seminar (at: 25/09/2019 1800)`  
`Now you have 3 tasks in the list.`

### **2. Edit tasks**
Edits the description or the date and time of the task.

**Usage**

`Keyword`- `edit`

Edits the description/date and time of the task  

`edit description [new description]`  
`edit datetime [dd/MM/yyyy HHmm]`

Examples of usage:

1. `edit 1 description watch Lord of the Rings`
2. `edit 2 datetime 29/10/2019 2130`
3. `edit 3 description attend wedding`

Expected outcomes:

1. `The task: [T][✘] watch Lord of the Rigs has been changed to [T][✘] watch Lord of the Rings`
2. `The task: [D][✘] return Harry Potter to library (by: 30th of October 2019, 9.30pm) has been changed to [D][✘] return Harry Potter to library (by: 29th of October 2019, 9.30pm)`
3. `The task: [E][✘] attend seminar (at: 25/09/2019 1800) has been changed to [E][✘] attend wedding (at: 25/09/2019 1800)`

### **3. Mark tasks as done**

Marks the tasks that are in the task list as done.

**Usage**

`Keyword`- `done`

Marks the task as done using the given task number  

`done [task number]`

Example of usage:  

`done 2`

Expected outcome:  
`Nice! I've marked this task as done:`  
`[D][✓] return Harry Potter to library (by: 29th of October 2019, 9.30pm)`

### **4. List tasks**

Lists the tasks that are in the task list.

**Usage**

`Keyword`- `list`  

Example of usage:  

`list`

Expected outcome:

`1.[T][✘] watch Lord of the Rings`  
`2.[D][✓] return Harry Potter to library (by: 29th of October 2019, 9.30pm)`  
`3.[E][✘] attend wedding (at: 25/09/2019 1800)`

### **5. Find tasks**

Finds a specific task  in the task list.

**Usage**

`Keyword`- `find`  

The keyword is not case sensitive.  

`find [keyword(s)]`

Example of usage:

`find harry potter`

Expected outcome:

`Here are the matching tasks in your list:`  
`2.[D][✓] return Harry Potter to library (by: 29th of October 2019, 9.30pm)`

### **6. Delete tasks**

Deletes a specific task in the task list.

**Usage**

`Keyword`- `delete`

Deletes specified task using task number  

`delete [task number]`

Example of usage:

`delete 2`

Expected outcome:

`Noted. I've removed this task:`<br/>
`[D][✓] return Harry Potter to library (by: 29th of October 2019, 9.30pm)`<br/>
`Now you have 2 tasks in the list.`

### **7. Quit Application**

Closes the application window. All tasks will be saved automatically.

**Usage**

`Keyword`- `bye`

Example of usage:

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`
