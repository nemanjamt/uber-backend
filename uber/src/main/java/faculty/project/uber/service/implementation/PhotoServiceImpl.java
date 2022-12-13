package faculty.project.uber.service.implementation;

import faculty.project.uber.constants.ErrorConstants;
import faculty.project.uber.constants.PhotoConstants;
import faculty.project.uber.exceptions.BadFileType;
import faculty.project.uber.model.others.Photo;
import faculty.project.uber.model.users.User;
import faculty.project.uber.repository.PhotoRepository;
import faculty.project.uber.repository.UserRepository;
import faculty.project.uber.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Base64;

@Service
public class PhotoServiceImpl implements PhotoService {

    PhotoRepository photoRepository;

    UserRepository userRepository;
    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository, UserRepository userRepository){
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String loadPhoto(Long id) {
        Photo p = findById(id);
        String path=p.getPath();
        String sep = FileSystems.getDefault().getSeparator();
        String format = String.format(PhotoConstants.IMAGE_PATH, sep, sep, sep, sep, sep, path);
        try{
            FileSystemResource file = new FileSystemResource(format);
            byte[] fileContent = Files.readAllBytes(file.getFile().toPath());
            String encodedString = Base64.getEncoder().encodeToString(fileContent);

            return encodedString;
        }catch(IOException e){
            throw new EntityNotFoundException(String.format(ErrorConstants.ENTITY_NOT_FOUND,"photo",p.getPath()));
        }

    }

    private Photo findById(Long id){
        if(!photoRepository.existsById(id)){
            throw new EntityNotFoundException(String.format(ErrorConstants.ENTITY_NOT_FOUND, "Photo",id));
        }
        return photoRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void savePhoto(Long userId, MultipartFile img) {
        if(!img.getContentType().startsWith("image")){
            throw new BadFileType(String.format(ErrorConstants.BAD_FILE_TYPE, img.getContentType()));
        }

        try {
            User u = userRepository.findById(userId).get();
            String sep = FileSystems.getDefault().getSeparator();
            String photoName = String.format(PhotoConstants.IMAGE_NAME,userId);
            String format = String.format(PhotoConstants.IMAGE_PATH, sep, sep, sep, sep, sep, photoName);
            File f = new File(format);
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(img.getBytes());
            Photo p = userRepository.findById(userId).get().getPhoto();
            if(p == null)
                p = new Photo();
            p.setPath(photoName);
            p = photoRepository.save(p);
            u.setPhoto(p);
            userRepository.save(u);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
