package vn.studentexchange.service.mapper;

import vn.studentexchange.domain.*;
import vn.studentexchange.service.dto.DistrictDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity District and its DTO DistrictDTO.
 */
@Mapper(componentModel = "spring", uses = {CityMapper.class})
public interface DistrictMapper extends EntityMapper<DistrictDTO, District> {

    @Mapping(source = "city.id", target = "cityId")
    DistrictDTO toDto(District district);

    @Mapping(source = "cityId", target = "city")
    @Mapping(target = "wards", ignore = true)
    District toEntity(DistrictDTO districtDTO);

    default District fromId(Long id) {
        if (id == null) {
            return null;
        }
        District district = new District();
        district.setId(id);
        return district;
    }
}
