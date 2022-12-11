package com.tobias.managerservice.outer.adaptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tobias.managerservice.inner.domain.SaleRequest;
import com.tobias.managerservice.inner.repository.ManagerRepository;
import com.tobias.managerservice.inner.repository.SaleRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KafkaConsumer {
    SaleRequestRepository repository;

    @Autowired
    public KafkaConsumer(SaleRequestRepository repository){
        this.repository = repository;
    }

    @KafkaListener(topics = "saler-topic")
    public void processMessage(String kafkaMessage){
        log.info("Kafka Message: ====> " + kafkaMessage);

        Map<Object,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try{
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
            }catch(JsonProcessingException e){
            e.printStackTrace();
        }

        SaleRequest saleRequest = new SaleRequest();
        saleRequest.setSalerId((int)map.get("id"));
        saleRequest.setName((String)map.get("name"));
        saleRequest.setNickname((String)map.get("nickname"));
        saleRequest.setEmail((String)map.get("email"));
        saleRequest.setGender((String)map.get("gender"));
        saleRequest.setBirth((String)map.get("birth"));
        saleRequest.setPhone((String)map.get("phone"));
        saleRequest.setWebsite((String)map.get("website"));
        repository.save(saleRequest);
    }
}
