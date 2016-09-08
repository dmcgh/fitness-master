package com.chyld.controllers;

import com.chyld.entities.Device;
import com.chyld.entities.Position;
import com.chyld.entities.Run;
import com.chyld.entities.User;
import com.chyld.services.DeviceService;
import com.chyld.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/positions")
public class PositionController {

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
//    public List<Device> getDevices(Principal user) {
//        int uid = ((JwtToken)user).getUserId();
//        User u = userService.findUserById(uid);
//        return u.getDevices();
//    }

    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.POST)
    public Position createPosition(@PathVariable String serialNumber, @RequestBody Position position) {
        User u = deviceService.findUserByDeviceSerialNumber(serialNumber);
        Device device = deviceService.loadDeviceBySerialNumber(serialNumber);
        for (Run run: device.getRuns()) {
            if(run.getActive() == true){
                Position newPosition = new Position();
                newPosition.setLatitude(position.getLatitude());
                newPosition.setLongitude(position.getLongitude());
                newPosition.setAltitude(position.getAltitude());
                newPosition.setRun(run);
                run.getPositions().add(newPosition);
                deviceService.saveDevice(device);
                return newPosition;
            }
        }
        return null;
    }
}
