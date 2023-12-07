package org.scoula.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@Log4j
public class UploadedFile {
    private String baseDir;     // 업로드 디렉토리

    private Long no;            //
    private String filename;    // 원본 파일명
    private String path;        // 서버에 저장된 파일 경로
    private String contentType; // 파일 mime-type
    private Long size;          // 파일의 크기
    private Date regDate;       // 등록일

    public UploadedFile(String baseDir, MultipartFile part) {
        this.baseDir = baseDir;
        this.filename = part.getOriginalFilename();
        this.contentType = part.getContentType();
        this.size = part.getSize();

        this.path = upload(part);
    }

    private String getUniqueName() {
        int ix = filename.lastIndexOf(".");
        String name = filename.substring(0, ix);
        String ext =  filename.substring(ix+1);

        return String.format("%s-%d.%s", name, System.currentTimeMillis(),ext);
    }

    private String upload(MultipartFile part) {
        try {
            File dest = new File(baseDir, getUniqueName());
            part.transferTo(dest);
            return dest.getPath();
        } catch (IOException e) {
            log.error(e);
        }

        return "";
    }

    public String getFormatSize() {
        if (size <= 0)
            return "0";
        final String[] units = new String[] { "Bytes", "KB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public void download(HttpServletResponse response) throws Exception {

        response.setContentType("application/download");
        response.setContentLength(size.intValue());

        String filename = URLEncoder.encode(this.filename, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=\"" + filename + "\"");

        try(OutputStream os = response.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os)) {
            Files.copy(Paths.get(path), bos);
        }
    }

}
