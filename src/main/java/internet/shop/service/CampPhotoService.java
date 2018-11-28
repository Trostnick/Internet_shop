package internet.shop.service;

import internet.shop.exception.FileUploadException;
import internet.shop.model.entity.Camp;
import internet.shop.model.entity.CampPhoto;
import internet.shop.repository.CampPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CampPhotoService {

    @Value("${upload-file.path}")
    private String uploadDirPath;

    private final CampPhotoRepository campPhotoRepository;

    @Autowired
    public CampPhotoService(CampPhotoRepository campPhotoRepository) {
        this.campPhotoRepository = campPhotoRepository;
    }

    private void createIntermediateDir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    private String createRelativePath(Long campId){
        LocalDate dateNow = LocalDate.now();
        String relativePath = "//" + dateNow.getYear() + "-" + dateNow.getMonthValue();
        createIntermediateDir(uploadDirPath + relativePath);

        relativePath += "//" + dateNow.getDayOfMonth();
        createIntermediateDir(uploadDirPath + relativePath);

        relativePath += "//" + campId;
        createIntermediateDir(uploadDirPath + relativePath);

        return relativePath;
    }

    public void add(MultipartFile photo, Camp curCamp) {

        String relativePath = createRelativePath(curCamp.getId());
        String filename = photo.getOriginalFilename();

        relativePath += "//" + filename;
        File curPhotoFile = new File(uploadDirPath + relativePath);
        try {
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(curPhotoFile));
            BufferedInputStream inputStream = new BufferedInputStream(photo.getInputStream());
            try {
                inputStream.transferTo(outputStream);
            } catch (IOException e){
                outputStream.close();
                inputStream.close();
                throw new FileUploadException("photo", "В процессе загрузки файла " + filename + " произошла ошибка");
            }
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {

            throw new FileUploadException("photo", "В процессе загрузки файла " + filename + " произошла ошибка");
        }
        CampPhoto newCampPhoto = new CampPhoto();
        newCampPhoto.setRelativePath(relativePath);
        newCampPhoto.setRemoved(false);
        newCampPhoto.setFilename(filename);
        String[] pathList = filename.split("\\.");
        newCampPhoto.setExtension(pathList[(pathList.length-1)]);
        newCampPhoto.setCamp(curCamp);
        campPhotoRepository.save(newCampPhoto);


    }

    public void add(MultipartFile[] photoArray, Camp curCamp) {
        for (MultipartFile photo : photoArray) {
            add(photo, curCamp);
        }
    }

    public List<Long> getAllIdByCampId(Long campId) {
        List<Long> allCurId = new ArrayList<>();
        campPhotoRepository.findAllByCamp_IdAndRemovedFalse(campId).forEach(campPhoto -> allCurId.add(campPhoto.getId()));
        return allCurId;
    }

    public BufferedInputStream getInputStream(Long id) {
        try {
            File curPhoto = new File(uploadDirPath + campPhotoRepository.getOne(id).getRelativePath());
            return new BufferedInputStream(Files.newInputStream(curPhoto.toPath()));

        } catch (IOException e) {
            return null;
        }
    }

    public CampPhoto getOne(Long id){
        return campPhotoRepository.getByIdAndRemovedFalse(id);
    }


}
