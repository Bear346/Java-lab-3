import java.io.File;

public class main {
    public static void main(String[] args) throws Exception {
        Deanery deanery = new Deanery();
        deanery.importStudents("students.json");
        deanery.importGroup("groups.json");
        deanery.headInitialize();
        deanery.addMarksForAll();
        deanery.transferStudent(15, "Managers");
        deanery.deductStudent();
        deanery.writeToFile("result.json");
}
}
