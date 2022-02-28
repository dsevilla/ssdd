package es.um.sisdist.videofaces.backend.Service;

import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/")
public class ServiceApp extends Application
{

	@Override
	public Set<Object> getSingletons()
	{
		// TODO Auto-generated method stub
		return super.getSingletons();
	}
    // Vacía a propósito.
}
