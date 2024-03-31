package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class WebConfig implements WebMvcConfigurer {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String avatarFolder = "D:\\Study\\JavaWork\\CulturalTourism\\CulturalTourism-System\\src\\main\\resources\\static\\UserAvatar\\";
        String staticAvatarPath = "/userAvatar/**";

        String goodsFolder = "D:\\Study\\JavaWork\\CulturalTourism\\CulturalTourism-System\\src\\main\\resources\\static\\GoodsImg\\";
        String staticGoodsPath = "/goodsImg/**";

        String heritageFolder = "D:\\Study\\JavaWork\\CulturalTourism\\CulturalTourism-System\\src\\main\\resources\\static\\HeritageImg\\";
        String staticHeritagePath = "/heritageImg/**";

        String hotelFolder = "D:\\Study\\JavaWork\\CulturalTourism\\CulturalTourism-System\\src\\main\\resources\\static\\HotelImg\\";
        String staticHotelPath = "/hotelImg/**";

        String museumFolder = "D:\\Study\\JavaWork\\CulturalTourism\\CulturalTourism-System\\src\\main\\resources\\static\\MuseumImg\\";
        String staticMuseumPath = "/museumImg/**";

        String sceneryFolder = "D:\\Study\\JavaWork\\CulturalTourism\\CulturalTourism-System\\src\\main\\resources\\static\\SceneryImg\\";
        String staticSceneryPath = "/sceneryImg/**";

        String theaterFolder = "D:\\Study\\JavaWork\\CulturalTourism\\CulturalTourism-System\\src\\main\\resources\\static\\TheaterImg\\";
        String staticTheaterPath = "/theaterImg/**";

        registry.addResourceHandler(staticAvatarPath).addResourceLocations("file:"+avatarFolder);
        registry.addResourceHandler(staticGoodsPath).addResourceLocations("file:"+goodsFolder);
        registry.addResourceHandler(staticHeritagePath).addResourceLocations("file:"+heritageFolder);
        registry.addResourceHandler(staticHotelPath).addResourceLocations("file:"+hotelFolder);
        registry.addResourceHandler(staticMuseumPath).addResourceLocations("file:"+museumFolder);
        registry.addResourceHandler(staticSceneryPath).addResourceLocations("file:"+sceneryFolder);
        registry.addResourceHandler(staticTheaterPath).addResourceLocations("file:"+theaterFolder);

        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.app.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //用来自定义API的标题
                .title("智慧文旅项目移动端App接口文档")
                .build();
    }

}
