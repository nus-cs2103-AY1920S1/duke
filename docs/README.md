# User Guide

**What**

* Duke was built as part of a module on Software Engineering I read at the National University of Singapore as part of my minor in Computer Science. 
* Duke is a basic Command Line Interface (CLI) application written in JAVA, following the OOP paradigm, and supplied with a basic GUI.
* Duke can manage a list of tasks such as to-dos, events, and deadlines with nine recognised commands tabulated below. Simply enter the command in the specified format and let Duke do the rest!

**Getting Duke**

Simply download the v0.3 jar release to get started. It is available here: https://github.com/atharvjoshi/duke/releases/tag/A-Release

# Features

Command | What Duke does | Usage
---------------|---------------|---------------
`bye` | Exits this session of Duke. User can now type `restart` to begin another session of Duke. | `bye`
`deadline` | Adds a deadline type task to the task list with the description provided. The date must be entered in dd/mm/yyyy format and time must be entered in 24hr format. | `deadline <description> /by <dd/mm/yyyy> <hhmm>`
`delete` | Deletes task with specified index. | `delete <one-indexed positive integer>`
`done` | Marks task with specified index as done. Done tasks have a `[Y]` tag while undone tasks have a `[N]` tag. | `done <one-indexed positive integer>`
`event` | Adds an event type task to the task list with the description provided. The date must be entered in dd/mm/yyyy format and time must be entered in 24hr format. | `event <description> /at <dd/mm/yyyy> <hhmm>`
`find` | Finds task(s) corresponding to specified keywords. Any number of keywords separated by a space may be entered. | `find <keyword(s)>`
`list` | Prints the task list, along with their task type and done status. | `list`
`todo` | Adds a todo type task to the task list with the description provided. | `todo <description>`
`undo` | Undos the last add/delete/done command in this session. | `undo`

# Product Screenshot

![](Ui.png)


# Acknowledgements
The project was built on code supplied by the CS2103 Teaching Department. Some parts of the code were reused from the JavaFX tutorials written by the CS2103 Teaching Department. The inspiration for the `undo` functionality came from a description of the OOP Command pattern by Matt Berther. 

# Feedback, Bug Reports

If you have feedback or bug reports, please write to me at github.com/atharvjoshi
