package com.supership.ship.service.impl;

import com.supership.ship.converter.ProductConverter;
import com.supership.ship.dto.ProductDTO;
import com.supership.ship.entity.ProductEntity;
import com.supership.ship.repository.ProductRepository;
import com.supership.ship.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        if (productDTO.getId() != null) { // update
            Optional<ProductEntity> productEntityOptional = productRepository.findById(productDTO.getId());
            ProductEntity oldProductEntity = productEntityOptional.orElse(null);
            if (oldProductEntity != null) {
                productEntity = productConverter.toEntity(productDTO, oldProductEntity);
            } else {
                // Xử lý tình huống khi không tìm thấy người dùng để cập nhật
                // (có thể làm gì đó tùy theo yêu cầu của bạn)
            }
        }else { // create
            productEntity = productConverter.toEntity(productDTO);
        }

        productEntity = productRepository.save(productEntity);

        return productConverter.toDTO(productEntity);
    }

    @Override
    public void delete(long[] ids) {
        
    }
}
