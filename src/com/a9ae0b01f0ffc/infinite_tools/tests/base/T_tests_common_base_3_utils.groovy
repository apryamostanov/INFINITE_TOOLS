package main

import base.T_common_base_3_utils
import org.junit.Test

class T_tests_common_base_3_utils {

    @Test
    void util_01() {
        assert !T_common_base_3_utils.method_arguments_present(new ArrayList<Object>())
    }

    @Test
    void util_02() {
        ArrayList<Object> l_array = new ArrayList<Object>()
        l_array.add("z")
        assert T_common_base_3_utils.method_arguments_present(l_array)
    }

    @Test
    void util_03() {
        Object[] l_array = new Object[1]
        l_array[0] = "q"
        assert T_common_base_3_utils.method_arguments_present(l_array)
    }

    @Test
    void util_05() {
        System.out.println(T_common_base_3_utils.last_chars("123", 4))
    }

}