package com.br.dio.cloudparking.service;


import com.br.dio.cloudparking.excepion.ParkingNotFoundException;
import com.br.dio.cloudparking.model.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static HashMap<String, Parking> parkingMap = new HashMap<String, Parking>();

//    static {
//        var id = getUUID();
//        var id1 = getUUID();
//        Parking parking = new Parking(id, "MTT-1010", "SC", "TOYOTA", "PRETO");
//      //  Parking parking1 = new Parking(id1, "STB-1015", "MG", "FORD", "VERMELHO");
//       parkingMap.put(id,parking);
//      // parkingMap.put(id1,parking1);
//    }

    public List<Parking> findAll(){
        return parkingMap.values().stream()
                .collect(Collectors.toList());
    }

    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if (parking == null){
            throw new ParkingNotFoundException(id);
        }
        return parking;
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid,parkingCreate);
        return parkingCreate;
    }

    public Parking delete(String id) {
        findById(id);
        return parkingMap.remove(id);
    }

    public Parking update(String id, Parking parkingUpdate) {
        Parking parking = findById(id);
        parking.setColor(parkingUpdate.getColor());
        parkingMap.replace(id,parking);
        return parking;


    }

    //implementar esse metodo
    public Parking exit(String id) {
        //recuperar o estacionamento
        //atualizar data de saida
        //calcular o valor
        return null;
    }
}
