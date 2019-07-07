package vn.studentexchange.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.studentexchange.domain.Country;
import vn.studentexchange.service.dto.CountryDTO;

/**
 * Mapper for the entity Country and its DTO CountryDTO.
 */
@Mapper(componentModel = "spring", uses = {RegionMapper.class})
public interface CountryMapper extends EntityMapper<CountryDTO, Country> {

    @Mapping(source = "region.id", target = "regionId")
    CountryDTO toDto(Country country);

    @Mapping(source = "regionId", target = "region")
    Country toEntity(CountryDTO countryDTO);

    default Country fromId(Long id) {
        if (id == null) {
            return null;
        }
        Country country = new Country();
        country.setId(id);
        return country;
    }
}
