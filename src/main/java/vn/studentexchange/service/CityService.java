package vn.studentexchange.service;

import vn.studentexchange.domain.City;
import vn.studentexchange.repository.CityRepository;
import vn.studentexchange.service.dto.CityDTO;
import vn.studentexchange.service.mapper.CityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing City.
 */
@Service
@Transactional
public class CityService {

    private final Logger log = LoggerFactory.getLogger(CityService.class);

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;

    public CityService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    /**
     * Save a city.
     *
     * @param cityDTO the entity to save
     * @return the persisted entity
     */
    public CityDTO save(CityDTO cityDTO) {
        log.debug("Request to save City : {}", cityDTO);

        City city = cityMapper.toEntity(cityDTO);
        city = cityRepository.save(city);
        return cityMapper.toDto(city);
    }

    /**
     * Get all the cities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cities");
        return cityRepository.findAll(pageable)
            .map(cityMapper::toDto);
    }

    /**
     * Get all the cities.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {
        log.debug("Request to get all Cities");
        return cityRepository.findByEnabledTrueOrderByIndexAscNameAsc().stream()
            .map(cityMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one city by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CityDTO> findOne(Long id) {
        log.debug("Request to get City : {}", id);
        return cityRepository.findById(id)
            .map(cityMapper::toDto);
    }

    /**
     * Delete the city by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete City : {}", id);
        cityRepository.deleteById(id);
    }
}
