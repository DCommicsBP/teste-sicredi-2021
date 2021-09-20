package br.com.sicredi.assembly.core.validate;

import br.com.sicredi.assembly.core.exceptions.BadRequestException;
import br.com.sicredi.assembly.membership.entity.MembershipEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class CpfValidator {


    public static boolean isValid(String inputCpf)  {
        String cpfWithoutDigits = inputCpf.substring(0, 9);
        String digits = inputCpf.substring(9,11);
        int calc1 =  result(cpfWithoutDigits);
        int firstDigit = calc1 == 10? 0: calc1;
        String newCpf = cpfWithoutDigits.concat(Integer.toString(firstDigit));
        int calc2 = result(newCpf.substring(1, newCpf.length()));
        int secondDigit = calc2 == 10? 0: calc2;

        return Integer.toString(firstDigit).concat(Integer.toString(secondDigit)).equalsIgnoreCase(digits);
    }

    public static int result(String inputCpf){
        List<String> characteres = new ArrayList<>(Arrays.asList(inputCpf.split("")));

        AtomicInteger counter = new AtomicInteger(10);
        AtomicInteger accomulate = new AtomicInteger(0);

        characteres.forEach(charactere-> {
            int number = Integer.parseInt(charactere);
            int acc = accomulate.get();
            accomulate.set(acc+ (counter.getAndDecrement() * number));
        });
        return accomulate.get() * 10 % 11;
    }

    public static String checkZerosAndOne(String cpf){
        if("00000000000".equalsIgnoreCase(cpf)){
            throw new BadRequestException("Você não pode inserir um cpf composto apenas por zeros");
        }
        if("11111111111".equalsIgnoreCase(cpf)){
            throw new BadRequestException("Você não pode inserir um cpf composto apenas pelo número um. ");
        }
        return  cpf.replace(".","").replace("-","").replace("/", "");
    }
    public static boolean checkIfCpfHasAlreadyBeenUsed(List<MembershipEntity> membershipEntities, String cpf) {
        AtomicBoolean flag = new AtomicBoolean(false);
         membershipEntities.stream()
                .filter(membershipEntity -> membershipEntity.getCpf().equalsIgnoreCase(cpf))
                .findFirst()
                .ifPresentOrElse(elemet->{ flag.set(true);}, ()->  flag.set(false));

         return flag.get();

    }
}