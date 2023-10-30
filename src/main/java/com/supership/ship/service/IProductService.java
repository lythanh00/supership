package com.supership.ship.service;

import com.supership.ship.dto.ProductDTO;
import org.springframework.stereotype.Service;

@Service
public interface IProductService {
    ProductDTO save(ProductDTO productDTO);
    void delete(long[] ids);
}
