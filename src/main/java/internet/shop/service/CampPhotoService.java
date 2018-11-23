package internet.shop.service;

import internet.shop.exception.FileUploadException;
import internet.shop.model.entity.CampPhoto;
import internet.shop.repository.CampPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Service
public class CampPhotoService {

    @Value("${upload-file.path}")
    private String uploadDirPath;

    private final CampPhotoRepository campPhotoRepository;

    @Autowired
    public CampPhotoService(CampPhotoRepository campPhotoRepository) {
        this.campPhotoRepository = campPhotoRepository;
    }

    public void add(MultipartFile photo, String campName) {

        String relativePath = "//" + campName;

        File curDir = new File(uploadDirPath + relativePath);
        if (!curDir.exists()) {
            curDir.mkdir();
        }

        relativePath += "//" + photo.getOriginalFilename();
        File curPhotoFile = new File(uploadDirPath + relativePath);
        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(curPhotoFile));
            stream.write(photo.getBytes());
            CampPhoto newCampPhoto = new CampPhoto();
            newCampPhoto.setRelativePath(relativePath);
            newCampPhoto.setRemoved(false);
        }catch(Exception e){
            throw new FileUploadException("photo", "В процессе загрузки файла " +photo.getOriginalFilename()+" произошла ошибка");
        }

    }

    public void add(MultipartFile[] photoArray, String campName) {
        for (MultipartFile photo : photoArray) {
            add(photo, campName);
        }
    }
}
