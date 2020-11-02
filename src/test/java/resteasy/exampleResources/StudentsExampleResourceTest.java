package resteasy.exampleResources;

import org.acme.resteasy.Database.Database;
import org.acme.resteasy.exampleResources.StudentsExampleResource;
import org.acme.resteasy.model.Classes;
import org.acme.resteasy.model.Students;
import org.acme.resteasy.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import javax.ws.rs.WebApplicationException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

public class StudentsExampleResourceTest {

    StudentService studentService = new StudentService();
    StudentsExampleResource studentsExampleResource = new StudentsExampleResource();

    @BeforeEach
    void init() {
    }

    // GET Test
    @Test
    void getAllTest() {
        studentService = mock(StudentService.class);
        List<Students> expected = new ArrayList<Students>(Arrays.asList(
                new Students(1L, "Ahmed", 2001, "MTE"),
                new Students(2L, "Mohamed", 2000, "MTE"),
                new Students(3L, "Abdelrahman", 2004, "CIE"),
                new Students(4L, "Khaled", 2003, "BME"),
                new Students(5L, "Samir", 2000, "BCE")));

        when(studentService.getAllStudents()).thenReturn(expected);
        List<Students> actual = studentService.getAllStudents();
        verify(studentService, times(1)).getAllStudents();
        assertEquals(5, actual.size(), "The returned size must be the same as Expected");
    }

    @Test
    void getPositiveValidYearTest() {
        studentService = mock(StudentService.class);
        List<Students> expected = new ArrayList<Students>(Arrays.asList(
                new Students(1L, "Ahmed", 2000, "MTE"),
                new Students(5L, "Samir", 2000, "BCE")));

        when(studentService.filterStudentsByYear(2000)).thenReturn(expected);
        List<Students> actual = studentService.filterStudentsByYear(2000);
        verify(studentService, times(1)).filterStudentsByYear(2000);
        assertEquals(2, actual.size(), "The returned size must be the same as Expected");
    }

    @Test
    void getPositiveValidSizeTest() {
        studentService = mock(StudentService.class);
        List<Students> expected = new ArrayList<Students>(Arrays.asList(
                new Students(2L, "Mohamed", 2000, "MTE"),
                new Students(3L, "Abdelrahman", 2004, "CIE"),
                new Students(4L, "Khaled", 2003, "BME"),
                new Students(5L, "Samir", 2000, "BCE")));

        when(studentService.paginateStudets(0, 4)).thenReturn(expected);
        List<Students> actual = studentService.paginateStudets(0,  4);
        verify(studentService, times(1)).paginateStudets(0, 4);
        assertEquals(expected.size(), actual.size(), "The returned size must be the same as Expected");
    }
    @Test
    void getNegativeInValidYearTest() {
        studentService = Mockito.spy(StudentService.class);
        assertThrows(WebApplicationException.class, () -> {
            studentService.filterStudentsByYear(-5);
        });
    }

    @Test
    void getInvalidSizeTest() {
        studentService = Mockito.spy(StudentService.class);
        assertThrows(WebApplicationException.class, () -> {
            studentService.paginateStudets( 0, -2);
        });
    }

    @Test
    void getValidYearAndInvalidStart() {
        studentService = Mockito.spy(StudentService.class);
        assertThrows(WebApplicationException.class, () -> {
            studentService.paginateStudets( -5, 0);
        });
    }

    @Test
    void getExceedingPaginationLimitsTest()
    {
        studentService = Mockito.spy(StudentService.class);
        assertThrows(WebApplicationException.class, () -> {
            studentService.paginateStudets( 2, 100);
        });
    }
    // Get the whole list with spying
    @Test
    void getAllDetailedTest(){
        studentService = Mockito.spy(StudentService.class);
        List <Students> expected =  new ArrayList<Students>(Arrays.asList(
                new Students(1L,"Ahmed",2002,"MTE"),
                new Students(2L,"Mohamed",2000,"MTE"),
                new Students(3L,"Abdelrahman",2004,"CIE"),
                new Students(4L,"Khaled",2003,"BME"),
                new Students(5L,"Samir",2000,"BCE")));

        List <Students> actual =  studentService.getAllStudents();
        verify(studentService,times(1)).getAllStudents();
        assertEquals(5,actual.size(),"The returned size must be the same as Expected");
        assertEquals(Arrays.toString(new List[]{expected}), Arrays.toString(new List[]{actual}),
                "Student lists should be the same");
    }

    @Test
    void deleteTest()
    {
        studentService = Mockito.spy(StudentService.class);
        List <Students> expected =  new ArrayList<Students>(Arrays.asList(
                new Students(2L,"Mohamed",2000,"MTE"),
                new Students(3L,"Abdelrahman",2004,"CIE"),
                new Students(4L,"Khaled",2003,"BME"),
                new Students(5L,"Samir",2000,"BCE")));
        studentService.deleteStudent(1);
        List <Students> actual =  studentService.getAllStudents();
        assertEquals(4,actual.size(),"The returned size must be the same as Expected");
        assertEquals(Arrays.toString(new List[]{expected}), Arrays.toString(new List[]{actual}),
                "Student lists should be the same");
    }
    @Test
    void postTest()
    {
        studentService = Mockito.spy(StudentService.class);
        Students student = new Students(8L,"Moneer",2003,"MTE");
        List <Students> expected =  new ArrayList<Students>(Arrays.asList(
                new Students(1L,"Ahmed",2002,"MTE"),
                new Students(2L,"Mohamed",2000,"MTE"),
                new Students(3L,"Abdelrahman",2004,"CIE"),
                new Students(4L,"Khaled",2003,"BME"),
                new Students(5L,"Samir",2000,"BCE"),
                new Students(6L,"Moneer",2003,"MTE")));
        studentService.addStudent(student);
        List <Students> actual =  studentService.getAllStudents();
        assertEquals(Arrays.toString(new List[]{expected}), Arrays.toString(new List[]{actual}),
                "Student lists should be the same");


    }



    }
