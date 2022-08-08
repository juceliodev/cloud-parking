package com.br.dio.cloudparking.controller;

import com.br.dio.cloudparking.controller.dto.ParkingCreateDTO;
import com.br.dio.cloudparking.controller.dto.ParkingDTO;
import com.br.dio.cloudparking.controller.mapper.ParkingMapper;
import com.br.dio.cloudparking.model.Parking;
import com.br.dio.cloudparking.service.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {


    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    //Construtor para injetar a dependencia
    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper){
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    //DTO data transfer controll
    @GetMapping
    @ApiOperation("Find all parkings")
   public ResponseEntity<List<ParkingDTO>> findAll(){
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
        Parking parkingList = parkingService.findById(id);
        ParkingDTO result = parkingMapper.toParkingDTO(parkingList);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO parkingDTO){
        Parking parkingCreate = parkingMapper.toParkingCreate(parkingDTO);
        Parking parking = parkingService.create(parkingCreate);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
