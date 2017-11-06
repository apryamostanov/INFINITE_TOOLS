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

    @Test
    void util_06() {
        String l_props = T_common_base_3_utils.convert_xml_properties_into_text_properties("""<?xml version="1.0" encoding="UTF-8"?>
<commons>
    <request_method_internal value="POST"/>
    <black_box_config value="c:/middleware/conf/black_box/logger_commons.conf"/>
    <middleware_classes_conf value="c:/middleware/conf/classes.conf"/>
    <request_method_internal value="POST"/>
    <user_agent_internal value="Mozilla/5.0"/>
\t<user_agent_internal value=""/>
    <accept_language_internal value="en-US,enq=0.5"/>
\t<accept_language_external value="en-US,enq=0.5"/>
    <content_type_internal value="text/xml; charset=utf-8"/>
    <content_type_external value="application/json;charset=ISO-8859-1"/>
    <host_name_internal value="wp1.wirecard.com"/>
    <unsercure_test_tls_ssl_mode_internal value="true"/> <!--disable on Prod-->
    <endpoint_internal value="https://wp1.wirecard.com/QA/CoreCardServices/CoreCardServices.svc/basic"/>
    <endpoint_internal1 value="https://wp1wcweb1q:444/QA/CoreCardservices/CoreCardServices.svc/basic"/>
    <json_indent value="2"/>
    <soap_user value="api_middleware"/>
    <soap_password value="Wirecard1@"/>
    <soap_ip value="10.10.98.101"/>
    <soap_source value="YND"/>
    <soap_api_version value="1.2"/>
    <keystore_type value="jks"/>
    <keystore_path value="C:\\middleware\\conf\\middleware.jks"/>
    <keystore_password value="client_test_lkgthnOIUG*&amp;#^K"/>
    <jwt_keystore_type value="jceks"/>
    <jwt_keystore_path value="C:\\middleware\\jwt.jks"/>
    <jwt_keystore_password value="change"/>
    <jwt_keystore_alias value="jwt"/>
    <javax_debug value="all"/>
    <soapaction_header_prefix value="www.corecard.com/ICoreCardServices/"/>
\t<replace_xml_null value="{&quot;nil&quot;: true}"/>
\t<replace_json_null value="null"/>
    <xml_templates_path value="C:\\Users\\anton.pryamostanov\\IdeaProjects\\Middleware\\src\\com\\a9ae0b01f0ffc\\middleware\\user_programmability\\templates\\xml\\"/>
    <json_templates_path value="C:\\Users\\anton.pryamostanov\\IdeaProjects\\Middleware\\src\\com\\a9ae0b01f0ffc\\middleware\\user_programmability\\templates\\json\\"/>
    <template_filename_extension value=".txt"/>
    <types_path value="C:\\Users\\anton.pryamostanov\\IdeaProjects\\Middleware\\src\\com\\a9ae0b01f0ffc\\middleware\\user_programmability\\data_types\\json\\"/>
    <dynamic_token_code value="{event -> return event.context('UniqueID').toString() + '_' + event.c().GC_THREADNAME + '_' + event.c().GC_THREADID}"/>
    <routing_module_path value="C:\\Users\\anton.pryamostanov\\IdeaProjects\\Middleware\\src\\com\\a9ae0b01f0ffc\\middleware\\user_programmability\\routing_modules\\"/>
    <routing_module_name value="T_wdip_mobile_app_routing_module"/>
    <middleware_package_token value="mdwl_pck"/>
    <middleware_package_name value="com.a9ae0b01f0ffc.middleware.implementation"/>
    <service_modules_path value="C:\\Users\\anton.pryamostanov\\IdeaProjects\\Middleware\\src\\com\\a9ae0b01f0ffc\\middleware\\user_programmability\\services\\"/>
    <composite_services_path value="C:\\Users\\anton.pryamostanov\\IdeaProjects\\Middleware\\src\\com\\a9ae0b01f0ffc\\middleware\\user_programmability\\composite_services\\"/>
    <no_jwt_services value=""/>
    <jwt_test_mode value="true"/>
    <test_trace_mode value="true"/>
\t<endpoint_name value="QA"/>
</commons>""")
        System.out.println(l_props)
    }

}