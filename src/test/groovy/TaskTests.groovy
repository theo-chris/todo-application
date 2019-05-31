import java.util.concurrent.atomic.DoubleAdder
import java.util.prefs.AbstractPreferences.NodeRemovedEvent

import spock.lang.Specification
import taskapp.application.*

// Need to import any other classes we want to test...


class TaskTests extends Specification {

  def "Example test"() {

    given:
    int value1 = 1
    when:
    int value2 = 2
    then:
    value1 != value2
  }
  def "Check if the list is empty"(){
	  given:
	  def taskManager = new TaskManager()
	  
	  when: "No tasks have been added from the user"
	  def totalTasks = taskManager.getNumberOfTasks()
	  then: "There should be no tasks in the list"
	  totalTasks == 0
  }
  def "Add task test if a task is added"(){
	  given:
	  def taskManager = new TaskManager()
	  
	  when:
	  taskManager.addTask("yes", "nothing")
	  then:
	  !taskManager.getTaskList().isEmpty()
	  
  }
  def "Adding a task should display 1 in the size of the list"(){
	  given:
	  def taskManager = new TaskManager()
	  taskManager.addTask("Dishses","clean")
	  when: "One task is added"
	  def totalTasks = taskManager.getNumberOfTasks()
	  then:
	  totalTasks == 1
	  
  }
  def "Checking if the listed tasks can be displayed to the user"(){
	  given:
	  def taskManager = new TaskManager()
	  def task = new Task("Dishes","clean")
	 
	  when: 
	  taskManager.addTask("Dishes","clean")
	  taskManager.displayTasks()
	  
	  then:
	  taskManager.getTaskList().get(0).getName() == task.getName()
	  taskManager.getTaskList().get(0).getDescription() == task.getDescription()
	
	  
  }
 def "savong tasks"(){
	 given: "get list"
	 def taskManager = new TaskManager()
	
	 
	 when:"add a tasks"
	 taskManager.saveTasks()
	 File test = new File("savedTasks.txt")
	 
	 then: "show if it gets saved"
	 test.exists()
	 
 }
 def"Reading saved tasks"(){
	 given:
	 def taskManager = new TaskManager()
	
	 when:
	 taskManager.addTask("yes", "test")
	 taskManager.saveTasks()
	 File test = new File("savedTasks.txt")
	 then:
	 test.length() > 0
 }
 
