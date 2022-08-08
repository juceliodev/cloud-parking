package com.br.dio.cloudparking.service;

import com.br.dio.cloudparking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static HashMap<String, Parking> parkingMap = new HashMap<String, Parking>();

    static {
        var id = getUUID();
        var id1 = getUUID();
        Parking parking = new Parking(id, "MTT-1010", "SC", "TOYOTA", "PRETO");
        Parking parking1 = new Parking(id1, "STB-1015", "MG", "FORD", "VERMELHO");
       parkingMap.put(id,parking);
       parkingMap.put(id1,parking1);
    }

    public List<Parking> findAll(){
        return parkingMap.values().stream()
                .collect(Collectors.toList());
    }

    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public Parking findById(String id) {
        return parkingMap.get(id);
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid,parkingCreate);
        return parkingCreate;
    }
}
