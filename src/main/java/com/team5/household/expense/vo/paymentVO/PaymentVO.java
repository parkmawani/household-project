package com.team5.household.expense.vo.paymentVO;



import com.team5.household.expense.entity.PaymentInfoEntity;

import lombok.Data;

@Data
public class PaymentVO {
    private Integer paymentType;
    private String paymentName;
    

    // public PaymentVO(PaymentInfoEntity data){
    //     this.paymentName = data.getPiName();
    //     this.paymentType = data.getPiType();
    // }
}
