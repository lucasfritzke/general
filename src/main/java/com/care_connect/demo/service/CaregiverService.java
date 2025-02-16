package com.care_connect.demo.service;

import com.care_connect.demo.domain.Address;
import com.care_connect.demo.domain.Caregiver;
import com.care_connect.demo.domain.User;
import com.care_connect.demo.infra.security.UserRole;
import com.care_connect.demo.mapper.CaregiverMapper;
import com.care_connect.demo.repository.CaregiverRepository;
import com.care_connect.demo.request.CaregiverRequestDto;
import com.care_connect.demo.response.CaregiverResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CaregiverService {

    @Autowired
    private CaregiverRepository caregiverRepository;

    @Autowired
    private CaregiverMapper caregiverMapper;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @Transactional
    public CaregiverResponseDto registerCaregiver(CaregiverRequestDto dto) {
        Caregiver caregiver = caregiverMapper.toEntity(dto);
        Address address = addressService.save(caregiver.getAddress());
        caregiver.setAddress(address);
        User user = userService.createUser(dto.getEmail(), dto.getPassword(), UserRole.ADVISER);
        caregiver.setId(user.getId());
        caregiverRepository.save(caregiver);
        return caregiverMapper.toResponseDto(caregiver);
    }
}

