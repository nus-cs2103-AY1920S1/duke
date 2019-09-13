# User Guide

## Features 

### Feature 1 
Add Tasks to the current List of Duke.

    1. Todo Tasks
        a. Consist of Description of Task only.
    2. Deadline Tasks
        a. Consist of Description of Task.
	b. Consist of Time whereby Task must be done by.
    3. Event Tasks
	a. Consist of Description of Task
	b. Consist of Time whereby Task occurs at.

### Feature 2
Set Tasks as Done in Duke.

### Feature 3
Update Tasks in Duke, by changing their Description or Time.

### Feature 4
Delete Tasks in Duke.

### Feature 5
List out all Tasks current in Duke.

### Feature 6
Find certain Tasks in the List currently in Duke using a keyword.

## Usage

### `todo/deadline/event`

Adds the corresponding Task to the List in Duke.

Example of usage: 

`todo borrow book`

`deadline return book /by 19/8/2019 1800`

Expected outcome:

`Adds a Todo Task to Duke that is represented as [T][X] borrow book`

`Adds a Deadline Task to Duke that is represented as [D][X] return book (by: 19th of August 2019, 6:00pm)`

### `done`

Marks the Task in Duke as done.

Example of Usage:
`done 1`

Expected Outcome:
Task number 1 in Duke will change from [X] to [✔].

### `update`

Updates the given Task's description of time depending on user input.

Example of Usage:
`update 3 description buy bread`

Expected Outcome:
Task number 3 in Duke will change its current description to "buy bread".

### `delete`

Deletes a particular Task in Duke based on the number.

Example of Usage:
`delete 4`

Expected Outcome:
Task number 4 currently in Duke will be deleted.

### `list`

Lists out all the Tasks in Duke currently.

Example of Usage:
`list`

Expected Outcome:
All Tasks in Duke will be listed out, with each Task represented in the format described in `todo/deadline/event`.

### `find`

Lists out all the Tasks in Duke with a certain keyword given by the user command line input.

Example of Usage:
`find book`

Expected Outcome:
All Tasks that contain "book" in their description will be printed out in the format described in `todo/deadline/event`.

