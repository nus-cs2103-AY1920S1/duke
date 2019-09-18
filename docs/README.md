# Duke

Duke is a combination of a chatbot and To-Do List which allows users to manage their tasks in an interactive manner. 

## Features 

### Add a new Task
Save three types of Task: ToDo, Event, Deadline.

#### Usage

`todo [description]` - add a new `todo` Task with the given `description`.

`event [description] /at [dd/mm/yyyy HHmm]` - add a new `event` Task with the given `description` and event time.

`deadline [description] /by [dd/mm/yyyy HHmm]` - add a new `deadline` Task with the given `description` and due date.

#### Example of usage:

`event Google Tech Talk /at 17/09/2019 1730` 

#### Expected outcome:

![Add](add.png)


### List Task
Show a list of Task that have been added.

#### Usage

`list` - show the list of Task that the user has added.

#### Example of usage:

`list` 

#### Expected outcome:

![List](list.png)


### Update Task
Set Task as done.

#### Usage

`done [task number]` - set the Task at the specified number as done.

#### Example of usage:

`done 1` 

#### Expected outcome:

![Done](done.png)

### Remove Task
Remove Task from the TaskList.

#### Usage

`delete [task number]` - delete the Task at the specified number.

#### Example of usage:

`delete 1` 

#### Expected outcome:

![Delete](delete.png)

### Find Task
Show a list of Task that contains a specified keyword.


#### Usage

`find [keyword]` - show Task that contains `keyword`

#### Example of usage:

`find better` 

#### Expected outcome:

![Find](find.png)

### Archive
Move the list of Task to a separate file and clear the current active list.


#### Usage

`archive` - move all Task currently in the list to archive file

#### Example of usage:

`archive`

#### Expected outcome:

![Archive](archive.png)

### List Archive
Show a list containing all the Task that have been moved to archive.


#### Usage

`list archive` - show all task currently in the archive

#### Example of usage:

`list archive` 

#### Expected outcome:

![List Archive](listarchive.png)
