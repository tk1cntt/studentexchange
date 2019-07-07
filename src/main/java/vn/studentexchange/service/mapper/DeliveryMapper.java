package vn.studentexchange.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.studentexchange.domain.Delivery;
import vn.studentexchange.service.dto.DeliveryDTO;

/**
 * Mapper for the entity Delivery and its DTO DeliveryDTO.
 */
@Mapper(componentModel = "spring", uses = {WarehouseMapper.class, UserMapper.class})
public interface DeliveryMapper extends EntityMapper<DeliveryDTO, Delivery> {

    @Mapping(source = "warehouse.id", target = "warehouseId")
    @Mapping(source = "createBy.id", target = "createById")
    @Mapping(source = "createBy.login", target = "createByLogin")
    @Mapping(source = "updateBy.id", target = "updateById")
    @Mapping(source = "updateBy.login", target = "updateByLogin")
    DeliveryDTO toDto(Delivery delivery);

    @Mapping(target = "packages", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(source = "warehouseId", target = "warehouse")
    @Mapping(source = "createById", target = "createBy")
    @Mapping(source = "updateById", target = "updateBy")
    Delivery toEntity(DeliveryDTO deliveryDTO);

    default Delivery fromId(Long id) {
        if (id == null) {
            return null;
        }
        Delivery delivery = new Delivery();
        delivery.setId(id);
        return delivery;
    }
}
