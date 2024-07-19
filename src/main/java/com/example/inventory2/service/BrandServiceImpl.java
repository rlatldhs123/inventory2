package com.example.inventory2.service;

import com.example.inventory2.dto.BrandDTO;
import com.example.inventory2.entity.Brand;
import com.example.inventory2.repository.BrandRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

  private final BrandRepository brandRepository;

  @Override
  public List<BrandDTO> getBrandList() {
    List<Brand> entityList = brandRepository.findAll();
    return entityList
      .stream()
      .map(this::entityToDto)
      .collect(Collectors.toList());
  }

  @Override
  public void save(BrandDTO brandDTO) {
    Brand brand = dtoToEntity(brandDTO);
    brandRepository.save(brand);
  }
}
