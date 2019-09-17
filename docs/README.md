# User Guide

## Features 

### Feature 1 
Adding a **Task** to your list
* A todo Task : No date involved
* A deadline Task : Which has to be done *by* a certain date
* An event Task: : Which has to done *at* a certain date

## Usage

### `todo` - adding a todo to the list
adds a todo without any date.

Example of usage: 

`todo cycle`

Expected outcome:

`Got it. I've added this task:`
`  [T][✘] cycle`
`Now you have 1 task in the list.`

### `deadline` - adding a deadline to the list
adds a deadline and the date that it should done by

Example of usage: 

`deadline drop CS /by 30/09/2019 2359`

Expected outcome:

`Got it. I've added this task: \n
  [D][✘] drop CS (by 30/09/2019 2359) \n
Now you have 2 tasks in the list.`

### `deadline` - adding a deadline to the list
adds a deadline and the date that it should done by

Example of usage: 

`event McDonald's interview /at 1/10/2019 1300`

Expected outcome:

`Got it. I've added this task:`
`  [E][✘] McDonald's interview (at 1/10/2019 1300)`
`Now you have 3 tasks in the list.`