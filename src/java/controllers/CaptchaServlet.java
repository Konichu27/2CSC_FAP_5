package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CaptchaServlet extends HttpServlet
{
    private static int captchaLength;
    
    public void init(ServletConfig config) throws ServletException {
        captchaLength = Integer.parseInt(config.getInitParameter("captchaLength"));
    }
    
    static boolean checkCaptcha(String captcha, String user_captcha)
    {
            return captcha.equals(user_captcha);
    }

    // Generates a CAPTCHA of given length
    static String generateCaptcha(int n)
    {
            //to generate random integers in the range [0-61]
            Random rand = new Random(62); 

            // Characters to be included
            String chrs = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

            // Generate n characters from above set and
            // add these characters to captcha.
            String captcha = "";
            while (n-- > 0){
                    int index = (int)(Math.random()*62);
                    captcha+=chrs.charAt(index);
            }

            return captcha;
    }
    
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
        String uname, urole;
        HttpSession session = request.getSession(); 
        try {
            uname = session.getAttribute("uname").toString();
            urole = session.getAttribute("urole").toString();
        }
        catch (NullPointerException npe) {
            uname = "";
            urole = "";
        }
        if (!uname.isEmpty() && !urole.isEmpty()) {
            session.setAttribute("captcha", generateCaptcha(captchaLength));
            RequestDispatcher rs = request.getRequestDispatcher("WEB-INF/captcha.jsp");
            rs.forward(request, response);
        }
        else {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
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
