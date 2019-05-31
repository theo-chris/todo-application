package taskapp.application;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class TaskManager {
	TextIO textIO = TextIoFactory.getTextIO();
	TextTerminal terminal = textIO.getTextTerminal();
	 ArrayList<Task> tasks = new ArrayList<Task>();
	ArrayList<Task> priorityTasks = new ArrayList<Task>();
	ArrayList<Task> overDueListTest = new ArrayList<Task>();
	public int getNumberOfTasks() {
		return tasks.size();
	}
	public List<Task> getTaskList(){
		return tasks;
	}
	public void numberedTaskList() {
		for (int i = 0; i < tasks.size(); i++)
		{
		
			int trueDisplay = i +1;
			terminal.println("Task number: " + trueDisplay);
			terminal.println("Task name :" +tasks.get(i).getName());
			terminal.println("Tasks description:" + tasks.get(i).getDescription());
		
		}
	}
	
	public void addTask(String name, String desc) {
		Task taskManager = new Task(name, desc);
		tasks.add(taskManager);
	}
	
	 
	
	public void displayTasks() {
		int counter = 1;
		for (Task task : tasks)
		{
			terminal.println("Task number: " +counter);
			terminal.println("Task name: " +task.getName());
			terminal.println("Tasks description: " + task.getDescription());
			
			
			
			counter ++;
		}
		if (tasks.isEmpty()) {
			terminal.println();
			terminal.println("There are no tasks in the list.");
		}
	}
	public int showIncomplete() {
		int counter = 0;
		for (Task task : tasks)
		{
			
			if (task.getComplete() == false) {
				
				counter ++;
			}
			
			
		}
		return counter;

		
		
	}
	public void showTasksStatus() {
		int counter = 1;
		for (Task task : tasks)
		{
			String status;
			if (task.getComplete() == true) {
				
			
				status = "Completed";
				
			}
			
			else 
				status = "Uncompleted";
			terminal.println("Task number: " +counter);
			terminal.println("Task name: " +task.getName());
			terminal.println("Tasks description: " + task.getDescription());
			
			
			
			terminal.println("Status: " + status);
			
			
			terminal.println();
			counter ++;
		}
		if (tasks.isEmpty()) {
			terminal.println();
			terminal.println("There are no tasks in the list.");
		}
	}
	public void saveTasks() {
		try {
			
			PrintWriter writer = new PrintWriter("savedTasks.txt");
			
		for (Task task : tasks)
		{
			
			writer.println("Task name:"+ task.getName());
			
			
			writer.println("Tasks description:" + task.getDescription());
			
			
		}
		
			writer.close();
		
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadFile() {
	

		try{
  
  FileInputStream fstream = new FileInputStream("savedTasks.txt");
  
  
  DataInputStream in = new DataInputStream(fstream);
  BufferedReader br = new BufferedReader(new InputStreamReader(in));
  String strLine;
  
  while ((strLine = br.readLine()) != null)   {
 
	  Task task = new Task();
     String[] splitted = strLine.split(":"); 
     task.setName(splitted[1]);
     strLine = br.readLine();
     splitted = strLine.split(":");
     task.setDescription(splitted[1]);
     tasks.add(task);
  }
  
  in.close();
    }catch (Exception e){
  System.err.println("Error: " + e.getMessage());
  }
		
	}
	public  boolean validDueDate(String dueDate) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		df.setLenient(false);
		
		try {
			Date checkDate =df.parse(dueDate);
			return true;
		}catch(Exception e){
			
			return false;
		}
	}
	public void displayDetails(int counter) {
		
		Task task = new Task();
		task =getTaskList().get(counter -1);
			terminal.println("Task number: " +counter);
			terminal.println("Task name: " +task.getName());
			terminal.println("Tasks description: " + task.getDescription());
			terminal.println("Tasks due date: " + task.getDueDate());
			terminal.println("Tasks priority: " + task.getPriority());
			terminal.println("Tasks tag: " + task.getTag());
			String status;
			if (task.getComplete() == true) {
				
			
				status = "Completed";
				
			}
			
			else 
				status = "Uncompleted";

			terminal.println("Tasks status: " + status);
			
			
			
			
		
		if (tasks.isEmpty()) {
			terminal.println();
			terminal.println("There are no tasks in the list.");
		}

	}
	
	public void removeCompleted() {
		
		for(Iterator<Task> iterator = tasks.iterator();iterator.hasNext(); )
		{
		
			
			Task task = iterator.next();
			if(task.getComplete()) {
				iterator.remove();
			}
			
			
		}
	}
	public void sortByDueDate() {
		
	
		 ArrayList<Task> listByDate = new ArrayList<Task>(tasks);
		 
	Collections.sort(listByDate, new Comparator<Task>(){
		
		
			public int compare(Task t1, Task t2)
			{
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				
				try {						//|| t2.getDueDate() == "No due date added yet."
					if(t1.getDueDate() == "No due date added yet." 
							){
						return 1;
					}
					else if (t2.getDueDate() == "No due date added yet.") {
						return -1;
					}
					else {
					return formatter.parse(t1.getDueDate()).compareTo(formatter.parse(t2.getDueDate()));
					}
				}
				catch(ParseException e){
					e.printStackTrace();
				}
				
			
				return 0;
			}
		});
	
	int counter = 1;
	for (Task task : listByDate)
	{
		terminal.println("Task number: " +counter);
		terminal.println("Task name: " +task.getName());
		terminal.println("Tasks description: " + task.getDescription());
		terminal.println("Tasks due date: " + task.getDueDate());
		terminal.println();
		
		counter ++;
	}
	if (tasks.isEmpty()) {
		terminal.println();
		terminal.println("There are no tasks in the list.");
	}
	
	}
	
	public void editDescription(int i,String newDescription) {
		tasks.get(i).setDescription(newDescription);
	}
	public void sortByPriority() {
		int counter = 1;
		for (int a = 0; a < tasks.size(); a++) {
			
			if (tasks.get(a).getPriority() == "Very High") {
			terminal.println("Task number: " +counter);
			terminal.println("Task name: " +tasks.get(a).getName());
			terminal.println("Tasks description: " + tasks.get(a).getDescription());
			terminal.println("Tasks priority: " + tasks.get(a).getPriority());
			terminal.println();
			counter ++;
			priorityTasks.add(tasks.get(a));
			}
		}
		for (int b = 0; b < tasks.size(); b++) {
			if (tasks.get(b).getPriority() == "High") {
				terminal.println("Task number: " +counter);
				terminal.println("Task name: " +tasks.get(b).getName());
				terminal.println("Tasks description: " + tasks.get(b).getDescription());
				terminal.println("Tasks priority: " + tasks.get(b).getPriority());
				terminal.println();
				counter++;
				priorityTasks.add(tasks.get(b));
			}
		}
		for (int c =0;c <tasks.size(); c++) {
			if (tasks.get(c).getPriority() == "Medium") {
				terminal.println("Task number: " +counter);
				terminal.println("Task name: " +tasks.get(c).getName());
				terminal.println("Tasks description: " + tasks.get(c).getDescription());
				terminal.println("Tasks priority: " + tasks.get(c).getPriority());
				terminal.println();
				counter++;
				priorityTasks.add(tasks.get(c));
			}
		}
		for (int d =0;d <tasks.size(); d++) {
			if (tasks.get(d).getPriority() == "Low") {
				terminal.println("Task number: " +counter);
				terminal.println("Task name: " +tasks.get(d).getName());
				terminal.println("Tasks description: " + tasks.get(d).getDescription());
				terminal.println("Tasks priority: " + tasks.get(d).getPriority());
				terminal.println();
				counter++;
				priorityTasks.add(tasks.get(d));
			}
		}
		for (int e =0;e <tasks.size(); e++) {
			if (tasks.get(e).getPriority() == "No priority set yet.") {
				terminal.println("Task number: " +counter);
				terminal.println("Task name: " +tasks.get(e).getName());
				terminal.println("Tasks description: " + tasks.get(e).getDescription());
				terminal.println("Tasks priority: " + tasks.get(e).getPriority());
				terminal.println();
				counter++;
				priorityTasks.add(tasks.get(e));
			}
		}
		
		
		
		
	}
	
	
	public void displayOverdue() {
		LocalDate currentDate = LocalDate.now();
		SimpleDateFormat dueFormatter = new SimpleDateFormat("dd/MM/yyyy");
		DateTimeFormatter curTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
		
		ArrayList<Task> overDueList = new ArrayList<Task>();
		int counter = 1;
		Date taskDueDate,newCurrentTime;
		for (Task task : tasks) {
			
			try {
				
			taskDueDate = dueFormatter.parse(task.getDueDate());
			newCurrentTime = dueFormatter.parse(curTimeFormatter.format(currentDate));
				
			if (!(task.getComplete())  && taskDueDate.before(newCurrentTime) ){
				overDueList.add(task);
				overDueListTest.add(task);
				
			}
			
		
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		for (Task overDueTask : overDueList) {
			
			terminal.println("Task number: " +counter);
			terminal.println("Task name: " +overDueTask.getName());
			terminal.println("Tasks description: " + overDueTask.getDescription());
			String status;
			if (overDueTask.getComplete() == true) {
				
			
				status = "Completed";
				
			}
			
			else 
				status = "Uncompleted";
			
			terminal.println("Tasks status: " + status);
			terminal.println("Tasks due date: " + overDueTask.getDueDate());
			counter ++;
		}
		if (overDueList.isEmpty()) {
			terminal.println("There are no overdue tasks.");
		}
	}
	public void addTag(int aTask, String aTag) {
		 tasks.get(aTask).setTag(aTag);
		
           
	}
	public void removeTag(int aTask) {
		if (tasks.get(aTask).getTag() != "No tag added yet.")
		tasks.get(aTask).setTag("No tag added yet.");
	}
	public void displayTagged() {
		
		int counter =1;
		 for (Task task: tasks) {
   		  
   		  		terminal.println("Task number: " +counter);
     			terminal.println("Task name: " +task.getName());
     			terminal.println("Tasks description: " + task.getDescription());
     			terminal.println("Tasks due date: " + task.getDueDate());
     			terminal.println("Tasks priority: " + task.getPriority());
     			terminal.println("Tasks tag: " + task.getTag());
     			counter++;
   	  
	}
	
	
	}
	
}
