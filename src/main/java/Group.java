import java.util.ArrayList;
import java.util.Random;

public class Group {
    String title;
    public ArrayList<Student> students;
    private Student head;

    Group(String groupMame){
        this.title = groupMame;
        this.students = new ArrayList<Student>();
        head = null;
    }

    public void addStudent(Student student){
        this.students.add(student);
        student.addGroup(this);
    }

    public Student chooseHead(){
        if (students.size() > 0 && this.head==null) {
            Random rndm = new Random();
            this.head = students.get(rndm.nextInt(students.size()));
        } else {
            return head;
        }
        return null;
    }

    public Student searchStudentName(String name) {
        for (Student student: this.students){
            if (name == student.getFio()){
                return student;
            }
        }
        return null;
    }

    public Student searchStudentId(int id) {
        for (Student student: this.students){
            if (id == student.getId()){
                return student;
            }
        }
        return null;
    }

    public double averageMarkGroup(){
        float general = 0;
        for (Student student: students){
            general += student.averageMark();
        }
        return general / students.size();
    }

    public void removeStudent(Student student){
        students.remove(student);
    }

    public String getTitle(){
        return this.title;
    }

    public Student getHead(){
        return this.head;
    }

    public ArrayList<Student> getStudents(){
        return this.students;
    }
}
