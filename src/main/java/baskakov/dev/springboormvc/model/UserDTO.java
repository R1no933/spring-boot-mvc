package baskakov.dev.springboormvc.model;

import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    @Null
    private Long id;

    @NotBlank
    @Size(min = 1, max = 30)
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Min(1)
    @Max(100)
    private Integer age;

    private List<PetDTO> pets;

    public UserDTO(Long id, String name, String email, Integer age, List<PetDTO> pets) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.pets = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<PetDTO> getPets() {
        return pets;
    }

    public void setPets(List<PetDTO> pets) {
        this.pets = pets;
    }
}
