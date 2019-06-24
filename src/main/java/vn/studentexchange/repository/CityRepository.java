package vn.studentexchange.repository;

import vn.studentexchange.domain.City;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the City entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {
    List<City> findByEnabledTrueOrderByIndexAscNameAsc();
}
