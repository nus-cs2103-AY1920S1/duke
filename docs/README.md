# User Guide

## Features 

### Viewing help: `help`
Shows a help page that summarizes commands.

### Adding to-do tasks: `todo`
Adds a new to-do task with description.

### Adding deadline tasks: `deadline`
Adds a new deadline task with description and deadline.

### Adding event tasks: `event`
Adds a new event task with description and event time.

### Marking tasks as done: `done`
Marks an existing task as complete.

### Deleting tasks: `delete`
Deletes an existing task.

### Searching tasks: `find`
Searches through all existing tasks for a given search phrase.

### Listing tasks: `list`
Lists all existing tasks, in order of addition.

### Creating notes: `note write`
Creates a new note with note title and content.

### Reading notes: `note read`
Reads an existing note.

### Deleting notes: `note delete`
Deletes an existing note.

### Listing notes: `note list`
Lists all existing notes.

### Exiting program: `bye`
Exits the program.


## Usage

### `help`

Expected outcome:

Displays a help page that summarizes commands.

### `todo <TASK_DESCRIPTION>`

Expected outcome:

Adds a new to-do task with description <TASK_DESCRIPTION>.

### `deadline <TASK_DESCRIPTION> /by <TASK_DEADLINE>`

Expected outcome:

Adds a new deadline task with description <TASK_DESCRIPTION> and deadline <TASK_DEADLINE>.
<TASK_DEADLINE> should be in the format DD/MM/YYYY HHMM.

### `event <TASK_DESCRIPTION> /at <EVENT_TIME>`

Expected outcome:

Adds a new event task with description <TASK_DESCRIPTION> and event time <EVENT_TIME>.
<EVENT_TIME> should be in the format DD/MM/YYYY HHMM.

### `done <TASK_NUMBER>`

Expected outcome:

Marks as done the task with task number <TASK_NUMBER>, if it exists.

### `delete <TASK_NUMBER>`

Expected outcome:

Deletes the task with task number <TASK_NUMBER>, if it exists.

### `find <SEARCH_PHRASE>`

Expected outcome:

Displays all tasks which contain the phrase <SEARCH_PHRASE>.

### `list`

Expected outcome:

Lists all existing tasks, in order of addition.

### `note write <NOTE_TITLE> | <NOTE_CONTENT>`

Expected outcome:

Creates a new note with title <NOTE_TITLE> and content <NOTE_CONTENT>.

### `note read <NOTE_TITLE>`

Expected outcome:

Displays the content of the note with title <NOTE_TITLE>, if it exists.

### `note delete <NOTE_TITLE>`

Expected outcome:

Deletes an existing note with title <NOTE_TITLE>, if it exists.

### `note list`

Expected outcome:

Lists all existing notes.

### `bye`

Expected outcome:

Exits the program.