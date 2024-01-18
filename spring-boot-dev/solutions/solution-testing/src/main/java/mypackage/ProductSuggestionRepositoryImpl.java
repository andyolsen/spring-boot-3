package mypackage;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.Collection;

@Repository
public class ProductSuggestionRepositoryImpl implements ProductSuggestionRepository {

	@PersistenceContext
	protected EntityManager entityManager;
    
	@Override
	public Collection<ProductSuggestion> getProductSuggestions() {
		String jpql = "select p from ProductSuggestion p";
		TypedQuery<ProductSuggestion> query = entityManager.createQuery(jpql, ProductSuggestion.class);
	    return query.getResultList();
	}

	@Override
	public ProductSuggestion getProductSuggestion(long id) {
		return entityManager.find(ProductSuggestion.class, id);
	}

	@Override
	@Transactional
	public long insertProductSuggestion(ProductSuggestion ps) {
		entityManager.persist(ps);
		entityManager.flush();
		return ps.getId();
	}
	
	@Override
	@Transactional
	public boolean modifyPrice(long id, double newPrice) {
		ProductSuggestion ps = entityManager.find(ProductSuggestion.class, id);
		if (ps == null) {
			return false;
		}
		else {
			ps.setRecommendedPrice(newPrice);
			return true;
		}
	}
	
	@Override
	@Transactional
	public boolean modifySales(long id, long newSales) {
		ProductSuggestion ps = entityManager.find(ProductSuggestion.class, id);
		if (ps == null) {
			return false;
		}
		else {
			ps.setEstimatedAnnualSales(newSales);
			return true;
		}
	}

	@Override
	@Transactional
	public void deleteProductSuggestions() {
		Query query = entityManager.createQuery("delete from ProductSuggestion");
		query.executeUpdate();
	}

	@Override
	@Transactional
	public int increasePriceForPopularProducts(long sales) {
		String jpql = "update ProductSuggestion set recommendedPrice=recommendedPrice*1.1 where estimatedAnnualSales >= :s";

		Query query = entityManager.createQuery(jpql);
		query.setParameter("s", sales);

		int numRowsAffected = query.executeUpdate();
		return numRowsAffected;
	}
}
