package com.team5.household.lchwork.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@DynamicInsert
@Table(name = "expense_history")
public class LchExpenseHistoryEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eh_seq") private Long ehSeq;
    @Column(name = "eh_title") private String ehTitle;
    @Column(name = "eh_content") private String ehContent;
    @ColumnDefault("current_timestamp")
    @Column(name = "eh_date") private LocalDateTime ehDate;
    // @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM")
    @Column(name = "eh_mi_seq") private Long ehMiSeq;
    @Column(name = "eh_pi_seq") private Long ehPiSeq;
    @Column(name = "eh_price") private Integer ehPrice;
    @Column(name = "eh_store_name") private String ehStoreName;
    @Column(name = "eh_img_file") private String ehImgFile;
    @Column(name = "eh_location") private String ehLocation;
    @Column(name = "eh_balance") private Integer ehBalance;
    @Column(name = "eh_cc_seq") private Long ehCcSeq;
    @Column(name = "eh_cdc_seq") private Long ehCdcSeq;
}
