package com.rafael.cursomc.domain.enums;

public enum TipoCliente {
	PESSOAFISICA(1, "Pessoa Fisica"),
	PESSOAJURIDICA(2, "Pessoa Juridica");
	
	private int code;
	private String description;
	
	private TipoCliente(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public static TipoCliente toEnum(Integer code) {
		if(code==null) {
			return null;
		}
		
		for(TipoCliente x : TipoCliente.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: "+code);
	}
}
