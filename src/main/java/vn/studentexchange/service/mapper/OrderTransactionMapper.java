package vn.studentexchange.service.mapper;

import vn.studentexchange.domain.*;
import vn.studentexchange.service.dto.OrderTransactionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderTransaction and its DTO OrderTransactionDTO.
 */
@Mapper(componentModel = "spring", uses = {OrderCartMapper.class, UserMapper.class})
public interface OrderTransactionMapper extends EntityMapper<OrderTransactionDTO, OrderTransaction> {

    @Mapping(source = "orderCart.id", target = "orderCartId")
    @Mapping(source = "orderCode.id", target = "orderCodeId")
    @Mapping(source = "orderCode.code", target = "orderCodeCode")
    @Mapping(source = "createBy.id", target = "createById")
    @Mapping(source = "createBy.login", target = "createByLogin")
    OrderTransactionDTO toDto(OrderTransaction orderTransaction);

    @Mapping(source = "orderCartId", target = "orderCart")
    @Mapping(source = "orderCodeId", target = "orderCode")
    @Mapping(source = "createById", target = "createBy")
    OrderTransaction toEntity(OrderTransactionDTO orderTransactionDTO);

    default OrderTransaction fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderTransaction orderTransaction = new OrderTransaction();
        orderTransaction.setId(id);
        return orderTransaction;
    }
}
