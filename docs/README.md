# User Guide

Say Hello to the **TOM and JERRY Task Tracker**!

![introImage](https://github.com/shiyao821/duke/blob/master/docs/Ui.png)

#### Create and manage your todo lists with the following commands:

## View Tasks
### `list` 
shows you the current task list


## Add Tasks
There are 3 different kinds of tasks that you can add: 

### 1. ToDos
#### `todo <description>`
eg. `todo sweep the floor`

### 2. Deadlines
#### `deadline <description> /<preposition> <memo>`
eg. `deadline assignment submission /by 09/08/2019 2059`

### 3. Events
#### `event <description> /<preposition> <memo>`
eg. `event happy new year /on 31/12/2019 2359`

NOTE: Entering the `<memo>` with the format `dd/MM/yyyy HHmm` will cause it to be converted to a real date and time. 
This will then allow the task to be sorted across the entire list. 

NOTE: The prepositions in the examples can be replaced with any word. 


## Mark Completed Tasks
#### `done <list_index>`

marks that task as done

eg. `done 4` 


## Delete Tasks
#### `delete <list_index>`
deletes that task from the list

eg. `delete 6`

NOTE: it is non-recoverable! So look before you submit!


## Find Tasks
#### `find <description>`
shows all the tasks that contain that description

eg. `find obladi`


## Check input formats
#### `formats`
shows all possible input formats


## Quit
#### `bye`
exits the Task Tracker