# User Guide

## Features 

### Tracking tasks, deadlines and completion status
You can add todos, events, deadlines and track their completion status.


### Finding, completing and deleting tasks
You can search for tasks by their description, as well as mark them as complete or delete them from your list.


## Usage

### `todo` - Create todo task

This will create a new todo task, which does not require a deadline.

Example of usage: 

`todo update digivice`

Expected outcome:

```   
     Got it. I've added this task:
     [T] [✘] update digivice
     Now you have 3 tasks in the list.
```

### `event` - Create event task

This will create a new event task, which requires a deadline. Deadline should be in DD/MM/YYYY HHMM format.

Example of usage: 

`event digi-egg hatching /at 03/12/19 1800`

Expected outcome:

```   
     Got it. I've added this task:
     [E] [✘] digi-egg hatching (at: 3rd of December 2019, 6.00PM)
     Now you have 3 tasks in the list.
```

### `deadline` - Create deadline task

This will create a new deadline task, which requires a deadline. Deadline should be in DD/MM/YYYY HHMM format.

Example of usage: 

`deadline evolve patamon /by 05/12/19 0600`

Expected outcome:

```   
     Got it. I've added this task:
     [D] [✘] evolve patamon (by: 5th of December 2019, 6.00AM)
     Now you have 3 tasks in the list.
```
### `find` - Find a task

This will search the list of tasks and return any tasks whose description
contains any of the provided keywords. This command accepts multiple keywords.

Example of usage: 

`find digi`

Expected outcome:

```   
     Here are the tasks found that match your search term:
     1.[T] [✘] update digivice
     2.[E] [✘] digi-egg hatching (at: 3rd of December 2019, 6.00PM)
```

### `list` - List all task

This will list all existing tasks in order of addition.

Example of usage: 

`list`

Expected outcome:

```   
     Here are the tasks:
     1.[T] [✘] update digivice
     2.[E] [✘] digi-egg hatching (at: 3rd of December 2019, 6.00PM)
     3.[D] [✘] evolve patamon (by: 5th of December 0019, 6.00AM)
```

### `delete` - Delete a task

This will delete a task specified at the one-indexed position provided in the second argument.

Example of usage: 

`delete 1`

Expected outcome:

```   
     Noted. I've removed this task:
      update digivice
     Now you have 2 tasks in the list.
```

### `bye` - Exit the program

This will end the program, and save all changes made. Note that if the program is exited
without using the bye command, the changes will __not__ be saved.

Example of usage: 

`bye`

Expected outcome:

```   
     Bye. Hope to see you again soon!
```