package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/mobile")
public class MobileResource {
    List<String> mobileList = new ArrayList<>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMobileList() {
        return Response.ok(mobileList).build();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addNewMobile(String mobileName) {
        mobileList.add(mobileName);
        return Response.ok(mobileName).build();
    }

    @PUT
    @Path("/{oldmobilename}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateMobile(@PathParam("oldmobilename") String oldMobileName, @QueryParam("newmobilename") String newMobileName) {
        mobileList = mobileList.stream().map(mobile -> {
            if(mobile.equals(oldMobileName)) {
                return newMobileName;
            } else {

                return mobile;
            }
        }).toList();
        return Response.ok(mobileList).build();
    }

    @DELETE
    @Path("/{mobileToDelete}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMobile(@PathParam("mobileToDelete") String mobileName) {
        boolean isRemoved = mobileList.remove(mobileName);
        if (isRemoved) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
