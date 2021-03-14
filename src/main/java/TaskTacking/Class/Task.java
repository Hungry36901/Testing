package TaskTacking.Class;

public class Task {
    private String type;
    private String category ;
    private String name ;
    private int priority ;
    private String state ;
    private String date ;

    public Task(String type , String category ,String name , int priority , String state, String date) {
        this.type = type ;
        this.category = category ;
        this.name = name ;
        this.priority = priority ;
        this.state = state ;
        this.date = date ;
    }

    public String getName() {
        return name;
    }
    public String getType(){return type;}
    public String getDate(){return date;}
    public int getPriorityNum(){return priority;}
    public void setUpdate(String name){
        this.name = name ;
    }
    public void setUpdate(String name , String category , int priority){
        this.name = name ;
        this.category = category ;
        this.priority = priority ;
    }
    public void setUpdate(String name, String category ,int priority , String date){
        this.name = name ;
        this.category = category ;
        this.priority = priority ;
        this.date = date ;
    }

    public String getPriority() {
        if((this.priority) == 1)
        {
            return "Low Important" ;
        }
        else if((this.priority) == 2)
        {
            return "Important";
        }
        else if((this.priority) == 3)
        {
            return "High Important" ;
        }
        return null;
    }
    public String getState() {
        return state;
    }

    public String getCategory() {
        return category;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Override
    public String toString(){
        return name + " " + getPriority() + " " + state ;
    }
}
