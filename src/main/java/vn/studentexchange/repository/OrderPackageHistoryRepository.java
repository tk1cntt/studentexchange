package vn.studentexchange.repository;

import vn.studentexchange.domain.OrderPackageHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the OrderPackageHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderPackageHistoryRepository extends JpaRepository<OrderPackageHistory, Long> {

    @Query("select order_package_history from OrderPackageHistory order_package_history where order_package_history.createBy.login = ?#{principal.username}")
    List<OrderPackageHistory> findByCreateByIsCurrentUser();

    @Query("select order_package_history from OrderPackageHistory order_package_history where order_package_history.updateBy.login = ?#{principal.username}")
    List<OrderPackageHistory> findByUpdateByIsCurrentUser();

}
