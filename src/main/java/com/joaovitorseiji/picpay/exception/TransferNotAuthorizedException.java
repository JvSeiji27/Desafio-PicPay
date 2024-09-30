package com.joaovitorseiji.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAuthorizedException extends PicPayException{
	@Override
	public ProblemDetail toProblemDetail() {
		var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		
		pb.setTitle("Transfer not authorized");
		pb.setDetail("Authorization service not authorized this transfer");
		return pb;
	}
}
