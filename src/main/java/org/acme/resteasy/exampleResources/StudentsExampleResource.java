package org.acme.resteasy.exampleResources;

import org.acme.resteasy.exampleResources.interfaces.StudentsResource;
import org.acme.resteasy.model.Students;
import org.acme.resteasy.services.StudentService;
import javax.ws.rs.*;
import java.util.List;

public class StudentsExampleResource implements StudentsResource {

    StudentService studentService = new StudentService();

    public List<Students> printAllStudents(@QueryParam("year") int year,
                                           @QueryParam("start") int start,
                                           @QueryParam("size")int size)
    {
        if(year>0)
        {
            return studentService.filterStudentsByYear(year);
        }
        if(start >= 0 && size > 0)
        {
            return studentService.paginateStudets(start,size);
        }
        return studentService.getAllStudents();
    }

    public Students printAStudentById(@PathParam("studentId") long id)
    {
        return studentService.getOneStudentById(id);
    }

    public Students  addAStudent(Students student)
    {
        return studentService.addStudent(student);
    }

    public Students  updateAStudent(@PathParam("studentId")long id, Students student)
    {
        student.setId(id);
        return studentService.updateStudent(student);
    }

    public Students  deleteAStudent(@PathParam("studentId")long id)
    {
        return studentService.deleteStudent(id);
    }
}