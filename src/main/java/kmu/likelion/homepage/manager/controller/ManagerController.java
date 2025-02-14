package kmu.likelion.homepage.manager.controller;

import jakarta.validation.Valid;
import kmu.likelion.homepage.manager.entity.Manager;
import kmu.likelion.homepage.manager.entity.dto.req.CreateManagerRequestDTO;
import kmu.likelion.homepage.manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manager")
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping("/all")
    public ResponseEntity<List<Manager>> getAllManager(){
        return ResponseEntity.ok(managerService.getAllManager());
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createManager(@RequestPart("manager") @Valid CreateManagerRequestDTO req,
                                                @RequestPart("image")MultipartFile image){
        return ResponseEntity.ok(managerService.createManager(req, image));
    }
}
