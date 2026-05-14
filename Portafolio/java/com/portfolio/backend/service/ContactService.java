package com.portfolio.backend.service;

import com.portfolio.backend.dto.ContactMessageDTO;
import com.portfolio.backend.model.ContactMessage;
import com.portfolio.backend.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final JavaMailSender mailSender;

    public void saveMessage(ContactMessageDTO contactDTO) {
        // 1. Guardar en Base de Datos (H2)
        ContactMessage contactMessage = ContactMessage.builder()
                .name(contactDTO.getName())
                .email(contactDTO.getEmail())
                .message(contactDTO.getMessage())
                .createdAt(LocalDateTime.now())
                .build();
        
        contactRepository.save(contactMessage);

        // 2. Enviar Email
        sendEmail(contactDTO);
    }

    private void sendEmail(ContactMessageDTO contactDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("alonso.b.millar@gmail.com");
        message.setTo("alonso.b.millar@gmail.com");
        message.setSubject("Nuevo contacto: " + contactDTO.getName());
        message.setText("Has recibido un nuevo mensaje desde tu portafolio:\n\n" +
                "Nombre: " + contactDTO.getName() + "\n" +
                "Email: " + contactDTO.getEmail() + "\n" +
                "Mensaje: " + contactDTO.getMessage());
        
        mailSender.send(message);
    }
}
