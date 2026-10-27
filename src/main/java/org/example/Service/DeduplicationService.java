package org.example.Service;

import org.example.Model.PackageEntity;
import org.example.Repository.PackageRepository;
import org.example.Util.UuidGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class DeduplicationService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private PackageRepository packageRepository;

    public PackageEntity getOrCreatePackage(String trackingId, String containerId) {
        String cacheKey = "PKG:" + trackingId;
        String cachedPackageId = redisTemplate.opsForValue().get(cacheKey);

        if (cachedPackageId != null) {
            return packageRepository.findById(cachedPackageId).orElse(null);
        }

        Optional<PackageEntity> existing = packageRepository.findByTrackingId(trackingId);
        if (existing.isPresent()) {
            redisTemplate.opsForValue().set(cacheKey, existing.get().getId(), Duration.ofHours(24));
            return existing.get();
        }

        PackageEntity newPkg = new PackageEntity();
        newPkg.setTrackingId(trackingId);
        newPkg.setContainerId(containerId);
        newPkg.setOwnershipFlag("Yes");

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("trackingId", trackingId);
        attributes.put("containerId", containerId);
        newPkg.setUuid(UuidGeneratorUtil.generateUuidFromMap(attributes, "Yes"));

        PackageEntity saved = packageRepository.save(newPkg);
        redisTemplate.opsForValue().set(cacheKey, saved.getId(), Duration.ofHours(24));
        return saved;
    }
}

