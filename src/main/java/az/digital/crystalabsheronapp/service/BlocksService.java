package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.repository.BlocksRepository;
import az.digital.crystalabsheronapp.dao.repository.ResidenceRepository;
import az.digital.crystalabsheronapp.mapper.BlocksMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlocksService {
    private final BlocksRepository blocksRepository;
    private final ResidenceRepository residenceRepository;
    private final BlocksMapper blocksMapper;
}
