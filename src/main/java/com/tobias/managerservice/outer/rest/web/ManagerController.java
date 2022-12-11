package com.tobias.managerservice.outer.rest.web;

import com.tobias.managerservice.inner.domain.*;
import com.tobias.managerservice.inner.service.ManagerService;
import com.tobias.managerservice.inner.service.SaleRequestService;
import com.tobias.managerservice.outer.adaptor.KafkaConsumer;
import com.tobias.managerservice.outer.adaptor.KafkaProducer;
import com.tobias.managerservice.outer.dto.SaleRequestDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;
    private final SaleRequestService saleRequestService;
    private final KafkaProducer kafkaProducer;
    private final KafkaConsumer kafkaConsumer;

    @GetMapping("/health_check")
    public String status(){
            return "It's Working in Manager CUD Service";
        }

    @PostMapping("/manager/v1")
    public HttpStatus addManager(@RequestBody RequestManager requestManager) {
        managerService.addManager(requestManager);
        return HttpStatus.OK;
    }

    @GetMapping("/manager/v1")
    public ResponseEntity<List<ResponseManager>> allManager() {
        Iterable<Manager> managers = managerService.getManagersAll();
        List<ResponseManager> result = new ArrayList<>();
        managers.forEach(v -> result.add(new ModelMapper().map(v, ResponseManager.class)));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @DeleteMapping("/manager/v1/{managerId}")
    public HttpStatus deleteManager(@PathVariable("managerId") int managerId){
        managerService.deleteManager(managerId);
        return HttpStatus.OK;
    }

    @PutMapping("/manager/v1/{managerId}")
    public HttpStatus setSaler(@PathVariable("managerId") int managerId,@RequestBody RequestManager requestManager){
        managerService.setManager(managerId, requestManager);
        return HttpStatus.OK;
    }

    @GetMapping("/manager/v1/saleRequest")
    public ResponseEntity<List<ResponseSaleRequest>> allSaleRequest() {
        Iterable<SaleRequest> saleRequests = saleRequestService.getSaleRequestsAll();
        List<ResponseSaleRequest> result = new ArrayList<>();
        saleRequests.forEach(v -> result.add(new ModelMapper().map(v, ResponseSaleRequest.class)));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/manager/v1/saleRequest/{saleRequestId}")
    public HttpStatus sendSaleRequest (@PathVariable("saleRequestId") int saleRequestId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SaleRequest saleRequest = saleRequestService.getSaleRequest(saleRequestId);
        SaleRequestDto saleRequestDto = mapper.map(saleRequest, SaleRequestDto.class);
        kafkaProducer.send("sale-request-topic", saleRequestDto);
          return HttpStatus.OK;
    }
}
