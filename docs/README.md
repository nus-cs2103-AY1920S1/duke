# Bunny Duke User Guide
Bunny Duke is a task and expense manager chat bot.

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

#### Add Task Command
Duke Bunny can help add the 3 different tasks into the task list to help you keep track of them all.
He will reply you if he successfully did so and the number of tasks in the list.
If there is incorrect user input format, he will also reply with what went wrong.
The data will be saved once the command is made.

- To add a Todo Task called *return book*, send:
	`todo return book`
- Expected outcome:

	```
	Got it. I've added this task:
	[T][✗] return book
	Now you have 1 task in the list.
	```

- To add an Event called *lab meeting* which would be held on *21 September 2019* at *12PM*, send:
	`event lab meeting /at 21/09/2019 1200`
	or
	`event lab meeting /at 21/09/2019 12.00 PM`

- Expected outcome:

	```
	Got it. I've added this task:
	[E][✗] lab meeting (at: 21 Sep 2019 12.00PM)`
	Now you have 2 tasks in the list.
	```
	
- To add a Task with a Deadline called *German Assignment* to be done by *21 September 2019* at *10.00AM*, send:
	`deadline german assignment /by 21/09/2019 1000`
	or
	`deadline german assignment /by 21/09/2019 10.00 AM`
	
- Expected outcome:

	```
	Got it. I've added this task:
	[D][✗] german assignment (by: 21 Sep 2019 10.00 AM)
	Now you have 3 tasks in the list.
	```
	
#### See Task List
Duke Bunny can help you to display all the tasks in your task list according to 
when it was inputted with the oldest on top and newest below.

- To see the list of tasks, send:
	`list`

- Expected outcome:

	```
	Here are the tasks in your list:
	1. [T][✗] return book
	2. [E][✗] lab meeting (at: 21 Sep 2019 12.00 PM)
	3. [D][✗] german assignment (by: 21 Sep 2019 10.00 AM)
	```
	

#### Mark Task as Done
Duke Bunny can help you to mark tasks as done.He will reply you if he successfully did so. 
If there is incorrect user input format, he will also reply with what went wrong.
The data will be saved once the command is made.

- To mark task at index 2 of list as done, send:
	`done 2`

- Expected outcome:

	```
	Nice! I've marked this task as done:
	[✓] lab meeting
	```
	
	
#### Delete Tasks
Duke Bunny can help you to delete the tasks that you no longer need to keep track of.
He will reply you if he successfully did so and the number of tasks in the list. 
If there is incorrect user input format, he will also reply with what went wrong.
The data will be saved once the command is made.

- To delete task at index 2 of list, send:
	`delete 2`

- Expected outcome:
	
	```
	Noted. I've removed this task:
	[✓] lab meeting (at: 21 Sep 2019 12.00 PM)
	Now you have 2 tasks in the list.
	```

#### Find Task by Keyword
Duke Bunny can help you find tasks that match a single word keyword. 
All matching tasks will be presented.

- To find if you have the task in the list by a single keyword like *lab*, send:
	`find assignment`

- Expected outcome:

	```
	Here are the matching tasks in your list:
	1.[D][✗] german assignment (by: 21 Sep 2019 10.00 AM)
	```

You can also find tasks by the date. 

- To find a task with the date *21/09/2019*, send:
	`find 21/09/2019`

- Expected outcome:

	```
	Here are the matching tasks in your list:
	1.[D][✗] german assignment (by: 21 Sep 2019 10.00 AM)
	```
	
	
## Expenses Managing Commands
This section will show you the commands for the expenses managing functions of Duke Bunny.

#### Add Expenses
Duke Bunny can help you add expenses into the expense list. 
If an income is already inputted, he will calculate how much of the income is left or how much is overspent.
He will also show the total expenditure.
If there is incorrect user input format, he will also reply with what went wrong.
The data will be saved once the command is made.

- To add an expense where *$220* was spent on buying a *Bike* and the income I previously inputted is *$1800*, send:
	`expense bike 220`

- Expected outcome:

	```
	Got it. I've added this expense:
	bike: 220.0
	Now you have 1 expense in the list.
	The total expenditure is now: 220.0
	The amount of income left is:
	1580.0
	```
	
	
#### Input Income
Duke Bunny can help to calculate how much is overspent or left of your income if you choose to input it.
The data will be saved once the command is made.
Everytime you do this command, the previous income will be **overwritten**.
If there is incorrect user input format, he will also reply with what went wrong.

- To input an income of *$1800* to see how much income is left or how much is overspent, send:
	`income 1800`
	
- Expected outcome:
	
	```
	Now you have 1 expense in the list.
	The total expenditure is now: 220.0
	The amount of income left:
	1580.0
	```


#### See Expense List
Duke Bunny can help you display all the expenses in your expense list.
He also shows the total expenditure and income left or amount of money overspent.

- To see all expenses in the list, send
	`elist`

- Expected outcome:

	```
	Here are the expenses in your list:
	1. books 100.0
	2. food 80.0
	2. bike: 220.0
	Total expenditure: 400.0
	The amount of income left:
	1400.0
	```
	
	
#### Delete Expenses
Duke Bunny can help you to delete specific expenses that you no longer need to keep track of.
The data will be saved once the command is made.
If there is incorrect user input format, he will also reply with what went wrong.

- To delete an expense at index 2 of the list, send:
	`delete e2`

- Expected outcome:

	```
	Noted. I've removed this expense:
	food: 80.0
	Now you have 2 expenses in the list.
	The total expenditure is now: 320.0
	The amount of income left:
	1480.0
	```
	
	
You can delete all expenses if you want to start anew when your monthly income has been received.
	
- To delete all expenses, send:
	`delete all`
	
- Expected outcome:

	```
	Now you have 0 expenses in the list.
	The total expenditure is now: 0.0
	The amount of income left:
	1800.0
	```
	