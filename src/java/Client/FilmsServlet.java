/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Familia
 */
@WebServlet(name = "FilmsServlet", urlPatterns = {"/FilmsServlet"})
public class FilmsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FilmsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Films: </h1>");
            
            FilmsClient client = new FilmsClient();
            String res = client.findAll_JSON(String.class);
            String id = request.getParameter("id");
            
            if (id.equals("")) {
                JSONArray json = new JSONArray(res);
                for (int i = 0; i < json.length(); i++) {

                    JSONObject film = json.getJSONObject(i); 
                    
                    String title = film.getString("title");
                    String director = film.getString("director");
                    String genre = film.getString("genre");
                    String release = film.getString("release");
                    String country = film.getString("country");
                    out.println("<br>Titulo: " + title + "<br>");
                    out.println("Director: " + director );
                    out.println("<br>Genero: " + genre + "<br>");
                    out.println("Lanzamiento: " + release);
                    out.println("<br>Pais: " + country + "<br>");
                    
                    out.println("<br>--------------------<br>");
                }
            }else{
                JSONArray json = new JSONArray(res);
  
                for (int i = 0; i < json.length(); i++) {

                    JSONObject film = json.getJSONObject(i); 
                    String idfilm = film.getString("id");
                    
                    if (id.equals(idfilm)) {
                        
                    String title = film.getString("title");
                    String director = film.getString("director");
                    String genre = film.getString("genre");
                    String release = film.getString("release");
                    String country = film.getString("country");
                    out.println("<br>Titulo: " + title + "<br>");
                    out.println("Director: " + director );
                    out.println("<br>Genero: " + genre + "<br>");
                    out.println("Lanzamiento: " + release);
                    out.println("<br>Pais: " + country + "<br>");
                    }
                }
                out.println("<br>-------------------<br>");
            }
         
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
        } catch (JSONException ex) {
            Logger.getLogger(FilmsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (JSONException ex) {
            Logger.getLogger(FilmsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
