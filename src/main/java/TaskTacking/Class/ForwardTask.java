package TaskTacking.Class;

public class ForwardTask extends Task {
    private String Responder ;
    private String RespondTime ;

    public ForwardTask(String type, String category ,String name, int priority, String responder, String respondDate, String respondTime, String state) {
        super(type, category ,name,priority,state,respondDate);
        Responder = responder;
        RespondTime = respondTime;
    }

    public String getResponder() {
        return Responder;
    }

    public String getRespondTime() {
        return RespondTime;
    }

    public void setUpdate(String responder, String respondTime) {
        Responder = responder;
        RespondTime = respondTime ;
    }

    @Override
    public String toString() {
        return super.getType() + " " + super.getName() + " " + super.getPriority() + " " + Responder + " " + super.getDate() + " " + RespondTime ;
    }
}
