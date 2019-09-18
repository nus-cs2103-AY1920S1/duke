# User Guide

## Features 

### Add tasks
Tasks can be added and stored using the keywords: todo, deadline and event.
While todo does not have a specific date and time, deadline and event are supposed to come with deadlines
given in the form: **/by** *datetime* and **/at** *datetime* respectively.

### Delete tasks
Tasks can be deleted from the list.

### Finish task
Tasks can be marked as finished. 
Status of the task will be changed from 1 to 0.

### List tasks
See the whole list of tasks. 

### Snooze tasks
 Tasks will be snoozed for 7 days.

## Usage

### `Snooze` - snoozes the task
task will be snoozed for 7 days upon use of this command


Example of usage: 

`snooze 2`

Expected outcome:

`Task 2 of the list will have its deadline pushed back by 7 days if it is not a todo`

### `Delete` - deletes the task
task will be deleted from the list

Example of usage: 
`delete 1` 
Expected outcome: 
Task 1 will be deleted from the list

### `todo` - adds a todo to the list

Example of usage: 
`todo read book` 
Expected outcome:
`T | 1 | read book` will be added to the list

### `deadline` - adds a deadline to the list

Example of usage: 
`deadline return book /by 21/02/2019 1800`
Expected outcome: 
`D | 1 | return book | by 21 February 2019 6pm` will be added to the list

### `event` - adds an event to the list

Example of usage: 
`event party /at 21/02/2019 1400`
Expected outcome: 
`E | 1 | party | at 21 February 2019 2pm` will be added to the list

### `done` - sets the status of the task to done

Example of usage: 
`done 2`

Expected outcome:
task 2 will be set to done

### `list` - displays the entire list of tasks

