package TaskTacking.Controllers;

import TaskTacking.Class.*;
import TaskTacking.Source.TaskDataSource;
import TaskTacking.Source.TaskTrackingDataSource;
import TaskTacking.Source.TaskTrackingFileDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class AddTaskController implements Initializable {
    int sun=0,mon=0,tue=0,wed=0,thu=0,fri=0,sat=0;
    @FXML
    AnchorPane heightPane;
    @FXML
    MenuButton workMenu ,selectedDaysMenu;
    @FXML
    Label taskName ,time ,timeExample ,period ,sym1 ,sym2 ,sym3 ,dateLabel ,responderLabel ,dateEndLabel;
    @FXML
    TextField taskNameField ,responderNameField , categoryTextField;
    @FXML
    DatePicker dateGeneral ,dateEnd ;
    @FXML
    ImageView addTaskButton;
    @FXML
    ComboBox<String> beginHourChoice ,beginMinuteChoice ,endHourChoice,endMinuteChoice,levelMenu,categoryMenu;
    @FXML MenuItem sunday ;
    @FXML Label warningLabel ;

    ObservableList<String> hour ;
    ObservableList<String> minute ;
    ObservableList<String> level ;
    ObservableList<String> categoryChoice = FXCollections.observableArrayList();

    private TaskDataSource dataSource;
    private TaskList taskList;
    private String[] date = {"non","non","non","non","non","non","non"};
    @FXML
    public void initialize(URL location, ResourceBundle resource){
        setChoice();
        dataSource = new TaskTrackingFileDataSource("data","Task.csv");
        taskList = dataSource.getAllWork();
        categoryChoice.addAll(taskList.getAllCategory());
    }
    public void setChoice(){
        categoryMenu.setItems(categoryChoice);
        hour = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
        beginHourChoice.setItems(hour);
        minute = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59");
        beginMinuteChoice.setItems(minute);
        endHourChoice.setItems(hour);
        endMinuteChoice.setItems(minute);
        level = FXCollections.observableArrayList("High Important","Important","Low Important");
        categoryMenu.setItems(categoryChoice);
        levelMenu.setItems(level);
    }
    public int levelPriority(String level){
        if(level.equals("High Important")){return 3;}
        else if(level.equals("Important")){return 2;}
        else if(level.equals("Low Important")){return 1;}
        return 0;
    }
    public void selectGeneralWork (ActionEvent event){
        workMenu.setText("General");
        generalMenuView(true);
    }
    public void selectWeeklyWork (ActionEvent event){
        workMenu.setText("Weekly");
        weeklyMenuView(true);
    }
    public void selectForwardWork (ActionEvent event) {
        workMenu.setText("Forward");
        forwardMenuView(true);
    }
    public void selectProjectWork (ActionEvent event){
        workMenu.setText("Project");
        projectMenuView(true);
    }
    public void categorySelect (ActionEvent event){
        categoryTextField.setText(categoryMenu.getValue());
    }
    public void selectSunday (ActionEvent event){ if(sun % 2 == 0) { date[0] = "Sun-"; sun = 1;} else {date[0] = "non"; sun = 0 ; }}
    public void selectMonday (ActionEvent event){ if(mon % 2 == 0) { date[1] = "Mon-"; mon = 1;} else {date[1] = "non"; mon = 0 ;}}
    public void selectTuesday (ActionEvent event){if(tue % 2 == 0) { date[2] = "Tue-"; tue = 1;} else {date[2] = "non"; tue = 0 ;}}
    public void selectWednesday (ActionEvent event){if(wed % 2 == 0) { date[3] = "Wed-"; wed = 1;} else {date[3] = "non"; wed = 0 ;}}
    public void selectThursday (ActionEvent event){if(thu % 2 == 0) { date[4] = "Thu-"; thu = 1;} else {date[4] = "non"; thu = 0 ;}}
    public void selectFriday (ActionEvent event){if(fri % 2 == 0) { date[5] = "Fri-"; fri = 1;} else {date[5] = "non"; fri = 0 ;}}
    public void selectSaturday (ActionEvent event){if(sat % 2 == 0) { date[6] = "Sat"; sat = 1;} else {date[6] = "non"; sat = 0 ;}}
    public void generalMenuView(boolean state){
        time.setVisible(state);
        timeExample.setVisible(state);
        period.setVisible(state);
        period.setText("Period");
        sym1.setVisible(state);
        sym2.setVisible(state);
        sym3.setVisible(state);
        beginHourChoice.setVisible(state);
        beginMinuteChoice.setVisible(state);
        endHourChoice.setVisible(state);
        endMinuteChoice.setVisible(state);
        dateGeneral.setVisible(state);
        dateLabel.setVisible(state);
        dateLabel.setText("Date");
        selectedDaysMenu.setVisible(!state);
        responderLabel.setVisible(!state);
        responderNameField.setVisible(!state);
        dateEnd.setVisible(!state);
        dateEndLabel.setVisible(!state);
        addTaskButton.setVisible(state);
        warningLabel.setVisible(false);
    }
    public void weeklyMenuView(boolean state){
        time.setVisible(state);
        timeExample.setVisible(state);
        period.setVisible(state);
        period.setText("Period");
        sym1.setVisible(state);
        sym2.setVisible(state);
        sym3.setVisible(state);
        beginHourChoice.setVisible(state);
        beginMinuteChoice.setVisible(state);
        endHourChoice.setVisible(state);
        endMinuteChoice.setVisible(state);
        dateLabel.setText("Days in week");
        dateGeneral.setVisible(!state);
        selectedDaysMenu.setVisible(state);
        responderLabel.setVisible(!state);
        responderNameField.setVisible(!state);
        dateEnd.setVisible(!state);
        dateEndLabel.setVisible(!state);
        addTaskButton.setVisible(state);
        warningLabel.setVisible(false);
    }
    public void forwardMenuView(boolean state){
        time.setVisible(state);
        timeExample.setVisible(state);
        period.setVisible(state);
        period.setText("Assign");
        sym1.setVisible(state);
        sym2.setVisible(!state);
        sym3.setVisible(!state);
        beginHourChoice.setVisible(state);
        beginMinuteChoice.setVisible(state);
        endHourChoice.setVisible(!state);
        endMinuteChoice.setVisible(!state);
        dateGeneral.setVisible(state);
        dateLabel.setVisible(state);
        dateLabel.setText("Date");
        selectedDaysMenu.setVisible(!state);
        responderLabel.setVisible(state);
        responderLabel.setText("Responder");
        responderNameField.setVisible(state);
        responderNameField.setPromptText("ResponderName");
        dateEnd.setVisible(!state);
        dateEndLabel.setVisible(!state);
        addTaskButton.setVisible(state);
        warningLabel.setVisible(false);
    }
    public void projectMenuView(boolean state){
        time.setVisible(!state);
        timeExample.setVisible(!state);
        period.setVisible(!state);
        period.setText("Assign");
        sym1.setVisible(!state);
        sym2.setVisible(!state);
        sym3.setVisible(!state);
        beginHourChoice.setVisible(!state);
        beginMinuteChoice.setVisible(!state);
        endHourChoice.setVisible(!state);
        endMinuteChoice.setVisible(!state);
        dateGeneral.setVisible(state);
        dateLabel.setVisible(state);
        dateLabel.setText("Date Start");
        selectedDaysMenu.setVisible(!state);
        responderLabel.setVisible(state);
        responderLabel.setText("Supervisor");
        responderNameField.setVisible(state);
        responderNameField.setPromptText("SupervisorName");
        dateEnd.setVisible(state);
        dateEndLabel.setVisible(state);
        addTaskButton.setVisible(state);
        warningLabel.setVisible(false);
    }
    public void refreshList() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/general.fxml"));
        Parent root = (Parent) loader.load();
        GeneralController generalController = loader.getController();
        generalController.refresh();
    }
    public void updateTable() throws IOException {
        dataSource.setTaskData(taskList);
        refreshList();
        Stage stage = (Stage) addTaskButton.getScene().getWindow();
        stage.close();
    }
    public void handleAddButton(Event event) throws IOException {
        try {
            int state = 0 ;
            if(taskList.size() == 0)
            {
                if(workMenu.getText().equals("General"))
                {
                    if(taskNameField.getText().equals("") || levelMenu.getValue().equals(null) || beginHourChoice.getValue().equals(null) || beginMinuteChoice.getValue().equals(null) || endHourChoice.getValue().equals(null) || endMinuteChoice.getValue().equals(null)){
                        warningLabel.setVisible(true);
                    }
                    else {
                        if (categoryTextField.getText().equals("")){categoryTextField.setText("-");}
                        String[] beginTime = {beginHourChoice.getValue(),beginMinuteChoice.getValue()};
                        String beginTimeString = Arrays.toString(beginTime).replace("[","").replace(",",".").replace("]","").replace(" ","");
                        String[] endTime = {endHourChoice.getValue(),endMinuteChoice.getValue()};
                        String endTimeString = Arrays.toString(endTime).replace("[","").replace(",",".").replace("]","").replace(" ","");
                        Task task = new GeneralTask(workMenu.getText(), categoryTextField.getText() ,taskNameField.getText(),levelPriority(levelMenu.getValue()),dateGeneral.getValue().toString(),beginTimeString,endTimeString,"Undone");
                        taskList.addWork(task);
                        updateTable();
                    }
                }
                else if(workMenu.getText().equals("Weekly"))
                {
                    if(taskNameField.getText().equals("") || levelMenu.getValue().equals(null) || beginHourChoice.getValue().equals(null) || beginMinuteChoice.getValue().equals(null) || endHourChoice.getValue().equals(null) || endMinuteChoice.getValue().equals(null) || Arrays.toString(date).equals("[non, non, non, non, non, non, non]")){
                        warningLabel.setVisible(true);
                    }
                    else {
                        if (categoryTextField.getText().equals("")){categoryTextField.setText("-");}
                        String weekly = Arrays.toString(date).replace("non","").replace("[","").replace(","," ").replace("]","").replace(" ","").replace("Sun-Mon-Tue-Wed-Thu-Fri-Sat","Everday");
                        String[] beginTime = {beginHourChoice.getValue(),beginMinuteChoice.getValue()};
                        String beginTimeString = Arrays.toString(beginTime).replace("[","").replace(",",".").replace("]","").replace(" ","");
                        String[] endTime = {endHourChoice.getValue(),endMinuteChoice.getValue()};
                        String endTimeString = Arrays.toString(endTime).replace("[","").replace(",",".").replace("]","").replace(" ","");
                        Task task = new WeeklyTask(workMenu.getText(), categoryTextField.getText() ,taskNameField.getText(),levelPriority(levelMenu.getValue()),weekly,beginTimeString,endTimeString,"Undone");
                        taskList.addWork(task);
                        updateTable();
                    }
                }
                else if(workMenu.getText().equals("Forward"))
                {
                    if (taskNameField.getText().equals("") || levelMenu.getValue().equals(null) || beginHourChoice.getValue().equals(null) || beginMinuteChoice.getValue().equals(null) || responderNameField.getText().equals("")){
                        warningLabel.setVisible(true);
                    }
                    else {
                        if (categoryTextField.getText().equals("")){categoryTextField.setText("-");}
                        String[] beginTime = {beginHourChoice.getValue(),beginMinuteChoice.getValue()};
                        String beginTimeString = Arrays.toString(beginTime).replace("[","").replace(",",".").replace("]","").replace(" ","");
                        Task task = new ForwardTask(workMenu.getText(), categoryTextField.getText() ,taskNameField.getText(),levelPriority(levelMenu.getValue()),responderNameField.getText(),dateGeneral.getValue().toString(),beginTimeString,"Undone");
                        taskList.addWork(task);
                        updateTable();
                    }
                }
                else if(workMenu.getText().equals("Project"))
                {
                    if (taskNameField.getText().equals("") || levelMenu.getValue().equals(null) || responderNameField.getText().equals("")){
                        warningLabel.setVisible(true);
                    }
                    else {
                        if (categoryTextField.getText().equals("")){categoryTextField.setText("-");}
                        Task task = new ProjectTask(workMenu.getText(), categoryTextField.getText() ,taskNameField.getText(),levelPriority(levelMenu.getValue()),responderNameField.getText(),dateGeneral.getValue().toString(),dateEnd.getValue().toString(),"Undone");
                        taskList.addWork(task);
                        updateTable();
                    }
                }
                dataSource.setTaskData(taskList);
                refreshList();
                Stage stage = (Stage) addTaskButton.getScene().getWindow();
                stage.close();
            }
            else{
                for (Task task : taskList.getAllWorks()) {
                    if (task.getName().equals(taskNameField.getText())){
                        state = 1 ;
                    }
                }
                for(int count = 0 ; count < taskList.size();count++) {
                    if (taskList.getName(count).equals(taskNameField.getText())) {
                        warningLabel.setVisible(true);
                    }
                    else if(count == taskList.size() - 1){
                        if(workMenu.getText().equals("General"))
                        {
                            if(taskNameField.getText().equals("") || levelMenu.getValue().equals(null) || beginHourChoice.getValue().equals(null) || beginMinuteChoice.getValue().equals(null) || endHourChoice.getValue().equals(null) || endMinuteChoice.getValue().equals(null)){
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
                                String[] endTime = {endHourChoice.getValue(),endMinuteChoice.getValue()};
                                String endTimeString = Arrays.toString(endTime).replace("[","").replace(",",".").replace("]","").replace(" ","");
                                Task task = new GeneralTask(workMenu.getText(), categoryTextField.getText() ,taskNameField.getText(),levelPriority(levelMenu.getValue()),dateGeneral.getValue().toString(),beginTimeString,endTimeString,"Undone");
                                taskList.addWork(task);
                                updateTable();
                            }
                        }
                        else if(workMenu.getText().equals("Weekly"))
                        {
                            if(taskNameField.getText().equals("") || levelMenu.getValue().equals(null) || beginHourChoice.getValue().equals(null) || beginMinuteChoice.getValue().equals(null) || endHourChoice.getValue().equals(null) || endMinuteChoice.getValue().equals(null) || Arrays.toString(date).equals("[non, non, non, non, non, non, non]")){
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
                                Task task = new WeeklyTask(workMenu.getText(), categoryTextField.getText() ,taskNameField.getText(),levelPriority(levelMenu.getValue()),weekly,beginTimeString,endTimeString,"Undone");
                                taskList.addWork(task);
                                updateTable();
                            }
                        }
                        else if(workMenu.getText().equals("Forward"))
                        {
                            if (taskNameField.getText().equals("") || levelMenu.getValue().equals(null) || beginHourChoice.getValue().equals(null) || beginMinuteChoice.getValue().equals(null) || responderNameField.getText().equals("")){
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
                                Task task = new ForwardTask(workMenu.getText(), categoryTextField.getText() ,taskNameField.getText(),levelPriority(levelMenu.getValue()),responderNameField.getText().replace(","," "),dateGeneral.getValue().toString(),beginTimeString,"Undone");
                                taskList.addWork(task);
                                updateTable();
                            }
                        }
                        else if(workMenu.getText().equals("Project"))
                        {
                            if (taskNameField.getText().equals("") || levelMenu.getValue().equals(null) || responderNameField.getText().equals("")){
                                warningLabel.setText("Please fill the form completely");
                                warningLabel.setVisible(true);
                            }
                            else if(state == 1){
                                warningLabel.setVisible(true);
                                warningLabel.setText("Task'name is duplicating");
                            }
                            else {
                                if (categoryTextField.getText().equals("")){categoryTextField.setText("-");}
                                Task task = new ProjectTask(workMenu.getText(), categoryTextField.getText() ,taskNameField.getText(),levelPriority(levelMenu.getValue()),responderNameField.getText(),dateGeneral.getValue().toString(),dateEnd.getValue().toString(),"Undone");
                                taskList.addWork(task);
                                updateTable();
                            }
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
}
