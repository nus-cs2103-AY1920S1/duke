# Table of contents
1. [Duke](#Duke)
2. [Features](#Features)

	2.1. [Adds todo tasks: `todo`](#Todo)
	
	2.2. [Adds deadline tasks: `deadline`](#Deadline)
	
	2.3. [Adds event tasks: `event`](#Event)
	
	2.4. [Mark task as complete: `done`](#Done)
	
	2.5. [Finds task: `find`](#Find)
	
	2.6. [Delete task: `delete`](#Delete)
	
	2.7. [Undo your previous command: `undo`](#Undo)

	2.8. [List all task items: `list`](#List)

	2.9. [Exit the program: `bye`](#Bye)

3. [Command summary](#summary)
	

	
## 1. Duke <a name="Duke"></a>
Duke is a task tracker desktop application which allows user to manage their own tasks. It uses Command-Line Interface (CLI), so user can perform operations by typing the commands into the textfield. 

![Duke](Ui.png)

## 2. Features <a name="Features"></a>
Command Format
* Words within <> are to be supplied by the user. For example, `todo <task>`, `<task>` requires user to filled in their own task. 

### 2.1. Adds todo task: `todo` <a name="Todo"></a>
Format: `todo <task>`

Example of usage: <br />
`todo create user stories`

Expected outcome: <br />
![Todo command](todo.PNG)

### 2.2. Adds deadline task: `deadline` <a name="Deadline"></a>
Format: `deadline <task> /by <date>`

NOTE: `<date>` must be in the format of dd/MM/yyyy HHmm. Example: 02/12/2019 1800

Example of usage: <br />
`deadline create user stories /by 12/09/2019 2359`

Expected outcome: <br />
![Deadline command](deadline.PNG)

### 2.3. Adds event task: `event` <a name="Event"></a>
Format: `event <task> /at <location>`

Example of usage: <br />
`event hackathon /at COM1`

Expected outcome: <br />
![Event command](event.PNG)

### 2.4. Mark task as complete: `done` <a name="Done"></a>
Format: `done <index>`

NOTE: `<index>` must be a valid task list item.

Example of usage: <br />
`done 5`

Expected outcome: <br />
![Done command](done.PNG)

### 2.5. Finds task: `find` <a name="Find"></a>
Show task that contains at least one of the user specified words. <br />
Format: `find <words to search>`

Example of usage: <br />
`find stories hackathon`

Expected outcome: <br />
![Find command](find.PNG)

### 2.6. Delete task: `delete` <a name="Delete"></a>
Format: `delete <index>`

NOTE: `<index>` must be a valid task list item.

Example of usage: <br />
`delete 5`

Expected outcome: <br />
![Delete command](delete.PNG)

### 2.7. Undo your previous command: `undo` <a name="Undo"></a>
Format: `undo`

### 2.8. List all task items: `list` <a name="List"></a>
Format: `list`

### 2.9. Exit the program: `bye` <a name="Bye"></a>
Format: `bye`

## 3. Command summary <a name="summary"></a>
* **Todo**: `todo <task>` <br />
Example: `todo create user stories` 
* **Deadline**: `deadline <task> /by <date>` <br />
Example: `deadline create user stories /by 12/09/2019 2359`
* **Event**: `event <task> /at <location>` <br />
Example: `event hackathon /at COM1`
* **Done**: `done <index>` <br />
Example: `done 5`
* **Find**: `find <words to search>` <br />
Example: `find stories hackathon`
* **Delete**: `delete <index>`
Example: `delete 5`
* **Undo**: `undo`
* **List**: `list`
* **Bye**: `bye`
