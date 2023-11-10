package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import model.BookHelper;

@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteBookServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookHelper dao = new BookHelper();
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		Book bookToDelete = dao.searchForBookById(tempId);
		dao.deleteBook(bookToDelete);
		getServletContext().getRequestDispatcher("/ViewAllBooksServlet").forward(request, response);
	}
}