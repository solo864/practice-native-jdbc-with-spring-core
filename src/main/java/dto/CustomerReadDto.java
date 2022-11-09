package dto;

import lombok.Builder;

@Builder
public record CustomerReadDto(String firstname,
                              String email) {
}
