package com.chyld.services;

import com.chyld.entities.Device;
import com.chyld.entities.User;
import com.chyld.repositories.IDeviceRepository;
import com.chyld.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    private IDeviceRepository repository;

    @Autowired
    public void setRepository(IDeviceRepository repository) {
        this.repository = repository;
    }

    public User findUserByDeviceId(Integer id) {
        return repository.findOne(id).getUser();
    }

    public Device saveDevice(Device device) {
        return repository.save(device);
    }

    public Device loadDeviceBySerialNumber(String serialNumber) {
        return repository.findBySerialNumber(serialNumber);
    }

    public User findUserByDeviceSerialNumber(String serialNumber) {
        return repository.findBySerialNumber(serialNumber).getUser();
    }


}
