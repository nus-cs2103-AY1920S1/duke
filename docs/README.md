# User Guide

![](duke-demo.gif)

## Introduction

Duke is a responsive and persistent task manager to help you manage and track your everyday activities! With its command-based syntax for input and basic Graphical User Interface (GUI), Duke is best-suited for those accustomed to working with a Command Line Interface (CLI), who will find it familiar and easy to use.

![](Ui.png)

## Features 

- **Task tracking** - add and remove a variety of tasks - to-dos, deadlines, events
- **Status tracking** - mark tasks as complete!
- **Tagging** - tag tasks with simple keywords
- **Search** - search for tasks by description or tags
- **Persistence** - your task list is saved even after Duke is closed!
- **Inbuilt guidance** - prompts from Duke if commands are input incorrectly!

## Requirements and Usage

- Java Runtime Environment (JRE) - versions 11 and later
- Simply download the application JAR and click on it to launch!

## Command Types

Commands that accept arguments specify each argument in angled brackets `<arg>`, with either-or arguments separated by a pipe `|` operator, e.g. `<arg1> | <arg2>`.

All date-time arguments `<date time>` have the format `DD/MM/YYYY HHMM`.

### `bye` - Close Duke
Gracefully closes the Duke application window after a short delay.

   - Command syntax: `bye`
   - Sample usage: `bye`
      - Behaviour: Duke responds with `bye` and the application terminates half a second later

---

### `list` - List all tasks
Lists all tasks currently tracked by Duke's to-do list.

   - Command syntax: `list`
   - Sample usage: `list`
      - Behaviour: prints the list of all tasks with their details (type, completion status, tags and description)

---

### `done` - Mark a task complete
Marks a given task in the to-do list complete by its task ID.

   - Command syntax: `done <id>`
   - Sample usage: `done 1`
      - Behaviour: marks the first task in the to-do list complete - its completion status will update from `[X]` to `[V]` the next time it is printed

---

### `delete` - Remove a task
Removes a task from Duke's to-do list by its task ID.

   - Command syntax: `delete <id>`
   - Sample usage: `delete 1`
      - Behaviour: removes the first task from the to-do list, printing out its details and the remaining length of the to-do list

---

### `find` - Search for tasks
Searches and returns all tasks that match a given description or given tag.

   - Command syntax: `find <search_string> | #<tag_string>`
   - Sample usage 1: `find dinner`
      - Behaviour: prints a list of all tasks containing the string `dinner` in their description with their respective details
   - Sample usage 2: `find #important`
      - Behaviour: prints a list of all tasks with the tag `#important` with their respective details
   - Sample usage 3: `find #very-important`
      - Behaviour: prints a list of all tasks with the tag `#very-important` with their respective details

---

### `tag` - Tag a task
Tags an existing task in Duke's to-do list by its task ID.

   - Command syntax: `tag <id> <tag_string>`
   - Sample usage 1: `tag 1 critical`
      - Behaviour: updates the first task in the to-do list with the new tag `#critical`
   - Sample usage 2: `tag 1 very important`
      - Behaviour: updates the first task in the to-do list with the new tag `#very-important`

---

### `todo` - Add a new to-do for tracking
Adds a new task with a description to Duke's to-do list.

   - Command syntax: `todo <description>`
   - Sample usage: `todo CS2103T user guide`
      - Behaviour: adds a new uncompleted to-do task with no tags and description `CS2103T user guide`, to the end of the to-do list 

---

### `deadline` - Add a new deadline for tracking
Adds a new task with a description and deadline to Duke's to-do list.

   - Command syntax: `deadline <description> /by <date time>`
   - Sample usage: `deadline CS2103T user guide /by 17/09/2019 2359`
      - Behaviour: adds a new uncompleted deadline task with no tags, description `CS2103T user guide` and deadline `Tue, 17 Sept 2019, 11:59PM`, to the end of the to-do list

---

### `event` - Add a new event for tracking
Adds a new task with a description and event time to Duke's to-do list.

   - Command syntax: `event <description> /at <date time>`
   - Sample usage: `deadline CS2103T tutorial /at 18/09/2019 1100`
      - Behaviour: adds a new uncompleted event task with no tags, description `CS2103T tutorial` and event time `Wed, 18 Sept 2019, 11:00AM`, to the end of the to-do list
