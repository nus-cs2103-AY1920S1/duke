# Duke - User Guide    

![Duke Logo]()
![Duke](https://raw.githubusercontent.com/charliechoong/duke/master/resources/images/theDuke.png)  

**Table of Contents :**  
- [1. Introduction](#1-introduction)  
- [2. Features](#2-features)  
    - [2.1 Feature 1: Task category](#21-feature-1-task-category)
    - [2.2 Feature 2: Saving and loading tasks](#22-feature-2-saving-and-loading-list)
- [3. Commands](#33-commands)  
    - [3.1 Adding a task](#31-adding-a-task)  
    - [3.2 Listing all tasks](#32-listing-all-tasks-list)
    - [3.3 Marking a task as done](#33-marking-a-task-as-done--done-)
    - [3.4 Deleting a task](#34-deleting-a-task--delete)
    - [3.5 Filtering tasks using a keyword](#35-filtering-tasks-using-keyword-find)
    - [3.6 Prioritising a task](#36-prioritizing-a-task---1-2-3)
    - [3.7 Exiting the program](#37-exiting-the-program---bye)
- [4. Command Summary](#4-command-summary)
- [5. Frequently Asked Questions](#5-faq)

## 1. Introduction
   Duke is an application for users to manage their tasks in life. It uses simple commands and
   is optimized for users who **prefer to use desktop app to manage tasks**. 
## 2. Features 
   To utilise the features, users simple type in appropriate commands and Duke will carry out.
   The features are as listed below:
### 2.1. Feature 1: Task Category  
   Duke categorised tasks into 3 types:
   - **To-do**   
   These tasks are things that the users would plan to 
     do. 
        
   - **Deadline**   
   These tasks are similar to **To-do** tasks, but they have deadlines. 
            
   - **Event**  
   These tasks are events that users would like to keep track of.  
        
### 2.2. Feature 2: Saving and loading list  
   Duke allows for loading and saving tasks from and to 
   hard disk. This means that users can exit the application
   and still have the updates list of task when they use
   it again. 
    
## 3. Commands
   The commands are as listed below : 

### 3.1. Adding a task : `add`
   This command allows you to add any of the 3 types of tasks to 
   list. The full command differs for all 3 types.

*Format for Todo task: `todo [description]`*
* __Example of adding a *Todo* task__:  
    `todo go for tutorial`
* __Expected outcome__:  
   ```
   Got it. I've added this task:            
     [T][-] go for tutorial
   Now you have 1 task in the list.
   ```
*Format for Deadline task: `deadline [description] /by [deadline]`*
* __Example of adding a *Deadline* task__:  
    `deadline watch 10 episodes of Running Man /by 3rd aug`
* __Expected outcome__:
    ```
    Got it. I've added this task:
      [D][-] watch 10 episodes of Running Man (by: 3rd aug)
    Now you have 2 tasks in the list.
    ```
*Format for Event task: `event [description] /at [event time]`*
* __Example of adding an *Event* task__:  
   `event presentation /at 9aug`  
   
* __Expected outcome__:  
    ```
    Got it. I've added this task:  
     [E][-] presentation (at: 10aug)
    Now you have 3 tasks in the list.
    ```
### 3.2. Listing all tasks: `list` 
This command prints out the list of tasks and their 
corresponding details in the list. 

* __Example of usage__:  
    `list`
    
* __Expected outcome__:  
    ```
    Here are the tasks in your list:
        1.[T][-] go for tutorial
        2.[D][-] watch 10 episodes of Running Man (by: 3rd aug)
        3.[E][-] presentation (at: 10aug)
        4.[D][-] throw mum off building :) (by: 3 sept 2090)
    ```
### 3.3. Marking a task as done : `done `
This command allows the user to mark a task as done.  
 
*Format: `done [taskIndex]`*
* __Example of usage__:  
    `done 3`  
    
* __Expected outcome__:  
    ```
    Nice! I've marked this task as done:     
     [+] presentation
    ```
    
### 3.4. Deleting a task : `delete`
This command allows the user to delete a task.  

*Format: `delete [taskIndex]`*
* __Example of usage__:  
    `delete 4`  
    
* __Expected outcome__":  
    ```
    Noted. I've removed this task:     
      [D][-] throw mum off building :) (by: 3 sept 2090)
    ```
### 3.5. Filtering tasks using keyword: `find`
This command allows the user to list out all tasks containing a
keyword.

*Format: `find [keyword]`*
* __Example of usage__:  
    `find 10`
    
* __Expected outcome__:  
    ```
    Here are the matching tasks in your list:
        1.[D][-] watch 10 episodes of Running Man (by: 3rd aug)
        2.[E][-] presentation (at: 10aug)
    ```
    *This command lists out all the tasks containing the keyword `10`.*
### 3.6. Prioritizing a task :  `#1` `#2` `#3`
This command allows the user to prioritise a task. `#1` represents high 
priority. `#2` represents medium priority. `#3` represents low
priority.    

*Format for setting as HIGH priority: `#1 [taskIndex]`
* __Example of usage__:  
    `#1 2`  
    
* __Expected outcome__:  
    ```
    Alright! Master Duke has set priority HIGH for this task:
      [T][+] go for tutorial <<Priority: HIGH>>
    ```  
    *This sets the second task with a HIGH priority.*

### 3.7. Exiting the program :  `bye`  
This command allows the user to exit the Duke application.
* __Example of usage__:  
    `bye`
    
* __Expected outcome__:  
    `Farewell detected. Duke turning off...zzz`

## 4. Command Summary

* Add : `[taskType] [description] ...`  
   * Todo: `todo [description]`
        * eg. `todo buy gift`
        
   * Deadline: `deadline [description] /by [deadline]`
        * eg. `deadline assignment /by 2pm`
        
   * Event: `event [description] /at [eventTime]`
        * eg. `event Justin Bieber's concert /at 08/02/1996` 
        
* Delete : `delete [taskIndex]`
    * eg. `delete 10`
    
* Mark as done : `done [taskIndex]`
    * eg. `done 2`
     
* Exit : `bye`

* Find : `find [taskIndex]`
    * eg. `find run` , `find 16aug`
* List : `list`

* Prioritise: `#[priorityNumber] [taskIndex]`
   * eg. `#2 3` , `#1 1` , `#3 1`  
   
## 5. FAQ
* Q: *Why doesn't my list load correctly when I re-opened the application?*  
    * You have to exit the program correctly (ie. with the "bye" command) in order for the application to update
      on the hard disk.  
      
* Q: *Why can't I add the Event/Deadline task?*
    * Please ensure that you followed the correct format. Please refer to [3.1 Adding a task](31-adding-a-task--add).