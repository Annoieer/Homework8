package homework_8.repository;

import homework_8.entity.LimitPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LimitPolicyRepository extends JpaRepository<LimitPolicy, Long> {
    @NonNull
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE LimitPolicy policy " +
            "SET userLimit=:userLimit " +
            "WHERE id=:id")
    void setLimitPolicy(@NonNull @Param("id") Long id, @NonNull @Param("userLimit") Long userLimit);
}
