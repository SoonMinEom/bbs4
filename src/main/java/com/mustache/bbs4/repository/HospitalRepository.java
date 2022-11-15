package com.mustache.bbs4.repository;

import com.mustache.bbs4.domain.Entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository  extends JpaRepository<Hospital, Integer> {

    List<Hospital> findByBusinessTypeNameIn(List<String> businessType);
    List<Hospital> findByRoadNameAddressContaining(String key);

    List<Hospital> findByHospitalNameStartsWith(String key);
    List<Hospital> findByHospitalNameEndsWith(String key);
    List<Hospital> findByPatientRoomCountBetweenOrderByPatientRoomCountAsc(int start, int end);

    List<Hospital> findByBusinessTypeNameInAndRoadNameAddressContaining(List<String> businessType, String key);

}
