package com.chyld.messaging;

import com.chyld.entities.Device;
import com.chyld.entities.Position;
import com.chyld.entities.Run;
import com.chyld.services.DeviceService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.HashMap;

@Service
public class PositionComputeService {

    private DeviceService deviceService;

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @RabbitListener(queues = "fit.queue.position")
    @Transactional
    public void recieve(Message msg, HashMap<String, Object> data){
        String key = msg.getMessageProperties().getReceivedRoutingKey();
        String serialNumber = (String) data.get("serialNumber");
        Position position = (Position) data.get("position");
        Device device = deviceService.loadDeviceBySerialNumber(serialNumber);
        for (Run run: device.getRuns()) {
            if(run.getActive() == true) {
                run.getPositions().add(position);
                deviceService.saveDevice(device);

            }
        }
    }
}
