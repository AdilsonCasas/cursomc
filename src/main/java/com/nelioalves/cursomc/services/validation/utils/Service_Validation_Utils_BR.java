package com.nelioalves.cursomc.services.validation.utils;

// Crédido de fonte p/ Adriano Luis em 'https://gist.github.com/adrianoluis/5043397d378ae506d87366abb0ab4e30'
public class Service_Validation_Utils_BR {

	// CPF
	private static final int[] var_weightSsn = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	// CNPJ
	private static final int[] var_weightTin = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };


	private static int calculate(final String var_str, final int[] var_weight) {
		int sum = 0;
		for (int i = var_str.length() - 1, digit; i >= 0; i--) {
			digit = Integer.parseInt(var_str.substring(i, i + 1));
			sum += digit * var_weight[var_weight.length - var_str.length() + i];
		}
		sum = 11 - sum % 11;
		return sum > 9 ? 0 : sum;
	}

	/**
	 * Valida CPF
	 *
	 * @param ssn
	 * @return
	 */
	public static boolean isValidCPF(final String ssn) {
	//public static boolean isValidCPF(String ssn) {
		if ((ssn == null) || (ssn.length() != 11) || ssn.matches(ssn.charAt(0) + "{11}"))
			return false;

		final Integer digit1 = calculate(ssn.substring(0, 9), var_weightSsn);
		final Integer digit2 = calculate(ssn.substring(0, 9) + digit1, var_weightSsn);
		return ssn.equals(ssn.substring(0, 9) + digit1.toString() + digit2.toString());
	}

	/**
	 * Valida CNPJ
	 *
	 * @param tin
	 * @return
	 */
	public static boolean isValidCNPJ(final String tin) {
		if ((tin == null) || (tin.length() != 14) || tin.matches(tin.charAt(0) + "{14}"))
			return false;

		final Integer digit1 = calculate(tin.substring(0, 12), var_weightTin);
		final Integer digit2 = calculate(tin.substring(0, 12) + digit1, var_weightTin);
		return tin.equals(tin.substring(0, 12) + digit1.toString() + digit2.toString());
	}

}
