package app.cloudkitchen.menuservice.dto;

import java.util.List;

public record PagedResponseDTO<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean last
) {
    public static <T> PagedResponseDTO<T> of(List<T> content, int page, int size, long totalElements, int totalPages, boolean last) {
        return new PagedResponseDTO<>(content, page, size, totalElements, totalPages, last);
    }
}