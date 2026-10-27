package org.example.Util;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

public class UuidGeneratorUtil {
    private static final String DEFAULT_FLAG = "Yes";

    public static String generateUuidFromMap(Map<String, Object> attributes, String flag) {
        String activeFlag = (flag != null && !flag.isEmpty()) ? flag : DEFAULT_FLAG;
        StringBuilder canonicalString = new StringBuilder();

        attributes.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> canonicalString.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue() != null ? entry.getValue().toString() : "")
                        .append("|"));

        canonicalString.append("ownership_flag=").append(activeFlag);
        return UUID.nameUUIDFromBytes(canonicalString.toString().getBytes(StandardCharsets.UTF_8)).toString();
    }
}

