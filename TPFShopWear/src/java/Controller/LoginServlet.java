/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import DAO.UserDAO;
import Models.Account;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Acer
 */
public class LoginServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("Login/Login.jsp").forward(request, response);

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

        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        
        System.out.println(userName);
        System.out.println(passWord);
        // Tạo đối tượng DAO và thực hiện truy vấn tài khoản
        AccountDAO aDAO = new AccountDAO();
//        Account acc = aDAO.login(userName,passWord);
        Account acc = aDAO.login(userName, passWord);
        // Kiểm tra tài khoản và mật khẩu
        
        if (acc != null) {
            // Nếu đăng nhập thành công, lưu thông tin vào session
            HttpSession session = request.getSession();
            session.setAttribute(userName, acc.getUsername());
//            session.setAttribute("userName", acc.getUsername());
            String message = "Login successful! with account " + acc.getUsername();
            request.setAttribute("message",message);

            // Chuyển hướng tới trang test.jsp
            request.getRequestDispatcher("Home/Home.jsp").forward(request, response);
        } else {
            // Nếu đăng nhập thất bại, thông báo lỗi
            request.setAttribute("message", " Username or Password is not correct! ");
            request.getRequestDispatcher("Home/test.jsp").forward(request, response);
        }
    }

    // Hàm kiểm tra mật khẩu bằng cách so sánh mật khẩu băm
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
