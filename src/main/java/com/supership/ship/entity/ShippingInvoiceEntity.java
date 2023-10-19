package com.supership.ship.entity;

import java.math.BigDecimal;


import javax.persistence.*;

@Entity
@Table(name = "shippinginvoice")
public class ShippingInvoiceEntity extends BaseEntity{
    @Column(name = "sendername")
    private String senderName;

    @Column(name = "senderaddress", columnDefinition = "TEXT")
    private String senderAddress;

    @Column(name = "senderphonenumber")
    private String senderPhoneNumber;

    @Column(name = "receivername")
    private String receiverName;

    @Column(name = "receiveraddress", columnDefinition = "TEXT")
    private String receiverAddress;

    @Column(name = "receiverphonenumber")
    private String receiverPhoneNumber;

    @Column(name = "shippername")
    private String shipperName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "shippingcharges", precision = 10, scale = 2)
    private BigDecimal shippingCharges;

    @Column(name = "invoicestatus")
    private String invoiceStatus;

    @Column(name = "paymentmethod")
    private String paymentMethod;

    @Column(name = "paymentamount", precision = 10, scale = 2)
    private BigDecimal paymentAmount;

    @Column(name = "trackingnumber", unique = true)
    private String trackingNumber;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "updatehistory", columnDefinition = "TEXT")
    private String updateHistory;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getSenderPhoneNumber() {
        return senderPhoneNumber;
    }

    public void setSenderPhoneNumber(String senderPhoneNumber) {
        this.senderPhoneNumber = senderPhoneNumber;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getShippingCharges() {
        return shippingCharges;
    }

    public void setShippingCharges(BigDecimal shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUpdateHistory() {
        return updateHistory;
    }

    public void setUpdateHistory(String updateHistory) {
        this.updateHistory = updateHistory;
    }
}
