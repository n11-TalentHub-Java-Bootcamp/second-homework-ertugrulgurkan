package com.ertugrul.springboot.controller;

import com.ertugrul.springboot.converter.ProductCommentConverter;
import com.ertugrul.springboot.converter.ProductConverter;
import com.ertugrul.springboot.dto.ProductCommentDto;
import com.ertugrul.springboot.dto.ProductDetailDto;
import com.ertugrul.springboot.dto.ProductDto;
import com.ertugrul.springboot.entity.Product;
import com.ertugrul.springboot.entity.ProductComment;
import com.ertugrul.springboot.exception.CommentNotFoundException;
import com.ertugrul.springboot.exception.ProductNotFoundException;
import com.ertugrul.springboot.service.ProductCommentService;
import com.ertugrul.springboot.service.ProductService;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//Api üzerinden ürünlere erişmek için yazılmış controller sınıfı
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    private final ProductCommentService productCommentService;

    public ProductController(ProductService productService, ProductCommentService productCommentService) {
        this.productService = productService;
        this.productCommentService = productCommentService;
    }

    @GetMapping(value = {"", "/"})
    public MappingJacksonValue findAllProductList() {

        List<Product> productList = productService.findAll();

        String filterName = "ProductFilter";

        SimpleFilterProvider filters = getProductFilterProvider(filterName);

        MappingJacksonValue mapping = new MappingJacksonValue(productList);

        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/{id}")
    public MappingJacksonValue findProductById(@PathVariable Long id) {

        Product product = productService.findById(id);

        if (product == null) {
            throw new ProductNotFoundException("Product not found. id: " + id);
        }

        WebMvcLinkBuilder linkToProduct = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass())
                        .findAllProductList()
        );

        ProductDto productDto = ProductConverter.INSTANCE.convertProductToProductDto(product);

        String filterName = "ProductDtoFilter";

        SimpleFilterProvider filters = getProductFilterProvider(filterName);

        EntityModel entityModel = EntityModel.of(productDto);

        entityModel.add(linkToProduct.withRel("all-products"));

        MappingJacksonValue mapping = new MappingJacksonValue(entityModel);

        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/detail/{id}")
    public ProductDetailDto findProductDtoById(@PathVariable Long id) {

        Product product = productService.findById(id);

        if (product == null) {
            throw new ProductNotFoundException("Product not found. id: " + id);
        }

        ProductDetailDto productDetailDto = ProductConverter.INSTANCE.convertProductToProductDetailDto(product);

        return productDetailDto;
    }

    @PostMapping("")
    public ResponseEntity<Object> saveProduct(@RequestBody ProductDto productDto) {

        Product product = ProductConverter.INSTANCE.convertProductDtoToProduct(productDto);

        product = productService.save(product);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("categories/{categoryId}")
    public List<ProductDetailDto> findAllProductByCategoryId(@PathVariable Long categoryId) {

        List<Product> productList = productService.findAllByCategoryOrderByIdDesc(categoryId);

        List<ProductDetailDto> productDetailDtoList = ProductConverter.INSTANCE.convertAllProductListToProductDetailDtoList(productList);

        return productDetailDtoList;
    }

    private SimpleFilterProvider getProductFilterProvider(String filterName) {
        SimpleBeanPropertyFilter filter = getProductFilter();

        return new SimpleFilterProvider().addFilter(filterName, filter);
    }

    private SimpleBeanPropertyFilter getProductFilter() {
        return SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "price", "recordDate");
    }

    // 3.2. Bir ürüne yapılan tüm yorumları getiren bir servis
    // Example Req:  http://localhost:8080/api/products/3/comments
    @GetMapping(value = {"/{id}/comments/", "/{id}/comments"})
    public List<ProductCommentDto> findProductCommentByUserId(@PathVariable Long id) {

        Product product = productService.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("Product not found. id: " + id);
        }
        List<ProductComment> productCommentList = productCommentService.findProductCommentByUserId(id);
        if (productCommentList == null || productCommentList.size() == 0) {
            throw new CommentNotFoundException("There are no comments for this product yet.");
        }
        List<ProductCommentDto> productCommentDtoList = ProductCommentConverter.INSTANCE.convertAllProductCommentListToProductCommentDtoList(productCommentList);
        return productCommentDtoList;
    }

}
