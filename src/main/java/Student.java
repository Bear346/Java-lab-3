import java.util.ArrayList;

import java.util.ArrayList;

public class Student {
    private int id;
    private String fio;
    protected Group group;
    protected ArrayList<Integer> marks = new ArrayList<Integer>();

    Student(String fio, int id){
        this.fio = fio;
        this.id = id;
    }

    public String getFio(){
        return this.fio;
    }

    public int getId(){
        return this.id;
    }

    public void addMark(int mark){
        this.marks.add(mark);
    }

    public double averageMark(){
        float general = 0;
        for (Integer integer: marks){
            general += integer;
        }
        return general / marks.size();
    }

    public Group addGroup(Group group){
        this.group = group;
        return group;
    }
}
