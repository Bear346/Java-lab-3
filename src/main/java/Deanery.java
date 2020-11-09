import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;

public class Deanery {
    ArrayList<Student> students;
    ArrayList<Group> groups;

    Deanery() {
        this.students = new ArrayList<Student>();
        this.groups = new ArrayList<Group>();
    }

    public void importStudents(String fileName){
        try{
            Object obj = new JSONParser().parse(new FileReader(fileName));
            JSONObject jo = (JSONObject) obj;
            JSONArray stud = (JSONArray) jo.get("students");
            Iterator studItr = stud.iterator();
            int id = 1;
            while(studItr.hasNext()){
                JSONObject buf = (JSONObject)studItr.next();
                Student temp = new Student(buf.get("fio").toString(),id);
                students.add(temp);
                id++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        catch(ParseException e){
            System.out.println(e.getMessage());
        }
    }

    public void importGroup(String fileName){
        try{
            Object obj = new JSONParser().parse(new FileReader(fileName));
            JSONObject jo = (JSONObject) obj;
            JSONArray grp = (JSONArray) jo.get("groups");
            Iterator grpItr = grp.iterator();
            int len = 0;
            int i = 0;
            int j = 0;
            int d = students.size() / grp.size();
            while(grpItr.hasNext()){
                len += d;
                JSONObject buf = (JSONObject)grpItr.next();
                Group temp = new Group(buf.get("title").toString());
                groups.add(temp);
                for (; i < len; i++){
                    groups.get(j).addStudent(students.get(i));
                }
                j++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        catch(ParseException e){
            System.out.println(e.getMessage());
        }
    }

    public void addMarksForAll(){
        float markCount = 100;
        for (Student student: students){
            Random random = new Random();
            for (int i = 0; i < markCount; i++){
                student.addMark(random.nextInt(5) + 1);
            }
        }
    }

    public void headInitialize(){
        for (Group group: groups){
            group.chooseHead();
        }
    }

    public void transferStudent(int id, String newTitle){
        Student tempStud = null;
        for (Group group: groups){
            if (group.searchStudentId(id) != null){
                tempStud = group.searchStudentId(id);
                group.removeStudent(tempStud);
            }
        }
        for (Group group: groups) {
            if (group.title == newTitle){
                group.addStudent(tempStud);
            }
        }
    }

    public void deductStudent(){
        double minAvgMark = 2.5;
        for (int i =0; i < students.size(); i++){
            if (students.get(i).averageMark() < minAvgMark){
                students.get(i).group.removeStudent(students.get(i));
                students.remove(students.get(i));
                i--;
            }
        }
    }

    public void writeToFile(String fileName){
        try{
            FileWriter fwriter = new FileWriter(fileName);
            JSONArray groupsJA = new JSONArray();
            for(Group group: groups){
                JSONObject groupJsn = new JSONObject();
                groupJsn.put("groupTitle", group.getTitle());
                groupJsn.put("groupAverageMark", group.averageMarkGroup());
                groupJsn.put("headStudent", group.getHead().getFio());
                JSONArray studentJA = new JSONArray();
                for (Student student: group.getStudents()){
                    JSONObject studentJsn = new JSONObject();
                    studentJsn.put("studentId", student.getId());
                    studentJsn.put("studentFIO", student.getFio());
                    studentJsn.put("studentAverageMark", student.averageMark());
                    studentJA.add(studentJsn);
                }
                groupJsn.put("students", studentJA);
                groupsJA.add(groupJsn);
            }
            fwriter.write(groupsJA.toJSONString());
            fwriter.flush();
            fwriter.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
