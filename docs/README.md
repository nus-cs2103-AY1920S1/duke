# Duke User Guide
Welcome to my Duke ChatBot! Duke is a Command Line Interface (CLI) ChatBot that helps you manage your todo list!


![Ui image](Ui.png)
## Features 
Below is a summary of commands you could use with Duke.
### Update tasks 
* Todo tasks
* Event tasks
* Deadline tasks

#### List tasks
Lists all tasks in Duke.

#### Mark task as done
Marks completed task as done.

#### Find task
Finds tasks in the list of tasks that contains the provided keyword.

#### Delete task
Deletes unwanted task from list of tasks in Duke.

#### 
## Usage

### `todo` - Adds a Todo task

Adds a Todo task into Duke, with a description.

Format: `todo <description>`

Example usage: `todo Laundry`

Expected outcome: `[T][✗] Laundry` will be added to list of tasks.
<br/>
![Todo image](todo.png)

### `event` Adds an Event task

Adds an Event task into Duke, with a description, date and time.

Format: `event <description> /at <date time> (dd/MM/yyyy HHmm)`

Example usage: `event Career Fair /at 04/10/2019 1200`

Expected outcome: 
`[E][✗] Career Fair (at: 4th of October 2019, 12.00pm) ` will be added to the list of tasks.
<br/>
![Event image](event.png)

### `deadline` Adds a deadline task
Adds a Deadline task into Duke, with a description, date and time.

Format: `deadline <description> /by <date time> (dd/MM/yyyy HHmm)`

Example usage: `deadline Assignment 0 /by 20/09/2019 2359`

Expected outcome: `[D][✗] Assignment 0 (by: 20th of September 2019, 11.59pm)`
<br/>
![Deadline image](image.png)

### `list` Lists all tasks
Lists all tasks in Duke with their description as well as date and time if applicable.

Format: `list`

Example usage: `list`

Expected outcome:<br/>
`1. [T][✗] Laundry` <br/>
`2. [E][✗] Career Fair (at: 4th of October 2019, 12.00pm)` <br/>
`3. [D][✗] Assignment 0 (by: 20th of September 2019, 11.59pm)`
<br/>
![List image](list.png)

### `done` Marks task as done
Marks task at the given index as done.

Format: `done <index>`

Example usage: `done 1`

Expected outcome: <br/>
`Nice! I've marked this task as done:`
`[T][✓] Laundry`
<br/>
![Done image](done.png)

### `find` Finds task
Finds tasks that contains given keywords.

Format: `find <keywords>`

Example usage: `find Career`

Expected outcome:<br/>
`Here are the matching tasks in your list:` <br/>
`1. [E][✗] Career Fair (at: 4th of October 2019, 12.00pm)`
<br/>
![Find image](find.png)

### `delete` Deletes task
Deletes task at the given index as done.

Format: `delete <index>`

Example usage: `delete 1` <br/>

Expected outcome:<br/>
`Noted. I've removed this task:`<br/>
`[T][✓] Laundry`<br/>
`Now you have 2 tasks in the list.`
<br/>
![Delete image](delete.png)
