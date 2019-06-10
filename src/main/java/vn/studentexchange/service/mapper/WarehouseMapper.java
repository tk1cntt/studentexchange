package vn.studentexchange.service.mapper;

import vn.studentexchange.domain.*;
import vn.studentexchange.service.dto.WarehouseDTO;

import org.mapstruct.*;

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
