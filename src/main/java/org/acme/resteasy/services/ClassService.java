package org.acme.resteasy.services;

import org.acme.resteasy.Database.Database;
import org.acme.resteasy.model.Classes;
import org.acme.resteasy.model.ErrorMessage;
import org.acme.resteasy.model.Students;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ClassService {

    Map<String, Classes> classesMap = Database.getAllClasses();
//    List<Students> studentsList = new ArrayList<Students>(Database.getAllStudents().values());
    public ClassService()
    {
        classesMap.put("Physics", new Classes(1L,"Physics",new ArrayList<Students>(Arrays.asList(
                (new Students(1L,"Abraham",2001,"MTE")),
                (new Students(2L,"Khaled",2003,"BCE")),
                (new Students(3L,"Nagwa",2001,"CIE"))  ))));
        classesMap.put("Maths2", new Classes(2L,"Maths2",new ArrayList<Students>(Arrays.asList(
                (new Students(4L,"Somaya",2004,"MTE")),
                (new Students(5L,"Sabrina",2006,"BCE")),
                (new Students(6L,"Karim",2001,"CIE"))  ))));
        classesMap.put("Java", new Classes(3L,"Java",new ArrayList<Students>(Arrays.asList(
                (new Students(7L,"AAA",2003,"BCE")),
                (new Students(8L,"ANN",2000,"MTE")),
                (new Students(9L,"AKK",2000,"CIE"))  ))));
        classesMap.put("Chemistry", new Classes(4L,"Chemistry",new ArrayList<Students>(Arrays.asList(
                (new Students(10L,"ALL",2001,"BCE")),
                (new Students(11L,"APP",2000,"CIE")),
                (new Students(12L,"AJJ",2002,"MTE"))  ))));
        classesMap.put("Electronics", new Classes(5L,"Electronics",new ArrayList<Students>(Arrays.asList(
                (new Students(13L,"Waled",2002,"MTE")),
                (new Students(14L,"Morsi",2005,"CIE")),
                (new Students(15L,"Islam",2003,"BCE"))  ))));
    }
    public List<Classes> getAllClasses()
    {
        return new ArrayList<Classes>(classesMap.values());
    }
    public Classes getOneClasstByName(String name)
    {
        ErrorMessage errorMessage = new ErrorMessage("There is no Class found for name: "+name,404);
        Response response = Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
        Classes classes = classesMap.get(name);
        if(classes == null)
        {
            throw new WebApplicationException(response);
        }

        return classes;
    }
    public List<Classes> filterClassesById(int id)
    {
        List<Classes> filterList = new ArrayList<Classes>();
        classesMap.values().forEach(classes -> {
            if(classes.getId() == id)
            {
                filterList.add(classes);
            }
        });
        return filterList;
    }

    public List<Classes> paginateClasses(int start, int size)
    {
        List<Classes> paginatedList = new ArrayList<Classes>(classesMap.values());
        if (start + size > paginatedList.size())
        {
            ErrorMessage errorMessage = new ErrorMessage("You are exceeding the Paginated list limit",404);
            Response response = Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
            throw new WebApplicationException(response);
        }
        return paginatedList.subList(start,start+size);
    }

    public Classes addClass (Classes classes)
    {
        classes.setId(classesMap.size()+1);
        classesMap.put(classes.getName(),classes);
        return classes;
    }
    public Classes updateClass(Classes classes)
    {
        classesMap.put(classes.getName(),classes);
        return classes;
    }
    public Classes deleteClass(String name)
    {
        return classesMap.remove(name);
    }



}
