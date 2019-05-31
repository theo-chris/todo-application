package taskapp.application;

import org.beryx.textio.*;
import java.util.*;

public class Main {
  public static void main(String[] args) {
    
    TextIO textIO = TextIoFactory.getTextIO();
    TextTerminal terminal = textIO.getTextTerminal();
   

    terminal.println("Welcome to our Application!");
   
    TaskManager taskManager = new TaskManager();
    
    boolean quit = false;

    while (!quit) {
    	
      terminal.println(); // blank line for better formatting
      
      TodoCommand command = textIO.newEnumInputReader(TodoCommand.class)
              .withAllValuesNumbered()
              .read("Choose Command?");

      switch (command) {
      case Add:
    	  while(true) {
    	  String name = textIO.newStringInputReader().read("Name of task: ");
    	  String desc = "No description added.";
    	  
    	  labelDesc:
    		  while (true) {
    			String decision=  textIO.newStringInputReader().read("Do you wish to add a description of the task? [Y/N] ");
    			switch(decision)
    			{
    			case "Y":
    				desc = textIO.newStringInputReader().read("Please add a description");
    				break labelDesc;
    			case "N":
    				break labelDesc;
    			default:
    				terminal.println("Please type Y for yes or N for no.");
    			}
    				
    		  }
    	  taskManager.addTask(name,desc);
          break ;
    	  }
    	  break;
      case Display:
    	  
          taskManager.displayTasks();
      
      		
          break;
      case TotalTasks:
  		
  		terminal.println("Number of tasks in the list: " + taskManager.getNumberOfTasks());
  		break;
      case Save:
    	taskManager.saveTasks();
    	terminal.println("Your tasks have been saved successfully in the file 'savedTasks.txt'");
    	break;
    	
      case Delete:
    		  
	    if (taskManager.getTaskList().isEmpty())
	    	  {
	    		  terminal.println("No tasks in the list to delete. You need to add tasks in the list first.");
	    	  }
	     else {
	    		  
	     while(true) {
	    	taskManager.numberedTaskList();
	    	int taskToDelete =  textIO.newIntInputReader().read("Which task do you wish to delete?(Write its number)");
	    	terminal.println();
	    	
	    	if (taskToDelete >=1 && taskToDelete <= taskManager.getNumberOfTasks())
	    	{
	    		taskManager.getTaskList().remove(taskToDelete - 1);
	    		terminal.println("Task " + taskToDelete + " successfully deleted");
	    		break;
	    	}
	    	terminal.println("You need to select a valid task to delete.");
	        }  
	     }
	     break;
	     
      case Load:
    	
    	  taskManager.loadFile();
    	 
    	  terminal.println("Loaded file successfully!");
    	  break;
      case CompleteTask:
    	  terminal.println();
    	  if (taskManager.getTaskList().isEmpty()) {
    		  terminal.println("No task in the list to mark as completed.");
    		  break;
    	  }else {
    		  while(true) {
    			  taskManager.showTasksStatus();
    			  terminal.println();
    		  
    		  int toComplete = textIO.newIntInputReader().read("Please select the task you want to complete.");
    		  		terminal.println();
    		  if (toComplete >=1 && toComplete <= taskManager.getNumberOfTasks()) {
    			  if (taskManager.getTaskList().get(toComplete -1).getComplete() == true) {
    				  terminal.println();
        			  terminal.println("The task you chose is already marked as completed.");
        			  break;
        		  }  
    		  
    			  terminal.println("Task marked as completed.");
    			  taskManager.getTaskList().get(toComplete -1).setComplete(true);
    			  break;
    		  }
    			  
    		  terminal.println("You need to select a valid task to complete.");
    		  
    		  }
    		 
    	  }
    	  break;
      case ShowComplete: 
    	  
    	  taskManager.showTasksStatus();
    	  break;
      case TotalIncomplete:
    	 terminal.println("The number of uncompleted tasks is: " +taskManager.showIncomplete());
    	  break;
      case DueDate:
    	  if (taskManager.getTaskList().isEmpty())
    	  {
    		  terminal.println("No tasks in the list to add a due-date for. You need to add tasks in the list first.");
    	  }
    	  else {
    		  
    	  
    	  while (true) {
    		  
    		  
    	  taskManager.numberedTaskList();
	    	int taskDueDate =  textIO.newIntInputReader().read("On which task do you wish to add due date?");
	    	terminal.println();
	    	if (taskDueDate >=1 && taskDueDate <= taskManager.getNumberOfTasks())
	    	{
	    		
	    		 String dueDate =  textIO.newStringInputReader().read("Enter the due date of the task");
	    		if (taskManager.validDueDate(dueDate)) {
	    			
	       		taskManager.getTaskList().get(taskDueDate -1).setDueDate(dueDate);
	    		terminal.println("Added due date successfully.");
	    		}
	    		else {
	    			terminal.println("Invalid format of date.");
	    		}
	    		break;
	    	}
	    	
	    	terminal.println("Please select a valid task.");
    	  }
    	  }
    	  
    	  break;
    	  
      case DisplayDetails:
    	  terminal.println();
    	  if (taskManager.getTaskList().isEmpty()) {
    		  terminal.println("No tasks in the list.");
    	  }
    	  else {
    	  while (true) {
    	 taskManager.displayTasks();
    	  int specificTask = textIO.newIntInputReader().read("Which task's details do you want to see?");
    	  terminal.println();
    	  if (specificTask >= 1 && specificTask <= taskManager.getNumberOfTasks())
    	  {
    	  taskManager.displayDetails(specificTask);
    	  
    	  }
    	  else {
    		  terminal.println();
    		  terminal.println("Invalid task");
    	  }
    	  
    	 
    	  
    	 break;
    	  }
    	 
    	  }
    	 break;
    	 
    	 
      case RemoveCompleted:
    	  int numberOfTasks = taskManager.getNumberOfTasks();
    	  taskManager.removeCompleted();
    	  if (numberOfTasks > taskManager.getNumberOfTasks())
    	  terminal.println("Completed tasks have been removed from the list.");
    	  else
    		  terminal.println("There were no completed tasks in the list to remove.");
    	  break;
      case SortByDueDate:
    	  if(taskManager.getTaskList().isEmpty()) {
    		  terminal.println("There are no tasks in the list to display.");
    		  break;
    	  }else {
    	  terminal.println("Tasks listed by due date.");
    	  terminal.println();
    	  taskManager.sortByDueDate();
      
    	  break;
    	  }
      case SpecifyPriority:
    	  terminal.println();
    	  taskManager.numberedTaskList();
    	  
    	 
    	  terminal.println();
    	  if (taskManager.getTaskList().isEmpty()) {
    		  terminal.println("No tasks in the list.");
    		  break;
    	  }
    	  else {
    		  int specifyPriority = textIO.newIntInputReader().read("Which task's priority do you want to set?");
    	  if (specifyPriority >= 1 && specifyPriority <= taskManager.getNumberOfTasks())
    	  {
    		  
    		  terminal.println("1.Low");
    		  terminal.println("2.Medium");
    		  terminal.println("3.High");
    		  terminal.println("4.Very High");
    		  int priorityLevel = textIO.newIntInputReader().read("Select priority level:");
    		 
    	  taskManager.getTaskList().get(specifyPriority - 1).setTaskPriority(priorityLevel -1);
    	  terminal.println("Priority level set successfully.");
    	  
    	  }
    	  }
    	  break;
      case EditDescription:
    	  terminal.println();
    	  if (taskManager.getTaskList().isEmpty()) {
    		  terminal.println("No tasks in the list.");
    		  break;
    	  }
    	  else {
    		  while(true) {
        	  taskManager.numberedTaskList();
    		  int choice = textIO.newIntInputReader().read("Which task's description do you wish to edit?");
    		  if (choice >= 1 && choice <= taskManager.getNumberOfTasks())
        	  {
        		  terminal.println();
        		  String newDescription =
        				textIO.newStringInputReader().read("What is the new description you wish to have for this task?");
        	      taskManager.editDescription(choice -1, newDescription);
        	  }else {
        		  terminal.println("Invalid task.");
        	  }
    		  
    		  break;
        	  }
    	  }
    	  
    	  break;
      case SortByPriority:
    	  terminal.println();
    	  if (taskManager.getTaskList().isEmpty()) {
    		  terminal.println("No tasks in the list.");
    		  break;
    	  }
    	  else {
    		  terminal.println("Tasks listed by priority.");
    		  terminal.println();
    		  taskManager.sortByPriority();
    		  break;
    	  }
      case DisplayOverdue:
        	  terminal.println();
        	  if (taskManager.getTaskList().isEmpty()) {
        		  terminal.println("No tasks in the list.");
        		  break;
        	  }
        	  else {
        	  taskManager.displayOverdue();
        	  break;
        	  }
      case AddTag:
    	  terminal.println();
    	  if (taskManager.getTaskList().isEmpty()) {
    		  terminal.println("No tasks in the list.");
    		  break;
    	  }else { 
    		  while(true) {
        	  taskManager.numberedTaskList();
    		  int select = textIO.newIntInputReader().read("Select the task you would like to add a tag to: ");
    		  if (select >=1   &&  select <= taskManager.getTaskList().size()) {
    			  String actualTag = textIO.newStringInputReader().read("Enter the tag you would like to add: ");
    			 taskManager.addTag(select -1 , actualTag);
    			 terminal.println();
    			 terminal.println("Added tag successfully.");
    			  
    		  }
    		  else {
    			  terminal.println("Invalid task.");
    		  }
    		  break;
    		  }
    		  
    	  }
    	  break;
      case RemoveTag:
    	  terminal.println();
    	  if (taskManager.getTaskList().isEmpty()) {
    		  terminal.println("No tasks in the list.");
    		  break;
    	  }else { 
    		  while(true) {
    			  
        	 taskManager.displayTagged();
    		  int select = textIO.newIntInputReader().read("Select the task that you want to remove its tag: ");
    		  if (select >=1   &&  select <= taskManager.getTaskList().size()
    				  && taskManager.getTaskList().get(select -1).getTag() != "No tag added yet." ) {
    			
    			  
    			taskManager.removeTag(select-1);
    			terminal.println();
    			terminal.println("Removed tag successfully.");
    			  
    			  
    		  }
    		  else {
    			  terminal.println("Invalid task.");
    		  }
    		  break;
    		  }
    		  
    	  }
    	  break;
      case EXIT:
            terminal.println("Bye!");
            quit = true;
            System.exit(0);
            break;
           
       
        		
       
       
      }
    }
  }

  public enum TodoCommand {
   Add, Display,TotalTasks,Save,Delete,Load,CompleteTask,ShowComplete,TotalIncomplete,DueDate
   ,DisplayDetails,RemoveCompleted,SortByDueDate,SpecifyPriority,EditDescription,SortByPriority,DisplayOverdue,AddTag
   ,RemoveTag,EXIT
  }
}