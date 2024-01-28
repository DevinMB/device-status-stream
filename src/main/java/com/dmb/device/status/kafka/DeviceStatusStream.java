package com.dmb.device.status.kafka;

import com.dmb.device.status.config.KafkaConfig;
import com.dmb.device.status.model.ChairSensorData;
import com.dmb.device.status.serde.ChairSensorDataSerde;
import com.dmb.device.status.serde.DeviceStatusSerde;
import com.dmb.device.status.service.DeviceStatusService;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

@Configuration
@EnableKafkaStreams
public class DeviceStatusStream {

    @Autowired
    ChairSensorDataSerde chairSensorDataSerde;
    @Autowired
    DeviceStatusSerde deviceStatusSerde;
    @Autowired
    DeviceStatusService deviceStatusService;

    @Autowired
    private KafkaStreamsConfiguration kafkaStreamsConfig;

    @Bean
    public KafkaStreams setupKafkaStream(StreamsBuilder builder) {
        KStream<String, ChairSensorData> stream = builder.stream("raw-sit-topic", Consumed.with(Serdes.String(), chairSensorDataSerde));
        stream.mapValues(deviceStatusService::convertChairSensorDataToDeviceStatus)
                .selectKey((key, deviceStatus) -> deviceStatus.getDeviceId())
                .to("device-status-test", Produced.with(Serdes.String(), deviceStatusSerde));

        KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), kafkaStreamsConfig.asProperties());
        kafkaStreams.start();

        return kafkaStreams;
    }



}
