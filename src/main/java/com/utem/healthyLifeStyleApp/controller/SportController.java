package com.utem.healthyLifeStyleApp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/list")
    public ResponseEntity<List<Sport>> getMatchingSportList(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(sportService.getMatchingSportList(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sport> getSport(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(sportService.getSportById(id));
    }

    @PostMapping("/user")
    public ResponseEntity<UserSportDTO> addUserSport(@RequestBody UserSportDTO dto){
  
        return ResponseEntity.status(HttpStatus.CREATED).body(sportService.addUserSport(dto));
    }

    @GetMapping("/user/{sportId}")
    public ResponseEntity<List<UserSportDTO>> getUserSport(@PathVariable("sportId") Integer sportId, @RequestParam Integer userId){
        return ResponseEntity.status(HttpStatus.OK).body(sportService.getUserSport(userId, sportId));
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserSportDTO>> getUserSportListByDate(@RequestParam Integer userId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(sportService.getUserSportListByDate(userId,date));
    }
}
