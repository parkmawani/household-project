package com.team5.household.lchwork.entity;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "culture_category")
public class LchCultureCategoryEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "대분류 카테고리 번호", example = "2")
    @Column(name = "cc_seq") private Long ccSeq;
    @Schema(description = "대분류 카테고리 이름", example = "공연")
    @Column(name = "cc_name") private String ccName;
    @Schema(description = "회원 번호", example = "12")
    @Column(name = "cc_mi_seq") private Long ccMiSeq;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cdc_cc_seq") List<LchCultureDetailCategoryEntity> cdclist;
    // @OneToMany(mappedBy = "culture_category")
    // List<LchCultureDetailCategoryEntity> cdclist = new ArrayList<LchCultureDetailCategoryEntity>();
}
