/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class search extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String id=request.getParameter("search");
        ResultSet result=null;
        
          try {
            HttpSession s1=request.getSession();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/lis","root","Sabi@319");
            String query="SELECT * FROM books_info WHERE Branch LIKE ?";
             PreparedStatement st=conn.prepareStatement(query);
             st.setString(1, id);
             
            result=st.executeQuery();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(search.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("text/html;charset=UTF-8"); 
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<script src=\"https://kit.fontawesome.com/c4254e24a8.js\" crossorigin=\"anonymous\">	\n" +
"			</script>\n" +
"			<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" 	rel=\"stylesheet\"                                   integrity=\"sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM\" crossorigin=\"anonymous\">\n" +
"			<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-                          geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz\" crossorigin=\"anonymous\"></script>\n" +
"");
            out.println(" <link rel='stylesheet' href='assets/css/formstyles.css'>");
            out.println("<title>Servlet signup</title>");            
            out.println("</head>");
            out.println("<body class='bg_color' >");
            //out.println("<h1>Servlet signup at " + request.getContextPath() + "</h1>");
             out.println("<center><h3 class='mt-3' style='text-decoration: underline;'>Books Details</h3></center>");
            out.println(" <table class='tables mt-3' border='4' align='center' cellpadding='5' width='1200'>");
            out.println("<thead class='head_color'>");
            out.println("<tr>");               
                            
            out.println("<th scope='col'width='250'>BookName</th>");
            out.println("<th scope='col'width='200'>ISBN</th>");
            out.println("<th scope='col'width='160'>AuthorName</th>");
            out.println("<th scope='col'width='120'>Branch</th>");
            out.println("<th scope='col' width='50'>RackNo</th>");
             out.println("<th scope='col'width='50'>Copies</th>");
              
            out.println("</tr>");
            out.println("</thead>");
            
            try {
                while(result.next()){
             out.println("<tr>");               
            out.println("<td scope='col' width='50'>" +result.getString("BookName")+ "</td>");                
            out.println("<td scope='col'width='250'>"+ result.getString("ISBN")+"</td>");
            out.println("<td scope='col'width='160'>"+result.getString("AuthorName")+"</td>");
            out.println("<td scope='col'width='120'>"+result.getString("Branch")+"</td>");
            out.println("<td scope='col' width='150'>"+result.getString("RackNumber")+"</td>");
             out.println("<td scope='col'width='80'>"+result.getString("Copies")+"</td>");
             
          
            out.println("</tr>");
                }
              }
             catch (SQLException ex) {
                Logger.getLogger(search.class.getName()).log(Level.SEVERE, null, ex);
            }
    
          
            out.println(" </table >");
           
             /* try {
                while(result.next()){
                    out.println(result.getString("username")+" "+result.getString("password"));
                }
              }
             catch (SQLException ex) {
                Logger.getLogger(viewbooks.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
            
            
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(search.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(search.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}