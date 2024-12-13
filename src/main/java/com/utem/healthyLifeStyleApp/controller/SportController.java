package com.utem.healthyLifeStyleApp.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utem.healthyLifeStyleApp.dto.SportSummary;
import com.utem.healthyLifeStyleApp.dto.UserSportDTO;
import com.utem.healthyLifeStyleApp.model.Sport;
import com.utem.healthyLifeStyleApp.service.SportService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sport")
@RequiredArgsConstructor
public class SportController {

    final private SportService sportService;

    @PostMapping
    public ResponseEntity<Sport> addSport(@RequestBody Sport sport){
        return ResponseEntity.status(HttpStatus.CREATED).body(sportService.addSport(sport));
    }

    //checked
    @GetMapping("/search")
    public ResponseEntity<List<Sport>> getMatchingSportList(@RequestParam("query") String query){
        return ResponseEntity.status(HttpStatus.OK).body(sportService.getMatchingSportList(query));
    }

    //checked
    @GetMapping("/{id}")
    public ResponseEntity<Sport> getSport(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(sportService.getSportById(id));
    }

    
    //checked
    @PostMapping("/user")
    public ResponseEntity<UserSportDTO> addUserSport(@RequestBody UserSportDTO dto){
  
        return ResponseEntity.status(HttpStatus.CREATED).body(sportService.addUserSport(dto));
    }

    //checked
    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, List<UserSportDTO>>> getUserSportListByDate(@PathVariable("userId") Integer userId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(sportService.getUserSportListByDate(userId,date));
    }

    //checked
    @GetMapping("/user/{userId}/summary")
    public ResponseEntity<SportSummary> getSportSummary(@PathVariable("userId") Integer userId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(sportService.getSportSummary(userId, date));
    }

    //checked
    @DeleteMapping("user/{userSportId}")
    public ResponseEntity<String> deleteUserSport(@PathVariable("userSportId") Integer userSportId){
        sportService.deleteUserSport(userSportId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    
}
