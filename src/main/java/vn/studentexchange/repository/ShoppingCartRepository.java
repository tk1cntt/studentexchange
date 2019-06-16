package vn.studentexchange.repository;

import vn.studentexchange.domain.ShoppingCart;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the ShoppingCart entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    @Query("select shopping_cart from ShoppingCart shopping_cart where shopping_cart.createBy.login = ?#{principal.username}")
    List<ShoppingCart> findByCreateByIsCurrentUser();

    @Query("select shopping_cart from ShoppingCart shopping_cart where shopping_cart.updateBy.login = ?#{principal.username}")
    List<ShoppingCart> findByUpdateByIsCurrentUser();

    ShoppingCart findFirstByShopId(String shopId);
}
