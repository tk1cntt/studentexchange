package vn.studentexchange.repository;

import vn.studentexchange.domain.OrderHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the OrderHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

    @Query("select order_history from OrderHistory order_history where order_history.createBy.login = ?#{principal.username}")
    List<OrderHistory> findByCreateByIsCurrentUser();

}
