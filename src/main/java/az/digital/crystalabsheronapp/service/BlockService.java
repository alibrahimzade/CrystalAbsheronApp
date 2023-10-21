package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.Block;
import az.digital.crystalabsheronapp.dao.entity.Residence;
import az.digital.crystalabsheronapp.dao.repository.BlockRepository;
import az.digital.crystalabsheronapp.dao.repository.ResidenceRepository;
import az.digital.crystalabsheronapp.dto.BlockDto;
import az.digital.crystalabsheronapp.exceptions.NoSuchBlocksException;
import az.digital.crystalabsheronapp.exceptions.NoSuchResidenceException;
import az.digital.crystalabsheronapp.mapper.BlockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class BlockService {
    private final BlockRepository blockRepository;
    private final ResidenceRepository residenceRepository;
    private final BlockMapper blockMapper;

    public ResponseEntity<List<BlockDto>> getAll(){// TODO: residence_id null qaytarir
        List<Block> all = blockRepository.findAll();
        return ResponseEntity.status(OK).body(blockMapper.fromEntityListToDtoList(all));
    }

    public ResponseEntity<BlockDto> getBlocksById(Long id){ // TODO: residence_id null qaytarir
        Block block = blockRepository.findById(id).
                orElseThrow(() -> new NoSuchBlocksException("The block does not exist"));

        return ResponseEntity.status(OK).body(blockMapper.fromEntityToDto(block));
    }

    public ResponseEntity<BlockDto> createBlock(BlockDto blockDto){ //TODO: residenceId null qaytarir amma database de oturur
        Residence residence = residenceRepository.findById(blockDto.getResidenceId()).
                orElseThrow(() -> new NoSuchResidenceException(
                        "The residence in " + blockDto.getResidenceId() + " does not exist")
                );
        Block block = blockMapper.fromDtoToEntity(blockDto);
//        block.setResidence(residenceRepository.findById(blockDto.getResidenceId()).get());
        block.setResidence(residence);
        return ResponseEntity.status(OK).body(blockMapper.fromEntityToDto(blockRepository.save(block)));
    }

    public ResponseEntity<BlockDto> updateBlock(BlockDto blockDto){ //TODO: ishlemir
        Block block = blockRepository.findById(blockDto.getId()).
                orElseThrow(() -> new NoSuchBlocksException("The block does not exist"));
        Residence residence = residenceRepository.findById(blockDto.getResidenceId()).
                orElseThrow(() -> new NoSuchResidenceException("The residence does not exist"));
        Block updated = blockMapper.fromDtoToEntity(blockDto);
        updated.setResidence(residence);
        return ResponseEntity.ok(blockMapper.fromEntityToDto(blockRepository.save(updated)));
    }

    public ResponseEntity<BlockDto> deleteBlock(Long id){
        Block block = blockRepository.findById(id).
                orElseThrow(() -> new NoSuchBlocksException("The block does not exist"));

        blockRepository.deleteById(id);
        return ResponseEntity.status(OK).build();
    }
}
