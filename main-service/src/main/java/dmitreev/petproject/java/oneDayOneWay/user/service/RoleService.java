package dmitreev.petproject.java.oneDayOneWay.user.service;

import dmitreev.petproject.java.oneDayOneWay.user.model.Role;
import dmitreev.petproject.java.oneDayOneWay.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}