package com.tobias.managerservice.outer.adaptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tobias.managerservice.inner.domain.SaleRequest;
import com.tobias.managerservice.inner.repository.ManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KafkaConsumer {
    ManagerRepository repository;

    @Autowired
    public KafkaConsumer(ManagerRepository repository){
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


        //repository.save(entity);
    }
}
