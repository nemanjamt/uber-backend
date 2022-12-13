package faculty.project.uber.service;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

    String loadPhoto(Long id);
    void savePhoto(Long userId, MultipartFile img);
}