 def "Deletes a task from a list"(){
	 given:
	 def taskManager = new TaskManager()
	 
	 when:
	 taskManager.addTask("Yes","test")
	 
	 then:
	 taskManager.getTaskList().size() == 1
	 taskManager.tasks.remove(0)
	 taskManager.getTaskList().size() == 0
 }
def "Loads a savedFile"(){
	given:"A list with 1 task, saved to a file"
	def taskManager = new TaskManager()
	taskManager.addTask("test","list")
	taskManager.saveTasks()
	when: "i remove the task from the list"
	taskManager.tasks.remove(0)
	then:"the list has 0 tasks, when I load the saved files though, it has 1 task inside."
	taskManager.getTaskList().size()== 0
	taskManager.loadFile()
	taskManager.getTaskList().size() == 1
	
	
}
def "Loads a file that is already saved"(){
	given:
	def taskManager = new TaskManager()
	taskManager.saveTasks()
	when:
	taskManager.loadFile()
	then:
	
	taskManager.getTaskList().size() == 0
	
}


def "A user can see how many uncompleted tasks there are in the list"(){
	given:
	def taskManager = new TaskManager()
	
	
	when:
	taskManager.addTask("Dishes", "clean")
	
	then:
	taskManager.showIncomplete() == 1
	
}

def "A user can mark a task as completed"(){
	given:
	def taskManager = new TaskManager();
	Task task = new Task("dishes", "clean")
	
	
	when:
	task.setComplete(true)
	
	then:
	taskManager.showIncomplete() == 0

}
def "A user can set a due date for a task"(){
	given:
	def taskManager = new TaskManager();
	Task task = new Task("dishes", "clean")
	
	when:
	task.setDueDate("12/12/17")
	
	then:
	task.getDueDate() == "12/12/17"
	
	
}

def "A user can view all details for a task"(){
	given:
	def taskManager = new TaskManager()
	def task = new Task("Dishes","clean")
    task.setComplete(true)
    task.setDueDate("12/12/17")
	when:
	taskManager.tasks.add(task)
	taskManager.displayTasks()
	
	then:
	taskManager.getTaskList().get(0).getName() == "Dishes"
	taskManager.getTaskList().get(0).getDescription() == "clean"
	taskManager.getTaskList().get(0).getDueDate() == "12/12/17"
	taskManager.getTaskList().get(0).getComplete() == true
	}
	
def "a user can remove completed tasks from the list"(){
	given:
	def taskManager = new TaskManager()
	def task = new Task("Dishes","clean")
	task.setComplete(true)
	taskManager.tasks.add(task)
	
	when:
	taskManager.removeCompleted();
	
	then:
	taskManager.tasks.size() == 0
	}
	def "a user can list tasks in due date order"(){
		given:
		def taskManager = new TaskManager()
		def task = new Task("Dishes", "clean")
		def task2 = new Task ("Laundry", "wash")
		task.setDueDate("12/12/18")
		task2.setDueDate("13/13/19")
		taskManager.tasks.add(task)
		taskManager.tasks.add(task2)
		
		when:
		taskManager.sortByDueDate();
	
		then:
		taskManager.getTaskList().get(0).getDueDate() == "12/12/18"
		}
	def "a user can specify the priority of a task"(){
		given:
		def taskManager = new TaskManager()
		def task = new Task("Dishes", "clean")
		task.setTaskPriority(0)
		taskManager.tasks.add(task)
		
		when:
		taskManager.tasks.get(0).setTaskPriority(1)
		
		then:
		taskManager.tasks.get(0).getPriority() == "Medium"
	}
def "a user can edit the description of a task"(){
		given:
		def taskManager = new TaskManager()
		def task = new Task("Dishes", "EDIT ME")
		taskManager.tasks.add(task)
		when:
		taskManager.editDescription(0, "edited desc")
		then:
		taskManager.getTaskList().get(0).getDescription() == "edited desc"
		
	}
def "a user can list tasks in priority order"(){
	given:
	def taskManager = new TaskManager()
	def task = new Task("Dishes", "clean")
	def task2 = new Task("Laundry", "wash")
	task.setTaskPriority(0)
	task2.setTaskPriority(3)
	taskManager.tasks.add(task)
	taskManager.tasks.add(task2)
	
	when:
	taskManager.sortByPriority();
	then:
	taskManager.priorityTasks.get(0).getPriority().equals("Very High")
	}
def "a user can list overdue tasks"(){
	given:
	def taskManager = new TaskManager()
	def task = new Task("Dishes", "clean")
	task.setDueDate("01/01/2001")
	
	when:
	taskManager.displayOverdue()
	taskManager.overDueListTest.add(task)
	
	then:
	taskManager.overDueListTest.get(0).getName().equals("Dishes")
	
	
	}
	def"a user can add a tag"(){
		given:
		def taskManager = new TaskManager()
		def task = new Task("Dishes","clean")
		
		
		
		when:
		taskManager.tasks.add(task)
		taskManager.addTag(0, "Important")
		then:
		taskManager.getTaskList().get(0).getTag().equals("Important")
	}
	def "A user can remove a tag"(){
		given:
		def taskManager = new TaskManager()
		def task = new Task("dishes","clean")
		
		
		
		when:
		taskManager.tasks.add(task)
		taskManager.addTag(0, "Deadline")
		taskManager.removeTag(0)
		
		
		then:
		taskManager.tasks.get(0).getTag().equals("No tag added yet.")
	}
}