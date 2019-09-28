# Duke Bot User Guide

## What is it?
Duke Bot is a bot that enables you to keep track of your recent tasks and also manage them efficiently.

## Features 

### list 
List all the saved tasks in user's current task list.

### done
Complete a specific task in user's current task list.

### delete
Delete a specific task in user's current task list.

### todo
Add a specific todo event to user's current event list.

### deadline
Add a specific deadline event to user's current event list.

### event
Add a specific event to user's current event list.

### find
Find all tasks in the current event list that match user input keyword either partially or fully.

### update
Update the deadline/event time of a particular deadline/event task in the current task list.

### bye 
Exit the program.

## Usage

### `List` - See all your tasks.

List all the tasks in your current task list.

Example of usage: 

`list`

### `Done` - Finish one of your task.

Mark the status of a particular task (identified by the index of that task in the task list) in your list as "completed".

Example of usage: 

`done 2`

### `Delete` - Remove one of your task.

Removes a particular task (identified by the index of that task in the task list) from your list.

Example of usage: 

`remove 2`

### `Todo` - Add a todo task to your list.

Add the description of a new todo task to your list. This task will be appended to the end of the user task list.

Example of usage: 

`todo finish maths quiz`

### `Deadline` - Add a deadline task to your list.

Add the description and also the specific deadline of a new deadline task to your list. This task will be appended to the end of the user task list.

Example of usage: 

`deadline finish maths quiz /by 08/09/2019 1800`

`deadline finish maths quiz /by 8th Aug 2019, 6pm`

### `Event` - Add an event task to your list.

Add the description and also the specific event date of a new event task to your list. This task will be appended to the end of the user task list.

Example of usage: 

`event attend formal dinner /at 08/09/2019 1800`

`event attend formal dinner /at 8th Aug 2019, 6pm`


### `Find` - Find all related tasks.

Find all relevant tasks that contains the given search keywords either partially or fully.

Example of usage: 

`find formal`

Expected outcome:

`Here are the matching tasks in your list:`
`1.[E][☓] attend formal dinner (at: Mon Aug 19 18:00:00 SGT 2019)`

### `Update` - Update the deadline/event date of a deadline/event task.

Edit the deadline/event date of a deadline/event task to a new one. The task is identified by its index in the current task list.

Example of usage: 

`Update 2 /by 08/09/2019 1700`
`Update 2 /by 13th Aug 2019, 5pm`

### `Bye` - Exit the program

Exit the program properly and save your modified task list.

Example of usage: 

`bye`
