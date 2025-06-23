package app.cloudkitchen.menuservice.util;

import app.cloudkitchen.menuservice.dto.PagedResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public final class PaginationUtil {

    private PaginationUtil() {
        // Private constructor to prevent instantiation
    }

    public static <T> PagedResponseDTO<T> fromPage(Page<T> page) {
        return new PagedResponseDTO<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }

    public static <T, R> PagedResponseDTO<R> fromPage(Page<T> page, Function<T, R> mapper) {
        List<R> content = page.getContent()
                .stream()
                .map(mapper)
                .toList();

        return new PagedResponseDTO<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}