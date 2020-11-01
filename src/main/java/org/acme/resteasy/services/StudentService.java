package org.acme.resteasy.services;

import org.acme.resteasy.Database.Database;
import org.acme.resteasy.model.ErrorMessage;
import org.acme.resteasy.model.Students;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.*;

public class StudentService {

     Map<Long,Students> studentsMap = Database.getAllStudents();
    public StudentService()
    {
        studentsMap.put(1L, new Students(1L,"Ahmed",2002,"MTE"));
        studentsMap.put(2L, new Students(2L,"Mohamed",2000,"MTE"));
        studentsMap.put(3L, new Students(3L,"Abdelrahman",2004,"CIE"));
        studentsMap.put(4L, new Students(4L,"Khaled",2003,"BME"));
        studentsMap.put(5L, new Students(5L,"Samir",2000,"BCE"));
    }
    public List<Students> getAllStudents ()
    {
        return new ArrayList<Students>(studentsMap.values());
    }
    public Students getOneStudentById(long id)
    {
        ErrorMessage errorMessage = new ErrorMessage("There is no student found for id: "+id,404);
        Response response = Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
        Students student = studentsMap.get(id);
        if(student == null)
        {
            throw new WebApplicationException(response);
        }

        return student;
    }
    public Students addStudent (Students student)
    {
        student.setId(studentsMap.size()+1);
        studentsMap.put(student.getId(),student);
        return student;
    }
    public List<Students> filterStudentsByYear(int year)
    {
        List<Students> filterList = new ArrayList<Students>();
        studentsMap.values().forEach(students -> {
            if(students.getEnrollmentYear() == year)
            {
                filterList.add(students);
            }
        });
        return filterList;
    }

    public List<Students> paginateStudets(int start, int size)
    {
        List<Students> paginatedList = new ArrayList<Students>(studentsMap.values());
        if (start + size > paginatedList.size())
        {
            ErrorMessage errorMessage = new ErrorMessage("You are exceeding the Paginated list limit",404);
            Response response = Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
            throw new WebApplicationException(response);
        }
        return paginatedList.subList(start,start+size);
    }



    public Students updateStudent(Students student)
    {
        studentsMap.put(student.getId(),student);
        return student;
    }
    public Students deleteStudent(long id)
    {
        return studentsMap.remove(id);
    }


}
