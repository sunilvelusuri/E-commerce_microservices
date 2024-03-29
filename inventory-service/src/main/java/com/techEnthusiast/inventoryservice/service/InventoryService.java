package com.techEnthusiast.inventoryservice.service;

import com.techEnthusiast.inventoryservice.dto.InventoryResponse;
import com.techEnthusiast.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    public List<InventoryResponse> isInStock(List<String> skuCode){
         return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                 .map(inventory ->
                     InventoryResponse.builder()
                             .skuCode(inventory.getSkuCode())
                             .isInStock(inventory.getQuantity() > 0)
                             .build()
                 ).toList();
    }
}
