package vn.studentexchange.service.mapper;

import vn.studentexchange.domain.*;
import vn.studentexchange.service.dto.RegionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Region and its DTO RegionDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface RegionMapper extends EntityMapper<RegionDTO, Region> {


    @Mapping(target = "countries", ignore = true)
    Region toEntity(RegionDTO regionDTO);

    default Region fromId(Long id) {
        if (id == null) {
            return null;
        }
        Region region = new Region();
        region.setId(id);
        return region;
    }
}
