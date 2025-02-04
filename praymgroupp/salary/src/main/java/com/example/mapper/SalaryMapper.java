package com.example.mapper;

import com.example.dto.request.SalaryRequest;
import com.example.dto.response.SalaryResponse;
import com.example.dto.response.UserResponse;
import com.example.exceptions.ResourceNotFoundException;
import com.example.model.Comment;
import com.example.model.Salary;
import com.example.model.User;
import com.example.repository.CommentRepository;
import com.example.repository.SalaryRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SalaryMapper {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final SalaryRepository salaryRepository;
    private final CommentMapper commentMapper;

    public Salary toSalary (SalaryRequest request) {
        if (request == null) {
            return null;
        }

        Salary salary = new Salary();
        if (request.getId() != null) {
            salary.setId(request.getId());
        }
        salary.setOwner(userRepository.getById(request.getOwnerId()));
        salary.setSalary(request.getSalary());
        salary.setReportingMonth(request.getReportingMonth());
        salary.setPremiya(request.getPremiya());
        salary.setFobo(request.getFobo());
        salary.setMiratorg(request.getMiratorg());
        salary.setSmety(request.getSmety());
        salary.setLenta(request.getLenta());
        salary.setAvans(request.getAvans());
        salary.setZpPoKarte(request.getZpPoKarte());
        salary.setRentCar(request.getRentCar());
        salary.setRentPhone(request.getRentPhone());
        salary.setComment(new Comment(request.getCommentId(), request.getComment()));
        return salary;
    }

    public Salary toSalaryByAdmin (SalaryRequest request) {
        if (request == null) {
            return null;
        }

        Salary salary = new Salary();
        if (request.getId() != null) {
            salary.setId(request.getId());
        }
        salary.setOwner(userRepository.getById(request.getOwnerId()));
        salary.setSalary(request.getSalary());
        salary.setReportingMonth(request.getReportingMonth());
        salary.setPremiya(request.getPremiya());
        salary.setFobo(request.getFobo());
        salary.setMiratorg(request.getMiratorg());
        salary.setSmety(request.getSmety());
        salary.setLenta(request.getLenta());
        salary.setAvans(request.getAvans());

        Salary salary1 = salaryRepository.findById(request.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        salary.setZpPoKarte(salary1.getZpPoKarte());
        salary.setRentCar(salary1.getRentCar());
        salary.setRentPhone(salary1.getRentPhone());

        salary.setComment(new Comment(request.getCommentId(), request.getComment()));
        return salary;
    }

    public SalaryResponse toResponse(Salary salary) {
        if (salary == null) {
            return null;
        }

        SalaryResponse.SalaryResponseBuilder salaryResponse = SalaryResponse.builder();
        salaryResponse.id(salary.getId());
        salaryResponse.owner(userMapper.toResponse(salary.getOwner()));
        salaryResponse.reportingMonth(salary.getReportingMonth());
        salaryResponse.salary(salary.getSalary());
        salaryResponse.premiya(salary.getPremiya());
        salaryResponse.fobo(salary.getFobo());
        salaryResponse.miratorg(salary.getMiratorg());
        salaryResponse.smety(salary.getSmety());
        salaryResponse.lenta(salary.getLenta());
        salaryResponse.avans(salary.getAvans());
        salaryResponse.zpPoKarte(salary.getZpPoKarte());
        salaryResponse.rentCar(salary.getRentCar());
        salaryResponse.rentPhone(salary.getRentPhone());
        salaryResponse.itog(0.00);
        UserResponse userResponse = userMapper.toResponse(salary.getOwner().getOwner());
        salaryResponse.responsible(userResponse);

        salaryResponse.comment(commentMapper.toResponse(salary.getComment()));
        return salaryResponse.build();
    }

    public List<SalaryResponse> toResponseCollection(List<Salary> salaries) {
        if (salaries == null) {
            return null;
        }
        List<SalaryResponse> list = new ArrayList<>(salaries.size());

        for (Salary salary : salaries) {
            list.add(toResponse(salary));
        }

        return list;
    }

    public List<SalaryResponse> toResponseCollectionByAdmin(List<Salary> salaries) {
        if (salaries == null) {
            return null;
        }
        List<SalaryResponse> list = new ArrayList<>(salaries.size());

        for (Salary salary : salaries) {
            SalaryResponse response = toResponse(salary);
            response.setItog(salary.getSalary() + salary.getPremiya() + salary.getFobo()
                    + salary.getMiratorg() + salary.getSmety() + salary.getLenta() - salary.getZpPoKarte());
            list.add(response);
        }

        return list;
    }

    public List<SalaryResponse> toResponseCollectionBySuperAdmin(List<Salary> salaries) {
        if (salaries == null) {
            return null;
        }
        List<SalaryResponse> list = new ArrayList<>(salaries.size());

        for (Salary salary : salaries) {
            SalaryResponse response = toResponse(salary);

            Double itog = salary.getSalary() + salary.getPremiya() + salary.getFobo()
                    + salary.getMiratorg() + salary.getSmety() + salary.getLenta() - salary.getAvans()
                    - salary.getZpPoKarte() - salary.getRentCar() - salary.getRentPhone();

            switch(response.getOwner().getCategory()) {
                case "Самозанятый": itog = itog + (itog / 100 * 6.5); break;
                case "Ип": itog = itog + (itog / 100 * 8); break;
            }

            response.setItog(itog);

            list.add(response);
        }

        return list;
    }

    public List<Salary> toSalaryCollection(List<SalaryRequest> requests) {
        if (requests == null) {
            return null;
        }
        List<Salary> list = new ArrayList<Salary>(requests.size());

        for (SalaryRequest request : requests) {
            list.add(toSalary(request));
        }

        return list;
    }

    public List<Salary> toSalaryCollectionByAdmin(List<SalaryRequest> requests) {
        if (requests == null) {
            return null;
        }
        List<Salary> list = new ArrayList<Salary>(requests.size());

        for (SalaryRequest request : requests) {
            list.add(toSalaryByAdmin(request));
        }

        return list;
    }
}
