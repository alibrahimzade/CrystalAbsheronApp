package az.digital.crystalabsheronapp.controller;

import az.digital.crystalabsheronapp.dao.entity.Mail;
import az.digital.crystalabsheronapp.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static javax.security.auth.callback.ConfirmationCallback.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/sendMail")
    public ResponseEntity<?> sendMail(@RequestBody Mail mail) {
        emailService.sendMail(mail);
        return ResponseEntity.status(OK).build();
    }
}
