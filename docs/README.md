# User Guide

Welcome! This chatbot has 2 main functions; it can act as a task manager, and can give trivia questions.
It handles 3 types of tasks - todo tasks, deadlines, and events, which will all be placed in a Tasklist for your reference.
Please read further for a more in-depth explanation.

## Features 

### 1. ToDo 
Adds an undone task with no specific deadline to the TaskList.

### 2. Deadline
Adds an undone task with a specific deadline to the Tasklist.

### 3. Event
Adds an undone Event task to the Tasklist.

### 4. List
This feature displays the list of tasks in the Tasklist.

### 5. Done
This feature marks a task as done.

### 6. Edit
This feature edits a task currently in the Tasklist.

### 7. Delete
This feature deletes a task from the Tasklist.

### 8. Find
This feature searches and retrieves tasks matching the given description.

### 9. Trivia
This feature asks a random trivia question and expects a response.

## Usage

### 1. ToDo

Format: `todo [description]'

[description] here represents the description of the task to be done.
For example, it could be "wash the dishes", or "review the CS2101 script".

The task will be expressed in the format of '[Task type][Done status] Task description'.

Example of usage: 

`todo wash the dishes`

Expected outcome:

`[T][✘] wash the dishes` will be added to the Tasklist.


### 2. Deadline

Format: 'deadline [description] /by [timing]'

[description] represents the description of the task.
For example, 'watch the CS2105 webcast'.
[timing] represents the time it must be done by.
It must be written in the following format:
DD/MM/YYYY TTTT
For example, **18/09/2019 0155** will represent **18th of September, 2019 at 01:55AM**,
and **01/12/2019 1900** will represent **1st of December, 2019 07:00PM**.

The task will be expressed in the format of '[Task type][Done status] Task description Task timing'.

Example of usage: 

'deadline finish CS2105 Assignment 1 /by 09/10/2019 2359`

Expected outcome:

`[D][✘] finish CS2105 Assignment 1 (by: 9 October 2019 11:59PM)`

### 3. Event

Format: 'event [description] /at [timing]'

This feature adds an event at a specified time to the Tasklist.

[description] represents the description of the event.
For example, 'Claire's architecture exhibition'.
[timing] represents the time it is happening at.
It must be written in the following format:
DD/MM/YYYY TTTT
For example, **18/09/2019 0155** will represent **18th of September, 2019 at 01:55AM**,
and **01/12/2019 1900** will represent **1st of December, 2019 07:00PM**.

The task will be expressed in the format of '[Task type][Done status] Task description Task timing'.

Example of usage: 

`event Claire's architecture exhibition /at 09/10/2019 1900`

Expected outcome:

`[E][✘] Claire's architecture exhibition (at: 9 October 2019 07:00PM)`

### 4. List

Format: 'list'

Displays the Tasklist with each task at a specific index representing their current position.

Example of usage: 

`event Claire's architecture exhibition /at 09/10/2019 1900`
`list`

Expected outcome:

`1. [E][✘] Claire's architecture exhibition (at: 9 October 2019 07:00PM)`

### 5. Done

Format: 'done [index]'

Marks the task currently at [index] position in the Tasklist as done.

Example of usage: 

`done 1`
`list`

Expected outcome:

`1. [E][✓] Claire's architecture exhibition (at: 9 October 2019 07:00PM)`

### 6. Edit

Format: 'edit [index] [new description]'

This will edit the task at [index] position in the Tasklist and replace its description.
In the event that a deadline or event is being edited, the command will look like this:
'edit [index] [new description] [/by or /at] [new timing]'

Example of usage: 

`edit 1 Claire's graduation ceremony /at 09/10/2019 1800`

Expected outcome:

`1. [E][✓] Claire's graduation ceremony (at: 9 October 2019 06:00PM)`

### 7. Delete

Format: `delete [index]'

This will delete the task at [index] position in the Tasklist. 
The remaining tasks' indexes will be updated to match their new position.

Example of usage: 

`list`
`1. [T][✘] wash the dishes
 2. [D][✓] finish CS2105 Assignment 1 (by: 9 October 2019 11:59PM)
 3. [E][✘] Claire's architecture exhibition (at: 9 October 2019 07:00PM)`
`delete 2`

Expected outcome:

`list`
`1. [T][✘] wash the dishes
 2. [E][✘] Claire's architecture exhibition (at: 9 October 2019 07:00PM)`

### 8. Find

Format: 'find [input]'

The search results will be returned if they contain [input].

Example of usage: 

`1. [T][✘] wash the dishes
 2. [D][✓] finish CS2105 Assignment 1 (by: 9 October 2019 11:59PM)
 3. [E][✘] Claire's architecture exhibition (at: 9 October 2019 07:00PM)`
`find dish`

Expected outcome:

`1. [T][✘] wash the dishes`

### 9. Trivia

Format: 'trivia'

Upon sending the 'trivia' command, the chatbot will return a question.
For example, "What instrument can Sherlock Holmes play?"
Your next input will then be your answer.
For example, "the violin".
The chatbot will then respond with a message indicating whether your answer was correct.

Example of usage: 

User input: `trivia`
Chatbot: `The art and practice of garden cultivation and management is called what?`
User input: `Agriculture`

Expected outcome:

Chatbot: `Sorry, the answer is Horticulture. Better luck next time!`