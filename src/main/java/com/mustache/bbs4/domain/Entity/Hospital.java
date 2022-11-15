package com.mustache.bbs4.domain.Entity;

import com.mustache.bbs4.domain.dto.HospitalResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nation_wide_hospitals")
@Getter
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String hospitalName;
    private String roadNameAddress;
    private Integer businessStatusCode;
    private Integer patientRoomCount;
    private String businessTypeName;

    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(hospital.getId(), hospital.getHospitalName(), hospital.getRoadNameAddress())
;    }
}
