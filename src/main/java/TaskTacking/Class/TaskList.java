package TaskTacking.Class;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class TaskList {
    private ArrayList<Task> works ;

    public TaskList(){
        works = new ArrayList<>();
    }

    public void addWork(Task work){
        works.add(work);
    }
    public ArrayList<Task> getAllWorks(){
        return works;
    }
    public void deleteTask(Task task){
        works.remove(task);
    }
    public int size(){
        return works.size();
    }
    public int countGeneral(){
        int count = 0 ;
        for ( Task task : works) {
            if (task.getType().equals("General")){
                count += 1 ;
            }
        }
        return count ;
    }
    public int countWeekly(){
        int count = 0 ;
        for ( Task task : works) {
            if (task.getType().equals("Weekly")){
                count += 1 ;
            }
        }
        return count ;
    }
    public int countForward(){
        int count = 0 ;
        for ( Task task : works) {
            if (task.getType().equals("Forward")){
                count += 1 ;
            }
        }
        return count ;
    }
    public int countProject(){
        int count = 0 ;
        for ( Task task : works) {
            if (task.getType().equals("Project")){
                count += 1 ;
            }
        }
        return count ;
    }
    public String getName(int count){
        return works.get(count).getName();
    }
    public ArrayList<String> getAllCategory(){
        ArrayList<String> category = new ArrayList<>();
        category.add("-");
        for(Task task : works){
            category.add(task.getCategory());
        }
        LinkedHashSet<String> hashSet = new LinkedHashSet<>(category);
        ArrayList<String> finCategory = new ArrayList<>(hashSet);
        return finCategory ;
    }
    public int getPriority(int count){
        return works.get(count).getPriorityNum();
    }
    public Class<? extends Task> each(int count){
        return works.get(count).getClass();
    }
}
