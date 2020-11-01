package org.acme.resteasy.exampleResources.interfaces;

import org.acme.resteasy.model.Classes;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/classes")

public interface ClassesResource {

    @GET
    public List<Classes> printAllClasses(@QueryParam("id") int id,
                                         @QueryParam("start") int start,
                                         @QueryParam("size")int size);
    @GET
    @Path("/{className}")
    public Classes printAClassByName(@PathParam("className") String name);

    @POST
    public Classes  addAClass(Classes classes);

    @PUT
    @Path("/{className}")
    public Classes  updateAClass(@PathParam("className")String name, Classes classes);

    @DELETE
    @Path("/{className}")
    public Classes  deleteAClass(@PathParam("className")String name);
}
