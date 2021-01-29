package com.example.app;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;


@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    Context ctx;
    @Resource(name="jdbc/mysql")
    private DataSource ds;

    public String getData() {
        StringBuilder text = new StringBuilder();
        try {
            ResultSet rs = null;
            Statement stmt = null;
            Connection con = null;
            try {
                con = ds.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery("select * from test");
                while(rs.next()){
                    //System.out.println(rs.getInt(1)+"  "+rs.getString(2));
                    text.append(rs.getInt(1)+"  "+rs.getString(2)+"<BR>");
                }
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (rs != null) {
                    try { rs.close(); } catch (SQLException e) { System.out.println(e.getMessage()); }
                }
                if (stmt != null) {
                    try { stmt.close(); } catch (SQLException e) { System.out.println(e.getMessage()); }
                }
                if (con != null) {
                    try { con.close(); } catch (SQLException e) { System.out.println(e.getMessage()); }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return text.toString();
    }
    public void init() {

        try {
            ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        }
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");


        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<div>" + getData() + "</div>");
        out.println("<a href=\"\\app\">Home</a>");
        String name = request.getParameter("name");
        if (name!= null){
            out.println(name);
        } else {
            name = "DefaultName";
        }
        out.println("</body></html>");

        request.setAttribute("result", name);
        try {
            request.getRequestDispatcher("result.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");


        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<div>" + getData() + "</div>");
        out.println("<a href=\"\\app\">Home</a>");
        String name = request.getParameter("name");
        if (name!= null){
            out.println(name);
        }
        out.println("</body></html>");
    }


    public void destroy() {
    }
}