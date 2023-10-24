package com.supership.ship.api;

import com.supership.ship.dto.ResponseDTO;
import com.supership.ship.dto.ShipmentDTO;
import com.supership.ship.service.IShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ShipmentAPI {
    @Autowired
    private IShipmentService shipmentService;

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
}
