# User Guide

Table of Contents

-   [1. Introduction](#1-introduction)
-   [2. Quick Start](#2-quick-start)
-   [3. Features](#3-features)
    -   [3.1 Adding a task: `add`](#31-adding-a-task)
    -   [3.2 List all tasks: `list`](#32-list-all-tasks)
    -   [3.3 Set a task as done: `done`](#33-set-as-done)
    -   [3.4 Delete a task: `delete`](#34-delete-a-task)
    -   [3.5 Find a task: `find`](#35-find-a-task)
    -   [3.6 Undo previous command: `undo`](#36-undo-previous-command)

## 1. Introduction

Duke is a Personal Assistant Chatbot that helps a those who prefer to use a desktop app for managing tasks. More important, Duke is optimised for those who prefer to work with a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). Duke can get your task management done faster than traditional GUI apps.

## 2. Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest Duke.jar [here](https://github.com/andyylam/duke/releases/tag/v1.0).
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Double-click the file to start the app. The GUI should appear in a few seconds.

## 3. Features

### 3.1 Adding a task

Adds a task to list of tasks in Duke

There are 3 different types of tasks:

1. ToDos: tasks without any date/time attached to it e.g., visit new theme park
    - Format: `todo [NAME]`
2. Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
    - Format: `deadline [NAME] /by [DATE]`
    - Date is formatted as such: DD/MM/YY HHMM
3. Events: tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm
    - Format: `event [NAME] /at [DATE]`
    - Date is formatted as such: DD/MM/YY HHMM

### 3.2 List all tasks

Shows a list of all tasks in Duke.

Format: `list`

### 3.3 Set as done

Sets a task as done.

Format: `done [INDEX]`

INDEX is the number of the task in the list of tasks when `list` is called.

### 3.4 Delete a task

Deletes a task.

Format: `delete [INDEX]`

INDEX is the number of the task in the list of tasks when `list` is called.

### 3.5 Find a task

Finds a task that corresponds to the given keyword, even if the keyword matches the item only partially.

Format: `find [KEYWORD]`

### 3.6 Undo previous command

Undos the previous command.

Format: `undo`
