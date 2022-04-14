package es.um.sisdist.videofaces.backend.Service;

import java.util.HashMap;
import java.util.Map;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

// https://stackoverflow.com/a/45627178/62365

@ApplicationPath("/")
public class ServiceApp extends Application
{
    @Override
    public Map<String, Object> getProperties()
    {
        Map<String, Object> props = new HashMap<>();
        props.put("jersey.config.server.provider.classnames",
		       "org.glassfish.jersey.media.multipart.MultiPartFeature");
        return props;
    }
}
