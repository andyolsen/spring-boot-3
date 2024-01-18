package mypackage;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProductSuggestionCrudRepository extends CrudRepository<ProductSuggestion, Long> {

    @Query("update ProductSuggestion p set p.recommendedPrice=?2 where p.id = ?1")
    @Modifying(clearAutomatically = true)
    @Transactional
    int modifyPrice(Long id, double newPrice);

    @Query("update ProductSuggestion p set p.estimatedAnnualSales=?2 where p.id = ?1")
    @Modifying(clearAutomatically = true)
    @Transactional
    int modifySales(Long id, long newSales);

    @Query("update ProductSuggestion set recommendedPrice=recommendedPrice*1.1 where estimatedAnnualSales >= ?1")
    @Modifying(clearAutomatically = true)
    @Transactional
    int increasePriceForPopularProducts(long newSales);
}