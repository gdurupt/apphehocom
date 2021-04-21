package com.hehocom.hehocom.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hehocom.hehocom.entities.coment.Coment;
import com.hehocom.hehocom.entities.member.Member;
import com.hehocom.hehocom.request.coment.NewComentRequest;
import com.hehocom.hehocom.request.coment.UpdateComentRequest;

public interface ComentService {

	public List<Coment> getComent(Long idSIte, int offset);

	public Coment updateComentById(Long id, UpdateComentRequest updateComent);

	public void deleteComentById(long id);

	public Coment addComent(NewComentRequest newComent, Member checkMember);

	public Coment addComentFile(NewComentRequest newComent, MultipartFile file, Member CheckMember);

	public Coment getLastCommentByIdSite(int idSite);

	public int getCountCommentByIdSite(int idSite);
}
