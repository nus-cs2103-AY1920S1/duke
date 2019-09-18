# User Guide

## Features 
* Keep track of various types of tasks
* Mark a task as completed
* Delete a task
* Parse dates for time-sensitive tasks
* Search through all existing tasks

## Usage

### `todo` - Creates a new basic task

Creates a new basic task.

Example of usage: 

`todo [description]`

Expected outcome:

![image](https://user-images.githubusercontent.com/41581512/65022122-f7449480-d962-11e9-9f48-761d1003f284.png)


### `deadline` - Creates a new deadline

Creates a new deadline, recording its description as well as the date and time by which it must be done.

Example of usage: 

`deadline [description] /by [dd-MM-yy] [hhmm]`

Expected outcome:

![image](https://user-images.githubusercontent.com/41581512/65022228-2955f680-d963-11e9-8b4a-fe3f9a648cf6.png)



### `event` - Creates a new event

Creates a new event, recording its description as well as the date and time of occurrence.

Example of usage: 

`event [description] /at [dd-MM-yy] [hhmm]`

Expected outcome:

![image](https://user-images.githubusercontent.com/41581512/65022155-06c3dd80-d963-11e9-88aa-3fb0c01f2ce0.png)



### `aftertask` - Creates a new "aftertask"

Creates a new aftertask (a task which can only be done after a certain date and time), recording its description as well as the date and time after which it can be done.

Example of usage: 

`aftertask [description] /after [dd-MM-yy] [hhmm]`

Expected outcome:

![image](https://user-images.githubusercontent.com/41581512/65022049-da0fc600-d962-11e9-9d43-9f246fec2151.png)



### `list` - Lists the existing items on your to-do list

Lists the existing items on your to-do list, including their categories (type of task) and their completion status.

Example of usage: 

`list`

Expected outcome:

![image](https://user-images.githubusercontent.com/41581512/65022123-f90e5800-d962-11e9-8514-f9076a829a4f.png)



### `done` - Marks a task as "done"

Marks a task as "done".

Example of usage: 

`done [index]`

Expected outcome:

![image](https://user-images.githubusercontent.com/41581512/65022187-16dbbd00-d963-11e9-92d9-8d14a0a11bdc.png)



### `delete` - Deletes a task

Deletes a task. Note that this action cannot be undone.

Example of usage: 

`delete [index]`

Expected outcome:

![image](https://user-images.githubusercontent.com/41581512/65022186-16dbbd00-d963-11e9-8e82-881c5383bfbe.png)



### `find` - Searches for a task

Searches for a task based on a user-supplied text string.

Example of usage: 

`find [query]`

Expected outcome:

![image](https://user-images.githubusercontent.com/41581512/65022154-062b4700-d963-11e9-93b8-c503c146b780.png)



### `bye` - Exits the program

Saves your data and exits the program.

Example of usage: 

`bye`

Expected outcome:

![image](https://user-images.githubusercontent.com/41581512/65022227-2955f680-d963-11e9-9ccf-8ab1fd4b8f35.png)
