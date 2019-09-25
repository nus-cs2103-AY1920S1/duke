# User Guide

**What**

* Duke was built as part of a module on Software Engineering I read at the National University of Singapore as part of my minor in Computer Science. 
* Duke is a basic Command Line Interface (CLI) application written in JAVA, following the OOP paradigm, and supplied with a basic GUI.
* Duke can manage a list of tasks such as to-dos, events, and deadlines with nine recognised commands tabulated below. Simply enter the command in the specified format and let Duke do the rest!

**Getting Duke**

Simply download the v0.3 jar release to get started. It is available here: https://github.com/atharvjoshi/duke/releases/tag/A-Release

# Using Duke 

Command | What Duke does | CLI entry format
---------------|---------------|---------------
`bye` | exits the application | `bye`
`deadline` | adds a deadline type task to the task list | `deadline <description> /by <dd/mm/yyyy> <hhmm>`
`delete` | deletes task with specified index | `delete <one-indexed positive integer>`
`done` | marks task with specified index as done | `done <one-indexed positive integer>`
`event` | adds an event type task to the task list | `event <description> /at <dd/mm/yyyy> <hhmm>`
`find` | finds a task corresponding to specified keywords | `find <keyword(s)>`
`list` | prints the task list | `list`
`todo` | adds a todo type task to the task list | `todo <description>`
`undo` | undos the last add/delete/done command in this session | `undo`

**Product Screenshot**

![](Ui.png)


# Acknowledgements
The project was built on code supplied by the CS2103 Teaching Department. Some parts of the code were reused from the JavaFX tutorials written by the CS2103 Teaching Department. The inspiration for the `undo` functionality came from a description of the OOP Command pattern by Matt Berther. 

# Feedback, Bug Reports

* If you have feedback or bug reports, please write to me at github.com/atharvjoshi
