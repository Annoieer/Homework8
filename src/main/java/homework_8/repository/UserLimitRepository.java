package homework_8.repository;

import homework_8.entity.UserLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserLimitRepository extends JpaRepository<UserLimit, Long> {

    UserLimit findByUserId(@NonNull @Param("id") Long userId);

    @NonNull
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE UserLimit limits " +
            "SET userLimit=:userLimit " +
            "WHERE userId=:id")
    void setUserLimit(@NonNull @Param("id") Long userId, @NonNull @Param("userLimit") Long userLimit);

    @NonNull
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE UserLimit limits " +
            "SET policyLevel=:policy " +
            "WHERE userId=:id")
    void setUserLimitPolicy(@NonNull @Param("id") Long userId, @NonNull @Param("policy") Long limitPolicy);

    @NonNull
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE UserLimit limits " +
            "SET userLimit=:userLimit " +
            "WHERE policyLevel=:policy")
    void changeUsersLimitByPolicy(@NonNull @Param("userLimit") Long userLimit, @NonNull @Param("policy") Long limitPolicy);
}
