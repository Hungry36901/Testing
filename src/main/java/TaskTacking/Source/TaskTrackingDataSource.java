package TaskTacking.Source;

import TaskTacking.Class.GeneralTask;
import TaskTacking.Class.Task;
import TaskTacking.Class.TaskList;

public class TaskTrackingDataSource {
    private TaskList taskList;

    public TaskTrackingDataSource(){
        taskList = new TaskList();
        readData();
    }
    public void readData(){
        taskList.addWork(new GeneralTask("General" , "Cleaning","Dishwashing",3,"4/10/21","14.00","16.00","Undone"));

    }

    public TaskList getTaskList(){
        return taskList;
    }
}
