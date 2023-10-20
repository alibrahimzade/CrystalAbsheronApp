package az.digital.crystalabsheronapp.controller;

import az.digital.crystalabsheronapp.dto.BlockDto;
import az.digital.crystalabsheronapp.service.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/block")
public class BlockController {

    private final BlockService blockService;

    @GetMapping("/getAll")
    public ResponseEntity<List<BlockDto>> getAllBlocks(){
        return blockService.getAll();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<BlockDto> getBlockById(@PathVariable(name = "id") Long id){
        return blockService.getBlocksById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<BlockDto> createBlock(@RequestBody BlockDto blockDto){
        return blockService.createBlock(blockDto);
    }

    @PutMapping("/update")
    public ResponseEntity<BlockDto> updateBlock(@RequestBody BlockDto blockDto){
        return blockService.updateBlock(blockDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BlockDto> deleteBlock(@PathVariable(name = "id") Long id){
        return blockService.deleteBlock(id);
    }
    }
