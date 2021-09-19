package br.com.sicredi.assembly.core.validate;

import br.com.sicredi.assembly.membership.business.MembershipBusiness;
import br.com.sicredi.assembly.membership.entity.MembershipEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CpfValidator {

    private static final List<Character> auxiliarArray = new ArrayList<>();

    public static String isValid(String inputCpf) throws Exception {

        int remainder = 0, total = 0;
        String digit = "";
        String cpf = new StringBuilder(inputCpf).reverse().toString();
        char[] array = cpf.toCharArray();

        for (Character c : array)
            auxiliarArray.add(c);

        auxiliarArray.remove(0);
        auxiliarArray.remove(0);

        for (int j = 0; j <= 1; j++) {
            total = iteration(j);
            Collections.reverse(auxiliarArray);
            remainder = 11 - (total % 11);

            if (remainder == 10 || remainder == 11) remainder = 0;

            digit = remainder + "";
            auxiliarArray.add(digit.charAt(0));
            Collections.reverse(auxiliarArray);
        }
        return new StringBuilder(buildValidatedCpf()).reverse().toString();
    }

    private static String buildValidatedCpf() {
        StringBuilder cpf = new StringBuilder();
        for (Character c : CpfValidator.auxiliarArray) cpf.append(c);
        return cpf.toString();
    }

    private static int iteration(int externalIndex) {
        int index = 8 + externalIndex, total = 0, max = 10 + externalIndex - CpfValidator.auxiliarArray.size();
        for (int i = 10 + externalIndex; i > max; ) {
            if (Character.isDigit(CpfValidator.auxiliarArray.get(index)))
                total += Integer.parseInt(CpfValidator.auxiliarArray.get(index) + "") * i;
            i--;
            index--;
        }
        return total;
    }

    public static boolean checkIfCpfHasAlreadyBeenUsed(List<MembershipEntity> membershipEntities, String cpf) {
       return membershipEntities.stream()
                .map(membershipEntity -> membershipEntity.getCpf().equalsIgnoreCase(cpf))
                .findFirst().get();

    }
}