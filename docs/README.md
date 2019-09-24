![Image of duke](https://ckb055.github.io/duke/Ui.png =100x200)
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
- Provides help in the program

## Usage
### Commands

#### <span style="color:red">todo</span>
`todo [task]`
Adds a todo to the tasklist.

Example of usage : 
`todo cs2103t tutorial`

Expected outcome :
`Got it. I've added this task:"\n\t"
    [T][✘] cs2103t tutorial"\n"
Now you have X tasks in the list.`
    
#### <span style="color:red">event</span>
`event [task] /at [DD/MM/YYYY HHmm]`
Adds an event with a specified date to the tasklist.

Example of usage :
`event birthday party /at 21/11/2014 1400`

Expected outcome :
`Got it. I've added this task:"\n\t"
    [E][✘] birthday party (at : Fri "\n"
    Nov 21 14:00:00 SGT 2014)"\n"
Now you have X tasks in the list.`

#### <span style="color:red">deadline</span>
`deadline [task] /by [DD/MM/YYYY HHmm]`
Adds a deadline with a specified date to the tasklist.

Example of usage : 
`deadline submit project /by 20/10/2011 1800`

Expected outcome :
`Got it. I've added this task:"\n\t"
    [D][✘] submit project (by : Thu Oct 20"\n"
    18:00:00 SGT 2011)"\n"
Now you have X tasks in the list.`

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
