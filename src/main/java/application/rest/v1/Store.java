package application.rest.v1;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import application.Utils;

@Path("/api/v1")
public class Store {
    Utils dbutils = new Utils();

    @GET
    @Path("/example")
    @Produces(MediaType.TEXT_PLAIN)
    public Response example() {
        List<String> list = new ArrayList<>();
        //return a simple list of strings
        list.add("Congratulations, your application is up and running");
        return Response.status(200).entity(list.toString()).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/items")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() {
        return Response.status(200).entity(dbutils.getItems()).build();
    }

    @PUT
    @Path("/items/{id}/{quantity}/{price}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addItems(@PathParam("id") String id, @PathParam("quantity") int quantity, @PathParam("price") double price) {
            dbutils.addItem(id, quantity, price);
            return Response.status(Response.Status.ACCEPTED).entity("New item added.").build();
    }
    
    // ADD DELETE ITEM FEATURE HERE - DELETE BACKEND FUNCTION

    @POST
    @Path("/price/{id}/{price}")
    @Consumes({"application/xml", "application/json"})
    @Produces(MediaType.TEXT_PLAIN)
    public Response updatePrice(@PathParam("id") String id, @PathParam("price") double price) {
            dbutils.updatePrice(id, price);
            return Response.status(Response.Status.ACCEPTED).entity("Updated price successfully.").build();
    }

    @GET
    @Path("/price/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getPrice(@PathParam("id") String id) {
        return Response.ok(dbutils.getPrice(id)).build();
    }

    @POST
    @Path("/quantity/{id}/{quantity}")
    @Consumes({"application/xml", "application/json"})
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateQuantity(@PathParam("id") String id, @PathParam("quantity") int quantity) {
            dbutils.updateQuantity(id, quantity);
            return Response.status(Response.Status.ACCEPTED).entity("Updated price successfully.").build();
    }

    @GET
    @Path("/quantity/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getQuantity(@PathParam("id") String id) {
        return Response.ok(dbutils.getQuantity(id)).build();
    }

}
