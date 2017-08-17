package top.peaktop.Config;
/**
 * spring java配置
 */
import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.peaktop.Model.OssConfig;

@Configuration
public class SpringConfig {
    @Bean
    Gson getGson() {
        return new Gson();
    }

    @Bean
    OssConfig getOssConfig() {
        return new OssConfig();
    }
}
