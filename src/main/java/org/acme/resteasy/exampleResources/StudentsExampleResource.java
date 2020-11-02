package org.acme.resteasy.exampleResources;

import org.acme.resteasy.exampleResources.interfaces.StudentsResource;
import org.acme.resteasy.model.ErrorMessage;
import org.acme.resteasy.model.Students;
import org.acme.resteasy.services.StudentService;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

public class StudentsExampleResource implements StudentsResource {

    StudentService studentService = new StudentService();

    public List<Students> printAllStudents(@QueryParam("year") int year,
                                           @QueryParam("start") int start,
                                           @QueryParam("size")int size)
    {
        if(year>=2000)
        {
            if(start<0 || size<0)
            {
                ErrorMessage errorMessage = new ErrorMessage("There is no content for start < 0 or size < 0",404);
                Response response = Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
                throw new WebApplicationException(response);
            }
            return studentService.filterStudentsByYear(year);
        }
        if(start >= 0 && size > 0)
        {
            return studentService.paginateStudets(start,size);
        }
        if(start<0 || (year<2000 && year>0) || year<0 || size<0)
        {
            ErrorMessage errorMessage = new ErrorMessage("There is no content for start < 0 or year < 2000 or size < 0",404);
            Response response = Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
            throw new WebApplicationException(response);
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