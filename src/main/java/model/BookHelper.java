package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class BookHelper {
	
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BookLibrary");

	public void insertBook(Book book) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(book);
		em.getTransaction().commit();
		em.close();
	}

	public List<Book> showAllBooks() {
		EntityManager em = emfactory.createEntityManager();
		List<Book> allBooks = em.createQuery("SELECT b FROM Book b").getResultList();
		em.close();
		return allBooks;
	}

	public void deleteBook(Book bookToDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Book> typedQuery = em.createQuery("select b from Book b where b.id = :selectedId", Book.class);
		typedQuery.setParameter("selectedId", bookToDelete.getId());
		typedQuery.setMaxResults(1);
		Book result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public Book searchForBookById(int id) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Book found = em.find(Book.class, id);
		em.close();
		return found;
	}

	public void updateBook(Book bookToUpdate) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(bookToUpdate);
		em.getTransaction().commit();
		em.close();
	}

	public void cleanUp() {
		emfactory.close();
	}
}