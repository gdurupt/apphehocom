package com.hehocom.hehocom.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hehocom.hehocom.entities.coment.Coment;
import com.hehocom.hehocom.entities.member.Member;
import com.hehocom.hehocom.request.coment.NewComentRequest;
import com.hehocom.hehocom.request.coment.UpdateComentRequest;
import com.hehocom.hehocom.services.AuthenticationService;
import com.hehocom.hehocom.services.ComentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/hehocom")
public class ComentController {

	@Autowired
	private ComentService comentService;

	@Autowired
	private AuthenticationService service;

	// Get all Coments
	@GetMapping(value = "/coment/{idSite}/{offset}")
	public List<Coment> getComent(@PathVariable Long idSite, @PathVariable int offset) {
		return comentService.getComent(idSite, offset);
	}

	// Get all mission by idSite
	@GetMapping(value = "/coment/last/{idSite}")
	public Coment getLastCommentById(@PathVariable int idSite) {
		return comentService.getLastCommentByIdSite(idSite);
	}

	// Get all mission by idSite
	@GetMapping(value = "/coment/count/{idSite}")
	public int getCountCommentById(@PathVariable int idSite) {
		return comentService.getCountCommentByIdSite(idSite);
	}

	// add Commentaire
	@PostMapping(value = "/coment/text")
	public Coment addComent(@RequestBody NewComentRequest NewComent) {
		Member CheckMember = this.service.getCurrentUser();

		return comentService.addComent(NewComent, CheckMember);
	}

	@PostMapping(value = "/coment/file")
	public Coment addComentFile(@RequestPart("file") MultipartFile file,
			@RequestPart("coment") NewComentRequest NewComent) {
		Member CheckMember = this.service.getCurrentUser();

		Path copyLocationFile = Paths.get("D:\\ProjetPro\\HehocomFront\\src\\assets\\fichiers" + File.separator
				+ StringUtils.cleanPath("file-de-" + CheckMember.getEmail().replaceAll(" ", "") + "-"
						+ file.getOriginalFilename().replaceAll(" ", "")));
		try {
			Files.copy(file.getInputStream(), copyLocationFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return comentService.addComentFile(NewComent, file, CheckMember);
	}

	// update Commentaire
	@PutMapping(value = "/coment/{id}")
	public Coment updateComentById(@PathVariable Long id, @RequestBody UpdateComentRequest updateComent) {
		return comentService.updateComentById(id, updateComent);
	}

	// delete Commentaire
	@DeleteMapping(value = "/coment/{id}")
	public void deleteComentById(@PathVariable long id) {
		comentService.deleteComentById(id);
	}
}
