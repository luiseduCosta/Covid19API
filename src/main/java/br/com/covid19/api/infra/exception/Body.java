package br.com.covid19.api.infra.exception;

import lombok.Getter;

@Getter
public class Body {
	private String msg;
	
	Body(String msg) {
		this.msg = msg;
	}
}
