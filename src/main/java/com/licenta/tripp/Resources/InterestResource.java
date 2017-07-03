package com.licenta.tripp.Resources;

import com.licenta.tripp.model.GooglePlaces;
import com.licenta.tripp.model.Place;
import com.licenta.tripp.model.PlacesResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Tudor on 6/28/2017.
 */
@Path("/interest")
public class InterestResource {


    @GET
    @Path("/getFromGoogle")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getFromGoogle(@QueryParam("interest") String interest,
                                  @QueryParam("city") String city){

        String searchQuery = interest +" " + city;

        try {
            GooglePlaces places = new GooglePlaces("AIzaSyDY7JCvsMM2LVIs_ASJgYrYCxrn8EJEQLs");
            PlacesResult result = places.searchText(searchQuery);

            System.out.println(result.getStatus());
            for (Place place : result)
                System.out.println(place.getName() + ", " + place.getFormattedAddress());

        } catch(Exception e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.status(Response.Status.OK).entity("Successfully get from google").build();
    }


}
