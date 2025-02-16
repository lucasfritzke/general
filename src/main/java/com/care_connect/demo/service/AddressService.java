package com.care_connect.demo.service;


import com.care_connect.demo.domain.Address;
import com.care_connect.demo.mapper.AddressMapper;
import com.care_connect.demo.repository.AddressRepository;
import com.care_connect.demo.request.AddressRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private GoogleMapsService googleMapsService;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public Address saveAddress(@NotNull(message = "O endereço não pode ser nulo") @Valid AddressRequestDto addressDto) {

        Address address = addressMapper.toEntity(addressDto);

        try {
            double[] coordinates = googleMapsService.getLatLong(address.toStringGoogleMaps());
            address.setLatitude(coordinates[0]);
            address.setLongitude(coordinates[1]);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar localização: " + e.getMessage());

        }

        return save(address);
    }
}

