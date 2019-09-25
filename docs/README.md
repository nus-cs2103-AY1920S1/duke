It's very easy to make some words **bold** and other words *italic* 
# User Guide

## Features 
- Create and add new tasks to a list.
- Categorise each tasks into three different types: Deadline, Todo and Event.
- Sort the Deadline and Event tasks based on the date to be done. 
- Mark a task as done.
- Delete a task from the list.
- Find a task based on an input.
- Do not allow duplicate task.

## Usage

### `list`
**List all the tasks in the list of tasks.**

Example of usage:
```
list
```
Expected outcome:
```
Here are the tasks in your list:
1. [D][✓] submit assignment (by: 21/09/2019 2359)
2. [E][✘] project meeting (at: 19/09/2019 1300)
3. [E][✘] eat deck mala (at: 21/09/2019 1230)
4. [E][✘] training (at: 23/09/2019 1900)
5. [T][✘] return book
```

### `todo`
**Creates a todo task and append it to the list.**

The format is as follow:
`todo <description>`

Example of usage: 
```
todo return book
```

Expected outcome:
```
Got it. I've added this task:
  [T][✘] return book
Now you have 3 tasks in the list
```

### `event`
**Creates an event task and append it to the list.**

The format is as follow:
`event <description> /at <datetimeformat>`

Example of usage: 
```
event project meeting /at 19/09/2019 1300 
```

Expected outcome:
```
Got it. I've added this task:
  [E][✘] project meeting (at: 19/09/2019 1300)
Now you have 4 tasks in the list
```

### `deadline`
**Creates a deadline task and append it to the list.**

The format is as follow:
`deadline <description> /by <datetimeformat>`

Example of usage: 
```
deadline submit assignment /by 21/09/2019 2359 
```

Expected outcome:
```
Got it. I've added this task:
  [E][✘] submit assignment (by: 21/09/2019 2359)
Now you have 5 tasks in the list
```

### `done <index>`
**Marks the task at the given index as done.**

Example of usage:
```
done 2
```

Expected outcome:
```
Nice! I've marked this task as done:
  [E][✓] project meeting (at: 19/09/2019 1300)
```

### `delete <index>`
**Delete the task at the given index from the list.**

Example of usage:
```
delete 1
```

Expected outcome:
```
Noted. I've removed this task:
  [D][✓] submit assignment (by: 21/09/2019 2359)
Now you have 4 taks in the list
```

### `find <string>`
**Finds all the tasks in the list that partially/ fully matches the given string.**

Example of usage:
```
find book
```

Expected outcome:
```
[T][✘] return book
```

### `sort`
**Sort the list of task in order from [D], [E], then [T]. In category [D] and [E], the tasks are sorted from the earliest date to the latest date.**

Example of usage:
```
sort
```

Expected outcome:
```
Here is your sorted List:
1. [D][✓] submit assignment (by: 21/09/2019 2359)
2. [E][✘] project meeting (at: 19/09/2019 1300)
3. [E][✘] eat deck mala (at: 21/09/2019 1230)
4. [E][✘] training (at: 23/09/2019 1900)
5. [T][✘] return book
```

### `bye`
**Terminates the program and exits.**

Example of usage:
```
bye
```
