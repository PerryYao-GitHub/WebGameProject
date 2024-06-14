# Stage 1
- 使用 IDEA 创建后端 backend 项目
- 在 `com.ypy.backend.` 目录下创建 `config` 和 `controller.pk` 两个软件包
- 在 `config` 创建 `CorsConfig` 类, 用于前端从获取不同域名的数据. 内容如下
  - ```java
    package com.ypy.backend.config;

    import org.springframework.context.annotation.Configuration;
    
    import jakarta.servlet.*;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import java.io.IOException;
    
    @Configuration
    public class CorsConfig implements Filter {
        @Override
        public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
            HttpServletResponse response = (HttpServletResponse) res;
            HttpServletRequest request = (HttpServletRequest) req;
    
            String origin = request.getHeader("Origin");
            if(origin!=null) {
                response.setHeader("Access-Control-Allow-Origin", origin);
            }
    
            String headers = request.getHeader("Access-Control-Request-Headers");
            if(headers!=null) {
                response.setHeader("Access-Control-Allow-Headers", headers);
                response.setHeader("Access-Control-Expose-Headers", headers);
            }
    
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Credentials", "true");
    
            chain.doFilter(request, response);
        }
    
        @Override
        public void init(FilterConfig filterConfig) {
    
        }
    
        @Override
        public void destroy() {
        }
    }

    ```
- 在 `controller.pk` 下创建两个Controller类: `IndexController` 和 `BotInfoController`, 做一个简单的测试. 内容如下:
  - IndexConroller:
    ```java
    package com.ypy.backend.controller.pk;

    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.RequestMapping;
    
    @Controller
    @RequestMapping("/pk/")
    public class IndexController {
        
        @RequestMapping("index/")  // 实际的 url 会将 "/pk/" + "index/"
        public String index() {
            return "pk/index.html";
        }
    }
    ```
  - BotInfoController: 
    ```java
    package com.ypy.backend.controller.pk;

    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
    
    import java.util.HashMap;
    import java.util.Map;
    
    @RestController
    @RequestMapping("/pk/")
    public class BotInfoController {
        
        @RequestMapping("get_bot_info/")
        public Map<String, String> getBotInfo() {
            Map<String, String> bot1 = new HashMap<String, String>();
            bot1.put("name", "Anon");
            bot1.put("rating", "1500");
            return bot1;
        }
    }
    ```
- 先来测试 index:
  - 在 `resources.static` 中添加文件夹 `img`, 再在其中一张图片 `ansy.png`; 在 `resources.templates` 中添加文件夹 `ok`, 再在其中写一个 `index.html`:
   
    ```html
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Test</title>
    </head>
    <body>
    <div style="text-align: center">
        <img src="/img/ansy.png">
    </div>
    </body>
    </html>
    ```
- 在 `resources` 下的 `application.properties` 中修改后端项目的端口: `server.port=3000`, 启动项目, 测试.
- 注意, `/` 开头代表绝对路径; url 对末尾的 `/` 敏感.