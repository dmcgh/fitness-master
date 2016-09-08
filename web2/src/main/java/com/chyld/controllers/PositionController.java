package com.chyld.controllers;

import com.chyld.entities.Device;
import com.chyld.entities.Position;
import com.chyld.entities.Run;
import com.chyld.entities.User;
import com.chyld.services.DeviceService;
import com.chyld.services.UserService;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/positions")
public class PositionController {

    private UserService userService;
    private DeviceService deviceService;
    private RabbitTemplate rabbitTemplate;
    private TopicExchange topicExchange;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Autowired
    public void setTopicExchange(TopicExchange topicExchange) {
        this.topicExchange = topicExchange;
    }


    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.POST)
    public Position createPosition(@PathVariable String serialNumber, @RequestBody Position position) {
        String topicName = topicExchange.getName();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("serialNumber", serialNumber);
        hashMap.put("position", position);
        rabbitTemplate.convertAndSend(topicName, "fit.topic.position", hashMap);

        return null;

//        User u = deviceService.findUserByDeviceSerialNumber(serialNumber);
//
//        Device device = deviceService.loadDeviceBySerialNumber(serialNumber);
//        for (Run run: device.getRuns()) {
//            if(run.getActive() == true){
//                Position newPosition = new Position();
//                newPosition.setLatitude(position.getLatitude());
//                newPosition.setLongitude(position.getLongitude());
//                newPosition.setAltitude(position.getAltitude());
//                newPosition.setRun(run);
//                run.getPositions().add(newPosition);
//                deviceService.saveDevice(device);
//                return newPosition;
//            }
//        }
//        return null;
    }
}
