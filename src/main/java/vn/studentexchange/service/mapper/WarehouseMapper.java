package vn.studentexchange.service.mapper;

import org.mapstruct.Mapper;
import vn.studentexchange.domain.Warehouse;
import vn.studentexchange.service.dto.WarehouseDTO;

/**
 * Mapper for the entity Warehouse and its DTO WarehouseDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WarehouseMapper extends EntityMapper<WarehouseDTO, Warehouse> {



    default Warehouse fromId(Long id) {
        if (id == null) {
            return null;
        }
        Warehouse warehouse = new Warehouse();
        warehouse.setId(id);
        return warehouse;
    }
}
