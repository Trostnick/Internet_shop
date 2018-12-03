package internet.shop.service;

import internet.shop.exception.FileUploadException;
import internet.shop.model.entity.Camp;
import internet.shop.model.entity.CampPhoto;
import internet.shop.repository.CampPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CampPhotoService {

    @Value("${upload-file.path}")
    private String uploadDirPath;

    @Value("${icon.small.height}")
    private int smallIconHeight;

    @Value("${icon.small.width}")
    private int smallIconWidth;

    @Value("${icon.height}")
    private int iconHeight;

    @Value("${icon.width}")
    private int iconWidth;

    @Value("${photo.height}")
    private int photoHeight;

    @Value("${photo.width}")
    private int photoWidth;

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

    private String createRelativePath(Long campId) {
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
        String extension = filename.substring(filename.lastIndexOf(".") + 1);

        relativePath += "//" + filename;
        File curPhotoFile = new File(uploadDirPath + relativePath);
        try {
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(curPhotoFile));
            BufferedInputStream inputStream = new BufferedInputStream(photo.getInputStream());
            resizePhotoAndSave(inputStream, outputStream, filename, extension);

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {

            throw new FileUploadException("photo", "В процессе загрузки файла " + filename + " произошла ошибка");
        }
        CampPhoto newCampPhoto = new CampPhoto();
        newCampPhoto.setRelativePath(relativePath);
        newCampPhoto.setRemoved(false);
        newCampPhoto.setFilename(filename);

        newCampPhoto.setExtension(extension);
        newCampPhoto.setCamp(curCamp);
        campPhotoRepository.save(newCampPhoto);


    }

    public void add(MultipartFile[] photoArray, Camp curCamp) {
        if (!(photoArray == null)) {
            for (MultipartFile photo : photoArray) {
                add(photo, curCamp);
            }
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

    public CampPhoto getOne(Long id) {
        return campPhotoRepository.getByIdAndRemovedFalse(id);
    }

    public void resizePhotoAndSave(BufferedInputStream inputStream, BufferedOutputStream outputStream,
                                   String filename, String extension) throws FileUploadException {
        BufferedImage inputPhoto;
        try {
            inputPhoto = ImageIO.read(inputStream);

            BufferedImage resizedPhoto = resize(inputPhoto, photoWidth, photoHeight);
            ImageIO.write(resizedPhoto, extension, outputStream);
        } catch (IOException e) {
            throw new FileUploadException("photo", "В процессе загрузки файла " + filename + " произошла ошибка");
        }
    }

    public List<byte[]> resizeToIconAndSmallIcon(InputStream inputStream, String filename) {
        BufferedImage inputImage;
        String iconExtension = filename.substring(filename.lastIndexOf(".") + 1);
        List<byte[]> iconList = new ArrayList<>();
        try {
            inputImage = ImageIO.read(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BufferedImage outputImage = resize(inputImage, iconWidth, iconHeight);

            ImageIO.write(outputImage, iconExtension, outputStream);
            outputStream.flush();
            iconList.add(outputStream.toByteArray());
            outputStream.close();

            outputStream = new ByteArrayOutputStream();
            outputImage = resize(inputImage, smallIconWidth, smallIconHeight);
            ImageIO.write(outputImage, iconExtension, outputStream);
            outputStream.flush();
            iconList.add(outputStream.toByteArray());
            outputStream.close();

            return iconList;
        } catch (IOException e) {
            throw new FileUploadException("icon", "В процессе загрузки иконки " + filename + " произошла ошибка");
        }
    }


    public BufferedImage resize(BufferedImage inputImage, int newWidth, int newHeight) {
        //create output image

        BufferedImage outputImage = new BufferedImage(newWidth, newHeight, inputImage.getType());

        // get new size
        double curHeight = inputImage.getHeight();
        double curWidth = inputImage.getWidth();
        double curRatio = curWidth / curHeight;
        double newRatio = (double) newWidth / newHeight;
        int intermediateHeight = newHeight;
        int intermediateWidth = newWidth;
        int startX = 0;
        int startY = 0;
        if (curRatio > newRatio) {
            double percent = newWidth / curWidth;
            intermediateHeight = (int) (curHeight * percent);
            double deltaY = (newHeight - intermediateHeight) / 2;
            startY += deltaY;
        } else if(curRatio < newRatio) {
            double percent = newHeight / curHeight;
            intermediateWidth = (int) (curWidth * percent);
            double deltaX = (newWidth - intermediateWidth) / 2.0;
            startX += deltaX;
        }


        // resize image and put new image to output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(inputImage, startX, startY, intermediateWidth, intermediateHeight, null);
        g2d.dispose();

        return outputImage;


    }


}
