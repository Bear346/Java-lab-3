import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StudentTest {
    Student student1 = new Student("Морякова Стела Рубеновна", 1);
    Student student2 = new Student("Челомцев Кир Егорович", 2);
    Student student3 = new Student("Рыжанова Нелли Петровна", 3);

    @Test
    public void checkGetIdFio(){
        assertEquals(1, student1.getId());
        assertEquals("Морякова Стела Рубеновна", student1.getFio());
    }

    @Test
    public void checkAddMark(){
        student2.addMark(3);
        student2.addMark(4);
        student2.addMark(5);
        student2.addMark(2);
        ArrayList<Integer> expectedMarks = new ArrayList<Integer>();
        expectedMarks.add(3);
        expectedMarks.add(4);
        expectedMarks.add(5);
        expectedMarks.add(2);
        assertEquals(expectedMarks, student2.marks);
    }

    @Test
    public void checkAverageMark(){
        student3.addMark(2);
        student3.addMark(4);
        student3.addMark(5);
        student3.addMark(2);
        assertEquals(3.25, student3.averageMark(), 0.001);
    }


}
