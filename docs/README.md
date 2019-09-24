![Image of duke](https://ckb055.github.io/duke/Ui.png)

# User Guide
User guide for Duke,<br/>
by Chen Kai Bin<br/>
[View the github project here](https://github.com/ckb055/duke)

# Introduction

Duke, is an individual project done for CS2103T, a Software Engineering module at the School of Computing of NUS.
It is a personal chatbot assistant that has a CLI (Command Line Interface) and it
helps to manage your tasks such as events, deadlines and todos.
Although its fuctionality is simple, Duke is highly user-friendly.

## Features 
- Supports adding/deletion of tasks
- Supports searching of tasks
- Ability to add dates to tasks
- Provides basic in-app statistics

## Getting started
Download Duke [here](https://github.com/ckb055/duke)

## Usage
### Commands

#### <span style="color:red">todo</span>
`todo [task]`
Adds a todo to the tasklist.

Example of usage : 
`todo cs2103t tutorial`

Expected outcome :
`Got it. I've added this task:`<br/>
    `[T][✘] cs2103t tutorial`<br/>
`Now you have X tasks in the list.`
    
#### <span style="color:red">event</span>
`event [task] /at [DD/MM/YYYY HHmm]`
Adds an event with a specified date to the tasklist.

Example of usage :
`event birthday party /at 21/11/2014 1400`

Expected outcome :
`Got it. I've added this task:`<br/>
    `[E][✘] birthday party (at : Fri`<br/>
    `Nov 21 14:00:00 SGT 2014)`<br/>
`Now you have X tasks in the list.`

#### <span style="color:red">deadline</span>
`deadline [task] /by [DD/MM/YYYY HHmm]`
Adds a deadline with a specified date to the tasklist.

Example of usage : 
`deadline submit project /by 20/10/2011 1800`

Expected outcome :
`Got it. I've added this task:`<br/>
    `[D][✘] submit project (by : Thu Oct 20`<br/>
    `18:00:00 SGT 2011)`<br/>
`Now you have X tasks in the list.`

#### <span style="color:red">find</span>
`find [keyword]`
Finds tasks related to the keyword entered.

Example of usage :
`find project`

Expect outcome :
`(Displays a numbered lists of all tasks containing the specified keyword)`

#### <span style="color:red">done</span>
`done [index]`
Marks the task with the specified index done.

Example of usage :
`done 3`

Expect outcome :
`Nice! I have marked this task as done`<br/>
`[T][✓] cs2103t tutorial`

#### <span style="color:red">delete</span>
`delete [index]`
Deletes the task with the specified index.

Example of usage :
`delete 3`

Expect outcome :
`Noted. I've removed this task`<br/>
`[T][✓] cs2103t tutorial`<br/>
`Now you have X tasks in the list.`

#### <span style="color:red">list</span>
Displays all your current tasks in the tasklist.

Example of usage :
`list`

Expect outcome :
`Here are the tasks in your list:`<br/>
`(a numbered list of tasks depending on your tasklist)`

#### <span style="color:red">stats</span>
Displays basic statistics of your tasklist.

Example of usage :
`stats`

Expected outcome :
`A simple overview of your tasklist`

#### <span style="color:red">funfact</span>
Shows a random funfact.

Example of usage :
`funfact`

Expected outcome :
`Fun fact! Did you know that...?`<br/>
    `(a random funfact)`
