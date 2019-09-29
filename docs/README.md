# User Guide
Welcome to the User Guide for my personal Duke implementation. In this User Guide, you will find the basic instructions on the commands you can execute in the input box of the Duke GUI. 

## Features
There are a few implemented features in Duke. The exhaustive list of the current features are as follows:
1. Listing of Tasks
2. Saving of Tasks
3. Deletion of Tasks
4. Marking of Tasks as Complete
5. Searching/Finding of Tasks
6. Creation of Tasks
    - Creation of ToDo Tasks
    - Creation of Event Tasks
    - Creation of Deadline Tasks
7. Help Page 
8. Error detection
    - Display of user-friendly error messages


## Usage
### Listing of Tasks
Command: `list`

This command will list the Tasks that you currently have stored in Duke.

Example Usage: `list`

Expected Output:
```
Here are the tasks in your list:
    1. [T][X] Complete Project Work
    2. [E][X] Purchase Groceries (by 26/10/2019 18:30)
```

### Saving of Tasks
Command: `save`

This command will save the current Tasks to disk.

Example Usage: `save`

Expected Output:
```
Duke has saved the latest data!
```


### Deletion of Tasks
Command: `delete [task_num]`

This command will delete the `task_num`-th Task from Duke. The `task_num` is the number of the Task when you run the `list` command.  

Example Usage: `delete 1`

Expected Output:
```
Noted. I've removed this task. 
    [T][X] Complete Project Work
Now you have 1 task in this list. 
```

### Marking of Tasks as Complete
Command: `done [task_num]`

This command will mark the `task_num`-th Task from Duke as complete. The `task_num` is the number of the Task when you run the `list` command.  

Example Usage: `done 1`

Expected Output:
```
Noted. I've marked this task as complete. 
    [T][X] Complete Project Work
Now you have 1 task in this list. 
```

### Finding Tasks
Command: `find [search_string]`

This command will match the `search_string` to the description of every single Task currently saved in Duke. 

Example Usage: `find Project`

Expected Output:
```
Here are the tasks that contain your search term:
    [T][X] Complete Project Work
```

### Creation of Tasks
Note that the creation of the different Tasks (ToDo, Deadline, Event) are differentiated by their command word (`todo`, `deadline` and `event` respectively)
#### Creation of Todo Tasks
Command: `todo [description]`

This command will create a new ToDo Task. This ToDo task will be displayed with the description of the ToDo.

Example Usage: `todo Complete Project Work`

Example Output:
```
Got it. I've added this task:
    [T][X] Complete Project Work
Now you have 3 tasks in the list.
```  

#### Creation of Deadline Tasks
Command: `deadline [description] /by [deadline_time]`

This command will create a new Deadline Task. Note that the `/by` portion of the command is compulsory. Its exclusion will result in an error thrown, and Duke will inform you accordingly.

Also, please note that the `deadline_time` has to be of the form: dd/MM/yyyy HH:mm

Example Usage: `deadline Purchase Groceries /by 26/02/2019 18:30`

Example Output:
```
Got it. I've added this task:
    [T][X] Purchase Groceries (by 26/02/2019 18:30)
Now you have 4 tasks in the list.
```

#### Creation of Event Tasks
Command: `event [description] /at [start_time] - [end_time]`

This command will create a new Event Task. Note that the `/at` and `-` portions of the command compulsory. Its exclusion will result in an error thrown, and Duke will inform you accordingly.

Also, please note that the `start_time` and `end_time` have to be of the form: dd/MM/yyyy HH:mm

Example Usage: `event Sandcastle Building at Sentosa /at 31/12/2019 09:00 - 31/12/2019 15:30`

Example Output:
```
Got it. I've added this task:
    [E][X] Sandcastle Building at Sentosa (at 31/12/2019 09:00 - 31/12/2019 15:30)
Now you have 4 tasks in the list.
```
  
#### Help Page
Command: `help`

This command will display the built-in help page in Duke. 

Example Usage: `help`

Expected Output:
```
Welcome to the Duke Help Page!
    list
        Type 'list' to obtain an exhaustive list of current Tasks
    save
        Type 'save' to save the current list of Tasks to disc
    delete <num>
        Deletes the <num>-th Task
    done <num>
        Marks the <num>-th Task as complete
    fine <keyword>
        Finds <keyword> in the current list of Tasks
    todo <description>
        Adds a ToDo Task with <description>
    event <description> /at <start_time> - <end_time>
        Adds an Event Task with <description> and start/end times
        Start/end times must be in this format: dd/MM/yyyy HH:mm
    deadline <description> /by <deadline_time>
        Adds a Deadline Task with <description> and deadline
        <deadline_time> must be in this format: dd/MM/yyyy HH:mm
        
    Hope this helps! :)
``` 
