package controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "WelcomeServlet", urlPatterns =
{
    "success"
})
public class WelcomeServlet extends HttpServlet
{

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
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        String uname, urole, isCaptchaValid;
        HttpSession session = request.getSession();
        try {
            uname = session.getAttribute("uname").toString();
            urole = session.getAttribute("urole").toString();
            isCaptchaValid = session.getAttribute("isCaptchaValid").toString();
        }
        catch (NullPointerException npe) {
            uname = "";
            urole = "";
            isCaptchaValid = "";
        }
        if (!uname.isEmpty() && !urole.isEmpty() && isCaptchaValid.equals("true")) {
            // RequestDispatcher rs = request.getRequestDispatcher("WEB-INF/success.jsp");
            // RequestDispatcher rs = request.getRequestDispatcher("WEB-INF/testhome.jsp");
            String redirectedLink = "";
            if (urole.equals("Admin")) {
                redirectedLink += "admin/applicants";
            }
            else if (urole.equals("Guest")) {
                // TODO redirect this to guest.jsp or guest-submitted.jsp
                redirectedLink += "checkGuest";
            }
            else {
                response.sendRedirect("index.jsp");
            }
            response.sendRedirect(redirectedLink);
        }
        else {
            response.sendRedirect("index.jsp");
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
            throws ServletException, IOException
    {
        processRequest(request, response);
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
