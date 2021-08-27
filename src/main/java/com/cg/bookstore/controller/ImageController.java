package com.cg.bookstore.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cg.bookstore.entities.ImageModel;
import com.cg.bookstore.repository.ImageRepository;

import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "check")

public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @PostMapping("/uploadss")
    public ImageModel uplaodImage(@RequestParam("myFile") MultipartFile file) throws IOException {

    	//file.getOriginalFilename(),file.getContentType(),
        ImageModel img = new ImageModel(compressBytes(file.getBytes()));


        final ImageModel savedImage = imageRepository.save(img);


        System.out.println("Image saved");


        return savedImage;


    }

    @GetMapping(path = { "/get/{id}" })

    public ImageModel getImage(@PathVariable("id") Long id) throws IOException {

        final Optional<ImageModel> retrievedImage = imageRepository.findById(id);

        ImageModel img = new ImageModel(

                decompressBytes(retrievedImage.get().getPic()));
        System.out.println(retrievedImage.get().getPic());

        return img;

    }
    
    public static byte[] compressBytes(byte[] data) {

        Deflater deflater = new Deflater();

        deflater.setInput(data);

        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] buffer = new byte[1024];

        while (!deflater.finished()) {

            int count = deflater.deflate(buffer);

            outputStream.write(buffer, 0, count);

        }

        try {

            outputStream.close();

        } catch (IOException e) {

        }

        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();

    }
    
    public static byte[] decompressBytes(byte[] data) {

        Inflater inflater = new Inflater();

        inflater.setInput(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] buffer = new byte[1024];

        try {

            while (!inflater.finished()) {

                int count = inflater.inflate(buffer);

                outputStream.write(buffer, 0, count);

            }

            outputStream.close();

        } catch (IOException ioe) {

        } catch (DataFormatException e) {

        }

        return outputStream.toByteArray();

    }
    

}
