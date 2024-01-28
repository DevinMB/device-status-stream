package com.dmb.device.status.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChairSensorData {
    private long timestamp;
    private String deviceId;
    private boolean sitStatus;
    private Double avgValue;


}
