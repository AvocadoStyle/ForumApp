/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webservice.Controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import com.webservice.modules.encryption.encrypt;
import com.webservice.modules.encryption.decrypt;
import java.util.StringTokenizer;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.Base64;
import com.webservice.modules.data.UsersData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author eden
 */
@Provider
public class SecurityFilter implements ContainerRequestFilter{

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
    private static final String SECURED_URL_PREFIX = "forums";
    

    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
//        System.out.println("debug1");
        if(requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)){
//            System.out.println("debug2");

            List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
//            System.out.println("authHeader is: " + authHeader);
            if(authHeader != null && !authHeader.isEmpty() && authHeader.size() > 0){
//                System.out.println("debug3");

                String authToken = authHeader.get(0);
                authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
//                System.out.println("debug4");
                
                
                /**
                 * decode string:
                 */
//                byte[] decodedBytes = Base64.getDecoder().decode(authToken);
//                String decodedString = new String(decodedBytes);
                
                /**
                 * without decoding:
                 */
                String decodedString = authToken;


//                System.out.println("debug5");

                StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                String username = tokenizer.nextToken(); //user
                String password = tokenizer.nextToken(); // pass

//                if("user".equals(username) && "password".equals(password)){
//                    System.out.println("debug6");
//                    return;
//                }
                try{
//                    System.out.println("debug9 user="+username+" password=" + password);
                    Connection connection;
                    connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/reservation","admin","admin");
                    UsersData ud;
                    ud = new UsersData(connection);  
//                    System.out.println("debug10");
                    if(ud.isUserAndPassExists(username, password)){
//                        System.out.println("Authorized! wow!");
                        return;
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
        }
//        System.out.println("debug7");
//        System.out.println("Boo! you're not Authorized to get inside!");

        Response unauthorizedStatus = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("User cannot access the resource ")
                .build();
       requestContext.abortWith(unauthorizedStatus);
        }
    }
}
