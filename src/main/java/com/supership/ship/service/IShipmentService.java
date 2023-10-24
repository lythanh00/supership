package com.supership.ship.service;

import com.supership.ship.dto.ShipmentDTO;
import org.springframework.stereotype.Service;

@Service
public interface IShipmentService {
    ShipmentDTO save(ShipmentDTO shippingInvoiceDTO);
    void delete(long[] ids);
}
