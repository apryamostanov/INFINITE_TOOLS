package other

import org.junit.Test

class T_tests_application_exception {

    @Test
    void application_exception_001() {
        E_application_exception e_application_exception = new E_application_exception(new T_static_string_builder().THIS_IS_ERROR_TEXT)
        try {
            throw e_application_exception
        } catch (E_application_exception e) {
            assert e.toString().contains("THIS IS ERROR TEXT")
        }
    }

}