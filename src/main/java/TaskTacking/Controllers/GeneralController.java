package TaskTacking.Controllers;

import TaskTacking.Class.*;
import TaskTacking.Source.StringConfiguration;
import TaskTacking.Source.TaskDataSource;
import TaskTacking.Source.TaskTrackingDataSource;
import TaskTacking.Source.TaskTrackingFileDataSource;
import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GeneralController {
    int sun=0,mon=0,tue=0,wed=0,thu=0,fri=0,sat=0;
    @FXML private AnchorPane mainPane ;
    @FXML private TableView<Task> AllWorkListView ;
    @FXML
    Pane noneSelectedPane , selectedPane ,filterPane;
    @FXML Button removeButtonOps , editButtonOps ,updateButtonOps ,processButtonOps ,doneButtonOps;
    @FXML private ImageView imageLight , removeButton , processingButton , doneButton , editButton , updateButton ;
    @FXML TextField nameTextField , responderTextField , categoryTextField;
    @FXML DatePicker editStartDatePicker , editEndDatePicker;
    @FXML MenuButton selectedDaysMenu ;
    @FXML Label typeSetLabel , nameSetLabel , timeStartLabel , timeEndLabel , priorityLabel ,stateLabel , dateSetLabel , symbolLabel , statusTranLabel ,stateTranLabel , statusOriginLabel , timeSetLabel , dateLabel, symbolSecLabel , symbolThiLabel , warningLabel, categoryLabel ,categorySetName, showCategoryLabel,showAllTaskNum,showGeneralNum,showWeeklyNum,showForwardNum,showProjectNum;
    @FXML
    ComboBox<String> beginHourChoice ,beginMinuteChoice ,endHourChoice,endMinuteChoice,levelMenu,categoryMenu,categorySelectBox;

    ObservableList<String> hour ;
    ObservableList<String> minute ;
    ObservableList<String> level ;
    ObservableList<String> categoryChoice = FXCollections.observableArrayList();
    ObservableList<String> categorySearchChoice = FXCollections.observableArrayList();

    private TaskDataSource dataSource;
    private TaskList taskList;
    private Task selectedTask ;
    private ObservableList<Task> TaskObservableList;
    private String[] date = {"non","non","non","non","non","non","non"};

    @FXML
    public void initialize(){
        dataSource = new TaskTrackingFileDataSource("data","Task.csv");
        taskList = dataSource.getAllWork();
        showAllWorkList(taskList);
        setChoice();
        AllWorkListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                try {
                    showInfoMenu(newValue);
                    selectedTask = newValue ;
                    editGeneral(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void processing(Task task) throws IOException {
        task.setState("Processing");
        dataSource.setTaskData(taskList);
        refresh();
    }
    public void done(Task task) throws IOException {
        task.setState("Done");
        dataSource.setTaskData(taskList);
        refresh();
    }
    public void remove(Task task) throws IOException {
        taskList.deleteTask(task);
    }
    @FXML public void handleRemoveButton(ActionEvent event) throws IOException{
        remove(selectedTask);
        dataSource.setTaskData(taskList);
        refresh();
    }
    public void categorySelect (ActionEvent event){
        categoryTextField.setText(categoryMenu.getValue());
    }
    public void update(Task task) throws IOException {
        warningLabel.setVisible(false);
        try {
            int state = 0 ;
                for (Task taskEdit : taskList.getAllWorks()) {
                    if (taskEdit.getName().equals(nameTextField.getText())){
                        state = 1 ;
                    }
                }
                for(int count = 0 ; count < taskList.size();count++) {
                    if (taskList.getName(count).equals(nameTextField.getText())) {
                        warningLabel.setVisible(true);
                    }
                    else if(count == taskList.size() - 1){
                        if(task.getType().equals("General"))
                        {
                            if(nameTextField.getText().equals("") || levelMenu.getValue().equals(null) || beginHourChoice.getValue().equals(null) || beginMinuteChoice.getValue().equals(null) || endHourChoice.getValue().equals(null) || endMinuteChoice.getValue().equals(null)){
                                warningLabel.setVisible(true);
                                warningLabel.setText("Please fill the form completely");
                            }
                            else if(state == 1){
                                warningLabel.setVisible(true);
                                warningLabel.setText("Task'name is duplicating");
                            }
                            else {
                                if (categoryTextField.getText().equals("")){categoryTextField.setText("-");}
                                task.setUpdate(nameTextField.getText(),categoryTextField.getText(),getPriority(levelMenu.getValue()),editStartDatePicker.getValue().toString());
                                String[] beginTime = {beginHourChoice.getValue(),beginMinuteChoice.getValue()};
                                String beginTimeString = Arrays.toString(beginTime).replace("[","").replace(",",".").replace("]","").replace(" ","");
                                String[] endTime = {endHourChoice.getValue(),endMinuteChoice.getValue()};
                                String endTimeString = Arrays.toString(endTime).replace("[","").replace(",",".").replace("]","").replace(" ","");
                                ((GeneralTask)task).updateTime(beginTimeString,endTimeString);
                                dataSource.setTaskData(taskList);
                                refresh();
                            }
                        }
                        else if(task.getType().equals("Weekly"))
                        {
                            if(nameTextField.getText().equals("") || levelMenu.getValue().equals(null) || beginHourChoice.getValue().equals(null) || beginMinuteChoice.getValue().equals(null) || endHourChoice.getValue().equals(null) || endMinuteChoice.getValue().equals(null) || Arrays.toString(date).equals("[non, non, non, non, non, non, non]")){
                                warningLabel.setVisible(true);
                                warningLabel.setText("Please fill the form completely");
                            }
                            else if(state == 1){
                                warningLabel.setVisible(true);
                                warningLabel.setText("Task'name is duplicating");
                            }
                            else {
                                if (categoryTextField.getText().equals("")){categoryTextField.setText("-");}
                                String weekly = Arrays.toString(date).replace("non","").replace("[","").replace(","," ").replace("]","").replace(" ","").replace("Sun-Mon-Tue-Wed-Thu-Fri-Sat","Everday");
                                String[] beginTime = {beginHourChoice.getValue(),beginMinuteChoice.getValue()};
                                String beginTimeString = Arrays.toString(beginTime).replace("[","").replace(",",".").replace("]","").replace(" ","");
                                String[] endTime = {endHourChoice.getValue(),endMinuteChoice.getValue()};
                                String endTimeString = Arrays.toString(endTime).replace("[","").replace(",",".").replace("]","").replace(" ","");
                                task.setUpdate(nameTextField.getText(),categoryTextField.getText(),getPriority(levelMenu.getValue()));
                                ((WeeklyTask)task).updateTime(beginTimeString,endTimeString);
                                ((WeeklyTask)task).updateWeek(weekly);
                                dataSource.setTaskData(taskList);
                                refresh();
                            }
                        }
                        else if(task.getType().equals("Forward"))
                        {
                            if (nameTextField.getText().equals("") || levelMenu.getValue().equals(null) || beginHourChoice.getValue().equals(null) || beginMinuteChoice.getValue().equals(null) || responderTextField.getText().equals("")){
                                warningLabel.setVisible(true);
                                warningLabel.setText("Please fill the form completely");
                            }
                            else if(state == 1){
                                warningLabel.setVisible(true);
                                warningLabel.setText("Task'name is duplicating");
                            }
                            else {
                                if (categoryTextField.getText().equals("")){categoryTextField.setText("-");}
                                String[] beginTime = {beginHourChoice.getValue(),beginMinuteChoice.getValue()};
                                String beginTimeString = Arrays.toString(beginTime).replace("[","").replace(",",".").replace("]","").replace(" ","");
                                task.setUpdate(nameTextField.getText(),categoryTextField.getText(),getPriority(levelMenu.getValue()),editStartDatePicker.getValue().toString());
                                ((ForwardTask)task).setUpdate(responderTextField.getText(),beginTimeString);
                                dataSource.setTaskData(taskList);
                                refresh();
                            }
                        }
                        else if(task.getType().equals("Project"))
                        {
                            if (nameTextField.getText().equals("") || levelMenu.getValue().equals(null) || responderTextField.getText().equals("")){
                                warningLabel.setText("Please fill the form completely");
                                warningLabel.setVisible(true);
                            }
                            else if(state == 1){
                                warningLabel.setVisible(true);
                                warningLabel.setText("Task'name is duplicating");
                            }
                            else {
                                if (categoryTextField.getText().equals("")){categoryTextField.setText("-");}
                                task.setUpdate(nameTextField.getText(),categoryTextField.getText(),getPriority(levelMenu.getValue()),editStartDatePicker.getValue().toString());
                                ((ProjectTask)task).setUpdate(responderTextField.getText(),editEndDatePicker.getValue().toString());
                                dataSource.setTaskData(taskList);
                                refresh();
                            }
                        }
                    }
                }
        }
        catch (Exception exception){
            System.err.println("Error Exception");
            warningLabel.setVisible(true);
        }
    }
    public int getPriority(String level){
        if(level.equals("High Important")){
            return 3 ;
        }
        else if (level.equals("Important")){
            return 2 ;
        }
        else if (level.equals("Low Important")){
            return 1 ;
        }
        return 0;
    }
    public void selectSunday (ActionEvent event){ if(sun % 2 == 0) { date[0] = "Sun-"; sun = 1;} else {date[0] = "non"; sun = 0 ; }}
    public void selectMonday (ActionEvent event){ if(mon % 2 == 0) { date[1] = "Mon-"; mon = 1;} else {date[1] = "non"; mon = 0 ;}}
    public void selectTuesday (ActionEvent event){if(tue % 2 == 0) { date[2] = "Tue-"; tue = 1;} else {date[2] = "non"; tue = 0 ;}}
    public void selectWednesday (ActionEvent event){if(wed % 2 == 0) { date[3] = "Wed-"; wed = 1;} else {date[3] = "non"; wed = 0 ;}}
    public void selectThursday (ActionEvent event){if(thu % 2 == 0) { date[4] = "Thu-"; thu = 1;} else {date[4] = "non"; thu = 0 ;}}
    public void selectFriday (ActionEvent event){if(fri % 2 == 0) { date[5] = "Fri-"; fri = 1;} else {date[5] = "non"; fri = 0 ;}}
    public void selectSaturday (ActionEvent event){if(sat % 2 == 0) { date[6] = "Sat"; sat = 1;} else {date[6] = "non"; sat = 0 ;}}
    public void editGeneral(boolean state){
        nameTextField.setVisible(state);
        editStartDatePicker.setVisible(state);
        beginHourChoice.setVisible(state);
        beginMinuteChoice.setVisible(state);
        endHourChoice.setVisible(state);
        endMinuteChoice.setVisible(state);
        levelMenu.setVisible(state);
        symbolSecLabel.setVisible(state);
        symbolThiLabel.setVisible(state);
        priorityLabel.setVisible(!state);
        nameSetLabel.setVisible(!state);
        dateSetLabel.setVisible(!state);
        stateLabel.setVisible(true);
        processingButton.setVisible(true);
        processButtonOps.setVisible(true);
        doneButton.setVisible(true);
        doneButtonOps.setVisible(true);
        updateButton.setVisible(false);
        updateButtonOps.setVisible(false);
        warningLabel.setVisible(false);
        categorySetName.setVisible(true);
        categoryLabel.setVisible(true);
        categoryTextField.setVisible(false);
        categoryMenu.setVisible(false);
        nameSetLabel.setVisible(true);
    }
    public void editWeekly(boolean state){
        nameTextField.setVisible(state);
        selectedDaysMenu.setVisible(state);
        beginHourChoice.setVisible(state);
        beginMinuteChoice.setVisible(state);
        endHourChoice.setVisible(state);
        endMinuteChoice.setVisible(state);
        levelMenu.setVisible(state);
        symbolSecLabel.setVisible(state);
        symbolThiLabel.setVisible(state);
        priorityLabel.setVisible(!state);
        stateLabel.setVisible(true);
    }
    public void editForward(boolean state){
        nameTextField.setVisible(state);
        editStartDatePicker.setVisible(state);
        beginHourChoice.setVisible(state);
        beginMinuteChoice.setVisible(state);
        endHourChoice.setVisible(!state);
        endMinuteChoice.setVisible(!state);
        levelMenu.setVisible(state);
        symbolLabel.setVisible(state);
        symbolSecLabel.setVisible(!state);
        symbolThiLabel.setVisible(!state);
        priorityLabel.setVisible(!state);
        stateLabel.setVisible(!state);
    }
    public void editProject(boolean state){
        nameTextField.setVisible(state);
        editStartDatePicker.setVisible(state);
        levelMenu.setVisible(state);
        symbolLabel.setVisible(state);
        symbolSecLabel.setVisible(!state);
        symbolThiLabel.setVisible(!state);
        priorityLabel.setVisible(!state);
        stateLabel.setVisible(!state);
    }
    private void showAllWorkList(TaskList taskList){
        TaskObservableList = FXCollections.observableArrayList(taskList.getAllWorks());
        AllWorkListView.setItems(TaskObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Priority", "field:priority"));
        configs.add(new StringConfiguration("title:Type", "field:type"));
        configs.add(new StringConfiguration("title:Category", "field:category"));
        configs.add(new StringConfiguration("title:Name", "field:name"));
        configs.add(new StringConfiguration("title:Status", "field:state"));

        for(StringConfiguration conf: configs){
            TableColumn col = new TableColumn(conf.get("title"));
            col.setPrefWidth(180);
            col.setResizable(false);
            col.setStyle("-fx-alignment: center");
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            col.setSortable(false);
            if (col.getText().equals("Priority"))
            {
                col.setSortable(true);
            }
            AllWorkListView.getColumns().add(col);
        }
    }
    public void setChoice(){
        categoryChoice.addAll(taskList.getAllCategory());
        categorySearchChoice.add("ALL");
        categoryMenu.setItems(categoryChoice);
        categorySearchChoice.addAll(categoryChoice);
        categorySelectBox.setItems(categorySearchChoice);
        hour = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
        beginHourChoice.setItems(hour);
        minute = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59");
        beginMinuteChoice.setItems(minute);
        endHourChoice.setItems(hour);
        endMinuteChoice.setItems(minute);
        level = FXCollections.observableArrayList("High Important","Important","Low Important");
        levelMenu.setItems(level);
    }
    private void showInfoMenu(Task task) throws IOException {
        noneSelectedPane.setVisible(false);
        filterPane.setVisible(false);
        selectedPane.setVisible(true);
        removeButton.setVisible(true);
        removeButtonOps.setVisible(true);
        imageLight.setVisible(true);
        if(task.getType().equals("General")){
            typeSetLabel.setText(task.getType());
            nameSetLabel.setText(task.getName());
            categorySetName.setText(task.getCategory());
            dateSetLabel.setText(task.getDate());
            dateLabel.setText("Date:");
            timeSetLabel.setText("Time:");
            timeStartLabel.setText(((GeneralTask)task).getStartTime());
            timeEndLabel.setVisible(true);
            symbolLabel.setVisible(true);
            timeEndLabel.setText(((GeneralTask)task).getEndTime());
            priorityLabel.setText(task.getPriority());
            stateTranLabel.setVisible(false);
            statusTranLabel.setVisible(false);
            statusOriginLabel.setText("Status:");
            stateLabel.setText(task.getState());
            editButton.setVisible(true);
            editButtonOps.setVisible(true);
            selectedDaysMenu.setVisible(false);
            dateSetLabel.setVisible(true);
            responderTextField.setVisible(false);
            editEndDatePicker.setVisible(false);
            if (task.getState().equals("Undone")){
                imageLight.setImage(new Image("Image\\TrafficRed.png"));
            }
            else if (task.getState().equals("Processing")){
                imageLight.setImage(new Image("Image\\TrafficYellow.png"));
            }
            else if (task.getState().equals("Done")){
                imageLight.setImage(new Image("Image\\TrafficGreen.png"));
            }
        }
        else if(task.getType().equals("Weekly")){
            typeSetLabel.setText(task.getType());
            nameSetLabel.setText(task.getName());
            categorySetName.setText(task.getCategory());
            dateLabel.setText("Date:");
            timeSetLabel.setText("Time:");
            symbolLabel.setText("-");
            dateSetLabel.setText(((WeeklyTask)task).getWeeklyOut());
            timeStartLabel.setText(((WeeklyTask)task).getStartTime());
            timeEndLabel.setVisible(true);
            symbolLabel.setVisible(true);
            timeEndLabel.setText(((WeeklyTask)task).getEndTime());
            priorityLabel.setText(task.getPriority());
            stateTranLabel.setVisible(false);
            statusTranLabel.setVisible(false);
            statusOriginLabel.setText("Status:");
            stateLabel.setText(task.getState());
            editButton.setVisible(true);
            editButtonOps.setVisible(true);
            selectedDaysMenu.setVisible(false);
            dateSetLabel.setVisible(true);
            responderTextField.setVisible(false);
            editEndDatePicker.setVisible(false);
            if (task.getState().equals("Undone")){
                imageLight.setImage(new Image("Image\\TrafficRed.png"));
            }
            else if (task.getState().equals("Processing")){
                imageLight.setImage(new Image("Image\\TrafficYellow.png"));
            }
            else if (task.getState().equals("Done")){
                imageLight.setImage(new Image("Image\\TrafficGreen.png"));
            }
        }
        else if(task.getType().equals("Forward")){
            typeSetLabel.setText(task.getType());
            nameSetLabel.setText(task.getName());
            categorySetName.setText(task.getCategory());
            dateLabel.setText("Date:");
            timeSetLabel.setText("Time:");
            symbolLabel.setText("-");
            dateSetLabel.setText(task.getDate());
            priorityLabel.setText(task.getPriority());
            timeStartLabel.setText(((ForwardTask)task).getRespondTime());
            timeEndLabel.setVisible(false);
            symbolLabel.setVisible(false);
            statusOriginLabel.setText("Respond:");
            stateLabel.setText(((ForwardTask)task).getResponder());
            stateTranLabel.setVisible(true);
            statusTranLabel.setVisible(true);
            statusTranLabel.setText("Status:");
            stateTranLabel.setText(task.getState());
            editButton.setVisible(true);
            editButtonOps.setVisible(true);
            selectedDaysMenu.setVisible(false);
            dateSetLabel.setVisible(true);
            responderTextField.setVisible(false);
            editEndDatePicker.setVisible(false);
            if (task.getState().equals("Undone")){
                imageLight.setImage(new Image("Image\\TrafficRed.png"));
            }
            else if (task.getState().equals("Processing")){
                imageLight.setImage(new Image("Image\\TrafficYellow.png"));
            }
            else if (task.getState().equals("Done")){
                imageLight.setImage(new Image("Image\\TrafficGreen.png"));
            }
        }
        else if(task.getType().equals("Project")){
            typeSetLabel.setText(task.getType());
            nameSetLabel.setText(task.getName());
            categorySetName.setText(task.getCategory());
            dateLabel.setText("Start:");
            dateSetLabel.setText(((ProjectTask)task).getStartDate());
            timeSetLabel.setText("End:");
            symbolLabel.setText("-");
            timeStartLabel.setText(task.getDate());
            timeEndLabel.setVisible(false);
            symbolLabel.setVisible(false);
            statusOriginLabel.setText("Leader:");
            stateLabel.setText(((ProjectTask) task).getLeaderName());
            stateTranLabel.setVisible(true);
            statusTranLabel.setVisible(true);
            statusTranLabel.setText("Status:");
            stateTranLabel.setText(task.getState());
            editButton.setVisible(true);
            editButtonOps.setVisible(true);
            selectedDaysMenu.setVisible(false);
            dateSetLabel.setVisible(true);
            responderTextField.setVisible(false);
            editEndDatePicker.setVisible(false);
            if (task.getState().equals("Undone")){
                imageLight.setImage(new Image("Image\\TrafficRed.png"));
            }
            else if (task.getState().equals("Processing")){
                imageLight.setImage(new Image("Image\\TrafficYellow.png"));
            }
            else if (task.getState().equals("Done")){
                imageLight.setImage(new Image("Image\\TrafficGreen.png"));
            }
        }
    }
    private void showEditMenu(Task task) throws  IOException{
        categoryTextField.setVisible(true);
        categoryMenu.setVisible(true);
        nameSetLabel.setVisible(false);
                if(task.getType().equals("General")){
                    editGeneral(true);
                    symbolLabel.setText(":");
                    nameTextField.setText(task.getName());
                    editStartDatePicker.setPromptText(task.getDate());
                }
                else if(task.getType().equals("Weekly")){
                    editWeekly(true);
                    symbolLabel.setText(":");
                    nameTextField.setText(task.getName());
                    dateSetLabel.setVisible(false);
                }
                else if(task.getType().equals("Forward")){
                    editForward(true);
                    symbolLabel.setText(":");
                    nameTextField.setText(task.getName());
                    responderTextField.setVisible(true);
                    editStartDatePicker.setPromptText(task.getDate());
                    responderTextField.setText(((ForwardTask)task).getResponder());
                }
                else if(task.getType().equals("Project")){
                    editProject(true);
                    nameTextField.setText(task.getName());
                    editEndDatePicker.setVisible(true);
                    responderTextField.setVisible(true);
                    responderTextField.setText(((ProjectTask)task).getLeaderName());
                    editStartDatePicker.setPromptText((((ProjectTask) task).getStartDate()));
                    editEndDatePicker.setPromptText(task.getDate());
                }
            }

    public void refresh() throws IOException {
        AllWorkListView.refresh();
        try {
            FXRouter.goTo("General");
        }
        catch (IOException e){
            System.err.println("มีปัญหาแล้วว");
        }
    }
    public void setTable(String category){
        TaskList tempWork = new TaskList();
        if(category.equals("ALL")){
            tempWork = taskList ;
        }
        else {
            for (Task task : taskList.getAllWorks()) {
                if(task.getCategory().equals(category)){
                    tempWork.addWork(task);
                }
            }
        }
        showAllWorkList(tempWork);
        showAllTaskNum.setText(String.valueOf(tempWork.size()));
        showGeneralNum.setText(String.valueOf(tempWork.countGeneral()));
        showWeeklyNum.setText(String.valueOf(tempWork.countWeekly()));
        showProjectNum.setText(String.valueOf(tempWork.countProject()));
        showForwardNum.setText(String.valueOf(tempWork.countForward()));
        AllWorkListView.refresh();
    }
    @FXML public void categorySearchSelected(ActionEvent event){
        showCategoryLabel.setText(categorySelectBox.getValue());
        setTable(categorySelectBox.getValue());
    }
    @FXML public void handleProfile(Event event)
    {
        AllWorkListView.refresh();
        try {
            FXRouter.goTo("Author");
        }
        catch (IOException e){
            System.err.println("ไปที่หน้า author ไม่ได้");
        }
    }
    @FXML public void handleAddWorkButton(Event event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddWork.fxml"));
        stage.setScene(new Scene(loader.load(), 700,550));
        stage.show();
    }
    @FXML public void handleTasking(){
        noneSelectedPane.setVisible(true);
        selectedPane.setVisible(false);
        filterPane.setVisible(false);
    }
    @FXML public void handleFilterButton(Event event) throws IOException {
        removeButton.setVisible(false);
        removeButtonOps.setVisible(false);
        AllWorkListView.getSelectionModel().clearSelection();
        filterPane.setVisible(true);
        noneSelectedPane.setVisible(false);
        selectedPane.setVisible(false);
        if (showCategoryLabel.getText().equals("ALL")){
            showAllTaskNum.setText(String.valueOf(taskList.size()));
            showGeneralNum.setText(String.valueOf(taskList.countGeneral()));
            showWeeklyNum.setText(String.valueOf(taskList.countWeekly()));
            showProjectNum.setText(String.valueOf(taskList.countProject()));
            showForwardNum.setText(String.valueOf(taskList.countForward()));
        }
    }
    @FXML public void handleEditButton(ActionEvent event) throws IOException {
        showEditMenu(selectedTask);
        updateButton.setVisible(true);
        updateButtonOps.setVisible(true);
        editButton.setVisible(false);
        editButtonOps.setVisible(false);
    }
    @FXML public void handleProcessingButton(ActionEvent event) throws IOException {
        processing(selectedTask);
    }
    @FXML public  void handleDoneButton(ActionEvent event) throws IOException {
        done(selectedTask);
    }
    @FXML public void handleUpdateButton(ActionEvent event) throws  IOException{
        selectedTask.setUpdate("");
        for(int count = 0 ; count < taskList.size();count++) {
            if (taskList.getName(count).equals(nameTextField.getText())) {
                warningLabel.setVisible(true);
            }
            else if (count == taskList.size() - 1){
                update(selectedTask);
            }
        }
    }
}
