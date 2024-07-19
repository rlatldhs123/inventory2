package com.example.inventory2.service;

import com.example.inventory2.dto.PriceDTO;
import com.example.inventory2.entity.Price;
import com.example.inventory2.repository.PriceRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

  @Autowired
  private final PriceRepository priceRepository;

  @Override
  public List<PriceDTO> findAllPrices() {
    List<Price> prices = priceRepository.findAll();
    return prices.stream().map(this::entityToDto).collect(Collectors.toList());
  }

  @Override
  public void save(PriceDTO priceDTO) {
    Price price = dtoToEntity(priceDTO);
    priceRepository.save(price);
  }
}
