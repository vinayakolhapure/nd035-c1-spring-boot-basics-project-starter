package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileService {

    private FilesMapper fileMapper;

    @Autowired
    public FileService(FilesMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<File> getAll() {
        return fileMapper.findAll();
    }

    public List<File> getAllByUserId(Long userId) {
        return fileMapper.findByUserId(userId);
    }

    public File getById(Long id, Long userId) {
        return fileMapper.findById(id, userId);
    }

    public boolean addFile(MultipartFile file, Long userId) {
        try {
            File newFile = new File(file.getOriginalFilename(), file.getContentType(),
                    file.getSize(), file.getBytes());

            fileMapper.create(newFile, userId);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFile(Long id, Long userId) {
        Integer result = fileMapper.delete(id, userId);

        return result > 0;
    }
}
