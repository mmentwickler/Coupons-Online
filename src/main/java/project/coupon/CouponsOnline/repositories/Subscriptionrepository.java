package project.coupon.CouponsOnline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.coupon.CouponsOnline.models.Subscription;

public interface Subscriptionrepository extends JpaRepository<Subscription,Long> {

}
