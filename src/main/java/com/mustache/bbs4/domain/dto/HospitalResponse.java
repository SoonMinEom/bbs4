package com.mustache.bbs4.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class HospitalResponse {
    private Integer id;
    private String hospitalName;
    private String roadNameAddress;
    private String businessStatusName;

    public HospitalResponse(Integer id, String hospitalName, String roadNameAddress) {
        this.id = id;
        this.hospitalName = hospitalName;
        this.roadNameAddress = roadNameAddress;
    }

    public void setBusinessStatusName(String businessStatusName) {
        this.businessStatusName = businessStatusName;
    }
}
