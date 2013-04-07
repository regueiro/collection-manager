//package es.regueiro.collectionManager.persistency.hibernate;
//
//import java.util.List;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import es.regueiro.collectionManager.model.Artist;
//import es.regueiro.collectionManager.persistency.ArtistDAO;
//
//@Transactional (readOnly=true)
//@Repository
//public class HibernateArtistDAO implements ArtistDAO {
//	private SessionFactory sessionFactory;
//
//	@Autowired
//	public HibernateArtistDAO(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
//
//	private Session currentSession() {
//		return sessionFactory.getCurrentSession();
//	}
//
//	@Override
//	@Transactional (propagation=Propagation.REQUIRED,readOnly=false)
//	public void addArtist(Artist artist) {
//		currentSession().save(artist);
//	}
//
//	@Override
//	public Artist getArtistById(long id) {
//		return (Artist) currentSession().get(Artist.class, id);
//	}
//
//	@Override
//	@Transactional (propagation=Propagation.REQUIRED,readOnly=false)
//	public void saveArtist(Artist artist) {
//		currentSession().update(artist);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	@Transactional (readOnly=true)
//	public List<Artist> listAllArtists() {
//		return currentSession().createCriteria(Artist.class).list();
//	}
//
//}
