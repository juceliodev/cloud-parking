package com.br.dio.cloudparking.repository;

import com.br.dio.cloudparking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking,String> {
}
