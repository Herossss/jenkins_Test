package com.uniteddate.facility_manage.common.service.generateId;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public interface GenerateIdService {

    @GetMapping("/id")
    Long getId();
}
