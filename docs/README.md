# Duke - User Guide
By Jasmine Yeo Jia Min, A0185321L

## Introduction
This is an individual project done for CS2103T module in NUS.
It is based on the basic duke structure provided by the module.
Through guided incremental modifications and enhancements, this is the final product of my Duke individual assignment though it has many areas of improvement. 

## Features 

### Adding tasks
Adds task into the task list according to 3 types of labels: `todo`, `deadline`, `event`.

#### Type: `todo`
Tasks classified under `todo` are basic tasks with no timestamps. 

For example, _cleaning the room_ or _buying bedsheets_.

##### Command Usage
Format: ```todo <TASK_DESCRIPTION>```

Examples: 
- `todo clean the room`
- `todo buy bedsheets`

#### Type: `deadline`
Tasks classified under `deadline` are tasks with a deadline. 

For example, _submit CS2103T quiz by 19 September 2019 at 23:59_ 
or _plan SEP module mapping by 30 September 2019 at 17:00_.

##### Command Usage
Format: ```deadline <TASK_DESCRIPTION> /by <dd/MM/yyyy> <hh:mm>```

Examples: 
- `deadline submit CS2103T quiz /by 19/09/2019 2359`
- `deadline plan SEP module mapping /by 30/09/2019 1700`

#### Type: `event`
Tasks classified under `event` are tasks with a fixed timestamp. 

For example, _family gather at 15 December 2019 at 14:00_ 
or _job interview at 20 January 2020 at 10:00_.

##### Command Usage
Format: ```event <TASK_DESCRIPTION> /at <dd/MM/yyyy> <hh:mm>```

Examples: 
- `event family gather /at 15/12/2019 1400`
- `event job interview /at 20/01/2020 1000`

### Deleting tasks: `delete`
Removes task corresponding to the task's index in the task list.

##### Command Usage
Format: ```delete <TASK_INDEX>```

Examples: 

Assuming the initial task list contains these tasks:
    
    1. T - Read book
    2. E - Project meeting (at: 02-12-2019 18:00)
    3. T - Join sports club
    4. D - Team submission (by 11-12-2020 23:59)
    
- `delete 1`

    Removes the first task `T - Read book` from the task list according to the order of the **initial** task list.
    
    The modified task list will look like the following:

    ```
    1. E - Project meeting (at: 02-12-2019 18:00)
    2. T - Join sports club
    3. D - Team submission (by 11-12-2020 23:59)
    ``` 

- `delete 2`
    
    Removes the second task `T - Join sports club` from the task list according to the order of the **modified** task list.
                                                      
    The modified task list will look like the following:
    
    ```
    1. E - Project meeting (at: 02-12-2019 18:00)
    2. D - Team submission (by 11-12-2020 23:59)
    ``` 

### Completing tasks: `done`
Marks the task corresponding to the given task's index as completed, denoted by `+` sign.

##### Command Usage
Format: `done <TASK_INDEX>`

Examples:

Assuming the initial task list contains these tasks:
    
    1. T - Read book
    2. E - Project meeting (at: 02-12-2019 18:00)
    3. T - Join sports club
    4. D - Team submission (by 11-12-2020 23:59)
    
- `done 1`

    Marks the first task `T - Read book` from the task list as completed.
    
    The modified task list will look like the following:

    ```
    1. T + Read book
    2. E - Project meeting (at: 02-12-2019 18:00)
    3. T - Join sports club
    4. D - Team submission (by 11-12-2020 23:59)
    ``` 

- `done 2`
    
    Marks the second task `E - Project meeting (at: 02-12-2019 18:00)` from the task list as completed.
                                                       
    The modified task list will look like the following:
    
    ```
    1. T + Read book
    2. E + Project meeting (at: 02-12-2019 18:00)
    3. T - Join sports club
    4. D - Team submission (by 11-12-2020 23:59)
    ``` 

### Listing tasks: `list`
Lists all the tasks in the task list.

##### Command Usage
Format: `list`

Example of an expected outcome:
    
```
Here are the tasks in your list:
1. T - Read book
2. E - Project meeting (at: 02-12-2019 18:00)
3. T - Join sports club
4. D - Team submission (by 11-12-2020 23:59)
``` 

### Finding tasks: `find`
Finds task(s) corresponding to given keyword(s).

##### Command Usage
Format: `find <KEYWORD>`
- `<KEYWORD>` can be 1 or many.

  Examples of `<KEYWORD>`: _book_, _find book_, _meeting_ and _team meeting_.

Examples:

Assuming the initial task list contains these tasks:
    
    1. T - Read book
    2. E - Project meeting (at: 02-09-2019 18:00)
    3. D - Join sports club (by 15-10-2019 17:00)
    4. E - Team Meeting (at 11-12-2020 23:59)
 
- `find meeting`

  Example of an expected outcome:
      
  ```
  Here are the matching tasks in your list:
  1. E - Project meeting (at: 02-12-2019 18:00)
  2. E - Team meeting (at 11-12-2020 23:59)
  ``` 
  
- `find team meeting`

  Example of an expected outcome:
      
  ```
  Here are the matching tasks in your list:
  1. E - Team meeting (at 11-12-2020 23:59)
  ``` 
  
### Exiting the program: `exit`
Exits the program.

##### Command Usage
Format: `exit`

Example of the expected outcome: 

`Bye. Hope to see you soon!`
