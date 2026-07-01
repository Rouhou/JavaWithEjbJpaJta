package org.example.resources;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entities.Compte;
import org.example.service.ICompteService; // <-- Importation de l'interface

@Path("/comptes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompteResource {

    @EJB
    private ICompteService compteService; // <-- Changé de CompteServiceImpl à ICompteService

    @POST
    public Response creer(Compte compte){
        return Response.status(Response.Status.CREATED)
                .entity(compteService.creerCompte(compte))
                .build();
    }

    @GET
    public Response lister(){
        return Response.ok(compteService.listerComptes()).build();
    }

    @GET
    @Path("/{id}")
    public Response consulter(@PathParam("id") Long id){
        return Response.ok(compteService.consulterCompte(id)).build();
    }

    @PUT
    @Path("/{id}")
    public Response modifier(@PathParam("id") Long id, Compte compte){
        compte.setId(id);
        compteService.modifierCompte(compte);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response supprimer(@PathParam("id") Long id){
        compteService.supprimerCompte(id);
        return Response.noContent().build();
    }
}