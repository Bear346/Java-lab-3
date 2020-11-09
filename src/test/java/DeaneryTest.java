import org.junit.Test;

import static org.junit.Assert.*;

public class DeaneryTest {
    Deanery deanery = new Deanery();
    Group group1 = new Group("Programmers");
    Group group2 = new Group("Manager");
    Group group3 = new Group("Testers");
    Student student1 = new Student("Иволгин Гаврила Казимирович", 1);
    Student student2 = new Student("Челомцев Кир Егорович", 2);
    Student student3 = new Student("Морякова Стела Рубеновна", 3);

    @Test
    public void headInitialize(){
        Student s1 = student1;
        Student s2= student2;
        Student s3 = student3;
        group1.addStudent(student1);
        group2.addStudent(student2);
        group3.addStudent(student3);
        deanery.groups.add(group1);
        deanery.groups.add(group2);
        deanery.groups.add(group3);
        deanery.headInitialize();
        assertEquals(s1, group1.getHead());
        assertEquals(s2, group2.getHead());
        assertEquals(s3, group3.getHead());
    }

    @Test
    public void addMarksForAll(){

        deanery.students.add(student1);
        deanery.students.add(student2);
        deanery.students.add(student3);
        deanery.addMarksForAll();
        assertNotNull(student1.marks);
        assertNotNull(student2.marks);
        assertNotNull(student3.marks);
    }

    @Test
    public void transferStudent(){

        group1.addStudent(student1);
        group2.addStudent(student2);
        group3.addStudent(student3);
        deanery.groups.add(group1);
        deanery.groups.add(group2);
        deanery.groups.add(group3);
        deanery.transferStudent(3, "Manager");
        assertEquals(1, deanery.groups.get(0).students.size());
        assertEquals(2, deanery.groups.get(1).students.size());
        assertEquals(0, deanery.groups.get(2).students.size());
    }
}
