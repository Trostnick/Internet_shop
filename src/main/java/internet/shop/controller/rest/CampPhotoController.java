package internet.shop.controller.rest;

import internet.shop.model.entity.CampPhoto;
import internet.shop.service.CampPhotoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CampPhotoController {

    private final CampPhotoService campPhotoService;

    public CampPhotoController(CampPhotoService campPhotoService) {
        this.campPhotoService = campPhotoService;
    }

    @GetMapping("/photo/{id}")
    public void getPhoto(@PathVariable Long id, HttpServletResponse response) {
        try {
            CampPhoto curPhoto = campPhotoService.getOne(id);
            response.setStatus(200);
            response.addHeader("Content-Disposition", "attachment; filename="+curPhoto.getFilename());
            response.setContentType("image/"+curPhoto.getExtension());

            BufferedInputStream inputStream = campPhotoService.getInputStream(id);
            int imageLength = inputStream.available();
            response.setContentLength(imageLength);

            BufferedOutputStream outputStream= new BufferedOutputStream(response.getOutputStream()) ;
            try {
                inputStream.transferTo(outputStream);
            } catch (IOException e){
                outputStream.close();
                inputStream.close();
            }
            outputStream.close();
            inputStream.close();
            response.flushBuffer();
        } catch (IOException e){
            response.setStatus(400);
        }


    }
}
