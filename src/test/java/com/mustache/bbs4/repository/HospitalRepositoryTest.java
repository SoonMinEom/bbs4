package com.mustache.bbs4.repository;

import com.mustache.bbs4.domain.Entity.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {

    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    @DisplayName("업태구분명으로 찾기")
    void find1() {
        List<String> businessType = new ArrayList<>();
        businessType.add("보건소");
        businessType.add("보건진료소");
        businessType.add("보건지소");

        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(businessType);
        for (Hospital hospital : hospitals) {
            System.out.printf("%s, %s\n",hospital.getHospitalName(), hospital.getBusinessTypeName());
        }
    }

    @Test
    @DisplayName("도로명주소로 찾기")
    void find2() {
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContaining("구로구");
        for (Hospital hospital : hospitals) {
            System.out.printf("%s, %s\n",hospital.getHospitalName(), hospital.getRoadNameAddress());
        }
    }

    @Test
    @DisplayName("StartsWith")
    void find3() {
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameStartsWith("연세");
        for (Hospital hospital : hospitals) {
            System.out.printf("%s,\n",hospital.getHospitalName());
        }
    }

    @Test
    @DisplayName("Between")
    void find4() {
        List<Hospital> hospitals = hospitalRepository.findByPatientRoomCountBetweenOrderByPatientRoomCountAsc(10,20);
        for (Hospital hospital : hospitals) {
            System.out.printf("%s, %d\n",hospital.getHospitalName(), hospital.getPatientRoomCount());
        }
    }

    @Test
    @DisplayName("복수조건")
    void find5() {
        List<String> businessType = new ArrayList<>();
        businessType.add("보건소");
        businessType.add("보건진료소");
        businessType.add("보건지소");

        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameInAndRoadNameAddressContaining(businessType, "송파구");
        for (Hospital hospital : hospitals) {
            System.out.printf("%s, %s, %s\n",hospital.getHospitalName(), hospital.getBusinessTypeName(), hospital.getRoadNameAddress());
        }
    }
}