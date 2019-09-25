package com.main.model;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Teachers")
public class Teachers {

	@Id
    private String id;
    @NotEmpty
    private String fullname;
    @NotEmpty
    private String gender;
    @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate birthdate;
    @NotEmpty
    private String typedoc;
    @NotEmpty
    private String document;
}
