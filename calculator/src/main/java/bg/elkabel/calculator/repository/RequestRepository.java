
package bg.elkabel.calculator.repository;

import bg.elkabel.calculator.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{
	
}
