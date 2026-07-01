package org.example.resources;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entities.Client;
import org.example.service.IClientService; // <-- Importation de l'interface

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    @EJB
    private IClientService clientService; // <-- Changé de ClientServiceImpl à IClientService

    @POST
    public Response creer(Client client){
        Client c = clientService.creerClient(client);
        return Response.status(Response.Status.CREATED)
                .entity(c)
                .build();
    }

    @GET
    public Response lister(){
        return Response.ok(clientService.listerClients()).build();
    }

    @GET
    @Path("/{id}")
    public Response consulter(@PathParam("id") Long id){
        return Response.ok(clientService.consulterClient(id)).build();
    }

    @PUT
    @Path("/{id}")
    public Response modifier(@PathParam("id") Long id, Client client){
        client.setId(id);
        clientService.modifierClient(client);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response supprimer(@PathParam("id") Long id){
        clientService.supprimerClient(id);
        return Response.noContent().build();
    }
}