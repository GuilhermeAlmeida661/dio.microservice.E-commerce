package br.com.dio.warehouse.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.UUID;

import br.com.dio.warehouse.controller.request.ProductSaveRequest;
import br.com.dio.warehouse.controller.response.ProductDetailResponse;
import br.com.dio.warehouse.controller.response.ProductSavedResponse;
import br.com.dio.warehouse.mapper.IProductMapper;
import br.com.dio.warehouse.service.IProductQueryService;
import br.com.dio.warehouse.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("products")
@AllArgsConstructor
public class ProductController {

    private final IProductService service;
    private final IProductMapper mapper;
    private final IProductQueryService queryService;

    @PostMapping
    @ResponseStatus(CREATED)
    ProductSavedResponse create(@RequestBody final ProductSaveRequest request){
        var entity = mapper.toEntity(request);
        entity = service.save(entity);
        return mapper.toSavedResponse(entity);
    }

    @PostMapping("{id}/purchase")
    @ResponseStatus(NO_CONTENT)
    void purchase(@PathVariable  UUID id) {
        service.purchase(id);
    }
    
    @GetMapping("{id}")
    ProductDetailResponse findById(@PathVariable  UUID id) {
        var dto = queryService.findById(id);
        return mapper.toDetailResponse(dto);
    }  

}
