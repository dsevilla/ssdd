package es.um.sisdist.backend.Service;

import java.net.URI;

import org.glassfish.jersey.client.ClientConfig;

//import com.fasterxml.jackson.core.util.JacksonFeature;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriBuilder;

public class TestClient
{
    public static void main(String[] args)
    {
        ClientConfig config = new ClientConfig();
        // config.property(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
//        ClientBuilder.newBuilder().register(JacksonFeature.class);
        Client client = ClientBuilder.newClient(config);
        WebTarget service = client.target(getBaseURI());
        // Fluent interfaces
        System.out.println(service.path("jaxrs").path("hello").request(
                MediaType.TEXT_PLAIN).get(String.class).toString());
        // Get plain text
        System.out.println(service.path("jaxrs").path("hello").request(
                MediaType.TEXT_PLAIN).get(String.class));
        // Get XML
        System.out.println(service.path("jaxrs").path("hello").request(
                MediaType.TEXT_XML).get(String.class));
        // The HTML
        System.out.println(service.path("jaxrs").path("hello").request(
                MediaType.TEXT_HTML).get(String.class));
    }

    private static URI getBaseURI()
    {
        return UriBuilder.fromUri(
                "http://localhost:8080/es.um.sisdist.RestTest").build();
    }
}