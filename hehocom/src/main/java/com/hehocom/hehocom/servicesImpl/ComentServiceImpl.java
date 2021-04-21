package com.hehocom.hehocom.servicesImpl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hehocom.hehocom.entities.coment.Coment;
import com.hehocom.hehocom.entities.member.Member;
import com.hehocom.hehocom.repository.ComentRepository;
import com.hehocom.hehocom.request.coment.NewComentRequest;
import com.hehocom.hehocom.request.coment.UpdateComentRequest;
import com.hehocom.hehocom.services.ComentService;

@Service
public class ComentServiceImpl implements ComentService {

	@Autowired
	private ComentRepository comentRepository;

	@Override
	public List<Coment> getComent(Long idSite, int offset) {
		offset = offset * 6;
		return comentRepository.findAllByIdSiteby6(idSite, offset);
	}

	@Override
	public Coment updateComentById(Long id, UpdateComentRequest updateComent) {
		Coment coment = comentRepository.getOne(id);

		coment.setDateCreation(new Timestamp(System.currentTimeMillis()));
		coment.setContent(updateComent.getContent());

		return comentRepository.save(coment);
	}

	@Override
	public void deleteComentById(long id) {
		comentRepository.deleteById(id);
	}

	@Override
	public Coment addComent(NewComentRequest newComent, Member checkMember) {
		Coment coment = new Coment();

		coment.setIdUser(checkMember.getId());
		coment.setStatus(checkMember.getStatus());
		coment.setNameUser(checkMember.getSecondname());

		coment.setContent(newComent.getContent());
		coment.setIdSite(newComent.getIdSite());
		coment.setFileName(null);

		return comentRepository.save(coment);
	}

	@Override
	public Coment addComentFile(NewComentRequest newComent, MultipartFile file, Member CheckMember) {
		Coment coment = new Coment();

		coment.setIdUser(CheckMember.getId());
		coment.setStatus(CheckMember.getStatus());
		coment.setNameUser(CheckMember.getSecondname());

		coment.setContent(newComent.getContent());
		coment.setIdSite(newComent.getIdSite());
		coment.setIdSite(newComent.getIdSite());
		if (file != null) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			coment.setFileName("file de " + CheckMember.getEmail() + " - " + file.getOriginalFilename());
		} else {
			coment.setFileName(null);
		}

		return comentRepository.save(coment);
	}

	@Override
	public Coment getLastCommentByIdSite(int idSite) {
		return comentRepository.findLast(idSite);
	}

	@Override
	public int getCountCommentByIdSite(int idSite) {
		return comentRepository.countAllByIdSite(idSite);
	}

}
