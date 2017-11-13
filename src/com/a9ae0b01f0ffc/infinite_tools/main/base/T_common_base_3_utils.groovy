package base

import groovy.util.slurpersupport.GPathResult
import groovy.xml.XmlUtil
import other.E_application_exception
import other.T_dic_country
import other.T_dic_currency
import other.T_static_string

import java.text.SimpleDateFormat

abstract class T_common_base_3_utils extends T_common_base_2_context {

    static void set_property(String i_property_name, String i_property_value) {
        if (is_not_null(i_property_value)) {
            System.setProperty(i_property_name, i_property_value)
        }
    }

    static final Boolean method_arguments_present(Object i_args) {
        if (i_args != GC_SKIPPED_ARGS && i_args != GC_NULL_OBJ_REF) {
            if (i_args instanceof Collection) {
                if (i_args.size() > GC_EMPTY_SIZE) {
                    return GC_TRUE
                } else {
                    return GC_FALSE
                }
            } else if (i_args instanceof Object[]) {
                if (i_args.length > GC_EMPTY_SIZE) {
                    return GC_TRUE
                } else {
                    return GC_FALSE
                }
            } else {
                return GC_FALSE
            }
        } else {
            return GC_FALSE
        }
    }

    static final Boolean not(Boolean i_boolean_to_invert) {
        return !i_boolean_to_invert
    }

    static
    final String tokenize(T_static_string i_msg_text, String i_token_space, String i_token_trace, List<Object> i_traces = GC_SKIPPED_ARGS) {
        Integer l_trace_seqno = GC_ZERO
        String l_text = i_msg_text.toString()
        l_text = l_text.replace(i_token_space, GC_SPACE)
        if (method_arguments_present(i_traces)) {
            for (Object l_runtime_trace in i_traces) {
                l_trace_seqno++
                l_text = l_text.replace(i_token_trace + l_trace_seqno.toString(), l_runtime_trace.toString())
            }
        }
        return l_text
    }

    static final String d2s(Date i_date, String i_format) {
        String l_result_string = GC_EMPTY_STRING
        if (i_date != GC_NULL_OBJ_REF) {
            SimpleDateFormat l_simple_date_format = new SimpleDateFormat(i_format)
            l_result_string = l_simple_date_format.format(i_date)
        }
        l_result_string = l_result_string.padRight(Math.max(i_format.length(), l_result_string.length()), GC_SPACE)
        return l_result_string
    }

    static final Date s2d(String i_date, String i_format) {
        final Integer LC_NOT_EXISTS = -1
        String l_format = i_format
        String l_date = nvl(i_date, new Date().format(i_format))
        Date l_result_date
        if (l_format.toLowerCase().indexOf(GC_YYYY) == LC_NOT_EXISTS) {
            l_format += GC_YYYY
            l_date += d2s(new Date(), GC_YYYY)
        } else if (l_format.toLowerCase().indexOf(GC_YY) == LC_NOT_EXISTS) {
            l_format += GC_YY
            l_date += d2s(new Date(), GC_YY)
        }
        if (l_date != GC_NULL_OBJ_REF) {
            SimpleDateFormat l_simple_date_format = new SimpleDateFormat(l_format)
            l_result_date = l_simple_date_format.parse(l_date)
        } else {
            throw new E_application_exception(s.UNABLE_TO_CONVERT_TEXT_Z1_TO_DATE_Z2, i_date, i_format)
        }
        return l_result_date
    }

    static final FileWriter init_file(String i_location) {
        File l_file = GC_NULL_OBJ_REF as File
        FileWriter l_file_writer = GC_NULL_OBJ_REF as FileWriter
        String l_location = i_location
        l_file = new File(l_location)
        l_file.getParentFile().mkdirs()
        l_file_writer = new FileWriter(l_file, GC_FILE_APPEND_YES)
        return l_file_writer
    }

    static Boolean like(String i_str, String i_expr) {
        return (i_str.contains(i_expr) || i_str.startsWith(i_expr) || i_str.endsWith(i_expr))
    }

    static final String bytes_to_string(byte[] i_bytes) {
        //ANDing with 0x00FF retains only the last 8 significant bits and zeros others bits
        StringBuilder l_result_string = new StringBuilder()
        String l_hex_string = GC_EMPTY_STRING
        for (byte l_byte : i_bytes) {
            l_hex_string = Integer.toHexString(GC_FULL_BYTE & l_byte)
            l_result_string.append(l_hex_string.length() == GC_ONE_CHAR ? GC_ZERO + l_hex_string : l_hex_string)
        }
        return l_result_string.toString()
    }

    static final Object coalesce(Object... i_objects = GC_SKIPPED_ARGS) {
        Object l_last_object = GC_NULL_OBJ_REF
        if (method_arguments_present(i_objects)) {
            for (Object l_object : i_objects) {
                if (is_not_null(l_object)) {
                    return l_object
                } else {
                    l_last_object = l_object
                }
            }
            return l_last_object
        } else {
            return GC_NULL_OBJ_REF
        }
    }

