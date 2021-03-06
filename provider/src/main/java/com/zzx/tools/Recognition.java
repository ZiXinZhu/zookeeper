/*
package com.zzx.provider.tools;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;

public class Recognition {

    public static void main(String[] args) throws Exception {
        ITesseract instance = new Tesseract();
        //如果未将tessdata放在根目录下需要指定绝对路径
        instance.setDatapath("F://pac//paychannelone//tessdata");

        //如果需要识别英文之外的语种，需要指定识别语种，并且需要将对应的语言包放进项目中
        instance.setLanguage("eng");

        // 指定识别图片
        File imgDir = new File("C://Users//Administrator//Pictures//121.jpg");
        long startTime = System.currentTimeMillis();
        String ocrResult = instance.doOCR(imgDir);

        // 输出识别结果
        System.out.println("OCR Result: \n" + ocrResult + "\n 耗时：" + (System.currentTimeMillis() - startTime) + "ms");

    }

}
*/
