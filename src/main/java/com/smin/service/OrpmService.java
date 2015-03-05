package com.smin.service;

import com.smin.dto.CompanyInfo;
import com.smin.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by phuonghqh on 3/3/15.
 */
@Service("ORPMService")
public class OrpmService implements SService {

  private final Logger LOGGER = LoggerFactory.getLogger(OrpmService.class);

  @Value("${orpm.link}")
  private String link;

  @Value("${ormp.wwwFolder}")
  private String wwwFolder;

  @Value("${orpm.soft}")
  private Resource orpmZip;

  public Result doService(CompanyInfo companyInfo) throws Exception {
    ZipUtil.unpack(orpmZip.getFile(), new File(wwwFolder));
    Files.move(Paths.get(wwwFolder + "orpm/"), Paths.get(wwwFolder + companyInfo.getName()));
    LOGGER.debug("Unpack done !!");

    Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
    //add owners permission
    perms.add(PosixFilePermission.OWNER_READ);
    perms.add(PosixFilePermission.OWNER_WRITE);
    perms.add(PosixFilePermission.OWNER_EXECUTE);
    //add group permissions
    perms.add(PosixFilePermission.GROUP_READ);
    perms.add(PosixFilePermission.GROUP_WRITE);
    perms.add(PosixFilePermission.GROUP_EXECUTE);
    //add others permissions
    perms.add(PosixFilePermission.OTHERS_READ);
    perms.add(PosixFilePermission.OTHERS_WRITE);
    perms.add(PosixFilePermission.OTHERS_EXECUTE);

    Files.walk(Paths.get(wwwFolder), FileVisitOption.FOLLOW_LINKS).forEach(path -> {
      try {
        Files.setPosixFilePermissions(path, perms);
        LOGGER.debug("Change permission for {}", path.toString());
      }
      catch (Exception e) {
//        throw new UnsupportedOperationException("Error internal");
      }
    });

    Result success = Result.Success;
    success.setMessage("Done!! Go to link: " + String.format(link, companyInfo.getName()) + " to continue the process!");
    return success;
  }

  public static void main(String[] arg) throws IOException {
//    FileUtils.copyDirectory(new File("sample/abc"), new File("sample/def"));

    String output = "sample/demo/";
    ZipUtil.unpack(new File("src/main/resources/soft/orpm.zip"), new File(output));
    Files.move(Paths.get(output + "orpm/"), Paths.get(output + "sampleApp/"));

    Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
    //add owners permission
    perms.add(PosixFilePermission.OWNER_READ);
    perms.add(PosixFilePermission.OWNER_WRITE);
    perms.add(PosixFilePermission.OWNER_EXECUTE);
    //add group permissions
    perms.add(PosixFilePermission.GROUP_READ);
    perms.add(PosixFilePermission.GROUP_WRITE);
    perms.add(PosixFilePermission.GROUP_EXECUTE);
    //add others permissions
    perms.add(PosixFilePermission.OTHERS_READ);
    perms.add(PosixFilePermission.OTHERS_WRITE);
    perms.add(PosixFilePermission.OTHERS_EXECUTE);

    Files.walk(Paths.get(output), FileVisitOption.FOLLOW_LINKS).forEach(path -> {
      try {
        Files.setPosixFilePermissions(path, perms);
      }
      catch (Exception e) {
      }
    });

  }
}
