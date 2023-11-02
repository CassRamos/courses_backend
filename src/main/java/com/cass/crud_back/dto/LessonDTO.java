package com.cass.crud_back.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record LessonDTO(
        Long id,
        @NotBlank @NotNull @Length(min = 4, max = 40) String name,
        @NotNull @NotBlank @Length(min = 10, max = 11) String youtubeUrl) {
}
