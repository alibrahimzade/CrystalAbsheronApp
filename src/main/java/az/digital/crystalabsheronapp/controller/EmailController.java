package az.digital.crystalabsheronapp.controller;

import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
import az.digital.crystalabsheronapp.dao.entity.Mail;
import az.digital.crystalabsheronapp.dto.CustomerInfoDto;
import az.digital.crystalabsheronapp.service.EmailService;
import az.digital.crystalabsheronapp.service.TransactionalEmailService;
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

    private final TransactionalEmailService transactionalEmailService;

    @PostMapping("/sendMail")
    public void sendMail(@RequestBody CustomerInfoDto customerInfoDto) {
        transactionalEmailService.warningMail(customerInfoDto);
    }
}


