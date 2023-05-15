package ru.neoflex.vacationpaycalculator.service.vacation;

import ru.neoflex.vacationpaycalculator.dto.VacationPayResponse;

import java.math.BigDecimal;

public interface VacationPayCalculatorService {

    VacationPayResponse getVacationPayCalculation(BigDecimal averageSalaryPerYear,
                                                  int vacationDays);
}
