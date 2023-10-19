package com.supership.ship.converter;

import com.supership.ship.dto.ShippingInvoiceDTO;
import com.supership.ship.dto.UserDTO;
import com.supership.ship.entity.RoleEntity;
import com.supership.ship.entity.ShippingInvoiceEntity;
import com.supership.ship.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShippingInvoiceConverter {

    public ShippingInvoiceEntity toEntity(ShippingInvoiceDTO dto){
        ShippingInvoiceEntity entity = new ShippingInvoiceEntity();
        entity.setSenderName(dto.getSenderName());
        entity.setSenderAddress(dto.getSenderAddress());
        entity.setSenderPhoneNumber(dto.getSenderPhoneNumber());
        entity.setReceiverName(dto.getReceiverName());
        entity.setReceiverAddress(dto.getReceiverAddress());
        entity.setReceiverPhoneNumber(dto.getReceiverPhoneNumber());
        entity.setShipperName(dto.getShipperName());
        entity.setDescription(dto.getDescription());
        entity.setShippingCharges(dto.getShippingCharges());
        entity.setInvoiceStatus(dto.getInvoiceStatus());
        entity.setPaymentMethod(dto.getPaymentMethod());
        entity.setPaymentAmount(dto.getPaymentAmount());
        entity.setTrackingNumber(dto.getTrackingNumber());
        entity.setNotes(dto.getNotes());
        entity.setUpdateHistory(dto.getUpdateHistory());
        return entity;
    }

    public ShippingInvoiceDTO toDTO(ShippingInvoiceEntity entity){
        ShippingInvoiceDTO dto = new ShippingInvoiceDTO();
        if (entity.getId() != null){
            dto.setId(entity.getId());
        }
        dto.setSenderName(entity.getSenderName());
        dto.setSenderAddress(entity.getSenderAddress());
        dto.setSenderPhoneNumber(entity.getSenderPhoneNumber());
        dto.setReceiverName(entity.getReceiverName());
        dto.setReceiverAddress(entity.getReceiverAddress());
        dto.setReceiverPhoneNumber(entity.getReceiverPhoneNumber());
        dto.setShipperName(entity.getShipperName());
        dto.setDescription(entity.getDescription());
        dto.setShippingCharges(entity.getShippingCharges());
        dto.setInvoiceStatus(entity.getInvoiceStatus());
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setPaymentAmount(entity.getPaymentAmount());
        dto.setTrackingNumber(entity.getTrackingNumber());
        dto.setNotes(entity.getNotes());
        dto.setUpdateHistory(entity.getUpdateHistory());

        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        return dto;
    }

    public ShippingInvoiceEntity toEntity(ShippingInvoiceDTO dto, ShippingInvoiceEntity entity){
        entity.setSenderName(dto.getSenderName());
        entity.setSenderAddress(dto.getSenderAddress());
        entity.setSenderPhoneNumber(dto.getSenderPhoneNumber());
        entity.setReceiverName(dto.getReceiverName());
        entity.setReceiverAddress(dto.getReceiverAddress());
        entity.setReceiverPhoneNumber(dto.getReceiverPhoneNumber());
        entity.setShipperName(dto.getShipperName());
        entity.setDescription(dto.getDescription());
        entity.setShippingCharges(dto.getShippingCharges());
        entity.setInvoiceStatus(dto.getInvoiceStatus());
        entity.setPaymentMethod(dto.getPaymentMethod());
        entity.setPaymentAmount(dto.getPaymentAmount());
        entity.setTrackingNumber(dto.getTrackingNumber());
        entity.setNotes(dto.getNotes());
        entity.setUpdateHistory(dto.getUpdateHistory());
        return entity;
    }

}
