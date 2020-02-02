# Duke User Guide
### Introduction
Duke is an application for those who want to manage a personal task list with command line interface.
With Duke, users can create and retrieve tasks with various commands.
- **User guide syntax**  
In this user guide, words highlighted in gray are commands. Command argument in `()` are optional.
`|` means either the arguments on the left or the one on the right is needed. `<name>` means a range of
possible arguments.<br> Other words, without special explanation, are literally presented in the command.
  - `<index>`, `<starting index>`, `<ending index> ` expect positive integers
  - `<command>` expects `todo`, `event`, `deadline`, `exit`,`bye`, `find`, `list`
  - `<filter>` expects `index filter`, `type filter`, `status filter`, `time filter`
  - `<task type>` expects `todo`, `event`, `deadline`,
  - `<date and time>` expects `(DD/MM/YYYY) HH:MM`. If date is omitted, then duke assumes it is today's date.
  - `<time operator>` expects `before`, `until`, `on`, `from`, `after`
  - `<task description>`, `<keywords>` expect any words

### Features 
### 0. Filter
Filter is used to filter tasks by different criteria, including index, type, done status of task and 
time and date associated with the task. It is combined with Delete, Done and List command(**cannot be 
used on its own**).
- 0.1 Index Filter
  - 0.1.1 Literal Index Filter
    * Format: `<index> (<literal index filter>)`
    * Example: 
      1. `2 1 20` filter out tasks with index 1, 2, 20
      2. `4` filter out the task with index 4
   
  - 0.1.2 Range Index Filter
    * Format: `[(<starting index>):(<ending index>)]`
    * (Starting index is inclusive. Ending index is exclusive. 
       If `<starting index>` is omitted, then it starts from the first task by default.
       If `<ending index>` is omitted, then it ends at the last task by default.)
    * Example: 
      1. `[5:10]` filter out tasks with index 5-10
      2. `[:20]` filter out tasks with index 1-20
      3. `[:]` all tasks
    

- 0.2 Type Filter
  - Format: `/type <task type>`
  - Example: `/type event` filter out all event tasks

- 0.3 Status Filter
  - Format: `/done <true | false>` 
  - Example: `/done true` filter out all tasks with status of done
  
- 0.4 Time Filter
  - Format: `/<time comparator> <date and time>`
  - `<time comparator>` includes `before`, `until`, `on`, `from`, `after`
    <br>before and after excludes tasks with `<date and time>`
    <br>until and from includes tasks with `<date and time>`
  - Example: 
    1. `/before 19:00` filter out all tasks that has time before today 19:00 
    2. `/from 20/1/2020 12:00` filter out all tasks that have time after or at 20 Jan 2020 12:00

### 1. Viewing help
* Format: `help (<command> | <filter>)`
* Examples: 
  1. `help` show the general help information
  2. `help event` show the help information for event command
  3. `help index filter` show the help information for index filter

### 2. Adding a task
- 2.1 Adding a Todo task
  - Format: `todo <task description>`
  - Examples: `todo buy apples` add a todo task with description 'buy apples'

- 2.2 Adding an Event task
  - Format: `event <task description> /at <date and time>`
  - Examples: `event family dinner /at 31/12/2019 17:00` add an event task with description 'family dinner'
  at 31 Dec 2019 17:00
  
- 2.3 Adding a Deadline task
  - Format: `deadline <task description> /by <date and time>`
  - Examples: `deadline finish milk /by 30/11/2019 23:59` add a deadline task with description 'finish milk' whose
  deadline is 30 Nov 2019 23:59
  
### 3. Deleting a task
* Format: `delete <filter>`
* Example: 
  1. `delete 2 9 1` delete tasks with index 1, 2, 9 
  2. `delete /done true` delete all tasks that are done

### 4. Setting a task status as done
* Format: `done <filter>`
* Example: 
  1. `done [:9]` set all tasks with index 1-9 as done
  2. `done /before 12:00` set all tasks that have time before today 12:00 as done

### 5. Listing tasks
* Format: `list (<filter>)`
* Index filter is not used for list command
* Example: 
  1. `list` list all tasks
  2. `list /done false` list all tasks that are not done

### 6. Finding tasks
* Format: `find <keyword>`
* Example: `find chocolate` find all tasks that include word 'chocolate'

### 7. Exiting the application
* Format: `bye`
* Example: `bye` exit the application
