package com.supership.ship.api;

import com.supership.ship.api.output.ShipmentOutput;
import com.supership.ship.dto.ResponseDTO;
import com.supership.ship.dto.ShipmentDTO;
import com.supership.ship.service.IShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ShipmentAPI {
    @Autowired
    private IShipmentService shipmentService;

    @GetMapping(value = "/shipment/{role}")
    public ResponseDTO showUser(@RequestParam(value = "page", required = false) Integer page,
                                @RequestParam(value = "limit", required = false) Integer limit,
                                @RequestParam(value = "trackingNumber", required = false) String trackingNumber,
                                @PathVariable String role) {
        // if
        // findRoleByUserName
        // middleware kiểm tra user có đúng role không
        // đoạn code phân quyền

        if (!role.equals("ADMIN")){
            return new ResponseDTO(404, null, "User not authenrization");
        }
        ShipmentOutput shipmentOutput = new ShipmentOutput();
        if (trackingNumber != null) {
            // Tìm kiếm người dùng theo tên
            ShipmentDTO shipment = shipmentService.findShipmentByTrackingNumber(trackingNumber);
            if (shipment != null) {
                return new ResponseDTO(200, shipment, "Shipment found successfully");
            } else {
                return new ResponseDTO(404, null, "Shipment not found");
            }
        }
        if (page != null && limit != null) {
            shipmentOutput.setPage(page);
            Pageable pageable = new PageRequest(page - 1, limit);
            shipmentOutput.setListResult(shipmentService.findAll(pageable));
            shipmentOutput.setTotalPage((int) Math.ceil((double) (shipmentService.totalItem()) / limit));
        } else {
            shipmentOutput.setListResult(shipmentService.findAll());
        }

        if (!shipmentOutput.getListResult().isEmpty()) {
            return new ResponseDTO(200, shipmentOutput, "Shipments found successfully");
        } else {
            return new ResponseDTO(404, null, "No shipments found");
        }
    }

    @PostMapping(value = "/shipment")
    public ResponseDTO createShipment(@RequestBody ShipmentDTO model) {
        ShipmentDTO shipmentDTO = shipmentService.save(model);
        if (shipmentDTO != null) {
            return new ResponseDTO(200, shipmentDTO, "Tạo vận đơn thành công");
        } else {
            return new ResponseDTO(500, null, "Tạo vận đơn thất bại");
        }
    }

    @PutMapping("/shipment/{id}")
    public ResponseDTO updateShipment(@RequestBody ShipmentDTO model, @PathVariable("id") long id) {
        model.setId(id);
        ShipmentDTO shipmentDTO = shipmentService.save(model);
        if (shipmentDTO != null) {
            return new ResponseDTO(200, shipmentDTO, "Thay đổi thông tin vận đơn thành công");
        } else {
            return new ResponseDTO(500, null, "Thay đổi thông tin vận đơn thất bại");
        }
    }

    @DeleteMapping(value = "/shipment")
    public ResponseEntity<ResponseDTO> deleteShipment(@RequestBody long[] ids) {
        try {
            shipmentService.delete(ids);
            return new ResponseEntity<>(new ResponseDTO(200, null, "Xóa thông tin vận đơn thành công"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(500, null, "Xóa thông tin vận đơn thất bại"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/shipment/shipmentstatus/{shipmentId}")
    public ResponseEntity<ResponseDTO> updateShipmentStatus(@PathVariable("shipmentId") long shipmentId,
                                                            @RequestParam("newStatus") String newStatus) {

        ShipmentDTO updatedShipment = shipmentService.updateShipmentStatus(shipmentId, newStatus);
        if (updatedShipment != null){
            return new ResponseEntity<>(new ResponseDTO(200, updatedShipment, "Thay đổi trạng thái vận đơn thành công"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(500, null, "Thay đổi trạng thái vận đơn thất bại"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
