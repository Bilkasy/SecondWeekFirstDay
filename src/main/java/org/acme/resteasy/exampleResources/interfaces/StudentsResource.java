package org.acme.resteasy.exampleResources.interfaces;

import org.acme.resteasy.model.Students;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/students")
public interface StudentsResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Students> printAllStudents(@QueryParam("year") int year,
                                           @QueryParam("start") int start,
                                           @QueryParam("size")int size);
    @GET
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Students printAStudentById(@PathParam("studentId") long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Students  addAStudent(Students student);

    @PUT
    @Path("/{studentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Students  updateAStudent(@PathParam("studentId")long id, Students student);

    @DELETE
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Students  deleteAStudent(@PathParam("studentId")long id);
}
