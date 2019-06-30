package vn.studentexchange.service;

import vn.studentexchange.domain.UserBalance;
import vn.studentexchange.repository.UserBalanceRepository;
import vn.studentexchange.service.dto.UserBalanceDTO;
import vn.studentexchange.service.mapper.UserBalanceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing UserBalance.
 */
@Service
@Transactional
public class UserBalanceService {

    private final Logger log = LoggerFactory.getLogger(UserBalanceService.class);

    private final UserBalanceRepository userBalanceRepository;

    private final UserBalanceMapper userBalanceMapper;

    public UserBalanceService(UserBalanceRepository userBalanceRepository, UserBalanceMapper userBalanceMapper) {
        this.userBalanceRepository = userBalanceRepository;
        this.userBalanceMapper = userBalanceMapper;
    }

    /**
     * Save a userBalance.
     *
     * @param userBalanceDTO the entity to save
     * @return the persisted entity
     */
    public UserBalanceDTO save(UserBalanceDTO userBalanceDTO) {
        log.debug("Request to save UserBalance : {}", userBalanceDTO);

        UserBalance userBalance = userBalanceMapper.toEntity(userBalanceDTO);
        userBalance = userBalanceRepository.save(userBalance);
        return userBalanceMapper.toDto(userBalance);
    }

    /**
     * Get all the userBalances.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<UserBalanceDTO> findAll() {
        log.debug("Request to get all UserBalances");
        return userBalanceRepository.findAll().stream()
            .map(userBalanceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one userBalance by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<UserBalanceDTO> findOne(Long id) {
        log.debug("Request to get UserBalance : {}", id);
        return userBalanceRepository.findById(id)
            .map(userBalanceMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<UserBalanceDTO> findByOwner(String username) {
        log.debug("Request to get owner UserBalance: {}", username);
        return userBalanceRepository.findFirstByCreateByLogin(username)
            .map(userBalanceMapper::toDto);
    }
    /**
     * Delete the userBalance by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete UserBalance : {}", id);
        userBalanceRepository.deleteById(id);
    }
}
