package vn.studentexchange.repository;

import vn.studentexchange.domain.CurrencyRate;
import vn.studentexchange.domain.enumeration.CurrencyType;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CurrencyRate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {
	Optional<CurrencyRate> findFirstByCurrency(CurrencyType type);
}
