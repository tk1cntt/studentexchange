package vn.studentexchange.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.studentexchange.domain.OrderCart;
import vn.studentexchange.service.dto.OrderCartDTO;
import vn.studentexchange.service.dto.ShoppingCartDTO;

/**
 * Mapper for the entity OrderCart and its DTO OrderCartDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface OrderCartMapper extends EntityMapper<OrderCartDTO, OrderCart> {

    @Mapping(source = "buyer.id", target = "buyerId")
    @Mapping(source = "buyer.login", target = "buyerLogin")
    @Mapping(source = "chinaStocker.id", target = "chinaStockerId")
    @Mapping(source = "chinaStocker.login", target = "chinaStockerLogin")
    @Mapping(source = "vietnamStocker.id", target = "vietnamStockerId")
    @Mapping(source = "vietnamStocker.login", target = "vietnamStockerLogin")
    @Mapping(source = "exporter.id", target = "exporterId")
    @Mapping(source = "exporter.login", target = "exporterLogin")
    @Mapping(source = "createBy.id", target = "createById")
    @Mapping(source = "createBy.login", target = "createByLogin")
    @Mapping(source = "updateBy.id", target = "updateById")
    @Mapping(source = "updateBy.login", target = "updateByLogin")
    OrderCartDTO toDto(OrderCart orderCart);

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "histories", ignore = true)
    @Mapping(target = "packages", ignore = true)
    @Mapping(target = "transactions", ignore = true)
    @Mapping(source = "buyerId", target = "buyer")
    @Mapping(source = "chinaStockerId", target = "chinaStocker")
    @Mapping(source = "vietnamStockerId", target = "vietnamStocker")
    @Mapping(source = "exporterId", target = "exporter")
    @Mapping(source = "createById", target = "createBy")
    @Mapping(source = "updateById", target = "updateBy")
    OrderCart toEntity(OrderCartDTO orderCartDTO);

    OrderCartDTO toOrderCartDto(ShoppingCartDTO shoppingCartDTO);

    default OrderCart fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderCart orderCart = new OrderCart();
        orderCart.setId(id);
        return orderCart;
    }
}
