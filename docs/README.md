# The Jedi Academy - User Guide
![Yoda](/src/main/resources/images/Yoda.png)

## 1. Introduction
_**The Jedi Academy**_ is a Personal Assistant Chatbot that helps a person to keep track of various things. _**The Jedi Academy**_ is optimized for those who prefer to work with a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). Continue reading to find out more about its features and usage!

## 2. Getting Started
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest _**The Jedi Academy**_ jar file here.
3. Copy the file to the folder you want to use as the home folder. Your tasks will be saved to and loaded from `data/duke.txt` located in the same folder.
4. Double-click the file to start the app. The GUI should appear in a few seconds.
5. Type the command in the command box and press Enter to execute it.
6. Refer to Section 3, “Features” for details of each command.

## 3. Features 
### Command Format
* Words in `UPPER_CASE` are the parameters to be supplied by the user

### 3.1. Listing all Tasks: `list` 
Lists all Tasks saved in hard drive currently    
* Usage Format:     
`list`    
&nbsp;

### 3.2. Adding Todo Task: `todo`     
Adds a Todo Task with the supplied Task Description     
* Usage Format:         
`todo {TASK}`    
* Example Usage:    
`todo cartwheel to school`  
&nbsp;

### 3.3. Adding Deadline Task: `deadline` 
Adds a Deadline Task with the supplied Task Description and DateTime    
* Usage Format:      
`event {TASK} /by {DATETIME}`    
* Example Usage:    
`deadline assignment /by 14/07/2019 2359`  
&nbsp;

### 3.4. Adding Event Task: `event`     
Adds a Event Task with the supplied Task Description and DateTime    
* Usage Format:       
`event {TASK} /at {DATETIME}`
* Example Usage:     
`event diving competition /at 21/07/2019 1400`    
&nbsp;


### 3.5. Marking Task as Done: `done`  
Marks a Task at the specified index in the task list as Done    
* Usage Format:     
`done {INDEX}`    
* Example Usage:    
`done 2`    
&nbsp;

### 3.6. Deleting Task: `delete` 
Deletes a Task at the specified index in the task list    
* Usage Format:     
`delete {INDEX}`    
* Example Usage:    
`delete 3 `   
&nbsp;

### 3.7. Finding Task: `find` 
Finds the Tasks whose Description contains the supplied KEYWORD    
* Usage Format:     
`find {KEYWORD}`    
* Example Usage:    
`find something`    
&nbsp;

### 3.8. Exiting the Program: `bye` 
Exits the Program    
* Usage Format:     
`bye`    
&nbsp;

### 3.9. Saving/Loading Tasks
Tasks are saved in the hard disk automatically after any command that changes the task list.    
Existing task list is also loaded from the hard disk automatically when _**The Jedi Academy**_ starts up.    
There is no need to save manually.      
&nbsp;
  
### 3.10. Detecting duplicate Tasks 
Throws an error message when user tried to add a Task with an existing Task description    
`"OOPS!!! Duplicate task already exists!"`    
&nbsp;
 
## 4. Command Summary
* **List**: `list`
* **Todo**: `todo TASK`
* **Deadline**: `deadline TASK /by DATETIME`
* **Event**: `event TASK /at DATETIME`
* **Done**: `done INDEX`
* **Delete**:`delete INDEX`
* **Find**: `find KEYWORD`
* **Bye**: `bye`
