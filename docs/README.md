# Milk & Mocha's Task Manager - User Guide

By: Sim Khiang Leon 
Since: 30/9/2019


## Introduction

Milk & Mocha's (M&M) task manager serves as a personal chatbot assistant to help individuals manage and record their daily tasks in a readily available format. For those who prefer to work with a Command Line Interface (CLI) on top of a beautiful Graphical User Interface (GUI), this application will be perfect for you! 


## Quick Start

1. Ensure you have Java 11 or above installed on your computer. 
2. Download the latest jar file [here](https://github.com/khiangleon/duke/releases). 
3. Double-click the jar file to start the app. The GUI should appear in a few seconds. 
4. Type the command in the command box and press enter to execute it. Refer to the Features section for a list of all the commands you can try. 


## Features 

#### Command Format: 

Words in { } are parameters supplied by the user. 
Input integers for *task index*. 


### Adding a todo task: `todo`

Adds a todo to the list of tasks.
Format: todo {*task*}

Example of usage: 

`todo exercise`

Expected outcome:

`I've added this task: [T][x] exercise`


### Adding a deadline task: `deadline`

Adds a task with a deadline to the list of tasks.
Format: deadline {*task*} /by {*deadline*}

Specify *deadline* in date and time (24hr) format: DD/MM/YYYY HHMM

Example of usage: 

`deadline return book /by 24/11/2019 1700`

Expected outcome:

`I've added this task: [D][x] return book (by: Nov 24 2019 17:00 PM)`


### Adding an event task: `event`

Adds a task as an event with starting and ending times to the list of tasks.
Format: event {*task*} /at {*starting date and time*} /to {*ending time*}

Specify *starting date and time* in date and time (24hr) format: DD/MM/YYYY HHMM
Specify *ending time* in time (24hr) format: HHMM

Example of usage: 

`event project meeting /at 1/12/2019 1000 /to 1200`

Expected outcome:

`I've added this task: [E][x] project meeting (at: Dec 1 2019 10:00 AM - 12:00 PM)`


### Adding a 'do after' task: `do`

Adds a task that needs to be done after an existing task in the list of tasks.
Format: do {*task*} /after {*task index*}

*task index* is the index of the task on the list which needs to be done first.

Example of usage: 

`do return book /after 2`

Expected outcome:

`I've added this task: [DA][x] return book (after: read book)`


### Listing all tasks: `list`

Shows all current existing tasks in a list. 
Format: list


### Marking a task as done: `done`

Marks a task with a slash (/) to show that task has been completed. All tasks are set undone (x) initially.
Format: done {*task index*} 

*task index* is the index of the task on the list which is completed. 

Example of usage: 

`done 3`

Expected outcome:

`I've marked this task as done: [D][/] return book (by: Nov 24 2019 17:00 PM)`


### Deleting a task: `delete`

Removes a task permanently from the list of tasks. 
Format: delete {*task index*}

*task index* is the index of the task on the list which is to be removed. 

Example of usage: 

`delete 5`

Expected outcome:

`I've removed this task: [D][x] return book (by: Nov 24 2019 17:00 PM)`


### Searching a task: `find`

Searches for a task in the list of tasks containing the provided keyword/phrase. 
The search is case sensitive.
You can search for specific dates and numbers too.

Format: find {*keyword/phrase*}

Example of usage: 

`find book`

Expected outcome:

`Here are the matching tasks in your list: 
1.[T][x] borrow book
2.[T][x] read book
3.[D][x] return book (by: Nov 24 2019 17:00 PM)`   


### Exiting the app: `bye`

Automatically saves the list of tasks to the existing text file and closes the app window.
Format: bye


## Acknowledgements

I do not own the below images used in the GUI:

[milk.png](https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwi_2omdsfjkAhWFA3IKHU6UD_YQjRx6BAgBEAQ&url=https%3A%2F%2Fwww.pinterest.com%2Fpin%2F847521223607434910%2F&psig=AOvVaw192gEcdS1P0WUpcOzgMk1q&ust=1569927264785686)
[mocha.jpg](https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwiW7cONtPjkAhW38HMBHVDABN8QjRx6BAgBEAQ&url=https%3A%2F%2Fwww.pinterest.com%2Fpin%2F329748003963651491%2F&psig=AOvVaw301bzFVBbSvHHLicpDmO8X&ust=1569928107397874) 
[background.jpg](https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwiTjaGhtPjkAhV1ILcAHZ3UC1sQjRx6BAgBEAQ&url=https%3A%2F%2Ftwitter.com%2Fmilkmochabear%2Fstatus%2F1034453857961631744&psig=AOvVaw1VWNuf1e0gPT00n4ElEd6M&ust=1569928152359688)

