# Task Manager User Guide

## Add a task with a description

To add a task, users have to select the "Add" option in the menu. After that, the terminal will print "Name of new task:" to ask users enter the name of the task. If users failed to enter anything in the terminal users will not be able to get to the next stage(add a description). After users entered the name of the task, the terminal will display "Do you wish to add a description? [Y/N]". If users want to add a description they need to enter "Y" in the terminal, if users want to create a task without a description users can enter "N". Users must to select one option between "Y" and "N" in order to move to the next stage. Once users enter "Y", the terminal will print "Please add a description:" and users can enter their description. After users entered the description, the program will prompts users to the menu.
The program will only exit when users entered "Exit".

## To list all tasks

After a user created a new task, the terminal will automatically displays the whole list of tasks that have been created. If users want to list all tasks users simply need to select the "Display" option in the menu. 

## See how many tasks are in the list

To see the number of tasks in the list, users need to select the "TotalTasks" option in the menu. After users selected this option, the terminal will display the total tasks of the list immediately. 

## To save tasks
To save new tasks, users need to select the "Save" option in the menu after they created new tasks. As users selected the "Save" option, the system will automatically save all the new tasks that has been added and print "Your tasks have been saved successfully in the file 'savedTasks.txt'".

## To delete tasks

To delete a task, users have to select the "Delete" option in the menu. Once the users selected the "Delete" option, users will see the list of tasks they created and users will be asked to select a task that want to delete by entering its task number. After users entered the task number, the task will be deleted.
If users have not created any task, users will not be able to delete anything from the file until they created a task.

## To load tasks

When users reopen the application, they have to select the "Load" option in the menu to restore all the tasks they have saved. If users did not select the "Load" option, they will not be able to see any tasks when they execute the "Display" option. The "Load" option can only be used once every time they reopen the application. If users selected the "Load" option twice, all the tasks in the list will display twice.
If users accidentally entered the "Load" option twice, they just need to reopen the application and load the tasks again. 
If  users accidentally entered the "Load" option twice and entered the "Save" option, they need to use the "Delete" option to delete the extra tasks.

## To mark a task as completed

To mark a task as completed, users need to choose the "CompleteTask" option in the menu. When users entered this option, they will be ask to select a task that they want to mark as completed. After users entered the number of the task, they will return to the menu. To mark more tasks, users have to keep select the "CompleteTask" option.
If there are no tasks are created or loaded, terminal will display “No task in the list to mark as complete”.

## To see how many uncompleted tasks are in the list

To see how many uncompleted tasks are in the list, users just need to select the "TotleInComplete" option in the menu, and the terminal will automatic display the the number of total uncompleted tasks.

## To specify the due date for a task

To set a due date for a task, users have to select the "DueDate" option in the menu. Then users will be asked to select a task and then enter the due date for it. When entering the date, users must to ensure that the date is in the correct format “DD/MM/YYYY” (e.g. 11/12/2017). Once the date has been added successfully, the system will return to the menu.

## To view all details for a task

To view all details for a task, users have to select the "DisplayDetails" option in the menu. After entered this option, users simply need to enter the task number of the task they want to see to display all detail of that tasks.
If there are no tasks created, the terminal will display "No tasks in the list".

## To remove all completed tasks from the list

To remove all completed tasks from the list, users have to select the "RemoveCompleted" option in the menu. After entered this option, the terminal will remove all the completed tasks from the list and print "Completed tasks have been removed from the list".

## To list tasks in due date order

To list tasks in due data order, users have to select the "SortByDueDate" option in the menu. Once entered this option the terminal will display the tasks in due date order.
If users have not set the due date for some tasks, these tasks will display at the bottom of the list and these tasks will be ordered according to their task number (in ascending order).
Users can identify the tasks that do not have a due date through the text "No due date added yet."

## To specify the priority of a task

To specify the priority of a task, users have to select the "SpecifyPriority" option in the menu. After entered this option, users will be asked to select a task. After users selected a task, they will be asked to set the priority level (with given options: "Low", "Medium", "High", and "Very High") for that task. 
Once users selected an option, they can check it through the "DisplayDetails" option in the menu.

## To edit the description of a task

To edit the description of a task, users have to select the "EditDescription" option in the menu. After entered this option, users will be asked to select a task that they want to edit. After user selected a task they can enter the new description for it.
User can view the new edited description through the "Display" option in the menu.

## To list tasks in priority order

To list tasks in the priority order, users have to select the "SortByPriority" option in the menu. Once entered this option, the terminal will display the tasks according to their priority level(From Very High to Low).
The non-prioritised tasks will be displayed on the bottom of the list.
User can identify non-prioritised tasks through the text "No priority set yet."

## To list all overdue tasks

To list all overdue tasks, users have to select the "DisplayOverDue" option in the menu. Once entered this option, the terminal will display all the overdue tasks with all task's details.
The Tasks that do not have a due date will not be displayed in the list.

### Note: To select an option, please enter the number of that option.
### Note: Remember to save all your tasks before you quit the application and after you launch the application, load your tasks first.
