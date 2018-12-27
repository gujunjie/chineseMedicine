package bean;

public class AccessToken {


    /**
     * refresh_token : 25.3e2fecc5d92f455c1c68fa6b41a59976.315360000.1860507927.282335-15016276
     * expires_in : 2592000
     * session_key : 9mzdWuy5xdL6NwFVAf+S58OyGyUuL4teMJNuVmYf6vk58WW7lkeV6b/3YL4mPzorATiWKW4XYGPrPeWZijLc2irZo4AsaQ==
     * access_token : 24.a0bef33709a20b351b7cd51b820e9ec3.2592000.1547739927.282335-15016276
     * scope : public vis-classify_dishes vis-classify_car brain_all_scope vis-classify_animal vis-classify_plant brain_object_detect brain_realtime_logo brain_dish_detect brain_car_detect brain_animal_classify brain_plant_classify brain_advanced_general_classify brain_poi_recognize wise_adapt lebo_resource_base lightservice_public hetu_basic lightcms_map_poi kaidian_kaidian ApsMisTest_Test权限 vis-classify_flower lpq_开放 cop_helloScope ApsMis_fangdi_permission smartapp_snsapi_base iop_autocar oauth_tp_app smartapp_smart_game_openapi oauth_sessionkey smartapp_swanid_verify
     * session_secret : f11455350ee2f13e92daad2fcaf1ea82
     */

    private String refresh_token;
    private int expires_in;
    private String session_key;
    private String access_token;
    private String scope;
    private String session_secret;

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getSession_secret() {
        return session_secret;
    }

    public void setSession_secret(String session_secret) {
        this.session_secret = session_secret;
    }
}
