package com.example.inventory2.controller;

import com.example.inventory2.dto.ProductDTO;
import com.example.inventory2.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class ProductController {

  private final ProductService productService;

  @GetMapping("/product_list")
  public void getAllProducts(Model model, ProductDTO productDTO) {
    // List<ProductDTO> products = productService.getAllProductDetails();
    // model.addAttribute("products", products);

    log.info("상품 리스트 요청 {}", productDTO);
  }

  @GetMapping("/move_list")
  public void getMove_list(Model model, ProductDTO productDTO) {
    // List<ProductDTO> products = productService.getAllProductDetails();
    // model.addAttribute("products", products);

    log.info("무브 리스트 요청 {}", productDTO);
  }

  @GetMapping("/inventory_list")
  public void getInventory_list(Model model, ProductDTO productDTO) {
    // List<ProductDTO> products = productService.getAllProductDetails();
    // model.addAttribute("products", products);

    log.info("인벤토리 리스트 요청 {}", productDTO);
  }
}
