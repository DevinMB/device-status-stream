package com.dmb.device.status.serde;

import com.dmb.device.status.model.ChairSensorData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;


public class ChairSensorDataSerde implements Serde<ChairSensorData> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Serializer<ChairSensorData> serializer() {
        return new Serializer<ChairSensorData>() {
            @Override
            public void configure(Map<String, ?> configs, boolean isKey) {
                // Nothing to configure
            }

            @Override
            public byte[] serialize(String topic, ChairSensorData data) {
                try {
                    return objectMapper.writeValueAsBytes(data);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to serialize ChairSensorData", e);
                }
            }

            @Override
            public void close() {
                // Nothing to close
            }
        };
    }

    @Override
    public Deserializer<ChairSensorData> deserializer() {
        return new Deserializer<ChairSensorData>() {
            @Override
            public void configure(Map<String, ?> configs, boolean isKey) {
                // Nothing to configure
            }

            @Override
            public ChairSensorData deserialize(String topic, byte[] data) {
                try {
                    return objectMapper.readValue(data, ChairSensorData.class);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to deserialize ChairSensorData", e);
                }
            }

            @Override
            public void close() {
                // Nothing to close
            }
        };
    }
}
