package com.sparta.delivery.domain.review.repository;

import com.sparta.delivery.domain.review.entity.Review;
import com.sparta.delivery.domain.store.entity.Stores;
import com.sparta.delivery.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    Optional<Review> findByReviewIdAndDeletedAtIsNull(UUID reviewId);

    List<Review> findAllByUserAndDeletedAtIsNull(User user, Pageable pageable);

    List<Review> findAllByStoreAndDeletedAtIsNull(Stores store, Pageable pageable);
}
