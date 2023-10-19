package com.supership.ship.dto;

import lombok.*;


import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingInvoiceDTO extends AbstractDTO<ShippingInvoiceDTO>{
    private String senderName;

    private String senderAddress;

    private String senderPhoneNumber;

    private String receiverName;

    private String receiverAddress;

    private String receiverPhoneNumber;

    private String shipperName;

    private String description;

    private BigDecimal shippingCharges;

    private String invoiceStatus;

    private String paymentMethod;

    private BigDecimal paymentAmount;

    private String trackingNumber;

    private String notes;

    private String updateHistory;

}
