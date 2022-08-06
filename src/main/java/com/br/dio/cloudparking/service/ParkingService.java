package com.br.dio.cloudparking.service;

import com.br.dio.cloudparking.model.Parking;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static HashMap<String, Parking> parkingMap = new HashMap<String, Parking>();

    static {
        var id = getUUID();
        Parking parking = new Parking(id, "MTT-1010", "SC", "TOYOTA", "PRETO");
       parkingMap.put(id,parking);
    }

    public List<Parking> findAll(){
        return parkingMap.values().stream()
                .collect(Collectors.toList());
    }

    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
