package faculty.project.uber.controller;

import faculty.project.uber.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/photo")
public class PhotoController {

    PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService){
        this.photoService = photoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity findPhotoById(@PathVariable Long id){
        return new ResponseEntity(photoService.loadPhoto(id), HttpStatus.OK);
    }


    @PostMapping("/{userId}")
    public ResponseEntity uploadPhoto(@PathVariable Long userId, @RequestParam("image") MultipartFile file){
        photoService.savePhoto(userId,file);
        return new ResponseEntity(HttpStatus.OK);
    }
}
