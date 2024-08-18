package homework_8.repository;

import homework_8.entity.TransactionalMemory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public interface TransactionalMemoryRepository extends JpaRepository<TransactionalMemory, Long> {

    ArrayList<TransactionalMemory> findByUserId(@NonNull @Param("id") Long userId);

    @NonNull
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE TransactionalMemory memory " +
            "SET isCancelled=true " +
            "WHERE id=:id")
    void cancelTransaction(@NonNull @Param("id") Long transactionalMemoryId);
}
