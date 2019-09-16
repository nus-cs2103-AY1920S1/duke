# Duke User Guide

## Features 

### Task Creation - Todo
Create todo tasks and store them within Duke.

## Usage

### `todo [todo description]`

Type and pass this command to Duke to create and store a new todo task. The todo's description
will be as specified by you.

Example of usage: 

`todo finish math homework`

Expected outcome:

`A new todo will be created with its description being "_todo finish math homework_"`

### Task Creation - Deadline
Create deadline tasks and store them within Duke.

## Usage

### `deadline [deadline description] /by [DD/MM/YYYY] [hhmm]`

Type and pass this command to Duke to create and store a new deadline task. The deadline's description
and completion date and time will be as specified by you. To create a deadline successfully, follow
the above syntax exactly with the time being specified in 24h format.

Example of usage: 

`deadline get ready for the party /by 2/12/2019 1800`

Expected outcome:

`A new deadline will be created with its description being "_get ready for the party_" and`
`the deadline's completion data and time being 2/12/2019 1800, or _2nd of December, 2019, 6pm_ as represented by Duke`

### Task Creation - Event
Create todo tasks and store them within Duke.

## Usage

### `event [event description] /at [4/12/2019] [hhmm-hhmm]`

Type and pass this command to Duke to create and store a new event task. The event's description
and duration date and time will be as specified by you. To create an event successfully, follow
the above syntax exactly with the time being specified in 24h format.

Example of usage: 

`event party at school /at 4/12/2019 1800-2230`

Expected outcome:

`A new event will be created with its description being "_party at school_" and`
`the event's duration data and time being 2/12/2019 1800-2230, or _2nd of December, 2019, 6pm-10.30pm_ as represented by Duke`

### List
List down all the tasks stored in Duke.

## Usage

### `list`

All the tasks stored within Duke will be shown by Duke in a list form.

Example of usage: 

`list`

Expected outcome:

`A numbered list of tasks will be showed by Duke with all the tasks listed out.`

### Task Deletion
Delete any task stored in Duke which you no longer require or want to see.

## Usage

### `delete [task index]`

Deletes the task as specified by the index you type in the command.

Example of usage: 

`delete 2`

Expected outcome:

`The second task as shown by Duke's list of tasks will be deleted.`

### Mark Tasks as Done
Mark any task stored in Duke as done after you have completed it.

## Usage

### `done [task index]`

Marks the task as specified by the index you type in the command as done. 

Example of usage: 

`done 2`

Expected outcome:

`The second task as shown by Duke's list of tasks will be marked as done.`

### Search
Search Duke for any task based on a keyword.

## Usage

### `find [search query]`

Duke will search for every task that matches the query you type, either fully or partially.

Example of usage: 

`find return`

Expected outcome:

`Duke will list down all the tasks which have the word _return_ in them.`