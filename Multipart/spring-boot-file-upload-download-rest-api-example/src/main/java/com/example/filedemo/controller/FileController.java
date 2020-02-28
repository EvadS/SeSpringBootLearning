package com.example.filedemo.controller;

import com.example.filedemo.payload.UploadFileResponse;
import com.example.filedemo.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.IOUtils;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @RequestMapping(value = "/test")
    public void downloadZip(HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        try {

            Path zipPath = fileStorageService.getPath("noname");

            //String zioPath
                File file = new File(zipPath.toString());
                response.setHeader("Content-Length", String.valueOf(file.length()));
                response.setHeader("Content-Disposition", "attachment; filename=\"" +System.currentTimeMillis() + ".zip" + "\"");
                InputStream is = new FileInputStream(file);
                FileCopyUtils.copy(IOUtils.toByteArray(is), response.getOutputStream());
                response.flushBuffer();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**+
     * Download several files Only firefox     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/test-mixed")
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        // Set the response type and specify the boundary string
        response.setContentType("multipart/x-mixed-replace;boundary=END");

        // Set the content type based on the file type you need to download
        String contentType = "Content-type: image/jpg";

        // List of files to be downloaded
        List<File> files = new ArrayList<>();
        Path zipPath = fileStorageService.getPath("noname");
        Path zipPath2 = fileStorageService.getPath("noname2");
        Path zipPath3 = fileStorageService.getPath("noname3");

        files.add(new File(zipPath.toString()));
        files.add(new File(zipPath2.toString()));
        files.add(new File(zipPath3.toString()));

        ServletOutputStream out = response.getOutputStream();

        // Print the boundary string
        out.println();
        out.println("--END");

        for (File file : files) {

            // Get the file
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);

            } catch (FileNotFoundException fnfe) {
                // If the file does not exists, continue with the next file
                System.out.println("Could not find file " + file.getAbsolutePath());
                continue;
            }

            BufferedInputStream fif = new BufferedInputStream(fis);

            // Print the content type
            out.println(contentType);
            out.println("Content-Disposition: attachment; filename=" + file.getName());
            out.println();

            System.out.println("Sending file " + file.getName());

            // Write the contents of the file
            int data = 0;
            while ((data = fif.read()) != -1) {
                out.write(data);
            }
            fif.close();

            // Print the boundary string
            out.println();
            out.println("--END");
            out.flush();
            System.out.println("Finished sending file " + file.getName());
        }

        // Print the ending boundary string
        out.println("--END--");
        out.flush();
        out.close();
    }


    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public void getImageAsByteArray(HttpServletResponse response) throws IOException {
        InputStream in =
                fileStorageService.loadFileAsResource("noname").getInputStream();

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    @RequestMapping(value = "/image-response-entity", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageAsResponseEntity() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        InputStream in = fileStorageService.loadFileAsResource("noname").getInputStream();
        byte[] media = IOUtils.toByteArray(in);
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * Test. get as resource
     * @return
     */
    @RequestMapping(value = "/image-resource", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> getImageAsResource() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.parse("attachment"));
        Resource resource =
                fileStorageService.loadFileAsResource("noname");
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    /**
     * Downloading Multiple Files As Zip
     *
     * @author JavaDigest
     */
    @RequestMapping(value = "/test-zip")
   public void testZip(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            // Set the content type based to zip
            response.setContentType("Content-type: text/zip");
            response.setHeader("Content-Disposition",
                    "attachment; filename=mytest.zip");

            // List of files to be downloaded
            List<File> files = new ArrayList();
        Path zipPath = fileStorageService.getPath("noname");
        Path zipPath2 = fileStorageService.getPath("noname2");
        Path zipPath3 = fileStorageService.getPath("noname3");

        files.add(new File(zipPath.toString()));
        files.add(new File(zipPath2.toString()));
        files.add(new File(zipPath3.toString()));

        ServletOutputStream out = response.getOutputStream();
            ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(out));

            for (File file : files) {

                System.out.println("Adding file " + file.getName());
                zos.putNextEntry(new ZipEntry(file.getName()));

                // Get the file
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);

                } catch (FileNotFoundException fnfe) {
                    // If the file does not exists, write an error entry instead of
                    // file
                    // contents
                    zos.write(("ERROR: Could not find file " + file.getName())
                            .getBytes());
                    zos.closeEntry();
                    System.out.println("Could not find file "
                            + file.getAbsolutePath());
                    continue;
                }

                BufferedInputStream fif = new BufferedInputStream(fis);

                // Write the contents of the file
                int data = 0;
                while ((data = fif.read()) != -1) {
                    zos.write(data);
                }
                fif.close();

                zos.closeEntry();
                System.out.println("Finished adding file " + file.getName());
            }

            zos.close();
        }
}
