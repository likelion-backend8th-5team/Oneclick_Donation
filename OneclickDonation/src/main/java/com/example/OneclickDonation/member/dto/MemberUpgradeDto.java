package com.example.OneclickDonation.member.dto;

import com.example.OneclickDonation.member.entity.MemberUpgrade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 단체 사용자 전환 신청할 때 사용
public class MemberUpgradeDto {
    private String organization;  // 요청이 수락이 되면 이름이 단체 이름을 나오게 하기
    private Integer businessNumber;
    private String applicationReason;
    private Boolean approved; // 수락, 거절

    public static MemberUpgradeDto fromEntity(MemberUpgrade entity) {
        return MemberUpgradeDto.builder()
                .organization(entity.getOrganization())
                .businessNumber(entity.getBusinessNumber())
                .applicationReason(entity.getApplicationReason())
                .approved(entity.getApproved())
                .build();
    }
}
