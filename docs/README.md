# User Guide

![Image of Ui](https://github.com/bitterg0d/duke/blob/master/docs/Ui.png?raw=true)
## Features 
### 1. Duke can help you track tasks such as:
* To-Dos
* Events
* Deadlines

### 2. Find tasks using keywords
### 3. Delete tasks
### 4. Mark as done

## Usage
- `bye` - Exits programme.

- `todo <description>` - Adds a todo task with description.

- `event <description> /at <date> <time>` - Adds an event with description and a given start time.
    - Example of usage: `event wedding /at 12/12/12 2359`

- `deadline <description> /by <date> <time>` - Adds a deadline with description and a given time that task ends.
    - Example of usage: `deadline cs2103t user guide /by 12/12/12 2359`
- `list` - Lists all the tasks that are currently stored in the programme.

- `delete <task number>` - Deletes a task from the list.
    - Example of usage: `delete 3`
    
- `done <task number>` - Marks a task from the list as done.

- `find <keyword>` - Searches for tasks that match the given keyword.
    - Example of usage: `find wedding`
    
- `help` - Provides user assistance by listing all available commands.
    - Expected outcome:
    ```
    Hello, I'm Duke!
        I can keep track of your to-dos, deadlines and events. Use these commands to control me:
         - todo [desc]
         - event [desc] /at [date time]
         - deadline [des] /by [date time]
         - list
         - bye (exits duke)
         - find [keyword]
         * date format - DD/MM/YYYY
         * time format - 0000
    ```