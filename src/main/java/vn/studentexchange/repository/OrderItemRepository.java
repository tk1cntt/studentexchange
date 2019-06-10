package vn.studentexchange.repository;

import vn.studentexchange.domain.OrderItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the OrderItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("select order_item from OrderItem order_item where order_item.createBy.login = ?#{principal.username}")
    List<OrderItem> findByCreateByIsCurrentUser();

    @Query("select order_item from OrderItem order_item where order_item.updateBy.login = ?#{principal.username}")
    List<OrderItem> findByUpdateByIsCurrentUser();

}
