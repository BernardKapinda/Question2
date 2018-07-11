package com.comesa.client;


import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.teams.teams;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class JerseyClientGet {
 static String a;
	public static void main(String[] args) {

           //System.out.print(getAllTeams());
         // a =  convertTeamsToJson(getAllTeams());
         // System.out.print(a);
         //me
            try {

			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:8083/RESTful_COMESA_API/rest/json/get/teams");

			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
        
        public static String getAllTeams_As_String(){
        a = convertTeamsToJson(getAllTeams());
        return a;
        }
        
        public static ArrayList<teams> getAllTeams(){
     ArrayList<teams> all_teams = new ArrayList<teams>();
      try{
          
      Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fifa18?" + "user=root&password=20152016"); 
        
      PreparedStatement st =con.prepareStatement("select * from team");
      ResultSet rs = st.executeQuery();
       
      while(rs.next()){
       teams team = new  teams();
         team.setId(rs.getInt(1));
         team.setCountry(rs.getString(2));
         team.setAlternate_name(rs.getString(3));
         team.setFifa_code(rs.getString(4));
         team.setGroup_id(rs.getInt(5));
         team.setGroup_letter(rs.getString(6));
         
       
          all_teams.add(team);
          
      }
    
        }catch(Exception e){
        
        }
      
      return all_teams;
}
        
        public static String convertTeamsToJson(ArrayList t){
      
    Gson gson = new Gson();
    String tojson = gson.toJson(t);
    return (tojson);
}

}