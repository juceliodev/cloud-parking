package com.br.dio.cloudparking.controller;

import com.br.dio.cloudparking.controller.dto.ParkingDTO;
import com.br.dio.cloudparking.controller.mapper.ParkingMapper;
import com.br.dio.cloudparking.model.Parking;
import com.br.dio.cloudparking.service.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
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
   public List<ParkingDTO> findAll(){
//        var parking = new Parking();
//        parking.setModel("Gol");
//        parking.setColor("Preto");
//        parking.setState("SP");
//        parking.setLicense("mss-777");
//
//        return Arrays.asList(parking);
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return result;
    }
}
