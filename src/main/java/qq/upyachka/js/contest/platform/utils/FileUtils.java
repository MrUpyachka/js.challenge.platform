package qq.upyachka.js.contest.platform.utils;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

/**
 * Utility class for files processing.
 * Created on 23.02.17.
 * @author upyachka.
 */
public final class FileUtils {

    /** Internal logger. */
    private static final Logger LOG = LogManager.getLogger(FileUtils.class);

    /**
     * Reads file and returns its content as {@link String}.
     * @param file file to read.
     * @return content or null in case of errors.
     */
    public static String readFile(MultipartFile file) {
        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(file.getInputStream(), writer, StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            LOG.debug("Failed to read file: {}", e);
            return null;
        }
        return writer.toString();
    }

}
