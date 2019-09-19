# User Guide

## Features 

### Feature 1 
Description of feature.

## Usage

### Help

A condensed version of this entire guide.

Example of usage: 

`help`

Expected outcome:

    Hello! I'm Nick! You can use:
    todo <item>
    deadline <item> /by <when>
    event <item> /at <when>
    find <keyword>
    done <number>
    delete <number>
    stats
    bye
    
### To-do

Creates a new To-do Entry.

Example of usage: 

`todo Listen to Gazelle's new hit`

Expected outcome:

    Got it. I've added this task:
    [T][✘] Listen to Gazelle's new hit
    You now have 1 task in the list.
    
### Deadline

Creates a new Deadline Entry, taking in a deadline in the format DD/MM/YYYY HHmm.

Example of usage: 

`deadline Write report on Cheetah Chase /by 20/09/2019 1300`

Expected outcome:

    Got it. I've added this task:
    [D][✘] Write report on Cheetah Chase (by: 20/09/2019 1300)
    You now have 1 task in the list.
    
### Event

Creates a new Event Entry, taking in a when in the format DD/MM/YYYY HHmm.

Example of usage: 

`event Visit Mr Big /at 20/09/2019 1800`

Expected outcome:

    Got it. I've added this task:
    [E][✘] Visit Mr Big (by: 20/09/2019 1800)
    You now have 1 task in the list.
    
### Find

Displays a list of tasks with descriptions matching the keyword.

Example of usage: 

`find Gazelle`

Expected outcome:

    Here are the matching tasks in your list:
    1. [T][✓] Listen to Gazelle's new hit
    
### List

List the tasks stored in HustlePad.

Example of usage: 

`list`

Expected outcome:

    1. [T][✓] Listen to Gazelle's new hit
    2. [D][✘] Write report on Cheetah Chase (by: 20/09/2019 1300)
    3. [E][✘] Visit Mr Big (by: 20/09/2019 1800)    
    
### Done

Marks a task as Done.

Example of usage: 

`find Gazelle`

Expected outcome:

    Here are the matching tasks in your list:
    1. [T][✓] Listen to Gazelle's new hit

### Delete

Deletes a task from the list.

Example of usage: 

`delete 1`

Expected outcome:

    Noted. I've removed this task:
    1. [T][✓] Listen to Gazelle's new hit
    You now have 2 tasks in the list.

### Statistics

Provides stats on how many complete and incomplete tasks remain.

Example of usage: 

`stats`

Expected outcome:

    You have 1 complete task and 2 incomplete tasks.

### Bye

Saves the database and closes the application.

Example of usage: 

`bye`

## References

Images used:
- Screengrabs from Zootopia (2016)
- https://zootopia.fandom.com/wiki/Carrot_pen