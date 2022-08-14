package project.coupon.CouponsOnline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.coupon.CouponsOnline.models.Review;

public interface Reviewrepository extends JpaRepository<Review,Long> {

}
