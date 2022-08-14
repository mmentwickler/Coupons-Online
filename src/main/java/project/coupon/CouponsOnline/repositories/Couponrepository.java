package project.coupon.CouponsOnline.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.coupon.CouponsOnline.models.Coupon;

public interface Couponrepository extends JpaRepository<Coupon, Long> {

	/*
	 * @Query("@Query(select c from Coupon c where c.active = :active order by number desc limit 6)"
	 * ) public List<Coupon> find(@Param("lastName") String lastName);
	 * List<Todo> findTop6ByTitleOrderByTitleAscDescriptionDesc(String title)
	 * 
	 * public List<Coupon> findTop6ByOrderByNumberDesc();
	 * 
	 */
	@Query("select c from Coupon c where c.active = :active ORDER BY number desc")
	public List<Coupon> find6LatestCoupons(@Param("active") boolean active,Pageable pageable);
	
	
	Page<Coupon> findAllByOrderByNumberDesc(Pageable pageable);

	//public List<Coupon> findTop6ByOrderByNumberAndActiveDesc(boolean active);

	public List<Coupon> findAllByOrderByNumberDesc();

	public List<Coupon> findByCategory(String category);

	public List<Coupon> findByActive(boolean active);

	public List<Coupon> findByActiveAndExpirydateBefore(boolean active, Date date);

}
