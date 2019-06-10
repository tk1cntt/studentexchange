package vn.studentexchange.repository;

import vn.studentexchange.domain.UserShippingAddress;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the UserShippingAddress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserShippingAddressRepository extends JpaRepository<UserShippingAddress, Long> {

    @Query("select user_shipping_address from UserShippingAddress user_shipping_address where user_shipping_address.createBy.login = ?#{principal.username}")
    List<UserShippingAddress> findByCreateByIsCurrentUser();

    @Query("select user_shipping_address from UserShippingAddress user_shipping_address where user_shipping_address.updateBy.login = ?#{principal.username}")
    List<UserShippingAddress> findByUpdateByIsCurrentUser();

}
