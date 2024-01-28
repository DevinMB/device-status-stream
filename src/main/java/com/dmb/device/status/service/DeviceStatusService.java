package com.dmb.device.status.service;

import com.dmb.device.status.model.ChairSensorData;
import com.dmb.device.status.model.DeviceStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class DeviceStatusService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public DeviceStatus convertChairSensorDataToDeviceStatus(ChairSensorData chairSensorData) {

        String status = chairSensorData.isSitStatus() ? "In use" : "Not in use";
        return new DeviceStatus(chairSensorData.getTimestamp(), chairSensorData.getDeviceId(), status,chairSensorData.isSitStatus());
    }

}

