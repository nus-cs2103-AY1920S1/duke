# User Guide

1. [Task Features](#1-task-features) <br/>
    1.1. [Adding new Task](#11-add-new-task) : `todo`, `event`, `deadline` <br/>
    1.2. [Delete a Task](#12-delete-task) : `delete` <br/>
    1.3. [List all Tasks](#14-list-tasks) : `list` <br/>
    1.4. [Done a Task](#13-done-tasks) : `done` <br/>
    1.5. [Delete Tasks](#14-delete-tasks) : `list` <br/>

2. [Tag Features](#2-tag-features) <br/>
    2.1. [Adding Tags](#21-add-tags) : `todo`, `event`, `deadline` <br/>
    2.2. [Remove a Tag](#22-remove-tag-from-task) : `delete` <br/>
    2.3. [Query Tags a Task Has](#23-query-tags-a-task-has) : `done` <br/>
    2.4. [Query Tasks a Specific Tag Has](#24-query-tasks-a-specific-tag-has) : `list` <br/>
    2.5. [Update Tag](#25-update-tag): <br/>
    2.6. [Delete Tag](#26-delete-tag): <br/>

## Features

### 1. Tasks Features
Description of feature.
#### 1.1 Add new Task
Adds a new task to Tasklist `todo`, `deadline`, `event`.

##### Format
Format:
`todo (description of task)`
`deadline (description of task)`
`deadline (description of task) \by (time)`
`event (description of task)`
`event (description of task) \at (time)`

Example of use:
`todo visit innsmouth`
##### Note
(time) if it is in (dd/MM/yyy HHmm) can be understood
otherwise, it will be stored as a string

#### 1.2 Delete Task
Deletes a task from Tasklist `todo`, `deadline`, `event`.

##### Format
Format:
`delete (task number in list)`

Example of usage:
`delete 4` : fourth task displayed in Tasklist will be deleted


#### 1.3 List Tasks
List all tasks from Tasklist.

##### Format
Format:
`list`

Example of usage:
`list` :
    -------------


#### 1.4 Done Tasks
Mark a Task as "Done" in Tasklist.

##### Format
Format:
`done (task number in list)`

Example of usage:
`done 3` : marks the third task displayed in Tasklist as "Done"

##### Note
using `todo`, `deadline`, `event` commands, Tasks would be created as "Undone".
Marking a "Done" Task as "Done" will not have any change in the Task.


#### 1.5 Delete Tasks
Delete a Task in Tasklist.

##### Format
Format:
`delete (task number in list)`

Example of usage:
`delete 2` : delete the second task displayed in Tasklist

##### Note
using `todo`, `deadline`, `event` commands, Tasks would be created as "Undone".
Marking a "Done" Task as "Done" will not have any change in the Task.


### 2. Tag Features
Description of feature.

#### 2.1 Add Tags
Add a Tag to a Task in Tasklist.

##### Format
Format:
`tag addtag (name of tag) (task number in list)`

Example of usage:
`tag addtag @book 2` : adds the tag "@cthulhu" to the second task displayed in Tasklist

#### 2.2 Remove Tag From Task
Remove a Tag from a Task in Tasklist.

##### Format
Format:
`tag deletepair (name of tag) (task number in list)`

Example of usage:
`tag deletepair @book 2` : removes the tag "@book" from the second task displayed in Tasklist

##### Note
using `deletepair` on a task without the relevant tag will not do anything

#### 2.3 Query Tags a Task Has
List all the Tags a Task has

##### Format
Format:
`tag querytask (task number in list)`

Example of usage:
`tag querytask 2` : lists all tags item 2 is tagged with

#### 2.4 Query Tasks a specific Tag Has
List all the Tasks tagged with a specific Tag

##### Format
Format:
`tag querytag (name of tag)

Example of usage:
`tag querytag @hotel` : lists all items tagged with @hotel

#### 2.5 Query All Tags
List all Tags and Tasks tagged by them

##### Format
Format:
`tag all`

##### Note
This produces a view-only list. Not the TaskList

#### 2.6 Update Tag
Replace every instance of a Tag with a new Tag

##### Format
Format:
`tag updatetag (old tag name) (updated tag name)`

Example of usage:
`tag updatetag @productivity @producktivity` : replace every instance of @productivity with @producktivity

#### 2.6 Delete Tag
Delete every instance of a Tag

##### Format
Format:
`tag deletetag (tag name)

Example of usage:
`tag deletetag @work` : deletes every instance of @work tag



### `Keyword` - Describe action

Describe action and its outcome.

Example of usage:

`keyword (optional arguments)`

Expected outcome:

`outcome`


