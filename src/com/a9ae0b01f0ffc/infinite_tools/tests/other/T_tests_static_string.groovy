package other

import base.T_common_base_1_const
import base.T_common_base_3_utils
import org.junit.Test

class T_tests_static_string {

    @Test
    void static_string_002() {
        assert new T_static_string_builder().THIS_IS_SOME_RANDOM_TEXT.toString() == "THIS_IS_SOME_RANDOM_TEXT"
    }

    @Test
    void static_string_003() {
        assert new T_static_string_builder().THIS_IS_NEGATIVE_TEST.toString() != "THIS_IS_SOME_RANDOM_TEXT"
    }

    @Test
    void static_string_004() {
        assert T_static_string_builder.s().THIS_IS_NEGATIVE_TEST2.toString() != "THIS_IS_SOME_RANDOM_TEXT2"
    }

    @Test
    void static_string_005() {
        assert "qqqqVVV n nnYY" == T_common_base_3_utils.tokenize(T_common_base_1_const.s.qqqqZ1_n_nnZ2, "_", "Z", Arrays.asList("VVV", "YY"))
    }

}