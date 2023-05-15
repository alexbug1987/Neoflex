package ru.neoflex.vacationpaycalculator.controller;

import io.swagger.annotations.Api;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.vacationpaycalculator.dto.VacationPayResponse;
import ru.neoflex.vacationpaycalculator.service.days.DaysCalculationService;
import ru.neoflex.vacationpaycalculator.service.vacation.VacationPayCalculatorService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Api(tags = {"Vacation pay calculator part"})
@RestController
@Validated
public class VacationPayCalculatorController {

    private final VacationPayCalculatorService vacationPayCalculatorService;
    private final DaysCalculationService daysCalculationService;

    public VacationPayCalculatorController(VacationPayCalculatorService vacationPayCalculatorService,
                                           DaysCalculationService daysCalculationService) {
        this.vacationPayCalculatorService = vacationPayCalculatorService;
        this.daysCalculationService = daysCalculationService;
    }

    @GetMapping("/calculacte")
    public VacationPayResponse getVacationPay(
            @RequestParam("averageSalary") BigDecimal averageSalaryPerYear,
            @RequestParam("vacationDays") int vacationDays,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> startVacationDate
    ) {
        if (startVacationDate.isPresent()) {
            vacationDays = daysCalculationService.calculatePaidDays(vacationDays, startVacationDate.get());
        }
        return vacationPayCalculatorService.getVacationPayCalculation(averageSalaryPerYear, vacationDays);
    }
}
