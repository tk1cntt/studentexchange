package vn.studentexchange.service;

import vn.studentexchange.domain.Region;
import vn.studentexchange.repository.RegionRepository;
import vn.studentexchange.service.dto.RegionDTO;
import vn.studentexchange.service.mapper.RegionMapper;
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
 * Service Implementation for managing Region.
 */
@Service
@Transactional
public class RegionService {

    private final Logger log = LoggerFactory.getLogger(RegionService.class);

    private final RegionRepository regionRepository;

    private final RegionMapper regionMapper;

    public RegionService(RegionRepository regionRepository, RegionMapper regionMapper) {
        this.regionRepository = regionRepository;
        this.regionMapper = regionMapper;
    }

    /**
     * Save a region.
     *
     * @param regionDTO the entity to save
     * @return the persisted entity
     */
    public RegionDTO save(RegionDTO regionDTO) {
        log.debug("Request to save Region : {}", regionDTO);

        Region region = regionMapper.toEntity(regionDTO);
        region = regionRepository.save(region);
        return regionMapper.toDto(region);
    }

    /**
     * Get all the regions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<RegionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Regions");
        return regionRepository.findAll(pageable)
            .map(regionMapper::toDto);
    }

    /**
     * Get all the cities.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<RegionDTO> findAll() {
        log.debug("Request to get all Regions");
        return regionRepository.findAll().stream()
            .map(regionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Region with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<RegionDTO> findAllWithEagerRelationships(Pageable pageable) {
        return regionRepository.findAllWithEagerRelationships(pageable).map(regionMapper::toDto);
    }
    

    /**
     * Get one region by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<RegionDTO> findOne(Long id) {
        log.debug("Request to get Region : {}", id);
        return regionRepository.findOneWithEagerRelationships(id)
            .map(regionMapper::toDto);
    }

    /**
     * Delete the region by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Region : {}", id);
        regionRepository.deleteById(id);
    }
}
