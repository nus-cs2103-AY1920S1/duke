# Duke User Guide
[![Build Status](https://travis-ci.com/SendorasLeft/duke.svg?branch=master)](https://travis-ci.com/SendorasLeft/duke)

## 1. Introduction
The Duke Chat Assistant is for users who prefer a chat-assistant desktop-based app for managing their daily errands and pending tasks in lieu of traditional GUIs. This simple-to-use chat app combines both Command Line Interface (CLI) style commands with a friendly chat-style GUI whose responses aim to mimick the style of chatting with a close friend. Read on to find out how you can get started!

## 2. Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` link:{repoURL}/releases[here].
3. Copy the file to the folder you want to use as the home folder for Duke.
4. Double-click the file to start the app. The GUI should appear in a few seconds. You should see a greeting from Duke:
  [image goes here]
5. Type a command message in the text box and press enter to send it to Duke

   eg. type `list` to preview all tasks in your task list.  
   
6. Here are some commands you can try:

    - `list`: list out tasks in the current list
    - `deadline Go to the moon /by 17/02/2050 1800`: adds a reminder to `Go to the moon` by 17th February 2050, 6pm.
    - `done 1`: marks the first task in your list as completed. Well done!
    
7. Refer to Section 3, "Features" for full details of each command.

## 3. Features
### Command Format
- Each command must begin with a command word. See each command below for the list of associated command words.
- Command words are case-insensitive eg. `list`, `LIST`, and `LiSt` all activate the list command.
- Words in `UPPER_CASE` are the parameters to be supplied by the user eg. `list`

### 3.1 Adding a task: `todo`, `deadline`, and `event`
You can instruct Duke to add three different types of tasks:

#### 3.1.1 Todo 
Add tasks that have a description only.  
Format: `todo DESCRIPTION_OF_TASK`  

Examples:  
  - `todo Buy rocket fuel from the supermarket`
  - `todo Make sure the O-rings are up to scratch`
  
#### 3.1.2 Deadline
Add tasks that need to be completed by a certain date.  
Format: `deadline DESCRIPTION_OF_TASK /by dd/MM/YYYY HHmm`  

Examples:  
  - `deadline Assemble the astronaut team /by 08/08/2043 2359`
  - `deadline Make sure all personnel are actually on-board /by 17/02/2050 1000`
  - `deadline Go to the Moon /by 17/02/2050 1800`
  
#### 3.1.3 Event  
Add tasks that need to be done at a certain time.  
Format: `event DESCRIPTION_OF_TASK /at dd/MM/YYYY HHmm`  

Examples:  
  - `event Launch the rocket /at 17/02/2050 1500`
  - `event Hide the evidence and silence the reporters /at 17/02/2050 1830`
       
### 3.2 Listing out all tasks: `list`
Shows a list of all tasks in the current list.  
Format: `list`

### 3.3 Marking tasks as done: `done`
Marks a task in the list as done using its index. You may use the `list` command to view all current tasks and their respective indexes.  
Format: `done INDEX_OF_TASK_IN_LIST`  

Examples:  
  - `done 2`
  - `done 5`

### 3.4 Finding a task by description: `find`
Searches and displays a list of tasks in Duke whose description matches the supplied keyword. 
  - The search is case-insensitive. eg. `corpses` will match `CoRpSes`
  - Only the description of the tasks are searched.
  - As long as the keyword is present anywhere in the task description, it will be a search result eg. `off` will match `takeoff failure`

Format: `find KEYWORD`

Examples:
  - `find rocket`
  - `find debris strike`
  - `find 2003 columbia crash causes`

### 3.5 Deleting a task: `delete`
Deletes a task in the list by its index. You may use the `list` command to view all current tasks and their respective indexes.
Format: `delete INDEX_OF_TASK_IN_LIST`

Examples:
  - `delete 5`
  - `delete 3`

### 3.6 Loading and saving task lists: `load` and `save`
These commands instruct Duke to save and load from different directories. Note that if you close Duke, it will always startup with the most recently used/saved/loaded task list.

#### 3.6.1 Specifying a new save location: `save`
Changes the save location of the current list, and then attempts a save.  
Format: `save DIRECTORY_TO_SAVE_FILE`

Examples:
  - `save case_studies/Wanderer_2050_crash.tmp`
  - `save archives/Wanderer_Crew_Memorial.tmp`
  
#### 3.6.2 Loading a list from a save location: `load`
Loads an existing saved task list from a specified path on the disk. Note that if you close Duke, if will always startup with the most recently used/saved/loaded task list.
Format: `load DIRECTORY_TO_LOAD_FILE`

Examples:
  - `load case_studies/Columbia_2003_crash.tmp`
  - `load guides/How_to_avoid_being_found_guilty_of_gross_negligence.tmp`

### 3.7 Exiting Duke: `bye`
Closes and exits the chat application. Alternatively, you may close the window by clicking the 'x' button on the top right corner.  
Format: `bye`


## 4. FAQs

**Q:** How do i transfer my data to another computer?  
**A:** Install the app in the other computer using the Quick Start guide given above. If you want to preserve the current data and state of the Duke program, transfer the /config folder over along with any saved task list files.

**Q.** Duke can't save the list to the folder I want! How do I fix this?  
**A.** Check the permissions of the folder that Duke is trying to save to. Ensure that write permissions in on.

## 5. Command Summary
- **Add Todo tasks:** `todo DESCRIPTION_OF_TASK`  
eg. `todo write eulogy for crew`

- **Add Deadline tasks:** `deadline DESCRIPTION_OF_TASK /by dd/MM/yyyy HHmm`  
eg. `deadline finish investigative report /by 17/08/2050 0900`

- **Add Event tasks:** `event DESCRIPTION_OF_TASK /at dd/MM/yyyy HHmm`  
eg. `event attend Wanderer 2050 investigative trial /at 18/08/2050 0900`

- **List:** `list`

- **Mark as Done:** `done INDEX_OF_TASK_IN_LIST`  
eg. `done 8`

- **Find:** `find KEYWORD`  
eg. `find public apology speech script`

- **Delete:** `delete INDEX_OF_TASK_IN_LIST`  
eg. `delete 16`

- **Save:** `save DIRECTORY_TO_SAVE_FILE`  
eg. `save ./me.tmp`

- **Load:** `load DIRECTORY_TO_LOAD_FILE`  
eg. `load ./alcohol_list.tmp`

- **Bye:** `bye`
