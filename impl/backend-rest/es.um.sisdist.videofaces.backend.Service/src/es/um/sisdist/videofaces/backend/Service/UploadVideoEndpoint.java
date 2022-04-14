package es.um.sisdist.videofaces.backend.Service;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/uploadVideo")
public class UploadVideoEndpoint
{
    // private AppLogicImpl impl = AppLogicImpl.getInstance();

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadVideo(@FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception
    {
	// El fichero que se recibe se copia en /tmp/output
        File targetFile = new File("/tmp/output");

        java.nio.file.Files.copy(fileInputStream, targetFile.toPath(),
			StandardCopyOption.REPLACE_EXISTING);

        fileInputStream.close();
        return Response.ok(fileMetaData.getFileName()).build();
    }
}
