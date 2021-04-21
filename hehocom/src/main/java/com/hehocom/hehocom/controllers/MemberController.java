package com.hehocom.hehocom.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.hehocom.hehocom.entities.member.Member;
import com.hehocom.hehocom.entities.member.Status;
import com.hehocom.hehocom.exception.ConstraintViolationException;
import com.hehocom.hehocom.request.Jwt.JwtResponse;
import com.hehocom.hehocom.request.member.CreateMemberForm;
import com.hehocom.hehocom.request.member.LoginForm;
import com.hehocom.hehocom.request.member.UpdateMemberForm;
import com.hehocom.hehocom.request.member.UpdateMemberPasswordForm;
import com.hehocom.hehocom.request.member.UpdateMemberStatusForm;
import com.hehocom.hehocom.services.AuthenticationService;
import com.hehocom.hehocom.services.MemberService;
import com.hehocom.hehocom.services.UserBySiteService;

@RestController
@CrossOrigin("*")
@RequestMapping("/hehocom")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private AuthenticationService service;

	@Autowired
	private UserBySiteService UserBySiteService;

	@PostMapping(value = "/register")
	public Member registerMember(@Valid @RequestBody CreateMemberForm createMemberForm, Errors errors) {

		constraintViolationCheck(errors);

		return memberService.registerMember(createMemberForm);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginForm credentials) {
		final Authentication authenticate;
		try {
			authenticate = this.service.authentication(credentials.getEmail(), credentials.getPassword());
		} catch (AuthenticationException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
					"Wrong credentials, please try again or contact an administrator.");
		}
		final Member user = (Member) authenticate.getPrincipal();
		final String token = this.service.login(user);
		return new ResponseEntity<>(new JwtResponse(user, token, authenticate.getAuthorities()), HttpStatus.OK);
	}

	// Get a Member by his/her Id
	@GetMapping(value = "/profile/{id}")
	public Member getMemberById(@PathVariable long id) {
		return memberService.getMemberById(id);
	}

	// Get a Member by its username
	@GetMapping(value = "/profile/search/{mail}")
	public Member getMemberByUsername(@PathVariable String mail) {
		return memberService.getMemberByMail(mail);
	}

	// Get all Members
	@GetMapping(value = "/profile")
	public List<Member> getMembers() {
		return memberService.getMembers();
	}

	// Delete member
	@DeleteMapping(value = "/profile/{id}")
	public void deleteMemberById(@PathVariable long id) {
		Member CheckMember = this.service.getCurrentUser();
		if (CheckMember.getStatus() == Status.ADMINISTRATOR) {
			UserBySiteService.deleteAllUserByIdByIdUser(id);
			memberService.deleteById(id);
		}
	}

	// modify profile member attributes
	@PutMapping(value = "/profile/{id}")
	public Member updateMemberById(@PathVariable long id, @RequestBody UpdateMemberForm updateMemberForm) {
		return memberService.updateMemberProfile(id, updateMemberForm);
	}

	// modify profile password
	@PutMapping(value = "/profile/password/{id}")
	public Member updateMemberPassword(@PathVariable long id,
			@RequestBody UpdateMemberPasswordForm updateMemberPasswordForm) {
		Member CheckMember = this.service.getCurrentUser();
		return memberService.updatePassword(id, updateMemberPasswordForm, CheckMember);
	}

	// modidy member status
	@PutMapping(value = "/admin/members/{id}/status")
	public Member updateMemberStatus(@PathVariable long id,
			@RequestBody UpdateMemberStatusForm updateMemberStatusForm) {
		Member CheckMenber = this.service.getCurrentUser();
		return memberService.updateStatus(id, updateMemberStatusForm, CheckMenber);
	}

	@GetMapping("/member")
	public ResponseEntity<Member> getCurrentUser() {
		return new ResponseEntity<>(this.service.getCurrentUser(), HttpStatus.OK);
	}

	// ***************
	// ERROR MANAGEMENT
	// ***************

	private void constraintViolationCheck(Errors errors) {

		if (errors.hasErrors()) {
			List<ConstraintViolation<?>> violationsList = new ArrayList<>();
			for (ObjectError e : errors.getAllErrors()) {
				violationsList.add(e.unwrap(ConstraintViolation.class));
			}
			String exceptionMessage = "";
			for (ConstraintViolation<?> violation : violationsList) {
				if (violationsList.indexOf(violation) > 0) {
					exceptionMessage += " | ";
				}
				exceptionMessage += violation.getMessage();
			}
			throw new ConstraintViolationException(exceptionMessage);
		}
	}

}
