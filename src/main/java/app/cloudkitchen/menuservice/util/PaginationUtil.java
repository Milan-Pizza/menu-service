package app.cloudkitchen.menuservice.util;

import app.cloudkitchen.menuservice.dto.PagedResponse;
import org.springframework.data.domain.Page;

public class PaginationUtil {
    public static <T> PagedResponse<T> fromPage(Page<T> page) {
        return new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}
