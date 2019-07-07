package vn.studentexchange.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.studentexchange.domain.City;
import vn.studentexchange.service.dto.CityDTO;

/**
 * Mapper for the entity City and its DTO CityDTO.
 */
@Mapper(componentModel = "spring", uses = {DistrictMapper.class})
public interface CityMapper extends EntityMapper<CityDTO, City> {

    @Mapping(source = "districts", target = "districts")
    CityDTO toDto(City city);

    @Mapping(target = "districts", ignore = true)
    City toEntity(CityDTO cityDTO);

    default City fromId(Long id) {
        if (id == null) {
            return null;
        }
        City city = new City();
        city.setId(id);
        return city;
    }
}
