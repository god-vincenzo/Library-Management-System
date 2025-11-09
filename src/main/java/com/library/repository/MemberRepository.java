package com.library.repository;

import com.library.model.Member;
import com.library.model.Member.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String memberId);
    boolean existsByMemberId(String memberId);
    List<Member> findByNameContainingIgnoreCase(String name);
    List<Member> findByEmailContainingIgnoreCase(String email);
    List<Member> findByStatus(Status status);
    
    @Query("SELECT m FROM Member m WHERE " +
           "LOWER(m.memberId) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(m.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(m.email) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Member> searchMembers(@Param("query") String query);
}

