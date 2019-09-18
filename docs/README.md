# User Guide
![Screenshot of Duke GUI](Ui.png)
### Overview 
Duke is used to track your daily tasks


## Navigation
Commands that help you to navigate through the app

### `duke --help`

Get the documentation for duke commands


### `list` 

List of all the tasks added. The list is 1 index

#### Example command: 
```$xslt
list
```

#### Example response:

```$xslt
Here are the tasks in your list:
  1. [T][✗] wash clothes
  2. [D][✗] return book (by: Mon Dec 02: 18:00:00 SGT 2019)
```

### `find {key word}`

Find the tasks that contain the {key word}

####Example command:
```$xslt
find clothes
```

####Example response:
```$xslt
Here are the matching tasks in your list:
  1.[T][✓] wash clothes
```

### `done {index}`
Change the task status to done. Where {index} is the index of the task on the list

#### Example command:
```$xslt
done 1
```

#### Example response:
```$xslt
Nice! I've marked this task as done:
  [T][✓] wash clothes
```

### `delete {index}` 
Delete the task from the list. Where {index} is the index of the tasks on the list

#### Example command:
```$xslt
delete 1
```

#### Example response:
```$xslt
Noted. I've removed this from task:
  [T][✓] wash clothes
```

### `bye`
Quit duke

## Task Commands
Command that are used to add tasks to duke

###Todo
Todos are one time task that can either be marked as done or undone

#### `todo {Task Details}` - add Todo task to list

#### Example command: 
```$xslt
todo wash clothes
```

#### Example response:
```$xslt
Got it. I've added this task:
  [T][✗] wash clothes
Now you have 1 task in the list
```

### Deadline
Deadline are task with a date and time tagged to the task

#### `deadline {Task Details} /by {DD}/{MM}/{YYYY} {HHMM}` - add deadline task

#### Example command:
```$xslt
deadline return book /by 2/12/2019 1800
```

#### Example response:
```$xslt
Got it. I've added this task:
  [D][✗] return book  (by: Mon Dec 02 18:00:00 SGT 2019)
Now you have 1 tasks in the list.
```

### Event
Event are task with only a date and time details tagged to it

#### `event {Task Details} /at {Date and time details}` - add event

#### Example command:
```$xslt
event project meeting /at Mon 2-4pm
```

#### Example response:
```$xslt
Got it. I've added this task:
  [E][✗] project meeting  (at: Mon 2-4pm)
Now you have 2 tasks in the list.
```

