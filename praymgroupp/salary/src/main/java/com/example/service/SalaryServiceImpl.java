package com.example.service;

import com.example.dto.request.SalaryRequest;
import com.example.dto.request.SalarySearchFilter;
import com.example.dto.response.SalaryResponse;
import com.example.exceptions.ConflictServerError;
import com.example.exceptions.ResourceNotFoundException;
import com.example.mapper.SalaryMapper;
import com.example.model.Comment;
import com.example.model.Salary;
import com.example.model.User;
import com.example.repository.SalaryRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private final SalaryMapper salaryMapper;
    private final SalaryRepository salaryRepository;
    private final UserRepository userRepository;
    private final CommentService commentService;

    private static final Sort SORT_ID_ASC = Sort.by(Sort.Direction.ASC, "id");
    Pageable page = PageRequest.of(0 > 0 ? 0 / 10000 : 0, 10000, SORT_ID_ASC);

    @Override
    public Salary createToUser(User user) {
        Salary salary = new Salary();
        salary.setOwner(user);
        salary.setReportingMonth(LocalDate.now());
        salary.setSalary(0.00);
        salary.setPremiya(0.00);
        salary.setFobo(0.00);
        salary.setMiratorg(0.00);
        salary.setSmety(0.00);
        salary.setLenta(0.00);
        salary.setAvans(0.00);
        salary.setZpPoKarte(0.00);
        salary.setRentCar(0.00);
        salary.setRentPhone(0.00);
        salary.setComment(commentService.create(new Comment(null, " ")));

        return salary;
    }

    @Override
    public List<SalaryResponse> updateAllSalaryByAdmin(Long initiatorId, List<SalaryRequest> requests) {
        User initiator = userRepository.findById(initiatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        if (!initiator.getRole().equals("ADMIN")) {
            throw new ConflictServerError("Доступ запрещен");
        }

        List<Salary> salaries = salaryMapper.toSalaryCollectionByAdmin(requests);
        return updateAllSalary(salaries);
    }

    @Override
    public List<SalaryResponse> updateAllSalaryBySuperAdmin(Long initiatorId, List<SalaryRequest> requests) {
        User initiator = userRepository.findById(initiatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        if (!initiator.getRole().equals("SUPERADMIN")) {
            throw new ConflictServerError("Доступ запрещен");
        }

        List<Salary> salaries = salaryMapper.toSalaryCollection(requests);
        return updateAllSalary(salaries);
    }

    @Override
    public List<SalaryResponse> getAllBySuperAdmin(Long initiatorId,
                                                   String month,
                                                   String year,
                                                   String responsible,
                                                   String city,
                                                   String formaoplaty,
                                                   String companyName,
                                                   String dismissed) {

        return getAllSalary(initiatorId,
                month,
                year,
                responsible,
                city,
                formaoplaty,
                companyName,
                dismissed);
    }

    @Override
    public List<SalaryResponse> getAllSalarysByAdmin(Long initiatorId, String city, String formaoplaty) {

        User user = userRepository.findById(initiatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        return getAllSalary(initiatorId,
                null,
                null,
                user.getLastName(),
                city,
                formaoplaty,
                null,
                null);
    }

    private List<SalaryResponse> getAllSalary (Long initiatorId,
                                               String month,
                                               String year,
                                               String responsible,
                                               String city,
                                               String formaoplaty,
                                               String companyName,
                                               String dismissed) {

        User user = userRepository.findById(initiatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        if (user.getRole().equals("SUPERADMIN") || user.getRole().equals("ADMIN")) {
        } else {
            throw new ConflictServerError("Доступ запрещен");
        }

        SalarySearchFilter salarySearchFilter = new SalarySearchFilter();
        LocalDate localDate = LocalDate.now();
        Boolean isByMonth = true;


        if (month != null && year != null) {
            if (!month.equals("monthAll") && !year.equals("yearAll")) {
                localDate = localDate.withMonth(Integer.parseInt(month));
                localDate = localDate.withYear(Integer.parseInt(year));
            } else if (month.equals("monthAll") && !year.equals("yearAll")) {
                localDate = localDate.withYear(Integer.parseInt(year));
                isByMonth = false;
            } else if (!month.equals("monthAll") && year.equals("yearAll")) {
                localDate = localDate.withMonth(Integer.parseInt(month));
            } else if (month.equals("monthAll") && year.equals("yearAll")) {
                localDate = null;
            }
        } else if (month != null && year == null) {
            if (month.equals("monthAll")) {
                isByMonth = false;
            } else {
                localDate = localDate.withMonth(Integer.parseInt(month));
            }
        } else if (month == null && year != null) {
            if (year.equals("yearAll")) {
                localDate = null;
            } else {
                localDate = localDate.withYear(Integer.parseInt(year));
                isByMonth = false;
            }
        }

        if (localDate != null) salarySearchFilter.setLocalDate(localDate);
        if (responsible != null && !responsible.equals("Ответственный"))
            salarySearchFilter.setResponsible(responsible);
        if (city != null) salarySearchFilter.setCity(city);
        if (formaoplaty != null) salarySearchFilter.setFormaoplaty(formaoplaty);
        if (companyName != null) salarySearchFilter.setCompanyName(companyName);
        if (dismissed != null) {
            if (dismissed.equals("true")) salarySearchFilter.setDismissed(true);
        } else {
            salarySearchFilter.setDismissed(false);
        }

        List<Specification<Salary>> specifications = searchFilterToSpecifications(isByMonth, salarySearchFilter);

        Page<Salary> salaryPage = salaryRepository.findAll(
                specifications.stream().reduce(Specification::and).orElse(null), page
        );

        if (!salaryPage.hasContent()) {
            return getForTheCurrentMonth(initiatorId);
        }

        List<Salary> salaries = salaryPage.getContent();

        List<SalaryResponse> responses;

        if (user.getRole().equals("SUPERADMIN")) {
            responses = salaryMapper.toResponseCollectionBySuperAdmin(salaries);
        } else {
            responses = salaryMapper.toResponseCollectionByAdmin(salaries);
        }
        return responses;
    }

    private List<SalaryResponse> getForTheCurrentMonth(Long requesterId) {

        User requester = userRepository.findById(requesterId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        if(requester.getRole().equals("ADMIN") || requester.getRole().equals("SUPERADMIN")) {

        } else {
            throw new ConflictServerError("Нет прав");
        }

        SalarySearchFilter salarySearchFilter = new SalarySearchFilter();
        LocalDate localDate = LocalDate.now();
        salarySearchFilter.setLocalDate(localDate);

        if (requester.getRole().equals("ADMIN")) {
            salarySearchFilter.setResponsible(requester.getLastName());
        }

        List<Specification<Salary>> specifications = searchFilterToSpecifications(true, salarySearchFilter);

        Page<Salary> salaryPage = salaryRepository.findAll(
                specifications.stream().reduce(Specification::and).orElse(null), page
        );

        List<Salary> salaries = new ArrayList<>();

        if (!salaryPage.hasContent()) {
            List<User> users = userRepository.findByDismissedAndRoleNot(false, "SUPERADMIN");

            for (User user1 : users) {
                salaries.add(createToUser(user1));
            }

            salaryRepository.saveAll(salaries);
        }

        if (requester.getRole().equals("ADMIN")) {
            salaries.stream()
                    .filter(salary -> requester.getLastName().equals(salary.getOwner().getOwner().getLastName()))
                    .toList();
        }

        return salaryMapper.toResponseCollection(salaries);
    }

    private List<SalaryResponse> updateAllSalary (List<Salary> salaries) {

        List<Comment> comments = new ArrayList<>();
        for (Salary salary : salaries) {
            comments.add(salary.getComment());
        }

        commentService.saveAll(comments);
        return salaryMapper.toResponseCollection(salaryRepository.saveAll(salaries));
    }

    private List<Specification<Salary>> searchFilterToSpecifications(Boolean isByMonth, SalarySearchFilter salarySearchFilter) {
        List<Specification<Salary>> specifications = new ArrayList<>();
        if (salarySearchFilter.getLocalDate() != null) {
            if (isByMonth) {
                specifications.add(salaryDateMontAndYear(salarySearchFilter.getLocalDate()));
            } else {
                specifications.add(salaryByYear(salarySearchFilter.getLocalDate()));
            }
        }
        if (salarySearchFilter.getResponsible() != null) {
            specifications.add(responsible(List.of(salarySearchFilter.getResponsible())));
        }
        if (salarySearchFilter.getCity() != null) {
            specifications.add(city(List.of(salarySearchFilter.getCity())));
        }
        if (salarySearchFilter.getFormaoplaty() != null) {
            specifications.add(formaOplaty(List.of(salarySearchFilter.getFormaoplaty())));
        }
        if (salarySearchFilter.getCompanyName() != null) {
            specifications.add(companyName(List.of(salarySearchFilter.getCompanyName())));
        }
        if (salarySearchFilter.getDismissed() != null) {
            specifications.add(dismissed(salarySearchFilter.getDismissed()));
        }
        return specifications.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    private Specification<Salary> salaryDateMontAndYear(LocalDate values) {
        return (root, query, criteriaBuilder) -> {
            // Устанавливаем день в 1, чтобы игнорировать его при сравнении
            LocalDate startOfMonth = values.withDayOfMonth(1);

            // Создаем условие для проверки, что месяц и год совпадают
            return criteriaBuilder.and(
                    criteriaBuilder.greaterThanOrEqualTo(root.get("reportingMonth"), startOfMonth),
                    criteriaBuilder.lessThan(root.get("reportingMonth"), startOfMonth.plusMonths(1))
            );
        };
    }

    private Specification<Salary> salaryByYear(LocalDate values) {
        return (root, query, criteriaBuilder) -> {
            // Устанавливаем месяц и день в 1, чтобы игнорировать их при сравнении
            LocalDate startOfYear = values.withMonth(1).withDayOfMonth(1);

            // Создаем условие для проверки, что год совпадает
            return criteriaBuilder.and(
                    criteriaBuilder.greaterThanOrEqualTo(root.get("reportingMonth"), startOfYear),
                    criteriaBuilder.lessThan(root.get("reportingMonth"), startOfYear.plusYears(1))
            );
        };
    }

    private Specification<Salary> responsible(List<String> values) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .in(root.get("owner").get("owner").get("lastName")).value(values);
    }

    private Specification<Salary> city(List<String> values) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .in(root.get("owner").get("city")).value(values);
    }

    private Specification<Salary> formaOplaty(List<String> values) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .in(root.get("owner").get("category")).value(values);
    }

    private Specification<Salary> companyName(List<String> values) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .in(root.get("owner").get("companyName")).value(values);
    }

    private Specification<Salary> dismissed(Boolean dismissed) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .in(root.get("owner").get("dismissed")).value(dismissed);
    }
}
