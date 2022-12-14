package model.filter;

import lombok.Builder;

@Builder
public record CustomerFilter(String firstname,
                             String email,
                             Integer limit,
                             Integer offset) {
}