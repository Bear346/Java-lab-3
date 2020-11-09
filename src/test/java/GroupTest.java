import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GroupTest {
    Group group = new Group("Manager");
    Student student1 = new Student("Иволгин Гаврила Казимирович", 1);
    Student student2 = new Student("Челомцев Кир Егорович", 2);
    Student student3 = new Student("Морякова Стела Рубеновна", 3);

    @Test
    public void checkGetHead(){
        group.addStudent(student3);
        group.chooseHead();
        assertEquals(student3, group.getHead());
    }

    @Test
    public void checkAverageMarkGroup(){
        group.addStudent(student1);
        group.addStudent(student2);
        student1.addMark(3);
        student1.addMark(3);
        student1.addMark(3);
        student2.addMark(5);
        student2.addMark(5);
        student2.addMark(5);
        assertEquals(4.0, group.averageMarkGroup(), 0.001);
    }

    @Test
    public void checkRemoveStudent(){
        group.addStudent(student1);
        group.addStudent(student2);
        group.removeStudent(student2);
        ArrayList<Student> expectedStudents = new ArrayList<Student>();
        expectedStudents.add(student1);
        assertEquals(1, group.students.size());
        assertEquals(expectedStudents, group.students);
    }
}
