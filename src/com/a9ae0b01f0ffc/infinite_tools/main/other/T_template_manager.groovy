package other

import base.T_common_base_3_utils
import groovy.text.SimpleTemplateEngine
import groovy.text.Template

class T_template_manager extends T_common_base_3_utils {

    protected HashMap<String, Template> p_templates_by_name = new HashMap<String, Template>()

    void init_templates(String i_template_path) {
        new File(i_template_path).eachFile { l_file ->
            p_templates_by_name.put(l_file.getName().take(l_file.getName().lastIndexOf(GC_POINT)).toString(), new SimpleTemplateEngine().createTemplate(l_file))
        }
    }

    void init_template(String i_template_name, String i_template_script) {
        p_templates_by_name.put(i_template_name, new SimpleTemplateEngine().createTemplate(i_template_script))
    }

    Template get_template(String i_template_name) {
        if (p_templates_by_name.containsKey(i_template_name)) {
            return p_templates_by_name.get(i_template_name)
        } else {
            throw new E_application_exception(s.Template_not_found_Z1, i_template_name)
        }
    }

}
