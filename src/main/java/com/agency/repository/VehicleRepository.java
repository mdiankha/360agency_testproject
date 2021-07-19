package com.agency.repository;

import com.agency.entity.Vehicle;
import com.agency.entity.Vehicle.StatusEnum;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, String> {
    Optional<Vehicle> findByVin(String vin);
    Optional<Vehicle> findByMake(String make);
    List<Vehicle> findByDealerName(String name);
    Vehicle findById(int id);
    
   
    
}
