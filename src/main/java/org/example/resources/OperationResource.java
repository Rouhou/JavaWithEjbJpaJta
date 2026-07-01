package org.example.resources;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.dto.DepotRequest;
import org.example.dto.VirementRequest;
import org.example.service.IOperationService; // <-- Importation de l'interface

@Path("/operations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OperationResource {

    @EJB
    private IOperationService operationService; // <-- Changé de OperationServiceImpl à IOperationService

    @POST
    @Path("/depot")
    public Response depot(DepotRequest request){
        operationService.depot(
                request.getCompteId(),
                request.getMontant());
        return Response.ok("Dépôt effectué").build();
    }

    @POST
    @Path("/retrait")
    public Response retrait(DepotRequest request){
        operationService.retrait(
                request.getCompteId(),
                request.getMontant());
        return Response.ok("Retrait effectué").build();
    }

    @POST
    @Path("/virement")
    public Response virement(VirementRequest request){
        operationService.virement(
                request.getSourceId(),
                request.getDestinationId(),
                request.getMontant());
        return Response.ok("Virement effectué").build();
    }

    @GET
    public Response lister(){
        return Response.ok(operationService.listerOperation()).build();
    }
}