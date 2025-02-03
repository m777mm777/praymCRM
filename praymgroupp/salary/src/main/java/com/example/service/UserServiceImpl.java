package com.example.service;

import com.example.dto.request.SalarySearchFilter;
import com.example.dto.request.UserRequest;
import com.example.dto.request.UserRequestUpdate;
import com.example.dto.response.SalaryResponse;
import com.example.dto.response.UserResponse;
import com.example.exceptions.ConflictServerError;
import com.example.exceptions.ResourceNotFoundException;
import com.example.mapper.UserMapper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SalaryService salaryService;
    private final SalaryRepository salaryRepository;

    private static final Sort SORT_ID_ASC = Sort.by(Sort.Direction.ASC, "id");
    Pageable page = PageRequest.of(0 > 0 ? 0 / 10000 : 0, 10000, SORT_ID_ASC);

    @Override
    public User findById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
    }

    @Override
    public User chekUser(Long phone, String password) {

        return userRepository.findByPhoneAndPassword(phone, password)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
    }

    @Override
    public List<UserResponse> getAll() {

        List<User> users = userRepository.findAll();

        return userMapper.toResponseCollection(users);
    }

    @Override
    public List<String> getAdmins(Long initiatorId) {
        User initiator = userRepository.findById(initiatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        if (!initiator.getRole().equals("SUPERADMIN")) {
            throw new ConflictServerError("Доступ запрещен");
        }

        List<String> lastNameAdmins = new ArrayList<>();
        List<String> role = new ArrayList<>();
        role.add("ADMIN");
        role.add("SUPERADMIN");
        List<User> admins = userRepository.findAllByRoleIn(role);

        if (!admins.isEmpty()) {
            for (User user : admins) {
                lastNameAdmins.add(user.getLastName());
            }
        }

        return lastNameAdmins;
    }

    @Override
    public List<UserResponse> updateAllUsersBySuperAdmin(Long initiatorId, List<UserRequestUpdate> request) {
        User user = userRepository.findById(initiatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        if (!user.getRole().equals("SUPERADMIN")) {
            throw new ConflictServerError("Доступ запрещен");
        }

        List<User> users = userMapper.toUserCollectionUpdate(request);

        return userMapper.toResponseCollection(userRepository.saveAll(users));
    }

    @Override
    public List<UserResponse> getAllUsersByAdmin(Long initiatorId,
                                                 String city,
                                                 String formaoplaty,
                                                 String dismissed) {

        User user = userRepository.findById(initiatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        if (!user.getRole().equals("ADMIN")) {
            throw new ConflictServerError("Доступ запрещен");
        }

        return getAllUsers(user.getLastName(),
                city,
                formaoplaty,
                dismissed);
    }

    @Override
    public List<UserResponse> getAllUsersBySuperAdmin(Long initiatorId,
                                                      String responsible,
                                                      String city,
                                                      String formaoplaty,
                                                      String dismissed) {

        User user = userRepository.findById(initiatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        if (!user.getRole().equals("SUPERADMIN")) {
            throw new ConflictServerError("Доступ запрещен");
        }

        return getAllUsers(responsible,
                city,
                formaoplaty,
                dismissed);
    }

    @Override
    public UserResponse crateUserByAdmin(Long initiatorId, UserRequest request) {
        User owner = userRepository.findById(initiatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        request.setPassword("password");
        request.setDismissed(false);
        request.setRole("USER");
        User user = userMapper.toUser(request);

        user.setOwner(owner);

        user = userRepository.save(user);
        salaryRepository.save(salaryService.createToUser(user));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse crateUserBySuperAdmin(Long initiatorId, UserRequest request) {
        userRepository.findById(initiatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь супер админ не найден"));

        User owner = userRepository.findByLastName(request.getOwnerLastName())
                .orElseThrow(() -> new ResourceNotFoundException("Ответственный не найден"));

        request.setPassword("password");
        request.setDismissed(false);
        request.setRole("USER");
        User user = userMapper.toUser(request);

        user.setOwner(owner);
        user = userRepository.save(user);
        salaryRepository.save(salaryService.createToUser(user));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse crateAdminBySuperAdmin(Long initiatorId, UserRequest request) {
        User owner = userRepository.findById(initiatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь супер админ не найден"));

        request.setDismissed(false);
        request.setRole("ADMIN");
        User user = userMapper.toUser(request);

        user.setOwner(owner);
        user = userRepository.save(user);
        salaryRepository.save(salaryService.createToUser(user));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse crateSuperAdminBySuperAdmin(Long initiatorId, UserRequest request) {

        if (initiatorId != 1919L) {
            throw new ConflictServerError("В доступе отказано");
        }

        request.setDismissed(false);
        request.setRole("SUPERADMIN");
        User user = userMapper.toUser(request);

        user = userRepository.save(user);

        return userMapper.toResponse(user);
    }

    private List<UserResponse> getAllUsers (String responsible,
                                               String city,
                                               String formaoplaty,
                                               String dismissed) {

        SalarySearchFilter salarySearchFilter = new SalarySearchFilter();


        if (responsible != null)
            salarySearchFilter.setResponsible(responsible);
        if (city != null) salarySearchFilter.setCity(city);
        if (formaoplaty != null) salarySearchFilter.setFormaoplaty(formaoplaty);
        if (dismissed != null) {
            if (dismissed.equals("true")) salarySearchFilter.setDismissed(true);
        } else {
            salarySearchFilter.setDismissed(false);
        }

        List<Specification<User>> specifications = searchFilterToSpecifications(salarySearchFilter);

        Page<User> usersPage = userRepository.findAll(
                specifications.stream().reduce(Specification::and).orElse(null), page
        );

        if (!usersPage.hasContent()) {
            return new ArrayList<>();
        }

        List<User> users = usersPage.getContent();

        List<User> filteredUsers = users.stream()
                .filter(i -> i.getOwner() != null)
                .collect(Collectors.toList());

        return userMapper.toResponseCollection(filteredUsers);
    }

    private List<Specification<User>> searchFilterToSpecifications(SalarySearchFilter salarySearchFilter) {
        List<Specification<User>> specifications = new ArrayList<>();

        if (salarySearchFilter.getResponsible() != null) {
            specifications.add(responsible(List.of(salarySearchFilter.getResponsible())));
        }
        if (salarySearchFilter.getCity() != null) {
            specifications.add(city(List.of(salarySearchFilter.getCity())));
        }
        if (salarySearchFilter.getFormaoplaty() != null) {
            specifications.add(formaOplaty(List.of(salarySearchFilter.getFormaoplaty())));
        }
        if (salarySearchFilter.getDismissed() != null) {
            specifications.add(dismissed(salarySearchFilter.getDismissed()));
        }
        return specifications.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    private Specification<User> responsible(List<String> values) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .in(root.get("owner").get("lastName")).value(values);
    }

    private Specification<User> city(List<String> values) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .in(root.get("city")).value(values);
    }

    private Specification<User> formaOplaty(List<String> values) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .in(root.get("category")).value(values);
    }

    private Specification<User> dismissed(Boolean dismissed) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .in(root.get("dismissed")).value(dismissed);
    }

}