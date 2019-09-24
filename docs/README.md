![Image of duke](https://ckb055.github.io/duke/Ui.png)
[Project on github](https://github.com/ckb055/duke)

# User Guide
User guide for Duke, 
by Chen Kai Bin A0187250E

Duke, is an individual project done for CS2103T, a Software Engineering module at the School of Computing of NUS.
It is a personal chatbot assistant that has a CLI (Command Line Interface) and it
helps to manage your tasks such as events, deadlines and todos.
Although its fuctionality is simple, Duke is highly user-friendly.

## Features 
- Supports adding/deletion of tasks
- Supports searching of tasks
- Ability to add dates to tasks
- Provides basic in-app statistics

## Usage
### Commands

#### <span style="color:red">todo</span>
`todo [task]`
Adds a todo to the tasklist.

Example of usage : 
`todo cs2103t tutorial`

Expected outcome :
`Got it. I've added this task:`
    `[T][✘] cs2103t tutorial`
`Now you have X tasks in the list.`
    
#### <span style="color:red">event</span>
`event [task] /at [DD/MM/YYYY HHmm]`
Adds an event with a specified date to the tasklist.

Example of usage :
`event birthday party /at 21/11/2014 1400`

Expected outcome :
`Got it. I've added this task:`
    `[E][✘] birthday party (at : Fri`
    `Nov 21 14:00:00 SGT 2014)`
`Now you have X tasks in the list.`

#### <span style="color:red">deadline</span>
`deadline [task] /by [DD/MM/YYYY HHmm]`
Adds a deadline with a specified date to the tasklist.

Example of usage : 
`deadline submit project /by 20/10/2011 1800`

Expected outcome :
`Got it. I've added this task:`
    `[D][✘] submit project (by : Thu Oct 20`
    `18:00:00 SGT 2011)`
`Now you have X tasks in the list.`

#### <span style="color:red">find</span>
`find [task]`
Finds tasks related to the keyword entered.

Example of usage :
`find project`

Expect outcome :
`(Displays a numbered lists of all tasks containing the specified keyword)`

#### <span style="color:red">list</span>
Displays all your current tasks in the tasklist.

Example of usage :
`list`

Expect outcome :
`Here are the tasks in your list:`
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
`Fun fact! Did you know that...?`
    `(a random funfact)`

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
