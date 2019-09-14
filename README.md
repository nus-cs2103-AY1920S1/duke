# Bunny Duke User Guide
Bunny Duke is a tasks and expenses manager chat bot.

## Table of Contents

1. [Introduction](#introduction)
1. [General Commands](#general-commands)
1. [Tasks Managing Commands](#tasks-managing-commands)
	1. [Types of Tasks](#types-of-tasks) 
	1. [Add Tasks](#add-tasks) 
	1. [See Task List](#see-task-list)
	1. [Mark Task as Done](#mark-tasks-as-done)
	1. [Delete Tasks](#delete-tasks)
	1. [Find Task by Keyword](#find-task-by-keyword)
1. [Expenses Managing Commands](#expenses-managing-commands)
	1. [Add Expenses](#add-expenses)
	1. [Input Income](#input-income)
	1. [See Expense List](#see-expense-list)
	1. [Delete Expenses](#delete-expenses)
	
## Features

### Task Managing feature
Bunny Duke can help you to manage all your tasks.
He can list out all your tasks, let you add tasks, delete tasks and mark tasks as done.
He also can help you save your deadline or event's date and time.

### Expense Managing feature
Bunny duke can also help you manage your expenses.
He can let you input your income and input your expenses.
Then, He can help you calculate how much you have left to spend or how much is overspent.
He is able to save everything so when you close the app, your data is not lost.

Here is how it looks like in-action:

![Image of Duke Bunny](https://github.com/kangyeelim/duke/blob/master/docs/Ui.png)



## General Commands

#### Help Command
Duke Bunny will give you a quick summary of all the commands in one reply to help you anytime you forget any.

- To get a quick summary of all commands, you can send:
	`help`

#### Tutorial Command
Duke Bunny will guide you through a short hands-on tutorial to familiarise you with all the commands.
Just follow through the instructions and it will help you learn very quickly.
- To get the tutorial of all commands, you can send:
	`tutorial`

#### Bye Command
This is optional because the data will be saved whenever any command is made. 
Thus, do not fret if you close Duke Bunny without doing this.
- To bid Duke Bunny goodbye, you can send:
	`bye`


## Task Managing Commands
This section will show you the commands for the task managing functions of Duke Bunny.

#### Types of Tasks
- Todo:
Tasks that do not have a time or date. 
- Event:
Tasks that have a time and date to attend to it.
- Deadline:
Tasks that have a time and date as its deadline.

#### Add Tasks Command
Duke Bunny can help add the 3 different tasks into the task list to help you keep track of them all.
He will reply you if he successfully did so and the number of tasks in the list.
If there is incorrect user input format, he will also reply with what went wrong.

- To add a Todo Task called *return book*, send:
	`todo return book`
- Expected outcome:

	```
	Got it. I've added this task:
	[T][x] return book
	Now you have 1 task in the list.
	```
	:x:
- To add an Event called *lab meeting* which would be held on *21 September 2019* at *12PM*, send:
	`event lab meeting /at 21/09/2019 1200`
	or
	`event lab meeting /at 21/09/2019 12.00 PM`

- Expected outcome:

	`Got it. I've added this task:'

	`[E][:x:] lab meeting (at: 21 Sep 2019 12.00PM)`
	
	`Now you have 2 tasks in the list.`


- To add a Task with a Deadline called *German Assignment* to be done by *21 September 2019* at *10.30AM*, send:
	`deadline german assignment /by 21/09/2019 1000`
	or
	`deadline german assignment /by 21/09/2019 10.00 AM`
	
- Expected outcome:

	`Got it. I've added this task:`
	
	`[D][:x:] german assignment (by: 21 Sep 2019 10.00 AM)`
	
	`Now you have 3 tasks in the list.`
	
	
#### See Task List
- To see the list of tasks, send:
	`list`

-Expected outcome:

	`Here are the tasks in your list:`
	
	`1. [T][`:x:`] return book`
	
	`2. [E][:x:] lab meeting (at: 21 Sep 2019 12.00 PM)`
	
	`3. [D][:x:] german assignment (by: 21 Sep 2019 10.00 AM)`
	

#### Mark Task as Done
- To mark task at index 2 of list as done, send:
	`done 2`

- Expected outcome:

	`Nice! I've marked this task as done:`
	
	`[:heavy_check_mark:] lab meeting`
	
	
#### Delete Tasks
- To delete task at index 2 of list, send:
	`delete 2`


#### Find Task by Keyword
- To find if you have the task in the list by a single keyword like *lab*, send:
	`find lab`

All matching tasks will be presented.
You can also find tasks by the date. 

- To find a task with the date *21/09/2019*, send:
	`find 21/09/2019`


## Expenses Managing Commands
This section will show you the commands for the expenses managing functions of Duke Bunny.

#### Add Expenses
- To add an expense where *$220* was spent on buying a *Bike*, send:
	`expense bike 220`


#### Input Income
- To input an income of *$1800* to see how much income is left or how much is overspent, send:
	`income 1800`
	
Everytime you do this command, the previous income will be **overwritten**.


#### See Expense List
- To see all expenses in the list, send
	`elist`


#### Delete Expenses
- To delete an expense at index 2 of the list, send:
	`delete e2`
