package ru.neoflex.vacationpaycalculator.service.days;

import java.time.LocalDate;

public interface DaysCalculationService {

    int calculatePaidDays(int vacationDays,
                          LocalDate startVacationDate);
}
