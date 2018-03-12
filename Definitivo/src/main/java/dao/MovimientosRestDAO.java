/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Movimiento;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author rafa
 */
public class MovimientosRestDAO {
      JsonFactory JSON_FACTORY = new JacksonFactory();
    HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
        @Override
        public void initialize(HttpRequest request) {
            request.setParser(new JsonObjectParser(JSON_FACTORY));

        }
    });
    GenericUrl url = new GenericUrl("http://localhost:8083/Banky/rest/alumnos");
    ObjectMapper objectMapper = new ObjectMapper();
    
    public List<Movimiento> verMovimiento(String a, String b, String c) throws IOException{
       HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
            requestGoogle.getHeaders().set("Apikey", "si");

      
        List<Movimiento> lista = objectMapper.readValue(requestGoogle.execute().parseAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Movimiento.class));
        return lista;
    }

    public Movimiento ingresar(String a, Date b, int c) throws IOException {
     
       ObjectMapper m = new ObjectMapper();
        url.set("alumno", m.writeValueAsString(a));
        HttpRequest requestGoogle = requestFactory.buildPutRequest(url, new JsonHttpContent(new JacksonFactory(), a));
requestGoogle.getHeaders().set("Apikey", "si");
        Movimiento json = requestGoogle.execute().parseAs(Movimiento.class);

        return json;
    
    }

    
}
