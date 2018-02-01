package com.fmi.uni.sofia.www.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fmi.uni.sofia.www.objects.ConferenceDTO;
import com.fmi.uni.sofia.www.objects.NewConferenceDTO;
import com.fmi.uni.sofia.www.objects.StatusMessage;
import com.fmi.uni.sofia.www.objects.UserDTO;
import com.fmi.uni.sofia.www.objects.UserLoginDTO;
import com.fmi.uni.sofia.www.objects.UserRegDTO;
import com.fmi.uni.sofia.www.services.ConferenceService;
import com.fmi.uni.sofia.www.services.UserService;

@RestController
public class ApiController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ConferenceService conferenceService;
	
	@RequestMapping(value="registrate", method=RequestMethod.POST)
	public @ResponseBody StatusMessage registrate(@RequestBody UserRegDTO dto) {
		return userService.registrateUser(dto);
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public @ResponseBody StatusMessage login(HttpServletRequest req, @RequestBody UserLoginDTO dto) {
		StatusMessage s = userService.login(dto);
		if(s.isStatus()) {
			HttpSession session = req.getSession();
			session.setAttribute("userId", s.getReference().toString());
		}
		return s;
	}
	
	@RequestMapping(value="getUserInfo", method=RequestMethod.GET)
	public @ResponseBody UserDTO getUserInfo(HttpServletRequest req) {
		Integer userId = getUserId(req);
		return userService.getUserInfo(userId);
	}
	
	@RequestMapping(value="conferences/save", method=RequestMethod.POST)
	public @ResponseBody StatusMessage addConference(HttpServletRequest req, @RequestBody NewConferenceDTO dto) throws IOException {
		Integer userId = getUserId(req);
		StatusMessage stat = conferenceService.createConference(dto, userId);
		return stat;
	}
	
	@RequestMapping(value="conferences/{confId}", method=RequestMethod.GET)
	public @ResponseBody ConferenceDTO getCongerence(HttpServletRequest req, @PathVariable("confId") Integer confId) {
		return conferenceService.getConferenceById(confId);
	}
	
	@RequestMapping(value="conferences/mine", method=RequestMethod.GET)
	public @ResponseBody List<ConferenceDTO> getMyConferences(HttpServletRequest req) {
		Integer userId = getUserId(req);
		return conferenceService.getUserConferences(userId);
	}
	
	@RequestMapping(value="conferences/all", method=RequestMethod.GET)
	public @ResponseBody List<ConferenceDTO> getAllConferences(HttpServletRequest req) {
		Integer userId = getUserId(req);
		return conferenceService.getAllConferences(userId);
	}
	
	@RequestMapping(value="attend", method=RequestMethod.POST)
	public @ResponseBody StatusMessage attend(HttpServletRequest req, @RequestBody ConferenceDTO body) {
		Integer userId = getUserId(req);
		return conferenceService.attend(body, userId);
	}
	
	@RequestMapping(value="attendances", method=RequestMethod.GET)
	public @ResponseBody List<ConferenceDTO> attendances(HttpServletRequest req) {
		Integer userId = getUserId(req);
		return conferenceService.userAttendances(userId);
	}
	
	@RequestMapping(value="attendances/delete", method=RequestMethod.POST)
	public @ResponseBody StatusMessage deleteAttendance(HttpServletRequest req, @RequestBody ConferenceDTO dto) {
		Integer userId = getUserId(req);
		return conferenceService.deleteAttendance(dto.getId(), userId);
	}
	
	@RequestMapping(value="upload", method=RequestMethod.POST)
	public @ResponseBody String upload(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
		Iterator<String> itr =  request.getFileNames();
		if(!itr.hasNext()) {
			return "default.jpg";
		}
	    MultipartFile mpf = request.getFile(itr.next());
	    String fileName = UUID.randomUUID().toString() + ".jpg";
	    File dir = new File("images");
	    if(!dir.exists()) {
	    	if(dir.mkdir()) {
	    		System.out.println("Successfully create folder images.");
	    	}
	    }
	    File file = new File("./images/" + fileName);
	    if(!file.exists()) {
	    	file.createNewFile();
	    }
	    try(FileOutputStream fos = new FileOutputStream(file)) {
	    	fos.write(mpf.getBytes());
	    }
	    System.out.println(mpf.getOriginalFilename() +" uploaded!");
		return fileName;
	}
	
	@RequestMapping(value="/image/{imageName}", method=RequestMethod.GET, produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody /*BufferedImage*/ byte[] getImage(HttpServletResponse response, @PathVariable(name="imageName") String imageName) throws IOException {
		System.out.println("File name : " + imageName + ".jpg");
		File f = new File("./images/"  +  imageName + ".jpg");
		if(!f.exists() || imageName == null || "".equals(imageName)) {
			f = new File("./images/default.jpg");
		}
		InputStream st = new FileInputStream(f);
		return IOUtils.toByteArray(st);
	}
	
	private Integer getUserId(HttpServletRequest req) {
		String s = req.getSession().getAttribute("userId").toString();
		if(s == null) {
			return null;
		}
		return Integer.parseInt(s);
	}
}
