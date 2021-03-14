package TaskTacking.Source;

import TaskTacking.Class.TaskList;

public interface TaskDataSource {
    TaskList getAllWork();

    void setTaskData(TaskList taskList);
}
