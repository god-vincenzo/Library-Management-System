package com.library.service;

import com.library.model.Member;
import com.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    public Member addMember(Member member) {
        if (member.getMemberId() == null || member.getMemberId().isEmpty()) {
            member.setMemberId("MEM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        }
        if (memberRepository.existsByMemberId(member.getMemberId())) {
            throw new RuntimeException("Member ID already exists");
        }
        return memberRepository.save(member);
    }
    
    public Member updateMember(Long id, Member memberDetails) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Member not found"));
        member.setName(memberDetails.getName());
        member.setEmail(memberDetails.getEmail());
        member.setPhone(memberDetails.getPhone());
        member.setAddress(memberDetails.getAddress());
        member.setStatus(memberDetails.getStatus());
        return memberRepository.save(member);
    }
    
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
    
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    
    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }
    
    public Optional<Member> getMemberByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }
    
    public List<Member> searchMembers(String query) {
        return memberRepository.searchMembers(query);
    }
    
    public List<Member> getActiveMembers() {
        return memberRepository.findByStatus(Member.Status.ACTIVE);
    }
}

