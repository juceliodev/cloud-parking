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


@Service
public class ParkingService {

    //HasMap usado para implementacao antes de usar o banco de dados
   // private static HashMap<String, Parking> parkingMap = new HashMap<String, Parking>();


    private ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll(){
      return parkingRepository.findAll();
    }

    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    @Transactional(readOnly = true)
    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(()->
                new ParkingNotFoundException(id));
    }

    @Transactional
    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingCreate);
        return parkingCreate;
    }

    @Transactional
    public Parking update(String id, Parking parkingUpdate) {
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
        parkingRepository.save(parking);
        return parking;
    }
    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
    }

}
