package com.example.OneclickDonation.member.dto;

import com.example.OneclickDonation.member.entity.Member;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private Long id;
    private String username;  // email
    private String password;
    private String profile;
    private String nickname;
    private String age;
    private String phone;
    private String organization;
    private Integer businessNumber;
    private Integer donationAmount;
    private String authorities;
    @Getter
    private Member member;

    public static CustomUserDetails fromEntity(Member entity) {
        return CustomUserDetails.builder()
                .member(entity)
                .build();
    }

    public String getRawAuthorities() {
        return this.authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities  = new ArrayList<>();
        if (this.authorities != null) {
            String[] rawAuthorities = authorities.split(",");
            for (String rawAuthority : rawAuthorities) {
                // SimpleGrantedAuthority:Spring Security 내부에서 보통 String 형식의 권한을 표현하는 방식
                grantedAuthorities.add(new SimpleGrantedAuthority(rawAuthority));
            }
        }
        return grantedAuthorities;
    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
