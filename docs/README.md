# User Guide
1. [Features](#1-features) <br/>
    1.1. [Adding new task](#11-add-new-task) : `todo`, `event`, `deadline` <br/>
    1.2. [Delete a task](#12-delete-a-task) : `delete` <br/>
    1.3. [Complete a task](#13-complete-a-task) : `done` <br/>
    1.4. [List all tasks](#14-list-all-tasks) : `list` <br/>
    1.5. [Tag a task](#15-tag-a-task): <br/>
    * 1.5.1 [Add a tag](#151-add-a-tag) : `addtag` <br/>
    * 1.5.2 [Delete a tag](#152-delete-a-tag) : `deltag` <br/>
    * 1.5.3 [Filter by tag](#153-filter-by-tag) : `findtag` <br/>
    * 1.5.4 [List all tags](#154-list-all-tags) : `listtag` <br/>
    
    1.6. [Filter by keyword](#16-filter-by-keyword) : `find` <br/>
    1.7. [Exit Duke](#17-exit-duke) : `bye` <br/>


## 1. Features

### 1.1 Add new task
Adds a task to your task list. <br/>
There are three types of task: `todo` `event` `deadline` <br/>

Format: <br/>
`todo (description)`<br/> 
`deadline (description) /by (dd/MM/yyyy HHmm)`<br/>
`deadline (description) /by (dd/MM/yyyy HHmm) /tag (tag)`<br/>
`event (description) /at (dd/MM/yyyy HHmm)`<br/>
`event (description) /at (dd/MM/yyyy HHmm) /tag (tag)`<br/>

Example: <br/>
`todo borrow book` : Creates a Todo task <br/>
`todo borrow book /tag school cs` : Creates a Todo task with tags `school` and `cs` <br/>
`deadline lab /by 11/09/2019 2359` : Creates a Deadline task <br/>

### 1.2 Delete a task

Deletes a task from your list. <br/>

Format: <br/>
`delete (task number)` <br/>

Example: <br/>
`delete 3` : Deletes task number 3 <br/>

### 1.3 Complete a task
Completes a task in your list. <br/>

Format:<br/>
`done (task number)` <br/>

Example: <br/>
`done 3` : Mark task number 3 as completed <br/>


###  1.4 List all tasks
Lists out all the tasks in your list. <br/>

Format: <br/>
`list` <br/>


### 1.5 Tag a task
Tags a task. Tags can only be one word.
Includes the various functionality: <br/>

### 1.5.1 Add a tag
Adds a tag to the specified task. <br/>

Format: <br/>
 `addtag (task number) (tag name)` <br/>
 
 Example: <br/>
 `addtag 3 school` : Adds the tag `school` to task number 3 <br/>


### 1.5.2 Delete a tag
Deletes a tag from a specified task. <br/>
Format: <br/>
 `deltag (task number) (tag name)` <br/>
 
 Example: <br/>
 `deltag 3 school` : Deletes tag `school` from task number 3 <br/>


### 1.5.3 Filter by tag 
Filters tasks based on specified tag. <br/>

Format: <br/>
`findtag (tag name)` <br/>

Example: <br/>
`findtag school` : Filters out all the tasks with tag `school` <br/>


### 1.5.4 List all tags
Output a list of all tags. <br/>

Format: <br/>
`listtag` <br/>


### 1.6 Filter by keyword
Filters tasks based on specified keyword <br/>

Format: <br/>
`find (keyword)` <br/>

Example: <br/>
`find book` : Filters out all the task with the word `book` <br/>


### 1.7 Exit Duke
Exits from Duke. <br/>

Format: <br/>
`bye`



