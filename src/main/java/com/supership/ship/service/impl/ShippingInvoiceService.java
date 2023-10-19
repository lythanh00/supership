package com.supership.ship.service.impl;

import com.supership.ship.converter.ShippingInvoiceConverter;
import com.supership.ship.dto.ShippingInvoiceDTO;
import com.supership.ship.entity.ShippingInvoiceEntity;
import com.supership.ship.entity.UserEntity;
import com.supership.ship.repository.ShippingInvoiceRepository;
import com.supership.ship.service.IShippingInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShippingInvoiceService implements IShippingInvoiceService {

    @Autowired
    private ShippingInvoiceRepository shippingInvoiceRepository;

    @Autowired
    private ShippingInvoiceConverter shippingInvoiceConverter;

    @Override
    public ShippingInvoiceDTO save(ShippingInvoiceDTO shippingInvoiceDTO) {
        ShippingInvoiceEntity shippingInvoiceEntity = new ShippingInvoiceEntity();
        if (shippingInvoiceDTO.getId() != null) { // update
            Optional<ShippingInvoiceEntity> shippingInvoiceEntityOptional = shippingInvoiceRepository.findById(shippingInvoiceDTO.getId());
            ShippingInvoiceEntity oldShippingInvoiceEntity = shippingInvoiceEntityOptional.orElse(null);
            if (oldShippingInvoiceEntity != null) {
                shippingInvoiceEntity = shippingInvoiceConverter.toEntity(shippingInvoiceDTO, oldShippingInvoiceEntity);
            } else {
                // Xử lý tình huống khi không tìm thấy người dùng để cập nhật
                // (có thể làm gì đó tùy theo yêu cầu của bạn)
            }
        }else { // create
            shippingInvoiceEntity = shippingInvoiceConverter.toEntity(shippingInvoiceDTO);
        }
        shippingInvoiceEntity = shippingInvoiceRepository.save(shippingInvoiceEntity);

        return shippingInvoiceConverter.toDTO(shippingInvoiceEntity);
    }
}
