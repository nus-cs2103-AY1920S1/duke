# User Guide

## Features 

### Help
Provides a description of all functions that can be used and how to use them. 

#### Usage

Example usage:

`help`

Expected outcome:

```
help - This command. You couldn't figure that out?
list - Lists all your existing tasks.
find <description> - Finds a task which contains the description.

todo <description> - Creates a to-do item with a description.
event <description> /at <date and time> - Creates an event with a description at a certain time.
deadline <description> /by <date and time> - Creates a deadline with a description to be completed by a certain time.
Examples of <date and time>: tomorrow morning, next Mon night, two Tuesdays from now.

delete <index> - Deletes the task of that index.
done <index> - Completes the task of that index.

archive - Archives all tasks.
view - View all archived tasks.

bye - Exits the program.
```

### List 
Lists all existing tasks.

#### Usage

Example usage:

`list`

Expected outcome:

```
Fine. I'll tell you the tasks in your list:
1.[D][✓] submission for iP (by: 18/09/2019 2359)
2.[E][✘] team meeting (at: 20/09/2019 1930)
3.[T][✘] take the subway
```

### Find
Searches the description of all tasks and gives a list of tasks containing the search term.

#### Usage

Example usage:

`find sub`

Expected outcome:

```
I guess I'll give you the matching tasks...
1.[D][✓] submission for iP (by: 18/09/2019 2359)
2.[T][✘] take the subway
```

### Todo
Adds a Todo task. A Todo task has only a description.

#### Usage

Example usage:

`todo take the subway`

Expected outcome:

```
Added this:
[T][✘] take the subway
3 tasks left. Finish them quickly!
```

### Event
Adds a Event task. A Event task has a description and the date of the Event. 

#### Usage

Example usage:

`event team meeting next Fri 730 p.m.`

Expected outcome:

```
Added this:
[E][✘] team meeting (at: 20/09/2019 1930)
3 tasks left. Finish them quickly!
```

### Deadline
Adds a Deadline task. A Event task has a description and the date of the Deadline. 

#### Usage

Example usage:

`deadline submission for iP /by wed 2359`

Expected outcome:

```
Added this:
[D][✘] submission for iP (by: 18/09/2019 2359)
3 tasks left. Finish them quickly!
```

### Delete
Deletes a task based on a task number.

#### Usage

Example usage:

`delete 10`

Expected outcome:

```
Okay. Got rid of it.
[D][✘] submission for iP (by: 18/09/2019 2359)
You have 9 tasks left.
```

### Done
Marks a task as done based on a task number.

#### Usage

Example usage:

`done 1`

Expected outcome:

```
Took you long enough. It's done:
[D][✘] submission for iP (by: 18/09/2019 2359)
```

### Archive
Removes everything from your current task lists and archives it. 

#### Usage

Example usage:

`archive`

Expected outcome:

```
What you've archived is here:
1.[D][✓] submission for iP (by: 18/09/2019 2359)
2.[E][✘] team meeting (at: 20/09/2019 1930)
3.[T][✘] take the subway
```

Afterwards:
```
list
```
```
This is strange, but you have no tasks in your list.
```

### View
Views your archived tasks.

#### Usage

Example usage:

`view`

Expected outcome:

```
What you've archived is here:
1.[D][✓] submission for iP (by: 18/09/2019 2359)
2.[E][✘] team meeting (at: 20/09/2019 1930)
3.[T][✘] take the subway
```