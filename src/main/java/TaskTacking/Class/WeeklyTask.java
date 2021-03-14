package TaskTacking.Class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class WeeklyTask extends Task {
    private String[] Weeklydate ;
    private String StartTime ;
    private String EndTime ;

    public WeeklyTask(String type, String category ,String name, int priority , String weeklydate, String startTime, String endTime, String state) {
        super(type, category ,name,priority,state,weeklydate);
        Weeklydate = weeklydate.split("-");
        StartTime = startTime;
        EndTime = endTime;
    }

    public String getWeeklyOut() {
        ArrayList<String> week = new ArrayList<>();
        week.addAll(Arrays.asList(Weeklydate));
        LinkedHashSet<String> hashSet = new LinkedHashSet<>(week);
        ArrayList<String> finWeek = new ArrayList<>(hashSet);
        String[] arrWeek = new String[finWeek.size()];
        arrWeek = finWeek.toArray(arrWeek);
        return Arrays.toString(arrWeek).replace(", ","-").replace("[","").replace("]","");
    }

    public String getStartTime() {
        return StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void updateTime(String startTime , String endTime){
        this.StartTime = startTime ;
        this.EndTime = endTime ;
    }

    public void updateWeek(String strings){
        Weeklydate = strings.split("-");
    }
    @Override
    public String toString() {
        return super.getType() + " " + super.getName() + " " + super.getPriority() + " " + getWeeklyOut() + " " + StartTime + " " + EndTime + " " + super.getState();
    }
}
