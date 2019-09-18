# User Guide

![Ui](Ui.png)

(branch for chatbot style UI is [here](https://github.com/le0tan/duke/tree/branch-redo_GUI))

## Features 

### Add tasks with type

You may add tasks in three types: `To-Do`, `Deadline` and `Event`.

### View tasks in categories

You may view tasks in four categories: `To-Do`, `Deadline`, `Event` and `All`.

### Manage tasks

You may mark as done or delete a task.

### Load storage file

By default your storage would be located at `/Users/uicfa/Downloads/data.json` for Windows and `/Users/leo/Downloads/data.json` for other platforms.

You may also load your own storage file.

## Usage

Firstly, use `java -jar duke-0.2.3.jar` to launch the application.

### Manage tasks

#### Add a task

- Click `File` menu on the top right corner
- Click `New` in the dropdown menu
- Select a task type in the pop-up window
- Fill in description (and date, if applicable) in the fields below
- Click `Confirm` button on the bottom right to finish adding a task

#### Mark-as-done and delete a task

- In the Duke main window, right click on the task you want to modify
- Click on `Delete` or `Done` in the subsequent context menu

#### Load storage file

- Click `File` menu on the top right corner
- Click `Open...` in the dropdown menu
- Select your storage file in the file selector window, then click `Open`
- Duke will automatically load the new storage file for you

**NOTE:** please only load files that were created by Duke app itself.
