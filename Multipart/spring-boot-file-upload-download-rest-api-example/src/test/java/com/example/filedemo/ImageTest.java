package com.example.filedemo;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;


/**
 * Измение размера изображения на заданные
 * Дополнительно. Проще всего руками создать в корневой директории проекта папку Files и положить img_1.jpg
 * Дополнительно  Как сделать скрин
 */
public class ImageTest {

    private final String FILE_BASE_PATH = "Files";
    private final String RESULTS_BASE_PATH = "Converted";
    private final String file_name = "MY_FILE_NAME";
    private final String baseFileName = "img_1.jpg";

    String PATH = "/remote/dir/server/";
    String directoryName = PATH.concat(this.getClass().getName());


    @Test
    public void contextLoads() throws Exception {
        createOrRetrieveFolder(RESULTS_BASE_PATH);

        int i = 1;
        File baseImg = new File(FILE_BASE_PATH + File.separator + baseFileName);
        createThumbnail(baseImg);

        createThumbnailWithWidthAndHeigth(baseImg, 200, 200, i );
        i++;
        Assert.assertTrue(true);
    }

    @Test
    public void CreateScreenShot()  {
        createPartScreenShot();

        createScreenShot();
        Assert.assertTrue(true);
    }

        private void createPartScreenShot() {
        try {
            Robot robot = new Robot();
            String format = "jpg";
            String fileName = "PartialScreenshot_" + System.currentTimeMillis()+"."  + format;

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            // Size !!!
            Rectangle captureRect = new Rectangle(0, 0, screenSize.width / 2, screenSize.height / 2);
            BufferedImage screenFullImage = robot.createScreenCapture(captureRect);

            String results = getResultsFilePath(fileName);
            ImageIO.write(screenFullImage, format, new File(results));

            System.out.println("A partial screenshot saved!");
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }
    }

    private void createScreenShot() {
        try {
            Robot robot = new Robot();
            String format = "jpg";
            String fileName = "PartialScreenshot_full_" + System.currentTimeMillis()+"."  + format;

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            // Size !!!
            Rectangle captureRect = new Rectangle(0, 0, screenSize.width , screenSize.height);
            BufferedImage screenFullImage = robot.createScreenCapture(captureRect);

            String results = getResultsFilePath(fileName);
            ImageIO.write(screenFullImage, format, new File(results));

            System.out.println("A partial screenshot saved!");
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }
    }

    private String getResultsFilePath(String fileName) {
        return RESULTS_BASE_PATH + File.separator + fileName;
    }


    private String getResultsFilePath(String fileName, int count) {
        return RESULTS_BASE_PATH + File.separator + count + "_" + fileName;
    }


    public void createThumbnailWithWidthAndHeigth(File file, int width, int height, int count) throws Exception {

        BufferedImage img = ImageIO.read(file);
        BufferedImage scaledImage = new BufferedImage(width, height,  BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = (Graphics2D) scaledImage.getGraphics();
        g2d.drawImage(img, 0, 0, scaledImage.getWidth() - 1, scaledImage.getHeight() - 1, 0, 0,
                img.getWidth() - 1, img.getHeight() - 1, null);
        g2d.dispose();

        String results = getResultsFilePath("thumb__" + file.getName(), count);
        ImageIO.write(scaledImage, "PNG", new File(results));
    }

    public void createThumbnailWithWidthAndHeigth(File file, int width, int height, int count,BufferedImage image ) throws Exception {

        BufferedImage img = ImageIO.read(file);
        BufferedImage scaledImage = new BufferedImage(width, height,  image.getType());

        final AffineTransform at = AffineTransform.getScaleInstance(2.0, 2.0);
        final AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        scaledImage = ato.filter(image, scaledImage);

//        Graphics2D g2d = (Graphics2D) scaledImage.getGraphics();
//        g2d.drawImage(img, 0, 0, scaledImage.getWidth() - 1, scaledImage.getHeight() - 1, 0, 0,
//                img.getWidth() - 1, img.getHeight() - 1, null);
//        g2d.dispose();

        String results = getResultsFilePath("thumb__" + file.getName(), count);
        ImageIO.write(scaledImage, "PNG", new File(results));
    }



    public void createThumbnail(File file) throws Exception {


        BufferedImage img = ImageIO.read(file);
        BufferedImage thumb = new BufferedImage(100, 200,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = (Graphics2D) thumb.getGraphics();
        g2d.drawImage(img, 0, 0, thumb.getWidth() - 1, thumb.getHeight() - 1, 0, 0,
                img.getWidth() - 1, img.getHeight() - 1, null);
        g2d.dispose();

        final String extention = getExtensionByStringHandling(file.getName()).orElseThrow(() -> new RuntimeException("Can't get extention by this file"));
        String results = getResultsFilePath("thumb__" + file.getName(), 0);
        ImageIO.write(thumb, extention, new File(results));
    }

    /**
     * Creates a File if the file does not exist, or returns a
     * reference to the File if it already exists.
     */
    private void createOrRetrieveFolder(final String target) throws IOException {

        File file = new File(target);

        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
    }

    /**
     * Deletes the target if it exists then creates a new empty file.
     */
    private File createOrReplaceFileAndDirectories(final String target) throws IOException {

        final Path path = Paths.get(target);
        // Create only if it does not exist already
        Files.walk(path)
                .filter(p -> Files.exists(p))
                .sorted(Comparator.reverseOrder())
                .peek(p -> System.out.println("Deleted existing file or directory \"" + p + "\"."))
                .forEach(p -> {
                    try {
                        Files.createFile(Files.createDirectories(p));
                    } catch (IOException e) {
                        // TODO: just skip
                        //  throw new IllegalStateException(e);
                    }
                });

        System.out.println("Target file \"" + target + "\" will be created.");

        return Files.createFile(
                Files.createDirectories(path)
        ).toFile();
    }

    /**
     * @param filename
     * @return
     */
    public Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}