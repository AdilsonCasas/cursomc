package com.nelioalves.cursomc;

public enum enumErroPadrao {
	
	ERRO_PADRAO_0001(  1, "", ""),
	ERRO_PADRAO_0002(  2, "", ""),
	ERRO_PADRAO_0003(  3, "", ""),
	ERRO_PADRAO_0004(  4, "", ""),
	ERRO_PADRAO_0005(  5, "", ""),
	ERRO_PADRAO_0006(  6, "", ""),
	ERRO_PADRAO_0007(  7, "", ""),
	ERRO_PADRAO_0008(  8, "", ""),
	ERRO_PADRAO_0009(  9, "", ""),
	ERRO_PADRAO_0010( 10, "", ""),
	ERRO_PADRAO_0011( 11, "", ""),
	ERRO_PADRAO_0012( 12, "", ""),
	ERRO_PADRAO_0013( 13, "", ""),
	ERRO_PADRAO_0014( 14, "", ""),
	ERRO_PADRAO_0015( 15, "", ""),
	ERRO_PADRAO_0016( 16, "", ""),
	ERRO_PADRAO_0017( 17, "", ""),
	ERRO_PADRAO_0018( 18, "", ""),
	ERRO_PADRAO_0019( 19, "", ""),
	ERRO_PADRAO_0020( 20, "", ""),
	ERRO_PADRAO_0021( 21, "", ""),
	ERRO_PADRAO_0022( 22, "", ""),
	ERRO_PADRAO_0023( 23, "", ""),
	ERRO_PADRAO_0024( 24, "", ""),
	ERRO_PADRAO_0025( 25, "", ""),
	ERRO_PADRAO_0026( 26, "", ""),
	ERRO_PADRAO_0027( 27, "", ""),
	ERRO_PADRAO_0028( 28, "", ""),
	ERRO_PADRAO_0029( 29, "", ""),
	ERRO_PADRAO_0030( 30, "", ""),
	ERRO_PADRAO_0031( 31, "", ""),
	ERRO_PADRAO_0032( 32, "", ""),
	ERRO_PADRAO_0033( 33, "", ""),
	ERRO_PADRAO_0034( 34, "", ""),
	ERRO_PADRAO_0035( 35, "", ""),
	ERRO_PADRAO_0036( 36, "", ""),
	ERRO_PADRAO_0037( 37, "", ""),
	ERRO_PADRAO_0038( 38, "", ""),
	ERRO_PADRAO_0039( 39, "", ""),
	ERRO_PADRAO_0040( 40, "", ""),
	ERRO_PADRAO_0041( 41, "", ""),
	ERRO_PADRAO_0042( 42, "", ""),
	ERRO_PADRAO_0043( 43, "", ""),
	ERRO_PADRAO_0044( 44, "", ""),
	ERRO_PADRAO_0045( 45, "", ""),
	ERRO_PADRAO_0046( 46, "", ""),
	ERRO_PADRAO_0047( 47, "", ""),
	ERRO_PADRAO_0048( 48, "", ""),
	ERRO_PADRAO_0049( 49, "", ""),
	ERRO_PADRAO_0050( 50, "", ""),
	ERRO_PADRAO_0051( 51, "", ""),
	ERRO_PADRAO_0052( 52, "", ""),
	ERRO_PADRAO_0053( 53, "", ""),
	ERRO_PADRAO_0054( 54, "", ""),
	ERRO_PADRAO_0055( 55, "", ""),
	ERRO_PADRAO_0056( 56, "", ""),
	ERRO_PADRAO_0057( 57, "", ""),
	ERRO_PADRAO_0058( 58, "", ""),
	ERRO_PADRAO_0059( 59, "", ""),
	ERRO_PADRAO_0060( 60, "", ""),
	ERRO_PADRAO_0061( 61, "", ""),
	ERRO_PADRAO_0062( 62, "", ""),
	ERRO_PADRAO_0063( 63, "", ""),
	ERRO_PADRAO_0064( 64, "", ""),
	ERRO_PADRAO_0065( 65, "", ""),
	ERRO_PADRAO_0066( 66, "", ""),
	ERRO_PADRAO_0067( 67, "", ""),
	ERRO_PADRAO_0068( 68, "", ""),
	ERRO_PADRAO_0069( 69, "", ""),
	ERRO_PADRAO_0070( 70, "", ""),
	ERRO_PADRAO_0071( 71, "", ""),
	ERRO_PADRAO_0072( 72, "", ""),
	ERRO_PADRAO_0073( 73, "", ""),
	ERRO_PADRAO_0074( 74, "", ""),
	ERRO_PADRAO_0075( 75, "", ""),
	ERRO_PADRAO_0076( 76, "", ""),
	ERRO_PADRAO_0077( 77, "", ""),
	ERRO_PADRAO_0078( 78, "", ""),
	ERRO_PADRAO_0079( 79, "", ""),
	ERRO_PADRAO_0080( 80, "", ""),
	ERRO_PADRAO_0081( 81, "", ""),
	ERRO_PADRAO_0082( 82, "", ""),
	ERRO_PADRAO_0083( 83, "", ""),
	ERRO_PADRAO_0084( 84, "", ""),
	ERRO_PADRAO_0085( 85, "", ""),
	ERRO_PADRAO_0086( 86, "", ""),
	ERRO_PADRAO_0087( 87, "", ""),
	ERRO_PADRAO_0088( 88, "", ""),
	ERRO_PADRAO_0089( 89, "", ""),
	ERRO_PADRAO_0090( 90, "", ""),
	ERRO_PADRAO_0091( 91, "", ""),
	ERRO_PADRAO_0092( 92, "", ""),
	ERRO_PADRAO_0093( 93, "", ""),
	ERRO_PADRAO_0094( 94, "", ""),
	ERRO_PADRAO_0095( 95, "", ""),
	ERRO_PADRAO_0096( 96, "", ""),
	ERRO_PADRAO_0097( 97, "", ""),
	ERRO_PADRAO_0098( 98, "", ""),
	ERRO_PADRAO_0099( 99, "", ""),
	ERRO_PADRAO_0100(100, "", ""),

	ERRO_PADRAO_9999(9999, "", "");

	private int cod;
	private String MsgPadraoParaErro;
	private String MsgComplementar;

	private enumErroPadrao(int var_cod, String var_MsgPadraoParaErro, String var_MsgComplementar) {
		this.cod = var_cod;
		this.MsgPadraoParaErro = var_MsgPadraoParaErro; // Mensagem do erro a ser apresentada ao cliente/usuário do sistema.
		this.MsgComplementar = var_MsgComplementar;
	}
	
	
	public int getCod() {
		return this.cod;
	}


	public void setCod(int var_cod) {
		this.cod = var_cod;
	}


	public String getMsgPadraoParaErro() {
		return this.MsgPadraoParaErro;
	}


	public void setMsgPadraoParaErro(String var_MsgPadraoParaErro) {
		this.MsgPadraoParaErro = var_MsgPadraoParaErro;
	}


	public String getMsgComplementar() {
		return this.MsgComplementar;
	}


	public void setMsgComplementar(String var_MsgComplementar) {
		this.MsgComplementar = var_MsgComplementar;
	}

	public static enumErroPadrao toEnum(Integer var_cod) {
		if(var_cod == null) {
			return null;
		}
		for(enumErroPadrao x: enumErroPadrao.values()) {
			if(var_cod == x.getCod()) {
				return x;
			}
		}
		throw new IllegalArgumentException("ERRO_PADRAO#0020@IllegalArgumentException (enumErroPadrao): "+var_cod);
	}
}
