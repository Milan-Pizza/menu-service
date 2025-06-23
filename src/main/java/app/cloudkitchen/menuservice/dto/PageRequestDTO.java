package app.cloudkitchen.menuservice.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public record PageRequestDTO(
        int page,
        int size,
        String sortBy,
        Sort.Direction direction
) {
    public Pageable toPageable() {
        if (sortBy != null && direction != null) {
            return PageRequest.of(page, size, direction, sortBy);
        }
        return PageRequest.of(page, size);
    }

    public Pageable toPageable(Sort defaultSort) {
        if (sortBy != null && direction != null) {
            return PageRequest.of(page, size, direction, sortBy);
        }
        return PageRequest.of(page, size, defaultSort);
    }
}