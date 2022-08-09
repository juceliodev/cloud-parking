package com.br.dio.cloudparking.service;


import com.br.dio.cloudparking.excepion.ParkingNotFoundException;
import com.br.dio.cloudparking.model.Parking;
import com.br.dio.cloudparking.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    //HasMap usado para implementacao antes de usar o banco de dados
   // private static HashMap<String, Parking> parkingMap = new HashMap<String, Parking>();

    private ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

//    static {
//        var id = getUUID();
//        var id1 = getUUID();
//        Parking parking = new Parking(id, "MTT-1010", "SC", "TOYOTA", "PRETO");
//      //  Parking parking1 = new Parking(id1, "STB-1015", "MG", "FORD", "VERMELHO");
//       parkingMap.put(id,parking);
//      // parkingMap.put(id1,parking1);
//    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll(){
      return parkingRepository.findAll();
//        return parkingMap.values().stream()
//                .collect(Collectors.toList());
    }

    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    @Transactional(readOnly = true)
    public Parking findById(String id) {

        return parkingRepository.findById(id).orElseThrow(()->
                new ParkingNotFoundException(id));

//        Parking parking = parkingMap.get(id);
//        if (parking == null){
//            throw new ParkingNotFoundException(id);
//        }
//        return parking;
    }

    @Transactional
    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingCreate);
      //  parkingMap.put(uuid,parkingCreate);
        return parkingCreate;
    }
    @Transactional
    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
       // return parkingMap.remove(id);
    }
    @Transactional
    public Parking update(String id, Parking parkingUpdate) {
//        Parking parking = findById(id);
//        parking.setColor(parkingUpdate.getColor());
//        parkingMap.replace(id,parking);
//        return parking;

        Parking parking = findById(id);
        parking.setColor(parkingUpdate.getColor());
        parking.setState(parkingUpdate.getState());
        parking.setModel(parkingUpdate.getModel());
        parking.setLicense(parkingUpdate.getLicense());
        parkingRepository.save(parking);
        return parking;

    }

    //implementar esse metodo
    @Transactional
    public Parking checkOut(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckout.getBill(parking));


        //recuperar o estacionamento
        //atualizar data de saida
        //calcular o valor
        parkingRepository.save(parking);
        return parking;
    }
}
