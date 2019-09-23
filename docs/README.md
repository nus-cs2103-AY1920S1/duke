# User Guide

## Features 

### Add Todo 
Adds a todo item to your list

## Usage

### `todo` - adds an item

Adds a todo item at the end of your todo list

Example of usage: 

`todo (item)`

Expected outcome:

`Got it. I've added this task:`
`[T][X](item)`
`Now you have 1 tasks in the list`

### Add Event 
Adds an event item to your list

## Usage

### `event` - adds an item

Adds a event item at the end of your todo list

Example of usage: 

`event (item) /at dd/MM/yy HHss`

Expected outcome:

`Got it. I've added this task:`
`[E][X](item) at (dd/MM/yy HHss)`
`Now you have 1 tasks in the list`

### Add Deadline 
Adds an deadline item to your list

## Usage

### `deadline` - adds an item

Adds a deadline item at the end of your todo list

Example of usage: 

`deadline (item) /by dd/MM/yy HHss`

Expected outcome:

`Got it. I've added this task:`
`[D][X](item) by (dd/MM/yy HHss)`
`Now you have 1 tasks in the list`

### Mark as done
Mark item on list as done

## Usage

### `done` - marks item as done

Mark numbered item on list as done

Example of usage: 

`done 5`

Expected outcome:

`Nice! I've marked this task as done:`
`[ITEM][DONE] Buy juice`


### Delete
Deletes item from list

## Usage

### `delete` - deletes an item

Deletes numbered item on list

Example of usage: 

`delete 5`

Expected outcome:

`Noted. I've removed this task: `
`[E][✗] project meeting (at: Aug 6th 2-4pm)`
`Now you have 4 tasks in the list.`

### Sort
Sorts list by date

## Usage

### `sort` - sorts list

Sorts list by date

Example of usage: 

`sort`

Expected outcome:
`Your list is now sorted`

### Find
Finds substring in all item description

## Usage

### `find (subString)` - finds an items containing subString

Finds and lists your item

Example of usage: 

`find (subString)`

Expected outcome:
`Here are the matching tasks in your list:`
`2.[T][X] Do  fives times (substring) homework`
`2.[T][X] Do (substring)ly`