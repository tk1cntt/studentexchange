package vn.studentexchange.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.studentexchange.domain.OrderPackageHistory;
import vn.studentexchange.service.dto.OrderPackageHistoryDTO;

/**
 * Mapper for the entity OrderPackageHistory and its DTO OrderPackageHistoryDTO.
 */
@Mapper(componentModel = "spring", uses = {OrderPackageMapper.class, WarehouseMapper.class, UserMapper.class})
public interface OrderPackageHistoryMapper extends EntityMapper<OrderPackageHistoryDTO, OrderPackageHistory> {

    @Mapping(source = "orderPackage.id", target = "orderPackageId")
    @Mapping(source = "warehouse.id", target = "warehouseId")
    @Mapping(source = "createBy.id", target = "createById")
    @Mapping(source = "createBy.login", target = "createByLogin")
    @Mapping(source = "updateBy.id", target = "updateById")
    @Mapping(source = "updateBy.login", target = "updateByLogin")
    OrderPackageHistoryDTO toDto(OrderPackageHistory orderPackageHistory);

    @Mapping(source = "orderPackageId", target = "orderPackage")
    @Mapping(source = "warehouseId", target = "warehouse")
    @Mapping(source = "createById", target = "createBy")
    @Mapping(source = "updateById", target = "updateBy")
    OrderPackageHistory toEntity(OrderPackageHistoryDTO orderPackageHistoryDTO);

    default OrderPackageHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderPackageHistory orderPackageHistory = new OrderPackageHistory();
        orderPackageHistory.setId(id);
        return orderPackageHistory;
    }
}
