package vn.studentexchange.repository;

import vn.studentexchange.domain.OrderTransaction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the OrderTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderTransactionRepository extends JpaRepository<OrderTransaction, Long> {

    @Query("select order_transaction from OrderTransaction order_transaction where order_transaction.approver.login = ?#{principal.username}")
    List<OrderTransaction> findByApproverIsCurrentUser();

    @Query("select order_transaction from OrderTransaction order_transaction where order_transaction.createBy.login = ?#{principal.username}")
    List<OrderTransaction> findByCreateByIsCurrentUser();

    @Query("select order_transaction from OrderTransaction order_transaction where order_transaction.updateBy.login = ?#{principal.username}")
    List<OrderTransaction> findByUpdateByIsCurrentUser();

}
