package TaskTacking.Class;

public class ProjectTask extends Task {
    private String leaderName ;
    private String startDate ;

    public ProjectTask(String type, String category ,String name, int priority , String leaderName, String startDate, String endDate , String state) {
        super(type, category ,name ,priority ,state,endDate);
        this.leaderName = leaderName;
        this.startDate = startDate;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setUpdate(String responder, String respondTime) {
        leaderName = responder;
        startDate = respondTime ;
    }
    @Override
    public String toString() {
        return "ProjectWork{" +
                "leaderName='" + leaderName + '\'' +
                ", startTime='" + startDate + '\'' +
                ", endTime='" + super.getDate() + '\'' +
                '}';
    }
}
