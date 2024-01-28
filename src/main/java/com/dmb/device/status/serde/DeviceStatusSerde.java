package com.dmb.device.status.serde;

import com.dmb.device.status.model.DeviceStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class DeviceStatusSerde implements Serde<DeviceStatus> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Serializer<DeviceStatus> serializer() {
        return new Serializer<DeviceStatus>() {
            @Override
            public void configure(Map<String, ?> configs, boolean isKey) {
                // Nothing to configure
            }

            @Override
            public byte[] serialize(String topic, DeviceStatus data) {
                try {
                    return objectMapper.writeValueAsBytes(data);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to serialize DeviceStatus", e);
                }
            }

            @Override
            public void close() {
                // Nothing to close
            }
        };
    }

    @Override
    public Deserializer<DeviceStatus> deserializer() {
        return new Deserializer<DeviceStatus>() {
            @Override
            public void configure(Map<String, ?> configs, boolean isKey) {
                // Nothing to configure
            }

            @Override
            public DeviceStatus deserialize(String topic, byte[] data) {
                try {
                    return objectMapper.readValue(data, DeviceStatus.class);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to deserialize DeviceStatus", e);
                }
            }

            @Override
            public void close() {
                // Nothing to close
            }
        };
    }
}
