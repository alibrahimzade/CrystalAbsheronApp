package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.Information;
import az.digital.crystalabsheronapp.dao.repository.InformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InformationService {

    private final InformationRepository informationRepository;
    private final EmailService emailService;

    public void createConsultation(Information info) {
        emailService.sendMail(info);
        informationRepository.save(info);

    }
}
