package com.br.dio.cloudparking.controller.mapper;

import com.br.dio.cloudparking.controller.dto.ParkingCreateDTO;
import com.br.dio.cloudparking.controller.dto.ParkingDTO;
import com.br.dio.cloudparking.model.Parking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    //para execucao desta conversao esta sendo usada a dependecia do modelMapper
    public ParkingDTO toParkingDTO(Parking parking){
        return MODEL_MAPPER.map(parking, ParkingDTO.class);
    }

    public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
        return parkingList.stream()
                .map(this::toParkingDTO)
                .collect(Collectors.toList());
    }

    public Parking toParking(ParkingDTO parkingDTO){
        return MODEL_MAPPER.map(parkingDTO, Parking.class);
    }

    public Parking toParkingCreate(ParkingCreateDTO parkingDTO) {
        return MODEL_MAPPER.map(parkingDTO, Parking.class);
    }
}
