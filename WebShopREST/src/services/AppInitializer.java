package services;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

import dao.UserDAO;

@HandlesTypes({UserDAO.class})
public class AppInitializer implements ServletContainerInitializer{

	@Override
	public void onStartup(Set<Class<?>> classes, ServletContext ctx) throws ServletException {
		
		for (Class<?> cls : classes) {
                try {
                	if(cls == UserDAO.class) {
                		UserDAO userDAO = new UserDAO(ctx.getRealPath(""),ctx);
                		ctx.setAttribute("userDAO", userDAO);
                	}
                } catch (Throwable ex) {
                    throw new ServletException(
                              "Failed to instantiate WebApplicationInitializer" +
                                        " class", ex);
                }
            }
        }
		
}
