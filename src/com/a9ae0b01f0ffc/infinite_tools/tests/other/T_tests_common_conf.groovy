package other

import org.junit.Test

class T_tests_common_conf {

    @Test
    void common_conf_001() {
        String l_test_conf_file_name = "./test.conf"
        File l_test_conf_file = new File(l_test_conf_file_name)
        l_test_conf_file.write("setting_one = \"abcd\"")
        T_common_conf l_common_conf = new T_common_conf(l_test_conf_file_name)
        assert l_common_conf.get_conf().setting_one == "abcd"
        l_test_conf_file = new File(l_test_conf_file_name)
        l_test_conf_file.write("setting_one = \"abcd1\"")
        Thread.sleep(1000)
        System.out.println(l_test_conf_file.getAbsolutePath())
        assert l_common_conf.get_conf().setting_one == "abcd1"
    }

}