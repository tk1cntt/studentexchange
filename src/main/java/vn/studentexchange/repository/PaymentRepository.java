package vn.studentexchange.repository;

import vn.studentexchange.domain.Payment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Payment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>, JpaSpecificationExecutor<Payment> {

    @Query("select payment from Payment payment where payment.staffApproval.login = ?#{principal.username}")
    List<Payment> findByStaffApprovalIsCurrentUser();

    @Query("select payment from Payment payment where payment.staffCancel.login = ?#{principal.username}")
    List<Payment> findByStaffCancelIsCurrentUser();

    @Query("select payment from Payment payment where payment.customer.login = ?#{principal.username}")
    List<Payment> findByCustomerIsCurrentUser();

    @Query("select payment from Payment payment where payment.createBy.login = ?#{principal.username}")
    List<Payment> findByCreateByIsCurrentUser();

    Page<Payment> findByOrderByCreateAtDesc(Pageable pageable);

    Page<Payment> findByCreateByLoginOrderByCreateAtDesc(String username, Pageable pageable);

    Page<Payment> findByCustomerLoginOrderByCreateAtDesc(String username, Pageable pageable);
}
