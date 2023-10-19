package com.supership.ship.service;

import com.supership.ship.dto.ShippingInvoiceDTO;
import org.springframework.stereotype.Service;

@Service
public interface IShippingInvoiceService {
    ShippingInvoiceDTO save(ShippingInvoiceDTO shippingInvoiceDTO);
}
