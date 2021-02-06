package com.hehocom.hehocom.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hehocom.hehocom.entities.member.Member;
import com.hehocom.hehocom.exception.MemberNotFoundException;
import com.hehocom.hehocom.exception.MemberValidationException;
import com.hehocom.hehocom.repository.MemberRepository;
import com.hehocom.hehocom.request.member.CreateMemberForm;
import com.hehocom.hehocom.request.member.UpdateMemberForm;
import com.hehocom.hehocom.request.member.UpdateMemberPasswordForm;
import com.hehocom.hehocom.request.member.UpdateMemberStatusForm;
import com.hehocom.hehocom.services.MemberService;

@Service(value = "member")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	// contructor
	public MemberServiceImpl() {
	}

	// ***********************
	// DATA PROCESSING
	// ***********************
	@Override
	public Member registerMember(CreateMemberForm createMemberForm) {

		Member member = new Member();

		try {

			// validation of attributes
			validationEmail(createMemberForm.getEmail());
			member.setEmail(createMemberForm.getEmail());
			validationUsername(createMemberForm.getUsername());
			member.setUsername(createMemberForm.getUsername());
			member.setPassword(this.encoder.encode(createMemberForm.getPassword()));
			// save in database
			memberRepository.save(member);

		} catch (Exception e) {
			e.getMessage();
			return null;
		}

		return member;

	}

	@Override
	public Member updatePassword(long id, UpdateMemberPasswordForm updateMemberPasswordForm) {

		// get member
		Member member = this.getMemberById(id);

		// validate password and encrypt it
		try {
			member.setPassword(this.encoder.encode(updateMemberPasswordForm.getPassword()));
		} catch (Exception e) {
			e.getMessage();
			return null;
		}

		// update of password in database
		memberRepository.updatePassword(id, member.getPassword());
		System.out.println("Password correctly modified");

		return member;

	}

	@Override
	public Member updateMemberProfile(long id, UpdateMemberForm updateMemberForm) {

		Member member = this.getMemberById(id);

		// attributes updated for the return object and database updated
		if (updateMemberForm.getUsername() != null)
			member.setUsername(updateMemberForm.getUsername());

		return member;
	}

	@Override
	public Member switchAccountStatus(long id) {

		Member member = this.getMemberById(id);

		return member;
	}

	@Override
	public Member updateStatus(long id, UpdateMemberStatusForm updateMemberStatusForm, Member memberCheck) {
		String statusMember = memberCheck.getStatus().name();

		if (statusMember == "ADMINISTRATOR") {
			Member member = this.getMemberById(id);

			member.setStatus(updateMemberStatusForm.getStatus());
			memberRepository.updateStatus(id, member.getStatus().name()); // SQL query needs strings

			return member;
		} else {
			return memberCheck;
		}

	}

	@Override
	public Member getMemberById(long id) throws MemberNotFoundException {

		Member member = memberRepository.findById(id);

		if (member == null) {
			throw new MemberNotFoundException("Le membre n° " + id + " est introuvable");
		}

		return member;
	}

	@Override
	public Member getMemberByUserName(String username) {
		return this.memberRepository.findByUsername(username);
	}

	@Override
	public List<Member> getMembers() throws MemberNotFoundException {

		List<Member> members = memberRepository.findAll();

		if (members == null) {
			throw new MemberNotFoundException("Aucun membre trouvé");
		}

		return members;
	}

	@Override
	public void deleteById(long id) {
		this.memberRepository.deleteById(id);
	}

	// ***********************
	// DATA VALIDATION METHODS
	// ***********************

	private void validationEmail(String email) throws MemberValidationException {

		if (email != null && memberRepository.findByEmail(email) != null) {

			throw new MemberValidationException("Cette adresse mail est déjà utilisée, merci d'en choisir une autre.");

		}
	}

	private void validationUsername(String username) throws MemberValidationException {

		if (username != null && memberRepository.findByUsername(username) != null) {

			throw new MemberValidationException("Ce pseudo est déjà utilisé, merci d'en choisir un autre.");

		}

	}

	@Override
	public Member getMemberByEmail(String email) {
		return this.memberRepository.findByEmail(email);
	}

}