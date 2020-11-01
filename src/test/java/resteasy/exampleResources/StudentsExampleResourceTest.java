package resteasy.exampleResources;

import org.acme.resteasy.exampleResources.StudentsExampleResource;
import org.acme.resteasy.model.Students;
import org.acme.resteasy.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

public class StudentsExampleResourceTest {

    StudentService studentService;
    StudentsExampleResource studentsExampleResource;
    @BeforeEach
    void init()
    {
        Students x = mock(Students.class);

        studentService = new StudentService();
       studentsExampleResource = new StudentsExampleResource();
    }
    // GET Test
    @Test
    void getAllTest(){
        List<Students> expected =  studentService.getAllStudents();
        List<Students> actual = studentsExampleResource.printAllStudents(0,0,0);
        assertIterableEquals(expected,actual,"There is a problem in the GET Method");
    }

    @Test
    void postTest (){
        Students actualStudent = new Students(6,"Samir",2005,"MTE");
        studentsExampleResource.addAStudent(actualStudent);
        Students expectedStudent = studentService.getOneStudentById(6);
        assertEquals(expectedStudent,actualStudent,"There is a problem in the Post Method");
    }



}
