package vn.studentexchange.repository;

import vn.studentexchange.domain.OrderCart;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the OrderCart entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderCartRepository extends JpaRepository<OrderCart, Long> {

    @Query("select order_cart from OrderCart order_cart where order_cart.buyer.login = ?#{principal.username}")
    List<OrderCart> findByBuyerIsCurrentUser();

    @Query("select order_cart from OrderCart order_cart where order_cart.chinaStocker.login = ?#{principal.username}")
    List<OrderCart> findByChinaStockerIsCurrentUser();

    @Query("select order_cart from OrderCart order_cart where order_cart.vietnamStocker.login = ?#{principal.username}")
    List<OrderCart> findByVietnamStockerIsCurrentUser();

    @Query("select order_cart from OrderCart order_cart where order_cart.exporter.login = ?#{principal.username}")
    List<OrderCart> findByExporterIsCurrentUser();

    @Query("select order_cart from OrderCart order_cart where order_cart.createBy.login = ?#{principal.username}")
    List<OrderCart> findByCreateByIsCurrentUser();

    @Query("select order_cart from OrderCart order_cart where order_cart.updateBy.login = ?#{principal.username}")
    List<OrderCart> findByUpdateByIsCurrentUser();

}
