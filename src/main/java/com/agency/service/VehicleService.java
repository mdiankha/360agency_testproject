package com.agency.service;

import com.agency.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {

    Vehicle findOne(String id) ;

    List<Vehicle> findAll() ;

    Vehicle create(Vehicle vehiclesList);

    Vehicle update(String vin, Vehicle vehicle);

    void delete(String id);

    List<Vehicle> findAllByDealerNameAndStatus(String name, String status);

    Vehicle publishVehi(int id);

    Vehicle unpublishVehi(int id);

    Vehicle updateStatus(Vehicle vehicle);


}
