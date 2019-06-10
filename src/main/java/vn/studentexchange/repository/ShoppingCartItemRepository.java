package vn.studentexchange.repository;

import vn.studentexchange.domain.ShoppingCartItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the ShoppingCartItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

    @Query("select shopping_cart_item from ShoppingCartItem shopping_cart_item where shopping_cart_item.createBy.login = ?#{principal.username}")
    List<ShoppingCartItem> findByCreateByIsCurrentUser();

    @Query("select shopping_cart_item from ShoppingCartItem shopping_cart_item where shopping_cart_item.updateBy.login = ?#{principal.username}")
    List<ShoppingCartItem> findByUpdateByIsCurrentUser();

}
