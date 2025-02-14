package kmu.likelion.homepage.manager.service;

import kmu.likelion.homepage.manager.entity.Manager;
import kmu.likelion.homepage.manager.entity.Part;
import kmu.likelion.homepage.manager.entity.dto.req.CreateManagerRequestDTO;
import kmu.likelion.homepage.manager.repository.ManagerRepository;
import kmu.likelion.homepage.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Primary
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final S3Service s3Service;

    public String createManager(CreateManagerRequestDTO req, MultipartFile image){
        try {
            Part part = Part.valueOf(req.getPart().toUpperCase());
            String imageUrl = s3Service.uploadFile(image);
            managerRepository.save(Manager.builder()
                    .name(req.getName())
                    .major(req.getMajor())
                    .part(part)
                    .imageUrl(imageUrl).build());
            return "운영진 등록 성공";
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 중 오류 발생",e);
        }
    }
}
