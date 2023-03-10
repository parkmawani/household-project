package com.team5.household.expense.service;

import com.team5.household.expense.entity.ExpenseEntity;
import com.team5.household.expense.entity.PaymentInfoEntity;
import com.team5.household.expense.repository.ExpenseRepository;
import com.team5.household.expense.repository.PaymentInfoRepository;
import com.team5.household.lchwork.entity.LchCultureCategoryEntity;
import com.team5.household.lchwork.entity.LchCultureDetailCategoryEntity;
import com.team5.household.lchwork.repository.LchCultureCategoryRepository;
import com.team5.household.lchwork.repository.LchCultureDetailCategoryRepository;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ExpenseService {
    @Autowired ExpenseRepository eRepo;
    @Autowired LchCultureCategoryRepository lRepo;
    @Autowired LchCultureDetailCategoryRepository ldRepo;
    @Autowired PaymentInfoRepository pRepo;
    @Value("${file.image.expense}") String expense_img_path;



    public String addEvent(
        String ehTitle,
        LocalDateTime ehDate,
        Long ehMiSeq,
        Long ehPiSeq,
        Long ehPrice,
        String ehStoreName,
        @Nullable MultipartFile ehImgFile,
        @Nullable String ehContent,
        String ehLocation,
        Long ehBalance,
        Long ehCcSeq,
        Long ehCdcSeq
    ) throws Exception{


        Path targetLocation = Paths.get(expense_img_path);
        Calendar c = Calendar.getInstance();
        String eventOriginFileName = ehImgFile.getOriginalFilename();
        String[] iFile = eventOriginFileName.split(("\\."));
        String iExt = iFile[iFile.length-1];
        String saveExpenseFileName = "Expose"+"_";
        saveExpenseFileName+=c.getTimeInMillis()+"."+iExt;

        BufferedImage image = ImageIO.read(ehImgFile.getInputStream());
        // ???????????? 512x512 ????????? ?????????.
        int width = 512;
        int height = 512;
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // ?????? ???????????? BufferedImage ????????? ????????????.
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = outputImage.createGraphics();
        graphics.drawImage(newImage, 0, 0, null);
        graphics.dispose();

        // ????????? ???????????? ????????????, ????????? ????????? ????????????
        targetLocation = targetLocation.resolve(saveExpenseFileName);
        // ?????? ???????????? ????????? ????????????.
        ImageIO.write(outputImage, iExt, new File(targetLocation.toString()));

        // ????????? ???????????? ????????????, ????????? ????????? ????????????
        //        targetLocation = targetLocation.resolve(ehImgFile.getOriginalFilename());
        // ????????? ????????? ????????? ????????? ????????????.
        //        Files.copy(ehImgFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        // ????????? ????????? ???????????? ???????????? ????????????.

        // return targetLocation.toString();

        ExpenseEntity expense = ExpenseEntity.builder()
                .ehTitle(ehTitle)
                .ehContent(ehContent)
                .ehDate(ehDate)
                .ehMiSeq(ehMiSeq)
                .ehPiSeq(ehPiSeq)
                .ehPrice(ehPrice)
                .ehStoreName(ehStoreName)
                .ehLocation(ehLocation)
                .ehBalance(ehBalance)
                .ehCcSeq(ehCcSeq)
                .ehCdcSeq(ehCdcSeq)
                .ehImgFile(saveExpenseFileName).build();

        expense = eRepo.save(expense);
        return ehImgFile.getOriginalFilename();
    }

    public Map<String, Object> getExpenseList(Long ehMiSeq, Pageable pageable) {
        Page<ExpenseEntity> page = eRepo.findPageByehMiSeq(ehMiSeq, pageable);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("list", page.getContent());
        map.put("total", page.getTotalElements());
        map.put("totalPage", page.getTotalPages());
        map.put("currentPage", page.getNumber());
        return map;
    }


    public ResponseEntity<Resource> getImagedownload(
            @Parameter(description = "??????", example = "Expose_1676530079360.jpeg") @PathVariable String filename
    ) throws Exception
    {
        Path imgLocation = Paths.get(expense_img_path+"/"+filename).normalize();
        Resource r = new UrlResource(imgLocation.toUri());
        System.out.println(r.getFilename());
        String contentType = "application/octet-stream";
        // contentType = "image/*";

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + URLEncoder.encode(r.getFilename(), "UTF-8") + "\"")
                .body(r);
    }
}
