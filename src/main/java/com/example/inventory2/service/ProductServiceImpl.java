package com.example.inventory2.service;

import com.example.inventory2.dto.ProductDTO;
import com.example.inventory2.entity.CompanyBrand;
import com.example.inventory2.entity.Price;
import com.example.inventory2.entity.Product;
import com.example.inventory2.repository.ProductRepository;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  ProductRepository productRepository;

  @Override
  public List<Object[]> getAllProductDetails() {
    return null;
  }
}
