package com.example.app.filter;

import com.example.app.model.user.UserType;
import com.example.app.properties.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AccessFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AccessFilter.class);
    private static String mainPage;
    private static List<String> outOfControl = new ArrayList<>();

    public void init(FilterConfig fConfig) {
        LOGGER.info("Filter initialization starts");
        MappingProperties properties = MappingProperties.getInstance();
        mainPage = properties.getProperty("mainPage");

        // out of control
        outOfControl = asList(fConfig.getInitParameter("out-of-control"));
        LOGGER.info("Out of control commands --> " + outOfControl);

        LOGGER.info("Filter initialization finished");
    }

    public void destroy() {
        LOGGER.info("Filter destruction starts");
        // do nothing
        LOGGER.info("Filter destruction finished");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("Filter starts");

        if (accessAllowed(request)) {
            LOGGER.info("Filter finished");
            chain.doFilter(request, response);
        } else {
            String errorMessage = "You do not have permission to access the requested resource";
            LOGGER.info("Set the request attribute: errorMessage --> " + errorMessage);
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher(mainPage).forward(request, response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String command = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (!"/".equals(command)) {
            if(command.endsWith("/")) {
                command = command.substring(0, command.length() - 1);
            }
            if(command.startsWith("/")) {
                command = command.substring(1);
            }
            if(command.indexOf('?') > 0) {
                command.substring(command.lastIndexOf('?'));
            }
        }
        if (command.isEmpty()) {
            LOGGER.info("Filter command isEmpty");
            return false;
        }

        if (outOfControl.contains(command)){
            LOGGER.info("Filter command: '"+command+"' outOfControl");
            return true;
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            LOGGER.info("Filter command session is null");
            return false;
        }
        if (session.getAttribute("role") != null) {
            UserType userRole = UserType.valueOf((String) session.getAttribute("role"));
            return command.contains(userRole.toString().toLowerCase()+"/");
        } else {
            return false;
        }
    }



    /**
     * Extracts parameter values from string.
     *
     * @param str
     *            parameter values string.
     * @return list of parameter values.
     */
    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) list.add(st.nextToken());
        return list;
    }

}
