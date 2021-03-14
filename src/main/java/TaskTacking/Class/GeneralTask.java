package TaskTacking.Class;

public class GeneralTask extends Task {
    private String startTime ;
    private String endTime ;

    public GeneralTask(String type, String category ,String name, int priority, String date, String startTime, String endTime,String state) {
        super(type, category ,name ,priority ,state,date);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void updateTime(String startTime , String endTime){
        this.startTime = startTime ;
        this.endTime = endTime ;
    }
    @Override
    public String toString(){
        return super.getDate() + " " + super.getType() + " " + super.getName() + " " + startTime + " - " + endTime + " " + getPriority() + " " + getState() ;
    }
}
