package taskapp.application;



public class Task {

	String name;
	String description;
	boolean complete;
	String dueDate = "No due date added yet.";
	String priority= "No priority set yet.";
	String tag = "No tag added yet.";
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public boolean getComplete() {
		if (complete == true)
			return true;
		else
			return false;
	}
	public void setComplete(boolean markComplete) {
		if (markComplete == true)
			this.complete = true;
		else
			this.complete = false;
		
	   
		
	}
    public void setTaskPriority(int level) {
    	String[] priority =  new String[4];
		priority[0] = "Low";
		priority[1] = "Medium";
		priority[2] = "High";
		priority[3] = "Very High";
		if (level == 0 ) {
			this.priority = priority [0];
		}
		else if(level == 1) {
			this.priority = priority [1];
			
		}
		else if( level == 2) {
			this.priority = priority[2];
		}
		else if (level == 3) {
			this.priority = priority[3];
		}
		
		
		
		
		
	}
    public String getPriority() {
    	return priority;
    }
	
	
	
	public Task() {
		name = "";
		description = "";
	}
	
	public Task(String nameInput, String descInput) {
		name = nameInput;
		description = descInput;
	}
	
}
