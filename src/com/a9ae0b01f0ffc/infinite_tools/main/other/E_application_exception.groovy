package other

import static base.T_common_base_3_utils.*
import org.codehaus.groovy.runtime.StackTraceUtils

class E_application_exception extends Exception {

    private static final String PC_MESSAGE_FORMAT_TOKEN_TRACE = "Z"
    private static final String PC_MESSAGE_FORMAT_TOKEN_SPACE = "_"
    private ArrayList<Object> p_traces = new ArrayList<Object>()
    String p_guid = UUID.randomUUID()

    String get_guid() {
        return p_guid
    }

    E_application_exception(T_static_string i_msg_text, Object... i_traces = GC_SKIPPED_ARGS as Object[]) {
        super(tokenize(i_msg_text, PC_MESSAGE_FORMAT_TOKEN_SPACE, PC_MESSAGE_FORMAT_TOKEN_TRACE, Arrays.asList(i_traces)))
        if (method_arguments_present(i_traces)) {
            p_traces = new ArrayList<Object>(Arrays.asList(i_traces))
        }
    }

    @Override
    String toString() {
        String l_exception_text = GC_EMPTY_STRING
        Throwable sanitizedThrowable
        sanitizedThrowable = new StackTraceUtils().sanitizeRootCause(this)
        l_exception_text += this.getClass().getName() + GC_COMMA + this.getMessage() + System.lineSeparator()
        l_exception_text += Arrays.toString(sanitizedThrowable.getStackTrace()).replace(GC_COMMA, System.lineSeparator() + GC_AT_CHAR)
        return l_exception_text
    }

    ArrayList<Object> get_traces() {
        return p_traces
    }

}
