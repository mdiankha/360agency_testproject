package com.agency.service;

import com.agency.entity.Vehicle;
import com.agency.entity.Vehicle.StatusEnum;
import com.agency.repository.VehicleRepository;
import com.agency.exception.BadRequestException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class VehicleServiceImplementation implements VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    @Transactional()
    public Vehicle findOne(String vin) {
        Optional<Vehicle> vehicle = vehicleRepository.findByVin(vin);
        if (!vehicle.isPresent()) {
            throw new ResourceNotFoundException("Vehicle with vin " + vin + " doesn't exist.");
        }
        return vehicle.get();
    }

    @Transactional()
    public List<Vehicle> findAll() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    @Transactional
    public Vehicle create(Vehicle vehicle) {
        return (Vehicle) vehicleRepository.save(vehicle);
    }

    @Transactional()
    public Vehicle update(String vin, Vehicle vehicle) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findByVin(vehicle.getVin());
        if (!vehicleOptional.isPresent()) {
            throw new ResourceNotFoundException("Vehicle with make " + vehicle.getVin() + " doesn't exist.");
        }
        return vehicleRepository.save(vehicle);
    }


    @Transactional()
    public void delete(String vin) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findByVin(vin);
        if (!vehicleOptional.isPresent()) {
            throw new ResourceNotFoundException("Vehicle with VIN " + vin + " doesn't exist.");
        }
        vehicleRepository.delete(vehicleOptional.get());
    }

    @Transactional()
    public List<Vehicle> findAllByDealerNameAndStatus(String name, String status) {
        // TODO Auto-generated method stub
        List<Vehicle> listVehicleByStatus = new ArrayList<Vehicle>();
        listVehicleByStatus = vehicleRepository.findByDealerName(name);
        List<Vehicle> listToReturn = new ArrayList<Vehicle>();
        if(status.equals("DRAFT")){
            for (Vehicle vehicle : listVehicleByStatus) {
                if(vehicle.getStatus() == StatusEnum.DRAFT){
                    listToReturn.add(vehicle);
                } 
            }
            
        }else { 
            for (Vehicle vehicle : listVehicleByStatus) {
                if(vehicle.getStatus() == StatusEnum.PUBLISHED){
                    listToReturn.add(vehicle);
                } 
            }
            
          
        }
       
         

        return listToReturn;
    }

    @Transactional()
    public Vehicle publishVehi(int id) {
        Vehicle vehicule = new Vehicle();
        vehicule = vehicleRepository.findById(id);
        return vehicule;
    }

    @Transactional()
    public Vehicle unpublishVehi(int id) {
        Vehicle vehicule = new Vehicle();
        vehicule = vehicleRepository.findById(id);
        return vehicule;
    }


    @Transactional()
    public Vehicle updateStatus(Vehicle vehicle) {
      return   vehicleRepository.save(vehicle);
        
    }


}
