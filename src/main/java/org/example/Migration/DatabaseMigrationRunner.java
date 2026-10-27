package org.example.Migration;

import org.example.Model.PackageEntity;
import org.example.Repository.PackageRepository;
import org.example.Util.UuidGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DatabaseMigrationRunner implements CommandLineRunner {

    @Autowired
    private PackageRepository packageRepository;

    @Override
    public void run(String... args) {
        List<PackageEntity> packages = packageRepository.findAll();
        for (PackageEntity pkg : packages) {
            if (pkg.getUuid() == null) {
                Map<String, Object> attrs = new HashMap<>();
                attrs.put("trackingId", pkg.getTrackingId());
                attrs.put("containerId", pkg.getContainerId());

                pkg.setUuid(UuidGeneratorUtil.generateUuidFromMap(attrs, "Yes"));
                pkg.setOwnershipFlag("Yes");
                packageRepository.save(pkg);
            }
        }
    }
}
