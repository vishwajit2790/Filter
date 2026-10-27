package org.example.Repository;

import org.example.Model.PackageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface PackageRepository extends MongoRepository<PackageEntity, String> {
    Optional<PackageEntity> findByTrackingId(String trackingId);
}

