package es.um.sisdist.RestTest.first;

import java.net.URI;
import java.net.URISyntaxException;

import es.um.sisdist.RestTest.first.Resources.Item;
import es.um.sisdist.RestTest.first.Resources.ItemDetail;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// POJO, no interface no extends

//Sets the path to base URL + /hello
@Path("hello")
public class Hello
{
    // This method is called if TEXT_PLAIN is request
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello()
    {
        return "Hello Jersey";
    }

    // This method is called if XML is request
    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello()
    {
        return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
    }

    // This method is called if HTML is request
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello()
    {
        return "<html> " + "<title>" + "Hello Jersey" + "</title>"
                + "<body><h1>" + "Hello Jersey" + "</h1></body>" + "</html>";
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("r")
    public Response r() throws URISyntaxException
    {
        return Response.temporaryRedirect(new URI("http://www.um.es")).header("abc", "def").build();
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    @Path("item")
    public Item item()
    {
        Item i = new Item();
        i.setUri("abc in XML");
        ItemDetail id = new ItemDetail();
        id.setId(28);
        i.setItem_detail(id);
        return i;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("item")
    public Item itemJSON()
    {
        Item i = new Item();
        i.setUri("abc in JSON");
        ItemDetail id = new ItemDetail();
        id.setId(28);
        i.setItem_detail(id);
        return i;
    }

    @POST
    @Consumes(MediaType.TEXT_XML)
    @Path("item")
    public void newItem(Item i)
    {
        System.out.println("Recibido item: " + i.getUri());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("item")
    public void newItemJSON(Item i)
    {
        System.out.println("Recibido item: " + i.getUri());
    }

}
