package es.regueiro.collectionManager.persistency.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.regueiro.collectionManager.model.Artist;
import es.regueiro.collectionManager.persistency.ArtistDAO;

@Repository
public class HibernateArtistDAO implements ArtistDAO {
	private SessionFactory sessionFactory;
	private Session session;

	@Autowired
	public HibernateArtistDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		if (session == null || !session.isOpen()) {
			session = sessionFactory.openSession();
		}
		return session;
		
	}

	@Override
	public void addArtist(Artist artist) {
		Transaction t = currentSession().beginTransaction();
		currentSession().save(artist);
		t.commit();
		currentSession().close();
	}

	@Override
	public Artist getArtistById(long id) {
		return (Artist) currentSession().get(Artist.class, id);
	}

	@Override
	public void saveArtist(Artist artist) {
		currentSession().update(artist);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Artist> listAllArtists() {
		return currentSession().createCriteria(Artist.class).list();
	}
	
	

}
