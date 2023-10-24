package com.supership.ship.service.impl;

import com.supership.ship.converter.ShipmentConverter;
import com.supership.ship.dto.ShipmentDTO;
import com.supership.ship.entity.ShipmentEntity;
import com.supership.ship.repository.ShipmentRepository;
import com.supership.ship.service.IShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class ShipmentService implements IShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private ShipmentConverter shipmentConverter;

    @Override
    public ShipmentDTO save(ShipmentDTO shipmentDTO) {
        ShipmentEntity shipmentEntity = new ShipmentEntity();
        if (shipmentDTO.getId() != null) { // update
            Optional<ShipmentEntity> shipmentEntityOptional = shipmentRepository.findById(shipmentDTO.getId());
            ShipmentEntity oldShipmentEntity = shipmentEntityOptional.orElse(null);
            if (oldShipmentEntity != null) {
                shipmentEntity = shipmentConverter.toEntity(shipmentDTO, oldShipmentEntity);
            } else {
                // Xử lý tình huống khi không tìm thấy người dùng để cập nhật
                // (có thể làm gì đó tùy theo yêu cầu của bạn)
            }
        }else { // create
            shipmentEntity = shipmentConverter.toEntity(shipmentDTO);
        }
        shipmentEntity = shipmentRepository.save(shipmentEntity);

        return shipmentConverter.toDTO(shipmentEntity);
    }

    @Override
    public void delete(long[] ids) {
        for (long id : ids) {
            shipmentRepository.deleteById(id); // Sử dụng phương thức deleteById
        }
    }
}
