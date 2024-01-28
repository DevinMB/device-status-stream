package com.dmb.device.status.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceStatus {

    private long timestamp;

    private String deviceId;

    private String status;

    private boolean active;


}
