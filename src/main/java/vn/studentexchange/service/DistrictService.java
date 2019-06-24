package vn.studentexchange.service;

import vn.studentexchange.domain.District;
import vn.studentexchange.repository.DistrictRepository;
import vn.studentexchange.service.dto.DistrictDTO;
import vn.studentexchange.service.mapper.DistrictMapper;
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
 * Service Implementation for managing District.
 */
@Service
@Transactional
public class DistrictService {

    private final Logger log = LoggerFactory.getLogger(DistrictService.class);

    private final DistrictRepository districtRepository;

    private final DistrictMapper districtMapper;

    public DistrictService(DistrictRepository districtRepository, DistrictMapper districtMapper) {
        this.districtRepository = districtRepository;
        this.districtMapper = districtMapper;
    }

    /**
     * Save a district.
     *
     * @param districtDTO the entity to save
     * @return the persisted entity
     */
    public DistrictDTO save(DistrictDTO districtDTO) {
        log.debug("Request to save District : {}", districtDTO);

        District district = districtMapper.toEntity(districtDTO);
        district = districtRepository.save(district);
        return districtMapper.toDto(district);
    }

    /**
     * Get all the districts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<DistrictDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Districts");
        return districtRepository.findAll(pageable)
            .map(districtMapper::toDto);
    }

    /**
     * Get all the districts.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<DistrictDTO> findAll() {
        log.debug("Request to get all Districts");
        return districtRepository.findAll().stream()
            .map(districtMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one district by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<DistrictDTO> findOne(Long id) {
        log.debug("Request to get District : {}", id);
        return districtRepository.findById(id)
            .map(districtMapper::toDto);
    }

    /**
     * Delete the district by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete District : {}", id);
        districtRepository.deleteById(id);
    }
}
