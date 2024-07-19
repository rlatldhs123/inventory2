package com.example.inventory2.service;

import com.example.inventory2.dto.BrandDTO;
import com.example.inventory2.dto.CompanyDTO;
import com.example.inventory2.dto.PageRequestDto;
import com.example.inventory2.dto.PageResultDto;
import com.example.inventory2.dto.PriceDTO;
import com.example.inventory2.dto.ProductDTO;
import com.example.inventory2.dto.ProductInventoryDTO;
import com.example.inventory2.entity.Brand;
import com.example.inventory2.entity.Company;
import com.example.inventory2.entity.CompanyBrand;
import com.example.inventory2.entity.Price;
import com.example.inventory2.entity.Product;
import com.example.inventory2.entity.QProduct;
import com.example.inventory2.repository.BrandRepository;
import com.example.inventory2.repository.CompanyBrandRepository;
import com.example.inventory2.repository.CompanyRepository;
import com.example.inventory2.repository.PriceRepository;
import com.example.inventory2.repository.ProductRepository;
import com.example.inventory2.status.QuantitySatatus;
import com.querydsl.core.BooleanBuilder;
import jakarta.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final PriceRepository priceRepository;
  private final CompanyRepository companyRepository;
  private final BrandRepository brandRepository;
  private final CompanyBrandRepository companyBrandRepository;
  private final CompanyService companyService;
  private final BrandService brandService;
  private final PriceService priceService;

  @Override
  public PageResultDto<ProductDTO, Object[]> getList(PageRequestDto requestDto) {

    Page<Object[]> result = productRepository.getProductList(requestDto.getType(), requestDto.getKeyword(),
        requestDto.getQuantityStatusStr(),
        requestDto.getPageable(Sort.by("productId").descending()));

    Function<Object[], ProductDTO> function = (en -> entityToDto((Product) en[0],
        (CompanyBrand) en[1], (Price) en[2]));

    return new PageResultDto<>(result, function);

  }

  //
  // 재고 총량 재고 총량에 따른 총 계산되는 매입가 공급가 판매가 총액 계산 가능

  @Override
  public CompanyBrand createCompanyBrand(ProductDTO productDTO) {
    Company company = companyRepository.findByCompanyCode(
        productDTO.getCompanyCode());
    Brand brand = brandRepository.findByBrandCode(productDTO.getBrandCode());

    if (company == null || brand == null) {
      throw new IllegalArgumentException("Invalid company or brand code");
    }

    // findByCompanyAndBrand를 호출하여 결과를 리스트로 받음
    List<CompanyBrand> existingCompanyBrands = companyBrandRepository.findByCompanyAndBrand(
        company,
        brand);

    if (!existingCompanyBrands.isEmpty()) {
      // 중복된 레코드가 있을 경우, 첫 번째 레코드를 반환하거나 적절히 처리
      return existingCompanyBrands.get(0);
    }

    // 새로 CompanyBrand 생성
    CompanyBrand newCompanyBrand = CompanyBrand
        .builder()
        .company(company)
        .brand(brand)
        .build();

    return companyBrandRepository.save(newCompanyBrand);
  }

  @Transactional
  @Override
  public Long productCreate(ProductDTO productDTO) {
    // CompanyBrand 생성
    CompanyBrand companyBrand = createCompanyBrand(productDTO);

    Map<String, Object> entityMap = dtoToEntity(productDTO, companyBrand); // 수정: dtoToEntity 메서드에 companyBrand 추가
    Product product = (Product) entityMap.get("product");
    Price price = (Price) entityMap.get("price");

    if (companyBrand == null) {
      throw new IllegalArgumentException("CompanyBrand cannot be null");
    }

    if (price == null) {
      throw new IllegalArgumentException("Price cannot be null");
    }

    // Product와 Price를 연결

    // Product를 저장 (CascadeType.ALL로 인해 Price도 함께 저장됨)
    Price newPrice = priceRepository.save(price);

    product.setPrice(newPrice);
    productRepository.save(product);

    return product.getProductId();
  }

  @Override
  public PageResultDto<ProductDTO, Product> findByQuantityStatusList(
      QuantitySatatus quantitySatatus,
      Pageable pageable) {
    Page<Product> entityList = productRepository.findByQuantityStatus(
        quantitySatatus,
        pageable);
    // Function<Product, ProductDTO> fn = this::entityToDto;
    // return new PageResultDto<>(entityList, fn);

    return null; // 확인하고 풀어보기
  }

  @Override
  public ProductDTO getProduct(String productCode) {
    Product productEntity = productRepository.findByProductCode(productCode);
    // return entityToDto(productEntity);
    return null; // 확인하고 풀어보기
  }

  @Override
  public List<ProductInventoryDTO> searchProducts(String productCode, String productName) {
    return productRepository.searchProducts(productCode, productName);
  }

}
