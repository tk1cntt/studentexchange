package vn.studentexchange.repository;

import vn.studentexchange.domain.DeliveryPackage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the DeliveryPackage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryPackageRepository extends JpaRepository<DeliveryPackage, Long> {

    @Query("select delivery_package from DeliveryPackage delivery_package where delivery_package.createBy.login = ?#{principal.username}")
    List<DeliveryPackage> findByCreateByIsCurrentUser();

    @Query("select delivery_package from DeliveryPackage delivery_package where delivery_package.updateBy.login = ?#{principal.username}")
    List<DeliveryPackage> findByUpdateByIsCurrentUser();

}
