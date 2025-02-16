package com.care_connect.demo.service;

import com.care_connect.demo.domain.Address;
import com.care_connect.demo.domain.Adviser;
import com.care_connect.demo.domain.User;
import com.care_connect.demo.infra.security.UserRole;
import com.care_connect.demo.mapper.AdviserMapper;
import com.care_connect.demo.repository.AdviserRepository;
import com.care_connect.demo.request.AdviserRequestDto;
import com.care_connect.demo.response.AddressResponseDto;
import com.care_connect.demo.response.AdviserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdviserService {

    @Autowired
    private AdviserRepository adviserRepository;

    @Autowired
    private AdviserMapper adviserMapper;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;



    @Transactional
    public AdviserResponseDto registerAdviser(AdviserRequestDto dto) {
        Adviser adviser = adviserMapper.toEntity(dto);
        Address address = addressService.saveAddress(dto.getAddress());
        adviser.setAddress(address);
        User user = userService.createUser(dto.getEmail(), dto.getPassword(), UserRole.CAREGIVER);
        adviser.setId(user.getId());
        adviserRepository.save(adviser);
        return adviserMapper.toResponseDto(adviser);
    }
}

