package homework_8.repository;

import homework_8.entity.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {

    @NonNull
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Memory memory " +
            "SET isCancelled=true " +
            "WHERE id=:id")
    void cancelUserLimitChange(@NonNull @Param("id") Long id);
}
