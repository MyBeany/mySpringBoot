package com.example.demo.core.utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;
import net.coobird.thumbnailator.resizers.configurations.ScalingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 图片处理工具类，主要压缩，添加logo等
 * @author 张瑶
 */
public class ImageUtils {

    public static Logger logger = LoggerFactory.getLogger(ImageUtils.class);

    public static void main(String[] args) {
        //使用给定的图片生成指定大小的图片
//        generateFixedSizeImage();

        //对原图加水印,然后顺时针旋转90度,最后压缩为80%保存
        generateRotationWatermark();

        //转换图片格式,将流写入到输出流
        //generateOutputstream();

        //按比例缩放图片
        //generateScale();

        //生成缩略图到指定的目录
        //generateThumbnail2Directory();

        //将指定目录下所有图片生成缩略图
        //generateDirectoryThumbnail();
    }

    /**
     * 使用给定的图片生成指定大小的图片
     */
    public static void generateFixedSizeImage(){
        try {
            Thumbnails.of("C:\\Users\\Administrator\\Desktop\\微信图片_20180129100019.jpg").size(80,80).toFile("C:\\Users\\Administrator\\Desktop\\newmeinv.jpg");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 对原图加水印,然后顺时针旋转90度,最后压缩为80%保存
     */
    public static void generateRotationWatermark(){
        try {
            Thumbnails.of("C:\\Users\\Administrator\\Desktop\\微信图片_20180129100019.jpg").
                    // 缩放大小
                    size(1600,1600).
                    // 顺时针旋转90度
                    rotate(90).
                    //水印位于右下角,半透明
                    watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\微信图片_20180329153521.png")),1f).
                    // 图片压缩80%质量
                    outputQuality(0.8).
                    toFile("C:\\Users\\Administrator\\Desktop\\2016010208_new.jpg");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 转换图片格式,将流写入到输出流
     */
    public static void generateOutputstream(){
        try(OutputStream outputStream = new FileOutputStream("data/2016010208_outputstream.png")) {
            Thumbnails.of("data/2016010208.jpg").
                    size(500,500).
                    // 转换格式
                    outputFormat("png").
                    // 写入输出流
                    toOutputStream(outputStream);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 按比例缩放图片
     */
    public static void generateScale(){
        try {
            Thumbnails.of("data/2016010208.jpg").
                    // 图片缩放80%, 不能和size()一起使用
                    scale(0.8).
                    // 图片质量压缩80%
                    outputQuality(0.8).
                    toFile("data/2016010208_scale.jpg");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 生成缩略图到指定的目录
     */
    public static void generateThumbnail2Directory(){
        try {
            Thumbnails.of("data/2016010208.jpg","data/meinv.jpg").
                    // 图片缩放80%, 不能和size()一起使用
                    scale(0.8).
                    //指定的目录一定要存在,否则报错
                    toFiles(new File("data/new/"), Rename.NO_CHANGE);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 将指定目录下所有图片生成缩略图
     */
    public static void generateDirectoryThumbnail(){
        try {
            Thumbnails.of(
                    new File("data/new").listFiles()).
                    scalingMode(ScalingMode.BICUBIC).
                    scale(0.8).
                    toFiles(new File("data/new/"), Rename.SUFFIX_HYPHEN_THUMBNAIL);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
