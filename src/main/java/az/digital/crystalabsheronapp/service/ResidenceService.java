package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.Residence;
import az.digital.crystalabsheronapp.dao.repository.ResidenceRepository;
import az.digital.crystalabsheronapp.dto.ResidenceDto;
import az.digital.crystalabsheronapp.mapper.ResidenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.hibernate.engine.spi.Status.DELETED;
import static org.hibernate.engine.spi.Status.SAVING;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ResidenceService {
    private final ResidenceRepository repository;

    public ResponseEntity<?> findResidenceById(Long id){
        Residence residence = repository.findById(id).orElseGet(null);
        if (Objects.nonNull(residence)){
            return ResponseEntity.ok(ResidenceMapper.RESIDENCE_MAPPER.buildEntityToDto(residence));
        }

        return ResponseEntity.status(NOT_FOUND).body("This Residence does not exist.");
    }

    public ResponseEntity<?> findAllResidences(){
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<?> createResidence(ResidenceDto residenceDto){
        Residence residenceName = repository.getResidenceByName(residenceDto.getName()).orElseGet(null);
        if (Objects.isNull(residenceName)){
            Residence residence = ResidenceMapper.RESIDENCE_MAPPER.buildDtoToEntity(residenceDto);
            repository.save(residence);
            return ResponseEntity.ok(SAVING);
        }
        return ResponseEntity.status(CONFLICT).body("This Residence already exist");
    }

    public ResponseEntity<?> updateResidence(Long residenceId, ResidenceDto residenceDto){
        Residence residence = repository.findById(residenceId).orElseGet(null);
        if (Objects.nonNull(residence)){
            Residence updated = ResidenceMapper.RESIDENCE_MAPPER.buildDtoToEntity(residenceDto);
            updated.setName(residenceDto.getName());
            repository.save(updated);
            return ResponseEntity.ok(SAVING);
        }

        return ResponseEntity.status(NOT_FOUND).body("This Residence does not exist");
    }

    public ResponseEntity<?> deleteResidenceById(Long id){
        Residence residence = repository.findById(id).orElseGet(null);
        if (Objects.nonNull(residence)){
            repository.deleteById(id);
            return ResponseEntity.ok(DELETED);
        }
        return ResponseEntity.status(NOT_FOUND).body("This Residence does not exist");
    }
}
