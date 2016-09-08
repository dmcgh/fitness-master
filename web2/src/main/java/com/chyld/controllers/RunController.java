package com.chyld.controllers;

import com.chyld.entities.Device;
import com.chyld.entities.Run;
import com.chyld.entities.User;
import com.chyld.services.DeviceService;
import com.chyld.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/runs")
public class RunController {

    private UserService userService;
    private DeviceService deviceService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public List<Run> getRuns(Principal user) {
//        int uid = ((JwtToken)user).getUserId();
//        User u = userService.findUserById(uid);
//        return u.getRuns();
//    }

    @RequestMapping(value = "/{serialNumber}/start", method = RequestMethod.POST)
    public Run createRun(@PathVariable String serialNumber) {
        User u = deviceService.findUserByDeviceSerialNumber(serialNumber);
        Device device = deviceService.loadDeviceBySerialNumber(serialNumber);
        for (Run run: device.getRuns()
             ) {
            if(run.getActive() == true){
                return null;
            }
        }

        Run run = new Run();
        run.setStartTime(new Date());
        run.setActive(true);
        device.getRuns().add(run);
        run.setDevice(device);

        userService.saveUser(u);
        return run;
    }

    @RequestMapping(value = "/{serialNumber}/stop", method = RequestMethod.PATCH)
    public Run updateRun(@PathVariable String serialNumber) {
        User u = deviceService.findUserByDeviceSerialNumber(serialNumber);
        Device device = deviceService.loadDeviceBySerialNumber(serialNumber);
       for (Run run: device.getRuns()) {
           if(run.getActive() == true){
               run.setActive(false);
               run.setEndTime(new Date());
               deviceService.saveDevice(device);
               return run;
           }
        }
        return null;
    }
}
