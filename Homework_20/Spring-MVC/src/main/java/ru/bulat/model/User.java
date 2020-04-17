package ru.bulat.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "tuser", schema = "public")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "")
    private String email;

    @Size(min = 6)
    private String password;

    @Size(min = 6)
    private String passwordRepeat;

    @NotBlank(message = "Telephone number is required")
    @Column(name = "telephone_number")
    private String telephone_number;

    @NotBlank(message = "DateOfBirth is required")
    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "About myself is required")
    @Column(name = "about_myself")
    private String aboutMyself;

    @Column(name = "agree")
    private boolean check;
}

