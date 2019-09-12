# User Guide
Duke is a friendly event scheduler that helps you keep track of your outstanding tasks.

## Features 
- Ability to add Tasks of type -ToDo, Deadline and Event.
- Ability to list your tasks and their respective status - Done/Undone.
- Update your task status.
- Delete tasks that you no longer need to keep an eye on.

Command | Note | Result | Example
--------|--------|---------|---------
todo _Task_ |    |Adds todo to scheduler, status of task is undone | todo praise Duke
event _Task_ /at _description_ |    | Adds event to scheduler, status of task is undone with task description | event celebrate Mike's birthday /at 2am - 4pm
deadline _Task_ /by _description_ | Formatting _description_ as (d)d/(m)m/yyyy hhmm is formatted by Duke to more readable format. | Adds a deadline to scheduler, status of task undone with task description. | deadline go to gym /by 2/3/2020 1400
done _TaskNumber_ |  | Sets the status of task at number TaskNumber to be completed. | done 3
delete _TaskNumber_ |  | Removes the task at TaskNumber from the scheduler | delete 5
find _Task_ |  | Lists the tasks in the scheduler which has the keywords specified in _Task_ | find gym
bye |  | Closes Duke.  