package com.example.api_usuario.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.api_usuario.clients.AdministracionClient;
import com.example.api_usuario.clients.EvaluacionClient;
import com.example.api_usuario.dto.EvaluacionDTO;
import com.example.api_usuario.dto.PermisoDTO;
import com.example.api_usuario.dto.RolDTO;
import com.example.api_usuario.http.response.UserRolPermisosResponse;
import com.example.api_usuario.http.response.EvaluacionByUserResponse;
import com.example.api_usuario.models.entities.User;
import com.example.api_usuario.models.requests.UserCreate;
import com.example.api_usuario.models.requests.UserUpdate;
import com.example.api_usuario.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EvaluacionClient evaluacionClient;

    @Autowired
    private AdministracionClient administracionClient;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User register(UserCreate user){
        try {
            User newUser = new User();

            newUser.setCreatedAt(new Date());
            newUser.setIsActive(true);

            newUser.setName(user.getName());
            newUser.setLastName(user.getLastName());
            newUser.setSecondLastName(user.getSecondLastName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(passwordHashing(user.getPassword()));
            newUser.setPhone(user.getPhone());
            newUser.setCity(user.getCity());
            newUser.setRegion(user.getRegion());
            newUser.setCourseId(user.getCourseId());
            if (user.getRolId() != null) {
                newUser.setRolId(user.getRolId());
            }

            return userRepository.save(newUser);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario Registrado: ");
        }
    }

    public String passwordHashing(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    //para tener una prueba de hasheo de contraseña
    public boolean testPassword(String hash, String password){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            return passwordEncoder.matches(password, hash);
    }

    public User updateUser(UserUpdate body) {
        User user = userRepository.findById(body.getId()).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        if(body.getName() != null) {
            user.setName(body.getName());
        }

        if(body.getLastName() != null) {
            user.setLastName(body.getLastName());
        }

        if(body.getSecondLastName() != null) {
            user.setSecondLastName(body.getSecondLastName());
        }

        if(body.getPassword() != null) {
            user.setPassword(passwordHashing(body.getPassword()));
        }

        if(body.getPhone() != null) {
            user.setPhone(body.getPhone());
        }

        if(body.getCity() != null) {
            user.setCity(body.getCity());
        }

        if(body.getRegion() != null) {
            user.setRegion(body.getRegion());
        }
        
        if(body.getCourseId() != 0) {
            user.setCourseId(body.getCourseId());
        }

        if(body.getRolId() != null) {
            user.setRolId(body.getRolId());
        }

        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        userRepository.delete(user);
    }

    public List<User> getByCourse(int courseId) {
        return userRepository.findAllByCourseId(courseId);
    }


    public EvaluacionByUserResponse findEvaluacionByIdUsuario(int idUsuario) {
        User user = userRepository.findById(idUsuario).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        List<EvaluacionDTO> evaluacionDTOList = evaluacionClient.findAllEvaluationsByUser(idUsuario);

        return EvaluacionByUserResponse.builder()
            .name(user.getName())
            .lastName(user.getLastName())
            .secondLastName(user.getSecondLastName())
            .email(user.getEmail())
            .password(user.getPassword())
            .phone(user.getPhone())
            .city(user.getCity())
            .region(user.getRegion())
            .evaluacionDTOlist(evaluacionDTOList)
            .build();
    }

    public User assignRol(int userId, int rolId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        if (rolId > 0) {
            administracionClient.getRolById(rolId); // validar que el rol existe
        }
        user.setRolId(rolId > 0 ? rolId : null);
        return userRepository.save(user);
    }

    public User removeRol(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        user.setRolId(null);
        return userRepository.save(user);
    }

    public UserRolPermisosResponse getUserWithRolAndPermisos(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        RolDTO rol = null;
        List<PermisoDTO> permisos = List.of();
        if (user.getRolId() != null && user.getRolId() > 0) {
            rol = administracionClient.getRolById(user.getRolId());
            permisos = administracionClient.getPermisosByRolId(user.getRolId());
        }
        return UserRolPermisosResponse.builder()
            .user(user)
            .rol(rol)
            .permisos(permisos)
            .build();
    }
}
