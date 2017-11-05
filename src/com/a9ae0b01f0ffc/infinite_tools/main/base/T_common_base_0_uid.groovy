package base

import other.I_object_with_uid

class T_common_base_0_uid implements I_object_with_uid {

    String p_guid = UUID.randomUUID()

    String get_guid() {
        return p_guid
    }

}
