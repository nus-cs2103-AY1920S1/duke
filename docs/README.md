# User Guide

![Ui](https://user-images.githubusercontent.com/50571269/65426277-73971600-de42-11e9-91c7-0ea30f3b8007.png)
## Features 


### `1. todo` - add a task to be done

tasks without any date/time attached to it e.g., visit new theme park

`todo (description of task)`

Example of usage: 

`todo visit new theme park`

Expected outcome:

`
      Got it. I've added this task:`<br />`
        [T][✗] visit new theme park`<br />`
      Now you have 5 tasks in the list.
 `

### `2. event` - add an event task to be done

tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm

`event (description of event task) /at (time/place)`

Example of usage: 

`event team project meeting /at 2/10/2019 2-4pm`

Expected outcome:

`Got it. I've added this task:`<br />` 
        [E][✗] project meeting (at: Mon 2-4pm)`<br />`
      Now you have 7 tasks in the list.
 `
### `3. deadline` - add an deadline task to be done

tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm

`deadline (description of deadline task) /by (DD/MM/YYYY 24:hour format)`

Example of usage: 

`deadline submit project report /by 11/10/2019 1700`

Expected outcome:

`
    Got it. I've added this task: `<br />`
        [D][✗] submit project report (by: 11th of October 2019, 5.00pm) `<br />`
      Now you have 6 tasks in the list.
     `

### `4. list` - shows all the tasks in the list

Display all the tasks in the list back to the user when requested.

`list`

Example of usage: 

`list`

Expected outcome:

`
      Here are the tasks in your list: `<br />`
      1.[T][✓] read book `<br />`
      2.[D][✗] return book (by: June 6th) `<br />`
      3.[E][✗] project meeting (at: Aug 6th 2-4pm) `<br />`
      4.[T][✓] join sports club `<br />`
      5.[T][✗] borrow book `
 `
### `5. find` - search for a keyword

Give users a way to find a task by searching for a keyword.

`find (keyword)`

Example of usage: 

`find book`

Expected outcome:

`     Here are the matching tasks in your list: `<br />`
      1.[T][✓] read book `<br />`
      2.[D][✓] return book (by: June 6th) 
 `

### `6. delete` - deleting tasks from the list.

Give users a way to delete a specific task.

`delete (task number)`

Example of usage: 

`delete 3`

Expected outcome:

`     Noted. I've removed this task:  `<br />`
        [E][✗] project meeting (at: Aug 6th 2-4pm) `<br />`
      Now you have 4 tasks in the list.
 `
     
### `7. done` - mark tasks as done.

Mark tasks as done.

`done (task number)`

Example of usage: 

`done 2`

Expected outcome:

`     Nice! I've marked this task as done: `<br />`
        [✓] return book
 `    
     
### `8. sort` - sort the tasks in the list.

Sort the task according to description lexicographically or type of task 
(todo task first followed by event task and then deadline tasks).

8.1 `sort desc`

Example of usage: 

`sort desc`

Expected outcome:

`     Nice! I've marked this task as done: `<br />`
        [✓] return book
  `   

8.2 `sort type`

Example of usage: 

`sort type`

Expected outcome:

`     Nice! I've marked this task as done: `<br />` 
        [✓] return book
 `    
     
     
### `9. bye` - close the program safely.
 
 User cannot input anymore and close the program safely.
 
 `bye`
 
 Example of usage: 
 
 `bye`
 
 Expected outcome:
 
`     Bye. Hope to see you again soon!`
      

### `10. help` - display a list of commands
 
 display a list of commands for users to view.
 
 `help`
 
 Example of usage: 
 
 `help`
 
 Expected outcome:
 
 `     
*Help Command* `<br />`
"1. todo (desc): input your todo task. `<br />`
"2. event (desc) /at (place): input your event with description at a place. `<br />`
"3. deadline (desc) /by (time format: DD/MM/YYYY 24hour: input your task with" `<br />`
deadline with 24 hour format.`<br />`
"4. list: list out all the current tasks recorder in Duke. `<br />`
"5. find (keywords): find a task by input some keywords. `<br />`
"6. delete (task number): delete a task as indicated by task number.`<br />`
"7. done (task number): mark a task as done, indicated by task number.`<br />`
"8. sort desc/type: sort a task according to desc lexicographically.`<br />`
sort a task according to type, todo first then event then deadline task.
"9. bye: terminate the program safely.`<br />`
"10. help: Check commands.
`  

## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
