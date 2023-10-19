package com.supership.ship.api;

import com.supership.ship.dto.ResponseDTO;
import com.supership.ship.dto.ShippingInvoiceDTO;
import com.supership.ship.dto.UserDTO;
import com.supership.ship.service.IShippingInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ShippingInvoiceAPI {
    @Autowired
    private IShippingInvoiceService shippingInvoiceService;

    @PostMapping(value = "/shippinginvoice")
    public ResponseDTO createShippingInvoice(@RequestBody ShippingInvoiceDTO model) {
        ShippingInvoiceDTO shippingInvoiceDTO = shippingInvoiceService.save(model);
        if (shippingInvoiceDTO != null) {
            return new ResponseDTO(200, shippingInvoiceDTO, "Shipping invoice created successfully");
        } else {
            return new ResponseDTO(500, null, "Failed to create shipping invoice");
        }
    }

    @PutMapping("/shippinginvoice/{id}")
    public ResponseDTO updateShippingInvoice(@RequestBody ShippingInvoiceDTO model, @PathVariable("id") long id) {
        model.setId(id);
        ShippingInvoiceDTO shippingInvoiceDTO = shippingInvoiceService.save(model);
        if (shippingInvoiceDTO != null) {
            return new ResponseDTO(200, shippingInvoiceDTO, "Shipping invoice updated successfully");
        } else {
            return new ResponseDTO(500, null, "Failed to update shipping invoice");
        }
    }
}
