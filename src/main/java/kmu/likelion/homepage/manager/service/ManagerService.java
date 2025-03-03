package kmu.likelion.homepage.manager.service;

import kmu.likelion.homepage.manager.entity.Manager;
import kmu.likelion.homepage.manager.entity.Part;
import kmu.likelion.homepage.manager.entity.dto.req.CreateManagerRequestDTO;
import kmu.likelion.homepage.manager.repository.ManagerRepository;
import kmu.likelion.homepage.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final S3Service s3Service;

    @Transactional
    public String createManager(CreateManagerRequestDTO req, MultipartFile image){
        try {
            String imageUrl = null;
            if (!image.isEmpty()){
                imageUrl = s3Service.uploadFile(image);
            }
            Part part = Part.fromString(req.getPart().toUpperCase());
            managerRepository.save(Manager.builder()
                    .name(req.getName())
                    .major(req.getMajor())
                    .part(part.getValue())
                    .imageUrl(imageUrl).build());
            return "운영진 등록 성공";
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 중 오류 발생",e);
        }
    }

    public List<Manager> getAllManager(){
        return managerRepository.findAll();
    }

    @Transactional
    public void deleteManager(Long id){
        Optional<Manager> manager = managerRepository.findById(id);
        manager.ifPresent(managerRepository::delete);
        ResponseEntity.badRequest();
    }
}
