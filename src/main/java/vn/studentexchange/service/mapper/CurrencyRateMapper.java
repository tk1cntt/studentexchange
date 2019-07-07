package vn.studentexchange.service.mapper;

import org.mapstruct.Mapper;
import vn.studentexchange.domain.CurrencyRate;
import vn.studentexchange.service.dto.CurrencyRateDTO;

/**
 * Mapper for the entity CurrencyRate and its DTO CurrencyRateDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CurrencyRateMapper extends EntityMapper<CurrencyRateDTO, CurrencyRate> {



    default CurrencyRate fromId(Long id) {
        if (id == null) {
            return null;
        }
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setId(id);
        return currencyRate;
    }
}
