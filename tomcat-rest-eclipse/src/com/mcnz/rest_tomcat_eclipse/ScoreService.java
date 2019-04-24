package com.mcnz.rest_tomcat_eclipse;

import javax.ws.rs.*;

@Path("/")
public class ScoreService {
                @GET
                @Path("/score/wins")
                @Produces("text/plain")
                public int getWins() {
                                return Score.WINS;
                }
 
                @GET
                @Path("/score/losses")
                @Produces("text/plain")
                public int getLosses() {
                                return Score.LOSSES;
                }
 

                @GET
                @Path("/score/ties")
                @Produces("text/plain")
                public int getTies() {
                                return Score.TIES;
                }
                
                @POST
                @Path("/score/wins")
                @Produces("text/plain")
                public int increaseWins() {
                                return Score.WINS++;
                }
 
                @POST
                @Path("/score/losses")
                @Produces("text/plain")
                public int increaseLosses() {
                                return Score.LOSSES++;
                }
 

                @POST
                @Path("/score/ties")
                @Produces("text/plain")
                public int increaseTies() {
                                return Score.TIES++;
                }
                @GET
                @Path("/score")
                @Produces("application/json")
                public String getScore() {
                                String pattern = "{ \"wins\":\"%s\", \"losses\":\"%s\", \"ties\": \"%s\"}";
                                return String.format(pattern, Score.WINS, Score.LOSSES, Score.TIES);
                } 
}
