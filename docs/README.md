# User Guide

## Features 

### Add tasks
Add "todo", "event", and "deadline" tasks

## Usage

### `[Task type] [Description]` - Adds a task of the specified type with the given description

Example of usage: 

`todo nothing`
> `Got it. I've added this task.`
  `Now you have 1 task in the list.`

`list`
> `1.[T][X] nothing`

`deadline something /by 22/01/19 1200`
> `Got it. I've added this task:`
  `[D][X] something (by: Sun Jan 22 12:00:00 SGT 19)`
  `Now you have 2 tasks in the list.`

`delete 1`
> `Got it. I've removed this task:`
  `> 1.[T][X] nothing`
