package xyz.yuzh.cloud.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 过滤拦截：带有 accessToken 的请求才会路由到具体的微服务上
 * 如：http://localhost:1101/eureka-consuer-feign/consumer 会返回 401 状态码
 *    http://localhost:1101/eureka-consuer-feign/consumer?accessToken=test 会成功访问
 *
 * @author zhangyu
 * @since 2018-12-16
 */
@Component
public class AccessFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * 过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。这里定义为pre，代表会在请求被路由之前执行。
     *
     * @return
     */
    @Override
    public String filterType() {
        log.info("[AccessFilter.filterType]");
        return "pre";
    }

    /**
     * 过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行。数字越大，优先级越低
     *
     * @return
     */
    @Override
    public int filterOrder() {
        log.info("[初始化时执行 AccessFilter.filterOrder]");
        return 0;
    }

    /**
     * 判断该过滤器是否需要被执行。这里我们直接返回了true，因此该过滤器对所有请求都会生效。
     * 实际运用中我们可以利用该函数来指定过滤器的有效范围。
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        log.info("[AccessFilter.shouldFilter]");
        return true;
    }

    @Override
    public Object run() {
        log.info("[AccessFilter.run] begin...");

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

        Object accessToken = request.getParameter("accessToken");
        if (accessToken == null) {
            log.warn("access token is empty");
            ctx.setSendZuulResponse(false); // 令zuul过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(401); // 设置了其返回的错误码
            return null;
        }
        log.info("access token ok");
        return null;
    }
}
