package com.aprozeanu.smt.controller;

import com.aprozeanu.smt.service.manage.ManageStoreService;
import com.aprozeanu.smt.service.manage.dto.ManageStoreRequest;
import com.aprozeanu.smt.service.manage.dto.UpdatePriceRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage")
@PreAuthorize("hasRole('Administrator')")
public class ManageStoreController {

    private final ManageStoreService service;

    ManageStoreController(ManageStoreService service) {
        this.service = service;
    }

    @PostMapping("/set-price")
    public ResponseEntity<?> updatePrice(@Valid @RequestBody UpdatePriceRequest request) {
        return processRequest(request);
    }

    private ResponseEntity<?> processRequest(ManageStoreRequest<?> request) {
        var response = service.dispatchRequest(request);
        // Writing ifs like this while waiting for switch expressions.
        if (response.result() instanceof ManageStoreService.Success<?> success) {
            return ResponseEntity.ok(success);
        } else if (response.result() instanceof ManageStoreService.Failure<?> failure) {
            return ResponseEntity.internalServerError().body(failure);
        } else {
            throw new RuntimeException("never");
        }
    }
}
