package com.controller;

import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.ConfigEntity;
import com.entity.EIException;
import com.service.ConfigService;
import com.utils.R;
import com.utils.UploadUtils;

/**
 * 上传文件映射表
 */
@RestController
@RequestMapping("file")
@SuppressWarnings({"unchecked","rawtypes"})
public class FileController{
	@Autowired
    private ConfigService configService;

	@Value("${file.upload-dir:src/main/resources/static/upload}")
	private String uploadDir;
	/**
	 * 上传文件
	 */
	@RequestMapping("/upload")
    @IgnoreAuth
	public R upload(@RequestParam("file") MultipartFile file,String type) throws Exception {
		if (file.isEmpty()) {
			throw new EIException("上传文件不能为空");
		}
		String originalFilename = file.getOriginalFilename();
		String fileExt = StringUtils.isNotBlank(originalFilename) && originalFilename.lastIndexOf(".") > -1
				? originalFilename.substring(originalFilename.lastIndexOf(".") + 1) : "";
		File upload = UploadUtils.resolveUploadDir(uploadDir);
		String fileName = new Date().getTime()+"."+fileExt;
        if(StringUtils.isNotBlank(type) && type.contains("_template")) {
            fileName = type + "."+fileExt;
            new File(upload, fileName).deleteOnExit();
        }
		File dest = new File(upload, fileName);
		file.transferTo(dest);
		/**
  		 * 如果使用idea或者eclipse重启项目，发现之前上传的图片或者文件丢失，将下面一行代码注释打开
   		 * 请将以下的"D:\\springbootq33sd\\src\\main\\resources\\static\\upload"替换成你本地项目的upload路径，
 		 * 并且项目路径不能存在中文、空格等特殊字符
 		 */
//		FileUtils.copyFile(dest, new File("D:\\springbootq33sd\\src\\main\\resources\\static\\upload"+"/"+fileName)); /**修改了路径以后请将该行最前面的//注释去掉**/
		if(StringUtils.isNotBlank(type) && type.equals("1")) {
			ConfigEntity configEntity = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("name", "faceFile"));
			if(configEntity==null) {
				configEntity = new ConfigEntity();
				configEntity.setName("faceFile");
				configEntity.setValue(fileName);
			} else {
				configEntity.setValue(fileName);
			}
			configService.insertOrUpdate(configEntity);
		}
		return R.ok().put("file", fileName);
	}

	/**
	 * 下载文件
	 */
	@IgnoreAuth
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(@RequestParam String fileName) {
		try {
			File file = getUploadFile(fileName);
			if(file.exists()){
				/*if(!fileService.canRead(file, SessionManager.getSessionUser())){
					getResponse().sendError(403);
				}*/
				HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);    
			    headers.setContentDispositionFormData("attachment", fileName);    
			    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 文件在线预览。zip 合同中若包含图片或 PDF，自动预览第一个可展示文件。
	 */
	@IgnoreAuth
	@RequestMapping("/preview")
	public ResponseEntity<byte[]> preview(@RequestParam String fileName) {
		try {
			File file = getUploadFile(fileName);
			if(file.exists()){
				if (isZip(file.getName())) {
					return previewZip(file);
				}
				if (isPreviewable(file.getName())) {
					HttpHeaders headers = previewHeaders(file.getName());
					return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
				}
				return unsupportedPreview();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private File getUploadFile(String fileName) throws IOException {
		return new File(UploadUtils.resolveUploadDir(uploadDir), UploadUtils.cleanFileName(fileName));
	}

	private ResponseEntity<byte[]> previewZip(File file) throws IOException {
		try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file))) {
			ZipEntry zipEntry;
			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				if (!zipEntry.isDirectory() && isPreviewable(zipEntry.getName())) {
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[4096];
					int len;
					while ((len = zipInputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, len);
					}
					HttpHeaders headers = previewHeaders(zipEntry.getName());
					return new ResponseEntity<byte[]>(outputStream.toByteArray(), headers, HttpStatus.OK);
				}
			}
		}
		return unsupportedPreview();
	}

	private ResponseEntity<byte[]> unsupportedPreview() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<byte[]>("Unsupported preview file type, please download it.".getBytes(), headers, HttpStatus.OK);
	}

	private HttpHeaders previewHeaders(String fileName) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(getPreviewMediaType(fileName));
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"");
		return headers;
	}

	private boolean isZip(String fileName) {
		return getExt(fileName).equals("zip");
	}

	private boolean isPreviewable(String fileName) {
		List<String> previewableExts = Arrays.asList("pdf", "jpg", "jpeg", "png", "gif", "bmp", "webp", "txt", "html", "htm");
		return previewableExts.contains(getExt(fileName));
	}

	private MediaType getPreviewMediaType(String fileName) {
		String ext = getExt(fileName);
		if (ext.equals("pdf")) {
			return MediaType.APPLICATION_PDF;
		}
		if (ext.equals("jpg") || ext.equals("jpeg")) {
			return MediaType.IMAGE_JPEG;
		}
		if (ext.equals("png")) {
			return MediaType.IMAGE_PNG;
		}
		if (ext.equals("gif")) {
			return MediaType.IMAGE_GIF;
		}
		if (ext.equals("html") || ext.equals("htm")) {
			return MediaType.TEXT_HTML;
		}
		return MediaType.TEXT_PLAIN;
	}

	private String getExt(String fileName) {
		if (fileName == null || !fileName.contains(".")) {
			return "";
		}
		return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	}
}
