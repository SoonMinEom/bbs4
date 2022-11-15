package com.mustache.bbs4.service;

import com.mustache.bbs4.domain.Entity.Hospital;
import com.mustache.bbs4.domain.dto.HospitalResponse;
import com.mustache.bbs4.repository.HospitalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HospitalService {

    public final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public ResponseEntity<HospitalResponse> getHospital(Integer id) {
        Optional<Hospital> otpHospital = hospitalRepository.findById(id);
        Hospital hospital = otpHospital.get();
        HospitalResponse hospitalResponse = Hospital.of(hospital);

        if(hospital.getBusinessStatusCode() == 13) {
            hospitalResponse.setBusinessStatusName("영업중");
        } else if(hospital.getBusinessStatusCode() == 3) {
            hospitalResponse.setBusinessStatusName("폐업");
        } else {
            hospitalResponse.setBusinessStatusName(String.valueOf(hospital.getBusinessStatusCode()));
        }

        return ResponseEntity.ok().body(hospitalResponse);
    }


}
