package com.chyld.repositories;

import com.chyld.entities.Device;
import com.chyld.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IDeviceRepository extends PagingAndSortingRepository<Device, Integer> {
    public Device findBySerialNumber(String serialNumber);
}
