package com.team5.household.expense.api;

import com.team5.household.expense.repository.PaymentInfoRepository;
import com.team5.household.expense.service.PaymenrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.household.expense.vo.paymentVO.PaymentAddResponseVO;
import com.team5.household.expense.vo.paymentVO.PaymentAddVO;
import com.team5.household.expense.vo.paymentVO.PaymentDeleteResponseVO;
import com.team5.household.expense.vo.paymentVO.PaymentListVO;
import com.team5.household.expense.vo.paymentVO.PaymentResponseVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "결제수단정보", description = "결제수단 CRUD API")
@RestController
@RequestMapping("/api/payment")
public class PaymentAPIController {
    @Autowired
    PaymenrService pService;
    @Autowired
    PaymentInfoRepository pRepo;
    //결제 수단 등록
    @Operation(summary = "결제 수단 등록", description ="email로 결제수단을 등록합니다.")
    @PostMapping("/add/{email}")
    public ResponseEntity<PaymentAddResponseVO> postPaymentAdd(@Parameter(description = "") 
    @RequestBody PaymentAddVO data, @PathVariable String email){
        PaymentAddResponseVO response = pService.addPayment(email, data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    //결제수단 전체 조회
    @Operation(summary = "결제수단 리스트 전체조회", description = "결제수단을 모두 조회합니다.")
    @GetMapping("/listall")
    public ResponseEntity<PaymentListVO> getPaymentListAll(){
        PaymentListVO payList = pService.listall();
        return new ResponseEntity<>(payList, HttpStatus.CREATED);
    }
    @Operation(summary = "결제수단 타입별로 검색", description = "결제수단을 타입별로 검색합니다.(type:1.카드/2.계좌/3.현금)")
    @GetMapping("/list/{type}")
    public ResponseEntity<PaymentResponseVO> getPaymentList(
        @Parameter(description = "pathvariable로 데이터를 입력합니다.(type:1.카드/2.계좌/3.현금)")
        @PathVariable Integer type){
        PaymentResponseVO map = pService.checkPayment(type);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    //결제수단 삭제
    @Operation(summary = "결제 수단 삭제", description = "URL에 seq번호를 입력하여 데이터베이스 삭제합니다.")
    @DeleteMapping("/delete/{seq}")
    public ResponseEntity<PaymentDeleteResponseVO> deletePayment(
        @Parameter(description = "seq번호를 입력해서 삭제를 합니다.")
        @PathVariable Long seq){
            PaymentDeleteResponseVO response = pService.deletePayment(seq);
            return new ResponseEntity<>(response, HttpStatus.OK);

    }

}

