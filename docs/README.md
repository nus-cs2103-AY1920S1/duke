# User Guide

Duke is a chatbot to help you manage your tasks. Feel free to say hi!

## Getting Started
1. Make sure that you have Java 11 (or later) installed on your computer.

<img src="Step 2.png" alt="View on GitHub button">

2. Click on the **_View on GitHub_** button at the top of this page
3. Click on releases
4. Click on duke-0.2.1.jar and wait for the file to download
5. Choose to keep the file if your computer says that it might be harmful
6. Double-click on the file to run it.
7. For Mac users: If the file can't be opened because it is from an unidentified developer, right click on the file and select **_Open_**. A popup window will appear. Select **_Open_**.

## Features 

### Adding a task with a specific deadline: `deadline`
Adds a task with the specified deadline in DD/MM/YYYY HH:MM:SS format.

Example: 

`deadline buy groceries /by 10/10/2019 15:00:00`

### Adding a task with a specific date and time of event: `event`
Adds a task with the specified date and time of event in DD/MM/YYYY HH:MM:SS format.

Example: 

`event soccer match /at 10/10/2019 15:00:00`

### Adding a todo task: `todo`
Adds a general todo task.

Example: 

`todo water the plants`

### Deleting a task: `delete`
Deletes the task with the specified task number.

Example: 

`delete 3`: Deletes the 3rd task in the task list, provided that it exists.

### Setting a task as done: `done`
Sets the specified task's status as done.

Example: 

`done 3`: Sets the status of the 3rd task in the task list to done, provided that it exists.

### Getting help: `help`
Lists all the available commands.

Example: 

`help`

### Exiting the program: `bye`
Exits the program.

Example: 

`bye`

### Finding tasks which contains a specific phrase: `find`
Finds tasks which contains the specified phrase or keyword.

Example: 

`find bread`: Lists all tasks which contain the keyword "bread".

### Listing all tasks: `list`
Lists all stored tasks.

Example: 

`list`
