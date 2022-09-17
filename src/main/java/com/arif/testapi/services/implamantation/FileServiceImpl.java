package com.arif.testapi.services.implamantation;


import com.arif.testapi.repositories.UserRepo;
import com.arif.testapi.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {


    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {


        // File Name
        String name = file.getOriginalFilename();

        //File Path
        String randomId = UUID.randomUUID().toString();
        String filePath = path + File.separator + randomId + name;



        //Create Folder
        File f = new File(path);

        if (!f.exists()) {
            f.mkdir();
        }

        //File Copy

        Files.copy(file.getInputStream(), Paths.get(filePath));


        return filePath;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        return null;
    }
}
