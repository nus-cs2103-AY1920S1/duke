# User Guide

## Features 

### **1. Add tasks**
*todos* - tasks without specific deadlines or dates
*deadlines* - tasks with specific deadlines
*events* - tasks with specific time and dates

**Usage**

###`Keywords` - `todo`, `deadline`, `event`

Adds the task to the task list.

Example of usage:

`todo watch Lord of the Rigs`
`deadline return Harry Potter to library /by: 30th of October 2019, 9.30pm`
`event attend seminar /at 25/09/2019 1800`

Expected outcome:

`Got it. I've added this task:`
`  [T][✘] watch Lord of the Rigs`
`Now you have 1 task in the list.`

`Got it. I've added this task:`
`  [D][✘] return Harry Potter to library (by: 30th of October 2019, 9.30pm)`
`Now you have 2 tasks in the list.`

`Got it. I've added this task:`
`  [E][✘] attend seminar (at: 25/09/2019 1800)`
`Now you have 3 tasks in the list.`

### **2. Edit tasks**
Edit the description or the date and time of the task.

**Usage**

###`Keyword`- `edit`

Edits the description/date and time of the task.

Example of usage:

`edit 1 description watch Lord of the Rings`
`edit 2 datetime 29/10/2019 2130`
`edit 3 description attend wedding`

Expected outcome:

`The task: [T][✘] watch Lord of the Rings has been changed to [T][✘] watch Lord of the Rings`
`The task: [D][✘] return Harry Potter to library (by: 30th of October 2019, 9.30pm) has been changed to [D][✘] return Harry Potter to library (by: 29th of October 2019, 9.30pm)`
`The task: [E][✘] attend seminar (at: 25/09/2019 1800) has been changed to [E][✘] attend wedding (at: 25/09/2019 1800)`

### **3. Mark tasks as done**

Mark the tasks that are in the task list as done.

**Usage**

###`Keyword`- `done`

Example of usage:

`done 2`

Expected outcome:
`Nice! I've marked this task as done:`
`  [D][✓] return Harry Potter to library (by: 29th of October 2019, 9.30pm)`

### **4. List tasks**

List the tasks that are in the task list.

**Usage**

###`Keyword`- `list`

Example of usage:

`list`

Expected outcome:

`1.[T][✘] watch Lord of the Rings
2.[D][✓] return Harry Potter to library (by: 29th of October 2019, 9.30pm)
3.[E][✘] attend wedding (at: 25/09/2019 1800)`

### **5. Find tasks**

Find a specific task  in the task list.

**Usage**

###`Keyword`- `find`

Example of usage:

`find Harry Potter`

Expected outcome:

`Here are the matching tasks in your list:
2.[D][✓] return Harry Potter to library (by: 29th of October 2019, 9.30pm)`

### **6. Delete tasks**

Delete a specific task  in the task list.

**Usage**

###`Keyword`- `delete`

Example of usage:

`delete 2`

Expected outcome:

`Noted. I've removed this task:
  [D][✓] return Harry Potter to library (by: 29th of October 2019, 9.30pm)
Now you have 2 tasks in the list.`

### **7. Quit Application**

Close the application window. All tasks will be saved automatically.

**Usage**

###`Keyword`- `bye`

Example of usage:

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`
