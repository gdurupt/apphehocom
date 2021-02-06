package com.hehocom.hehocom.services;

import java.util.List;

import com.hehocom.hehocom.entities.member.Member;
import com.hehocom.hehocom.exception.MemberNotFoundException;
import com.hehocom.hehocom.request.member.CreateMemberForm;
import com.hehocom.hehocom.request.member.UpdateMemberForm;
import com.hehocom.hehocom.request.member.UpdateMemberPasswordForm;
import com.hehocom.hehocom.request.member.UpdateMemberStatusForm;

public interface MemberService {

	public Member registerMember(CreateMemberForm createMemberForm);

	public Member updatePassword(long id, UpdateMemberPasswordForm updateMemberPasswordForm);

	public Member updateMemberProfile(long id, UpdateMemberForm updateMemberForm);

	public Member switchAccountStatus(long id);

	public Member updateStatus(long id, UpdateMemberStatusForm updateMemberStatusForm, Member checkMenber);

	public Member getMemberById(long id) throws MemberNotFoundException;

	public Member getMemberByUserName(String username);

	public List<Member> getMembers() throws MemberNotFoundException;

	public void deleteById(long id);

	public Member getMemberByEmail(String email);

}