package TaskTacking.Source;

import TaskTacking.Class.*;

import java.io.*;

public class TaskTrackingFileDataSource implements TaskDataSource{
    private String fileDirectoryName ;
    private String fileName ;
    private TaskList taskList ;

    public TaskTrackingFileDataSource(String fileFirectoryName , String fileName){
        this.fileDirectoryName = fileFirectoryName ;
        this.fileName = fileName ;
        checkFileIsExisted();
    }
    private void checkFileIsExisted() {
        File file = new File(fileDirectoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Cannot create " + filePath);
            }
        }
    }
    private void readDataGeneral() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if(data[0].trim().equals("General")) {
                Task task = new GeneralTask(data[0].trim(),data[1].trim(),data[2].trim(), Integer.parseInt(data[3].trim()), data[4].trim(), data[5].trim(), data[6].trim(),data[7].trim());
                taskList.addWork(task);
            }
            else if(data[0].trim().equals("Weekly")){
                Task task = new WeeklyTask(data[0].trim(),data[1].trim(),data[2].trim(), Integer.parseInt(data[3].trim()), data[4].trim(),data[5].trim(),data[6].trim(),data[7].trim());
                taskList.addWork(task);
            }
            else if(data[0].trim().equals("Forward")){
                Task task = new ForwardTask(data[0].trim(),data[1].trim(),data[2].trim(), Integer.parseInt(data[3].trim()), data[4].trim(), data[5].trim(), data[6].trim(),data[7].trim());
                taskList.addWork(task);
            }
            else if(data[0].trim().equals("Project")){
                Task task = new ProjectTask(data[0].trim(),data[1].trim(),data[2].trim(), Integer.parseInt(data[3].trim()), data[4].trim(),data[5].trim(),data[6].trim(),data[7].trim());
                taskList.addWork(task);
            }
        }
        reader.close();
    }
    @Override
    public TaskList getAllWork(){
        try{
            taskList = new TaskList();
            readDataGeneral();
        } catch (FileNotFoundException e){
            System.err.println(this.fileName + "not found");
        } catch (IOException e){
            System.err.println("IOEception from reading " + this.fileName);
        }
        return taskList;
    }
    @Override
    public void setTaskData(TaskList taskList){
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null ;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for(Task task : taskList.getAllWorks()){
                if(task.getType().equals("General")){
                    String line = task.getType() + "," + task.getCategory() + "," + task.getName() + "," + task.getPriorityNum() + "," + task.getDate() + "," + ((GeneralTask)task).getStartTime() + "," + ((GeneralTask)task).getEndTime() + "," + task.getState();
                    writer.append(line);
                }
                else if(task.getType().equals("Weekly")){
                    String line = task.getType() + "," + task.getCategory() + "," + task.getName() + "," + task.getPriorityNum() + "," + ((WeeklyTask)task).getWeeklyOut() + "," + ((WeeklyTask) task).getStartTime() + "," + ((WeeklyTask) task).getEndTime() + "," + task.getState();
                    writer.append(line);
                }
                else if(task.getType().equals("Forward")){
                    String line = task.getType() + "," + task.getCategory() + "," + task.getName() + "," + task.getPriorityNum() + "," + ((ForwardTask)task).getResponder() + "," + task.getDate() + "," + ((ForwardTask) task).getRespondTime() + "," + task.getState();
                    writer.append(line);
                }
                else if(task.getType().equals("Project")){
                    String line = task.getType() + "," + task.getCategory() + "," + task.getName() + "," + task.getPriorityNum() + "," + ((ProjectTask)task).getLeaderName() + "," + ((ProjectTask) task).getStartDate() + "," + task.getDate() + "," + task.getState();
                    writer.append(line);
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException e){
            System.err.println("Connot write " + filePath);
        }
    }
}
