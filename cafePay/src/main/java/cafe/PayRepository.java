package cafe;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel="pays", path="pays")
public interface PayRepository extends PagingAndSortingRepository<Pay, Long>{

	Optional<Pay> findById(Long id);
}
