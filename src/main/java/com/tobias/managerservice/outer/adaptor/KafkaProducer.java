package com.tobias.managerservice.outer.adaptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tobias.managerservice.inner.domain.SaleRequest;
import com.tobias.managerservice.outer.dto.SaleRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public SaleRequestDto send(String kafkaTopic, SaleRequestDto saleRequestDto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(saleRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(kafkaTopic, jsonInString);
        log.info("Kafka Producer send data from the saleRequest: " + saleRequestDto);

        return saleRequestDto;
    }

    // TODO: service interface를 통해 생성 (리팩터링할 것)
}
