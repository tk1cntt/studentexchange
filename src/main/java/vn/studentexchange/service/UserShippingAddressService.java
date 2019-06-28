package vn.studentexchange.service;

import vn.studentexchange.domain.User;
import vn.studentexchange.domain.UserShippingAddress;
import vn.studentexchange.repository.UserRepository;
import vn.studentexchange.repository.UserShippingAddressRepository;
import vn.studentexchange.security.SecurityUtils;
import vn.studentexchange.service.dto.UserShippingAddressDTO;
import vn.studentexchange.service.mapper.UserShippingAddressMapper;
import vn.studentexchange.web.rest.util.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing UserShippingAddress.
 */
@Service
@Transactional
public class UserShippingAddressService {

    private final Logger log = LoggerFactory.getLogger(UserShippingAddressService.class);

    private final UserShippingAddressRepository userShippingAddressRepository;

    private final UserShippingAddressMapper userShippingAddressMapper;

    private final UserRepository userRepository;

    public UserShippingAddressService(UserShippingAddressRepository userShippingAddressRepository, UserShippingAddressMapper userShippingAddressMapper, UserRepository userRepository) {
        this.userShippingAddressRepository = userShippingAddressRepository;
        this.userShippingAddressMapper = userShippingAddressMapper;
        this.userRepository = userRepository;
    }

    /**
     * Save a userShippingAddress.
     *
     * @param userShippingAddressDTO the entity to save
     * @return the persisted entity
     */
    public UserShippingAddressDTO save(UserShippingAddressDTO userShippingAddressDTO) {
        log.debug("Request to save UserShippingAddress : {}", userShippingAddressDTO);
        Optional<User> existingUser = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get());
        UserShippingAddress userShippingAddress = userShippingAddressMapper.toEntity(userShippingAddressDTO);
        userShippingAddress.setCreateBy(existingUser.get());
        userShippingAddress.setCreateAt(LocalDate.now());
        userShippingAddress.setUpdateBy(existingUser.get());
        userShippingAddress.setUpdateAt(LocalDate.now());
        userShippingAddress = userShippingAddressRepository.save(userShippingAddress);
        return userShippingAddressMapper.toDto(userShippingAddress);
    }

    /**
     * Get all the userShippingAddresses.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<UserShippingAddressDTO> findAll() {
        log.debug("Request to get all UserShippingAddresses");
        return userShippingAddressRepository.findAll().stream()
            .map(userShippingAddressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public List<UserShippingAddressDTO> findByOwner() {
        log.debug("Request to get owner UserShippingAddresses");
        return userShippingAddressRepository.findByCreateByIsCurrentUserOrderByCreateAtDesc()
            .stream()    
            .map(userShippingAddressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userShippingAddress by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<UserShippingAddressDTO> findOne(Long id) {
        log.debug("Request to get UserShippingAddress : {}", id);
        return userShippingAddressRepository.findById(id)
            .map(userShippingAddressMapper::toDto);
    }

    /**
     * Delete the userShippingAddress by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete UserShippingAddress : {}", id);
        userShippingAddressRepository.deleteById(id);
    }
}
