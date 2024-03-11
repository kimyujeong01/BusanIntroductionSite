package com.bitc.java501_team4.common;

import com.bitc.java501_team4.dto.community.reviewFileDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class FileUtils {
//    private final ResourceLoader resourceLoader;
//
//    public FileUtils(ResourceLoader resourceLoader) {
//        this.resourceLoader = resourceLoader;
//    }

    public List<reviewFileDTO> parseFileInfo(int rIdx, String createId, MultipartHttpServletRequest multipart) throws Exception {
        if (ObjectUtils.isEmpty(multipart)) {

            return null;
        }

        List<reviewFileDTO> fileList = new ArrayList<>();

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        현재 위치를 기준으로 현재 시간을 가져옴
        ZonedDateTime current = ZonedDateTime.now();
//        저장할 파일 경로 설정, 기본위치 + 현재시간을 기준으로 지정된 형식 으로 전체 경로 설정
        String path = "C:/fullstack501/images/" + current.format(format);
//        String staticImagesPath = ResourceUtils.getFile("classpath:static/images/").getAbsolutePath();
//        String path = staticImagesPath + "/" + current.format(format);
//        File 클래스를 통해서 파일 객체 생성
        File file = new File(path);
        if (file.exists() == false) {
            file.mkdirs();
        }
        Iterator<String> iterator = multipart.getFileNames();

        String newFileName;
        String originalFileExtension;
        String contentType;

        while (iterator.hasNext()) {
//            파일 이름을 기준으로 파일 정보를 가져옴
            List<MultipartFile> fileLists = multipart.getFiles(iterator.next());
            for (MultipartFile uploadFile : fileLists) {

                if (uploadFile.isEmpty() == false) {
                    contentType = uploadFile.getContentType();//파일 타입 가져오기

                    if (ObjectUtils.isEmpty(contentType)) {
                        break;
                    } else {
//                        파일의 타입에 따라 확장자명 입력하기
                        if (contentType.contains("image/jpeg")) {
                            originalFileExtension = ".jpg";
                        } else if (contentType.contains("image/png")) {
                            originalFileExtension = ".png";
                        } else if (contentType.contains("image/gif")) {
                            originalFileExtension = ".gif";
                        } else {
                            break;
                        }
                    }

                    newFileName = System.nanoTime() + originalFileExtension;

                    reviewFileDTO reviewFile = new reviewFileDTO();
                    reviewFile.setRIdx(rIdx);//매개변수로 받아온 게시물 번호 저장
                    reviewFile.setFileSize(uploadFile.getSize());
                    reviewFile.setOFile(uploadFile.getOriginalFilename());
                    reviewFile.setSFile(path + "/" + newFileName);
                    reviewFile.setCreateId(createId);

                    fileList.add(reviewFile);

                    file = new File(path + "/" + newFileName);

                    uploadFile.transferTo(file);
                }
            }
        }
        return fileList;
    }

    public void deleteFIle(String fileName){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ZonedDateTime current = ZonedDateTime.now();
        String path = "C:/fullstack501/images/" + current.format(format);

        File file = new File(path +File.separator + fileName);

        if (file.exists()){
            file.delete();
        }
    }
}

