package com.group10.bookingtravel.controller;

import com.group10.bookingtravel.dto.GuideSelectBoxDTO;
import com.group10.bookingtravel.entity.Guide;
import com.group10.bookingtravel.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class GuideController {

    @Autowired
    private GuideService guideService;

    @GetMapping("/guides")
    public List<Guide> getGuideList(@RequestParam(name = "nameGuide" , required = false) String name,
                                    @RequestParam(name = "phoneGuide", required = false) String phone){
        return guideService.getListGuideByNameAndPhone(name,phone);
    };

    @GetMapping("/guides/maxid")
    public Integer getGuideList(){
        return guideService.getMaxId();
    };

    @GetMapping("/guide-select-box")
    public List<GuideSelectBoxDTO> getGuideSelectBoxDTO(){
        return guideService.getGuideSelectBox();
    }

    @PostMapping("/guides")
    public void addNewGuide(@RequestBody Guide guide){
      guideService.addNewGuide(guide);
    }

    @PostMapping("/guides/update")
    public void updateGuide(@RequestBody Guide guide){
        guideService.updateNewGuide(guide);
    }
}
