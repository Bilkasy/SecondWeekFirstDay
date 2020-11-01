package org.acme.resteasy.exampleResources;

import org.acme.resteasy.exampleResources.interfaces.ClassesResource;
import org.acme.resteasy.model.Classes;
import org.acme.resteasy.model.Students;
import org.acme.resteasy.services.ClassService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


public class ClassExampleResource implements ClassesResource {
    ClassService classService = new ClassService();


    public List<Classes> printAllClasses(@QueryParam("id") int id,
                                         @QueryParam("start") int start,
                                         @QueryParam("size")int size)
    {
        if(id>0)
        {
            return classService.filterClassesById(id);
        }
        if(start>=0 && size>0)
        {
            return classService.paginateClasses(start,size);
        }
        return classService.getAllClasses();
    }

    public Classes printAClassByName(@PathParam("className") String name)
    {
        return classService.getOneClasstByName(name);
    }

    public Classes  addAClass(Classes classes)
    {
        return classService.addClass(classes);
    }

    public Classes  updateAClass(@PathParam("className")String name, Classes classes)
    {
        classes.setName(name);
        return classService.updateClass(classes);
    }

    public Classes  deleteAClass(@PathParam("className")String name)
    {
        return classService.deleteClass(name);
    }



}
