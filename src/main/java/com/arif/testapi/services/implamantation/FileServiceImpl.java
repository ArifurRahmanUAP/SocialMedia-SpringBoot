package com.arif.testapi.services.implamantation;


import com.arif.testapi.repositories.UserRepo;
import com.arif.testapi.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {


    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {


        // File Name
        String name = file.getOriginalFilename();

        //Full Path
//        String randomId = UUID.randomUUID().toString();
        String filePath = path + name;


        //Create Folder
        File f = new File(path);

        if (!f.exists()) {
            f.mkdir();
        }

        //File Copy

        Files.copy(file.getInputStream(), Paths.get(filePath));


        return name;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {

        String fullPath = path + File.separator + fileName;

        InputStream is = new FileInputStream(fullPath);

        return is;
    }
}
