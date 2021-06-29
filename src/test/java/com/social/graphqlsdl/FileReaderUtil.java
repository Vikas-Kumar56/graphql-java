package com.social.graphqlsdl;

import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileReaderUtil {
    public static  String read(String location) throws IOException {
        return IOUtils.toString(
                new ClassPathResource(location).getInputStream(),
                StandardCharsets.UTF_8
        );
    }
}
