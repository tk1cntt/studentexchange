package vn.studentexchange.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.studentexchange.domain.OrderPackage;
import vn.studentexchange.service.dto.OrderPackageDTO;

/**
 * Mapper for the entity OrderPackage and its DTO OrderPackageDTO.
 */
@Mapper(componentModel = "spring", uses = {OrderCartMapper.class, WarehouseMapper.class, UserMapper.class, DeliveryMapper.class})
public interface OrderPackageMapper extends EntityMapper<OrderPackageDTO, OrderPackage> {

    @Mapping(source = "orderCart.id", target = "orderCartId")
    @Mapping(source = "warehouse.id", target = "warehouseId")
    @Mapping(source = "createBy.id", target = "createById")
    @Mapping(source = "createBy.login", target = "createByLogin")
    @Mapping(source = "updateBy.id", target = "updateById")
    @Mapping(source = "updateBy.login", target = "updateByLogin")
    @Mapping(source = "delivery.id", target = "deliveryId")
    OrderPackageDTO toDto(OrderPackage orderPackage);

    @Mapping(source = "orderCartId", target = "orderCart")
    @Mapping(target = "packages", ignore = true)
    @Mapping(source = "warehouseId", target = "warehouse")
    @Mapping(source = "createById", target = "createBy")
    @Mapping(source = "updateById", target = "updateBy")
    @Mapping(source = "deliveryId", target = "delivery")
    OrderPackage toEntity(OrderPackageDTO orderPackageDTO);

    default OrderPackage fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderPackage orderPackage = new OrderPackage();
        orderPackage.setId(id);
        return orderPackage;
    }
}
