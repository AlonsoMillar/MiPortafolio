package com.portfolio.backend.controller;

import com.portfolio.backend.dto.ContactMessageDTO;
import com.portfolio.backend.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // In production, replace with your frontend URL
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<?> receiveContact(@RequestBody ContactMessageDTO contactDTO) {
        try {
            contactService.saveMessage(contactDTO);
            return ResponseEntity.ok().body(Map.of("message", "¡Mensaje recibido correctamente!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error al procesar el mensaje"));
        }
    }
}
