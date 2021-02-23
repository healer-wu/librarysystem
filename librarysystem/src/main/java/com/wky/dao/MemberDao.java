//package com.wky.dao;
//
//import java.util.List;
//
//import com.wky.entity.Member;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Query;
//
//
//public interface MemberDao extends JpaRepository<Member,Integer>,JpaSpecificationExecutor< Member> {
//
//	@Query(value="select * from t_member where id = ?1",nativeQuery = true)
//	public Member  findId(Integer id);
//
//	public Member findByName(String name);
//
//
//}
