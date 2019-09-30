## Duke User Guide
###  Introduction
Duke is an automated Chat Bot system that is capable of keeping track of your everyday tasks so that you don't have to do them yourself. Duke operates entirely on your local system, so there is no need for internet access to call upon Duke to note down all the important events that you may have in your everyday life.

Duke operations are entirely based on a CLI (Command Line Interface). So any action that you want to perform in Duke has to be done through input on a keyboard.

### Set-Up

 1. Ensure that you have the latest Java version installed on your computer.
 2. Download the latest Duke.jar from [here]().
 3. Run and install the program.
 4. Enjoy!

### Features
 **1. Make a new To-Do:** `todo`
- Instructs Duke to make a new To-Do. It is mandatory for the To-Do to have **details**. 
- Format: **todo** [details of todo]

*Example  Usage: **todo** wisdom tooth extraction*

 **2. Make a new Deadline:** `deadline`
- Instructs Duke to make a new Deadline. It is mandatory for the Deadline to have details as well as a due date. The time can only be accepted as 24-hr format.
- Format: **deadline** [details of deadline] **by** [dd]/[mm]/[yy] [hhmm]

*Example  Usage: deadline homework by 10/12/19 1340*
 
**3. Make a new Event:** `event`
 - Instructs Duke to make a new Event. It is mandatory for the Event to have details as well as a date, start and end time. The time can only be accepted as 24-hr format.
- Format: **event** [details of event] **at** [dd]/[mm]/[yy] [hhmm]-[hhmm]

*Example Usage: event meeting at 23/09/19 1000-1400*

**4. List all Tasks:** `list`
 - Makes a list of all tasks that you have instruct Duke to keep a record of. 

*Example Usage: list*

**5. Find all Matching Tasks by Keyword:** `find`
 - Makes a list of all matching tasks according to the keyword that you have specified for Duke.
 - Format: **find** [keyword]

*Example Usage: find homework*

**6. Mark a Task as Done:** `done`
 - Marks a task as done according to the task number that you have specified for Duke. 
 - This command can be used after the `find` command to mark a task in the list of matching tasks as done. 
 - Format: **done** [task number]

*Example Usage: done 1*

**7. Delete Task:** `delete`
 - Deletes a task as done according to the task number that you have specified for Duke. 
 - This command can be used after the `find` command to delete a task in the list of matching tasks.
 - Format: **delete** [task number]

*Example Usage: delete 1*

**8. Exit Program:** `exit`
 - Exits the program after a 1.5 second delay.

*Example Usage: exit*