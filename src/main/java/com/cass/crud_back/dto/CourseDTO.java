package com.cass.crud_back.dto;

import com.cass.crud_back.enums.Category;
import com.cass.crud_back.enums.validation.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotNull @NotBlank @Length(min = 5, max = 40) String name,
        //status is not a data that should be exposed in a DTO
        @NotNull @Length(max = 10) @ValueOfEnum(enumClass = Category.class) String category,
        @NotNull @NotEmpty @Valid List<LessonDTO> lessons
) {
}
