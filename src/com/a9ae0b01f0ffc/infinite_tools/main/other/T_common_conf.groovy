package other

import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardWatchEventKinds
import java.nio.file.WatchEvent
import java.nio.file.WatchKey
import java.nio.file.WatchService

import static base.T_common_base_3_utils.*
import static java.nio.file.StandardWatchEventKinds.*

abstract class T_common_conf extends Thread {

    private static final String LC_CONF_THREAD_PREFIX = s.CONF_THREAD_
    private String p_conf_file_name = GC_EMPTY_STRING
    private File p_config_file = GC_NULL_OBJ_REF as File
    private ConfigObject p_config_object = GC_NULL_OBJ_REF as ConfigObject
    private WatchService p_watch_service = FileSystems.getDefault().newWatchService()

    T_common_conf() {
    }

    ConfigObject get_conf() {
        return p_config_object
    }

    String get_conf_file_name() {
        return p_conf_file_name
    }

    File get_config_file() {
        return p_config_file
    }

    T_common_conf(String i_conf_file_name) {
        p_conf_file_name = i_conf_file_name
        p_config_file = new File(i_conf_file_name)
        setName(LC_CONF_THREAD_PREFIX + p_config_file.getName())
        if (not(p_config_file.exists())) {
            throw new E_application_exception(s.Configuration_file_not_found_for_path_Z1, p_config_file.getAbsolutePath())
        }
        Paths.get(p_config_file.getParent()).register(p_watch_service, ENTRY_MODIFY)
        init_config()
        start()
    }

    abstract void refresh_config()

    void init_config() {
        p_config_object = convert_xml_properties_into_text_properties(p_config_file.getText())
        refresh_config()
    }

    @Override
    void run() {
        while (GC_TRUE) {
            WatchKey l_watch_key = p_watch_service.take()
            for (WatchEvent<?> l_event : l_watch_key.pollEvents()) {
                if (l_event.kind() == OVERFLOW) {
                    continue
                }
                final Path l_changed_path = (Path) l_event.context()
                if (l_changed_path.endsWith(p_config_file.getName())) {
                    init_config()
                }
            }
            if (not(l_watch_key.reset())) {
                break
            }
        }
    }

}
