package com.supership.ship.api;

import com.supership.ship.dto.ProductDTO;
import com.supership.ship.dto.ResponseDTO;
import com.supership.ship.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ProductAPI {
    @Autowired
    private IProductService productService;

    @PostMapping(value = "/product")
    public ResponseDTO createProduct(@RequestBody ProductDTO model) {
        ProductDTO productDTO = productService.save(model);
        if (productDTO != null) {
            return new ResponseDTO(200, productDTO, "Tạo sản phẩm thành công");
        } else {
            return new ResponseDTO(500, null, "Tạo sản phẩm thất bại");
        }
    }

    @PutMapping(value = "/product/{id}")
    public ResponseDTO updateProduct(@RequestBody ProductDTO model, @PathVariable("id") long id) {
        model.setId(id);
        ProductDTO productDTO = productService.save(model);
        if (productDTO != null) {
            return new ResponseDTO(200, productDTO, "Thay đổi thông tin sản phẩm thành công");
        } else {
            return new ResponseDTO(500, null, "Thay đổi thông tin sản phẩm thất bại");
        }
    }

    @DeleteMapping(value = "/shipment")
    public ResponseDTO deleteShipment(@RequestBody long[] ids) {
        try {
            productService.delete(ids);
            return new ResponseDTO(200, null, "Xóa thông tin sản phẩm thành công");
        } catch (Exception e) {
            return new ResponseDTO(500, null, "Xóa thông tin sản phẩm thất bại");
        }
    }
}
