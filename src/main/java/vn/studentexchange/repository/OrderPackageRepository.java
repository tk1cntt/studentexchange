package vn.studentexchange.repository;

import vn.studentexchange.domain.OrderPackage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the OrderPackage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderPackageRepository extends JpaRepository<OrderPackage, Long> {

    @Query("select order_package from OrderPackage order_package where order_package.createBy.login = ?#{principal.username}")
    List<OrderPackage> findByCreateByIsCurrentUser();

    @Query("select order_package from OrderPackage order_package where order_package.updateBy.login = ?#{principal.username}")
    List<OrderPackage> findByUpdateByIsCurrentUser();

}
