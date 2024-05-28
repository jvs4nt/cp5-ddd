package fiap.ads;

import fiap.ads.models.Collection;
import fiap.ads.repositories.CollectionRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("collections")
public class CollectionResource {

    private CollectionRepository collectionRepo;

    public CollectionResource() {
        this.collectionRepo = new CollectionRepository();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public Response getCollections(){
        List<Collection> collectionList = collectionRepo.getCollections();
        return Response.ok(collectionList).build();
    }

    @GET
    @Path("{id}")
    public Response getCollection(@PathParam("id") int id){
        var collection = collectionRepo.getCollection(id);
        if(collection == null){
            return Response.status(404).entity("Collection not found").build();
        }
        return Response.status(200).entity(collection).build();
    }

    @POST
    public Response createCollection(Collection collection) {
        if (collection == null) {
            return Response.status(400).entity("Collection cannot be null").build();
        }
        collectionRepo.createCollection(collection);
        return Response.status(201).entity(collection).build();
    }

    @PUT
    @Path("{id}")
    public Response updateCollection(@PathParam("id") int id, Collection collection){
        collectionRepo.updateCollection(id, collection);
        return Response.status(200).entity(collection).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteCollection(@PathParam("id") int id){
        collectionRepo.deleteConnection(id);
        return Response.status(200).build();
    }

}
