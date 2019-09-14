# Bunny Duke
Bunny Duke is a tasks and expenses manager chat bot.

## Table of Contents

1. [Introduction](#introduction)
	1. [General Commands](#general-commands)
1. [Tasks Managing](#tasks-managing)
	1. [Types of Tasks](#types-of-tasks) 
	1. [Add Tasks](#add-tasks) 
	1. [See Task List](#see-task-list)
	1. [Mark Task as Done](#mark-tasks-as-done)
	1. [Delete Tasks](#delete-tasks)
	1. [Find Task by Keyword](#find-task-by-keyword)
1. [Expenses Managing](#expenses-managing)
	1. [Add Expenses](#add-expenses)
	1. [Input Income](#input-income)
	1. [See Expense List](#see-expense-list)
	1. [Delete Expenses](#delete-expenses)
	
## Introduction

Bunny duke can interact with you to help you to manage all your tasks and expenses. 
She can list out all your tasks, let you add tasks, delete tasks and mark tasks as done.
She also can help you save your deadline or event date and time.
Bunny duke can also help you manage your expenses.
She can let you input your income and input your expenses.
Then, she can help you calculate how much you have left to spend or how much is overspent.
She is able to save everything so when you close the app, your data is not lost.

#### General Commands
- To get a quick summary of all commands in one glance, you can send:
```
help
```
---
- To get a hands-on short tutorial of all commands, you can send:
```
tutorial
```
Just follow through the instructions and it will help you learn the commands of Duke Bunny very quickly.
---
- To bid Duke Bunny goodbye, you can send:
```
bye
```
This is optional because the data will be saved whenever any command is made. 
Thus, do not fret if you close Duke Bunny without doing this.
---

### Task Managing
This section will show you the commands for the task managing functions of Duke Bunny

#### Types of Tasks
- Todo:
Tasks that do not have a time or date. 
- Event:
Tasks that has a time and date to attend to it.
- Deadline:
Tasks that has a time and date as its deadline.

#### Add Tasks
- To add a Todo Task called *return book*, send:
	```
	todo return book
---	```
- To add an Event called *lab meeting* which would be held on *21 September 2019* at *12PM*, send:
	```
	event lab meeting /at 21/09/2019 1200
	```
	or
	```
	event lab meeting /at 21/09/2019 12.00 PM
	```
---
- To add a Task with a Deadline called *German Assignment* to be done by *21 September 2019* at *10.30AM*, send:
	```
	deadline german assignment /by 21/09/2019 1000
	```
	or
	```
	deadline german assignment /by 21/09/2019 10.00 AM
	```
---
#### See Task List
To see the list of tasks, send:
```
list
```

#### Mark Task as Done
To mark task at index 2 of list as done, send:
```
done 2
```

#### Delete Tasks
To delete task at index 2 of list, send:
```
delete 2
```

#### Find Task by Keyword
To find if you have the task in the list by a single keyword like *lab*, send:
```
find lab
```

All matching tasks will be presented.
You can also find tasks by the date. 
To find a task with the date *21/09/2019*, send:
```
find 21/09/2019
```

### Expenses Managing
#### Add Expenses
To add an expense where *$220* was spent on buying a *Bike*, send:
```
expense bike 220
```

#### Input Income
To input an income of *$1800* to see how much income is left or how much is overspent, send:
```
income 1800
```
Everytime you do this command, the previous income will be **overwritten**.

#### See Expense List
To see all expenses in the list, send
```
elist
```

#### Delete Expenses
To delete an expense at index 2 of the list, send:
```
delete e2
```
