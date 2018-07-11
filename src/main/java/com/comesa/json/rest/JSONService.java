package com.comesa.json.rest;

import com.comesa.client.JerseyClientGet;
import static com.comesa.client.JerseyClientGet.getAllTeams;
import com.google.gson.Gson;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.teams.teams;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


@Path("/json/get")
public class JSONService {
static Connection con;
	@GET
	@Path("/teams")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTeamInJSON() {
                    
            return com.comesa.client.JerseyClientGet.getAllTeams_As_String();
}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTeamInJSON(teams team) {

		String result = "Team saved : " + team;
		return Response.status(201).entity(result).build();
		
	}
        
        


	
}