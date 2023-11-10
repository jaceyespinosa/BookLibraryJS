package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import model.BookHelper;

@WebServlet("/EditBookServlet")
public class EditBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditBookServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookHelper dao = new BookHelper();
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String genre = request.getParameter("genre");
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		Book bookToUpdate = dao.searchForBookById(tempId);
		bookToUpdate.setTitle(title);
		bookToUpdate.setAuthor(author);
		bookToUpdate.setGenre(genre);
		dao.updateBook(bookToUpdate);
		getServletContext().getRequestDispatcher("/ViewAllBooksServlet").forward(request, response);
	}
}