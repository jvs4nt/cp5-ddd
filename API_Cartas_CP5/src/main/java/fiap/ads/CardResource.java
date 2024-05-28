package fiap.ads;

import fiap.ads.models.Card;
import fiap.ads.repositories.CardRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("cards")
public class CardResource {

    private CardRepository cardRepo;

    public CardResource() {
        this.cardRepo = new CardRepository();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public Response getCards(){
        List<Card> cardList = cardRepo.getCards();
        return Response.ok(cardList).build();
    }

    @GET
    @Path("{id}")
    public Response getCard(@PathParam("id") int id){
        var card = cardRepo.getCard(id);
        if(card == null){
            return Response.status(404).entity("Card not found").build();
        }
        return Response.status(200).entity(card).build();
    }

    @POST
    public Response createCard(Card card) {
        if (card == null) {
            return Response.status(400).entity("Card cannot be null").build();
        }
        cardRepo.createCard(card);
        return Response.status(201).entity(card).build();
    }

    @PUT
    @Path("{id}")
    public Response updateCard(@PathParam("id") int id, Card card){
        cardRepo.updateCard(id, card);
        return Response.status(200).entity(card).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteCard(@PathParam("id") int id){
        cardRepo.deleteCard(id);
        return Response.status(200).build();
    }

}
