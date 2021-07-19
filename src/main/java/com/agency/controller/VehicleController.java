package com.agency.controller;

import com.agency.entity.Vehicle;
import com.agency.entity.Vehicle.StatusEnum;
import com.agency.exception.BadRequestException;
import com.agency.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.implementation.bytecode.Throw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

@Controller
@RestController
@Api(description = "Endpoint to manage listing for online advertising service")
//@RequestMapping("/api")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;
    @ApiOperation(value = "Endpoint to insert a list of values into Database")
    @RequestMapping(method = RequestMethod.POST, value = "/vehicles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Vehicle create(@RequestBody Vehicle vehicule) {
         vehicule.setStatus(StatusEnum.DRAFT);
        return vehicleService.create(vehicule);
    }

   /*  @ApiOperation(value = "Endpoint to update vehicle details by VIN")
    @RequestMapping(method = RequestMethod.PUT, value = "/vehicles/{vin}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Vehicle update(@PathVariable String vin, @RequestBody Vehicle vehicle) {
        return vehicleService.update(vin, vehicle);
    } */

    @ApiOperation(value = "Endpoint to get vehicle details by VIN")
    @RequestMapping(method = RequestMethod.GET, value = "/vehicles/{vin}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Vehicle findOne(@PathVariable String vin) {
        return vehicleService.findOne(vin);
    }

    @ApiOperation(value = "Endpoint to get all vehicle details")
    @RequestMapping(method = RequestMethod.GET, value = "/vehicles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> findAll() {
        return vehicleService.findAll();
    }

    @ApiOperation(value = "Endpoint to delete vehicle by VIN")
    @RequestMapping(method = RequestMethod.DELETE, value = "/vehicles/{vin}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void delete(@PathVariable String vin) {
        vehicleService.delete(vin);
    }

    @ApiOperation(value = "Endpoint to get all listing by status for a dealer")
    @RequestMapping(method = RequestMethod.GET, value = "/vehiclesbystatus", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> findAllByStatus(@RequestParam(value = "dealerName") String name, @RequestParam(value = "status") String status) {
        return vehicleService.findAllByDealerNameAndStatus(name, status);
    }


    @ApiOperation(value = "Endpoint to publish a vehicule")
    @RequestMapping(method = RequestMethod.PUT, value = "/vehicles/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Vehicle publishVehicle(@PathVariable String id) {
         
        Vehicle vehi =  vehicleService.publishVehi(Integer.parseInt(id));

        List<Vehicle> listVehPubByDealer = new ArrayList<Vehicle>();
         listVehPubByDealer = findAllByStatus(vehi.getDealerName(), "PUBLISHED");
        if( listVehPubByDealer.size() > 5 ){
            throw new ResourceNotFoundException("Pleaze you have exceeded the number of advertisements authorized,  " + listVehPubByDealer.size() + " delete the old advertisements in order to post again.");
          /*   try {

                vehi.setStatus(StatusEnum.PUBLISHED);

              return vehicleService.updateStatus(vehi);
                
            } catch (BadRequestException badRequestException) {
                //TODO: handle exception
                return new Vehicle(badRequestException.getMessage(), HttpStatus.CONFLICT);
            } */
       
        }  vehi.setStatus(StatusEnum.PUBLISHED);

        return vehicleService.updateStatus(vehi);
           
       
    }

    @ApiOperation(value = "Endpoint to Unpublish a vehicule")
    @RequestMapping(method = RequestMethod.PUT, value = "/vehiclesunpub/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Vehicle unpublishVehicle(@PathVariable String id) {
         
        Vehicle vehi =  vehicleService.unpublishVehi(Integer.parseInt(id));

        List<Vehicle> listVehPubByDealer = new ArrayList<Vehicle>();
        listVehPubByDealer = findAllByStatus(vehi.getDealerName(), "DRAFT");
       vehi.setStatus(StatusEnum.DRAFT);

        return vehicleService.updateStatus(vehi);
           
       
    }


}
