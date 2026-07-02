package cz.cvut.nss.deliveryservice.controller;

import cz.cvut.nss.deliveryservice.dto.CreateCourierRequest;
import cz.cvut.nss.deliveryservice.entity.Courier;
import cz.cvut.nss.deliveryservice.service.CourierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/couriers")
@RequiredArgsConstructor
public class CourierController {

    private final CourierService courierService;

    @GetMapping
    public List<Courier> getAllCouriers() {
        return courierService.getAllCouriers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Courier createCourier(@Valid @RequestBody CreateCourierRequest request) {
        return courierService.createCourier(request);
    }

    @PostMapping("/assign")
    public Courier assignCourier() {
        return courierService.assignAvailableCourier();
    }
}