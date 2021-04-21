package com.hehocom.hehocom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hehocom.hehocom.entities.member.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findByUsername(String username);

	Member findById(long id);

	Member findByEmail(String email);

	void deleteById(long id);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE members SET password = :password WHERE Id_Member = :id")
	void updatePassword(@Param(value = "id") long id, @Param(value = "password") String password);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE members SET username = :username, secondname = :secondname, tel = :tel WHERE Id_Member = :id")
	void updateMember(@Param(value = "id") long id, @Param(value = "username") String username,
			@Param(value = "secondname") String secondname, @Param(value = "tel") String tel);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE members SET status = :status WHERE Id_Member = :id")
	void updateStatus(@Param(value = "id") long id, @Param(value = "status") String status);

}
