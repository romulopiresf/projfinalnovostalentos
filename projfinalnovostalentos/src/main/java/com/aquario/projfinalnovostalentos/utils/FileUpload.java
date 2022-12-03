package com.aquario.projfinalnovostalentos.utils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

import com.aquario.projfinalnovostalentos.models.Usuario;

public class FileUpload {

    public static final String PATH = "~/uploads";

    public static void save(Usuario usuario, String name, MultipartFile file) throws Exception{
        Path filePath = Paths.get(fileName(usuario, name));
        try(InputStream inputStream = file.getInputStream()){
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch(Exception e){
            throw new Exception("Falha ao salvar imagem");
        }
    }

    public static String fileName(Usuario usuario, String name) throws Exception{
        String uploadDir = PATH + "/";
        if(usuario != null)
            uploadDir += usuario.getPk() + "/";
        uploadDir = uploadDir.replace("~", System.getProperty("user.home"));
        Path uploadPath = Paths.get(uploadDir);
        System.out.println(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(name);
        return filePath.toString();
    }
    
}
