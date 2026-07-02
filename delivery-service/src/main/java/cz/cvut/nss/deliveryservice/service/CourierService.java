package cz.cvut.nss.deliveryservice.service;

import cz.cvut.nss.deliveryservice.dto.CreateCourierRequest;
import cz.cvut.nss.deliveryservice.entity.Courier;
import cz.cvut.nss.deliveryservice.entity.CourierStatus;
import cz.cvut.nss.deliveryservice.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierService {

    private final CourierRepository courierRepository;

    @Transactional(readOnly = true)
    public List<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }

    @Transactional
    public Courier createCourier(CreateCourierRequest request) {
        Courier courier = Courier.builder()
                .name(request.name())
                .status(CourierStatus.AVAILABLE)
                .build();

        return courierRepository.save(courier);
    }

    @Transactional
    public Courier assignAvailableCourier() {
        // Find first available courier or throw an exception if everyone is busy
        Courier courier = courierRepository.findFirstByStatus(CourierStatus.AVAILABLE)
                .orElseThrow(() -> new RuntimeException("No available couriers at the moment"));

        courier.setStatus(CourierStatus.BUSY);
        return courierRepository.save(courier);
    }
}