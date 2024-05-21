package com.HuangYufan.week11;

import com.HuangYufan.DAO.BookDAO;
import com.HuangYufan.model.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DisplayImgServlet", value = "/viewbook")
public class DisplayImgServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public DisplayImgServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //int bookId = Integer.parseInt(request.getParameter("id"));
        int bookId = 2;
        BookDAO dao = new BookDAO();

        try {
            Book book = dao.get(bookId);

            request.setAttribute("book", book);

            String page = "/DisplayImg.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