    static final Object nvl(Object i_primary_object, Object i_backup_object) {
        Object l_result_object
        if (is_null(i_primary_object)) {
            l_result_object = i_backup_object
        } else {
            l_result_object = i_primary_object
        }
        return l_result_object
    }

    static final Object nvl_empty_map(Object i_primary_object, Object i_backup_object) {
        Object l_result_object
        if (is_null(i_primary_object)) {
            l_result_object = i_backup_object
        } else {
            if (i_primary_object instanceof Map && i_primary_object.isEmpty()) {
                l_result_object = i_backup_object
            } else {
                l_result_object = i_primary_object
            }
        }
        return l_result_object
    }

    static final String last_chars(String i_string, Integer i_how_much_chars) {
        if (is_null(i_string)) {
            return GC_EMPTY_STRING
        }
        Integer l_last_position = i_string.length() - i_how_much_chars
        if (l_last_position <= GC_ZERO) {
            return i_string
        } else {
            return i_string.substring(l_last_position)
        }
    }

    static final String substr(String i_string, Integer i_start, Integer i_end) {
        return i_string.substring(i_start, Math.min(i_string.length(), i_end))
    }

    static final String get_short_name(String i_class_name) {
        String l_short_name = GC_EMPTY_STRING
        if (is_null(i_class_name)) {
            return i_class_name
        }
        if (i_class_name.contains(GC_POINT)) {
            l_short_name = i_class_name.substring(i_class_name.lastIndexOf(GC_POINT) + GC_ONE_CHAR)
        } else {
            l_short_name = i_class_name
        }
        return l_short_name
    }

    static Boolean is_null(Object i_object) {
        if (i_object == GC_NULL_OBJ_REF || i_object == GC_EMPTY_STRING) {
            return GC_TRUE
        } else {
            return GC_FALSE
        }
    }

    static Boolean is_not_null(Object i_object) {
        return !is_null(i_object)
    }

    static HashMap invert_hashmap(HashMap i_hash_map) {
        HashMap l_inverted_hash_map = new HashMap()
        for (Map.Entry l_entry : i_hash_map.entrySet()) {
            l_inverted_hash_map.put(l_entry.getValue(), l_entry.getKey())
        }
        return l_inverted_hash_map
    }

    static ConfigObject convert_xml_properties_into_text_properties(String i_properties_string) {
        ConfigObject l_config_object
        if (validate_xml(i_properties_string, GC_TRUE)) {
            String l_properties_string = GC_EMPTY_STRING
            GPathResult l_gpathresult
            l_gpathresult = (GPathResult) new XmlSlurper().parse(new StringReader(i_properties_string))
            for (l_child_node in l_gpathresult.children()) {
                l_properties_string += l_child_node.name() + GC_EQUALS + l_child_node.@value.text() + System.lineSeparator()
            }
            Properties l_properties = new Properties()
            l_properties.load(new StringReader(l_properties_string.replace(GC_XML_SLASH, GC_XML_SLASH + GC_XML_SLASH)))
            l_config_object = new ConfigSlurper().parse(l_properties)
        } else {
            l_config_object = new ConfigSlurper().parse(i_properties_string)
        }
        return l_config_object
    }

    static String decode(String i_search_string, String i_default_value, List<String>... i_match_replacement_pairs) {
        for (l_match_replacement_pair in i_match_replacement_pairs) {
            if (l_match_replacement_pair.first() == i_search_string) {
                return l_match_replacement_pair.last()
            }
        }
        return i_default_value
    }

    static Object instantiate(String i_interface_name) {
        return Class.forName(i_interface_name).newInstance()
    }

    static String format_xml(String i_xml) {
        return XmlUtil.serialize(i_xml)
    }

    static Boolean validate_xml(String i_xml, Boolean i_is_soft = GC_FALSE) {
        if (is_null(i_xml)) {
            return GC_TRUE
        }
        try {
            String l_formatted_payload = format_xml(i_xml)
            return GC_TRUE
        } catch (Exception e_others2) {
            if (i_is_soft) {
                return GC_FALSE
            } else {
                throw new E_application_exception(s.Potentially_invalid_XML, i_xml, e_others2)
            }
        }
    }

    static final String country_digits(String i_country_chars) {
        return T_dic_country.GC_123_BY_ABC.get(i_country_chars)
    }

    static final String country_chars(String i_country_digits) {
        return T_dic_country.GC_ABC_BY_123.get(i_country_digits)
    }

    static final String currency_digits(String i_country_chars) {
        return T_dic_currency.GC_123_BY_ABC.get(i_country_chars)
    }

    static final String currency_chars(String i_country_digits) {
        return T_dic_currency.GC_ABC_BY_123.get(i_country_digits)
    }


}
