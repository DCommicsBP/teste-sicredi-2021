package br.com.sicredi.assembly.core.validate;


import br.com.sicredi.assembly.core.exceptions.BadRequestException;

import java.time.LocalDateTime;

public abstract class DateValidator {
    public static void validateDatesAfterToday(LocalDateTime localDateTime) {
        if (localDateTime.isBefore(LocalDateTime.now().minusMinutes(2))) {
            throw new BadRequestException("Não é possível cadastrar uma data anterior ao dia de hoje. ");
        }
    }

    public static void validateEndDateBeforeStartDate(LocalDateTime initDate, LocalDateTime finishDate) {
        if (finishDate.isBefore(initDate)) {
            throw new BadRequestException("Não é possível cadastrar data final anterior a data inicial de pauta ou assembléia");
        }
    }

    public static void validateVotingDateBetweenTwoDates(LocalDateTime initDate, LocalDateTime finishDate, LocalDateTime dateTimeBetweenInitAndFinish, String message) {
        if (dateTimeBetweenInitAndFinish.isAfter(finishDate) ){
            throw new BadRequestException(message);
        }
        if(dateTimeBetweenInitAndFinish.isBefore(initDate)){
            throw new BadRequestException(message);
        }
    }

    public void validateStartDateAftereEndDate(LocalDateTime startDate, LocalDateTime endDate ){
        if(startDate.isAfter(endDate)){
            throw new BadRequestException("Data inicial posterior a data final.");
        }
    }

    public static void validateAgendaDate(LocalDateTime initDateAgenda, LocalDateTime finishDateAgenda, LocalDateTime initDateMeeting, LocalDateTime finishDateMeeting) {
        validateEndDateBeforeStartDate(initDateAgenda, finishDateAgenda);
        validateEndDateBeforeStartDate(initDateMeeting, initDateMeeting);
        validateVotingDateBetweenTwoDates(initDateMeeting, finishDateMeeting, initDateAgenda, "A data inicial da pauta não está contida entre os horários de início e detémino da assembléia. ");
        validateVotingDateBetweenTwoDates(initDateMeeting, finishDateMeeting, finishDateAgenda, "A data final da pauta não está contida entre os horários de início e detémino da assembléia. ");
    }
    public static LocalDateTime validateInitDate(LocalDateTime initDate){
        if(initDate == null || initDate.toString().isEmpty()){
            return LocalDateTime.now();
        }
        return initDate;
    }
}
