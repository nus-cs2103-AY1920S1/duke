# User Guide

## Introduction

Duke is a personal chatbot that allows you to manage your todo list, along with keeping track of deadlines and events.

## Features

### Adding tasks

You can add 3 types of tasks into Duke, namely:
- ToDo: For any tasks that needs to be done
- Deadline: For any tasks with a specific deadline
- Event: For any appointments with a given starting and ending time

Note: Duke prevents adding tasks that have the exact same description and times.

#### Adding ToDo: `todo [DESCRIPTION]`

This adds a new ToDo into the database with the given description.

Examples:
- `todo Prepare for project meeting`
- `todo Submit user guide`

#### Adding Deadline: `deadline [DESCRIPTION] /by [DEADLINE]`

This adds a new task with a deadline into the database with the given description.

Note: The `[DEADLINE]` must be given in the format `DD/MM/YYYY HHMM`.

Examples:
- `deadline Prepare project summary /by 06/06/2019 0000`
- `deadline Return book /by 20/08/2019 2200`

#### Adding Event: `event [DESCRIPTION] /at [START] - [END]`

This adds a new appointment with a given description, starting and ending time into the database.

Note: The `[START]` AND `[END]` times must be given in the format `DD/MM/YYYY HHMM`.

Examples:
- `event Project meeting /at 15/06/2019 1200 - 15/06/2019 1400`
- `event Jay Chou concert /at 20/06/2019 1900 - 20/06/2019 2200`

### Marking tasks as done: `done [ID]`
This marks the task with the given ID as done.

Example:
- `done 2`

### Deleting tasks: `delete [ID]`
This deletes the task with the given ID from the database.

Example:
- `delete 2`

### Finding tasks: `find [KEYWORD]`
This finds any task with the given keyword in the description.

Example:
- `find book`
- `find project meeting`

### Listing all tasks: `list`
This lists all the tasks that are currently present in the database.

### Exiting the program: `bye`
This exits the program and saves the current tasks list to the hard disk.
