package com.bird.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author lipu
 * @Date 2021/10/8 14:26
 * @Description Ribbon配置
 */
@Configuration
public class RibbonConfig {

    /**
     * Ribbon有7种负载均衡策略
     * 1.RoundRobinRule 轮询策略  依次选择 默认负载均衡算法
     * 2.RandomRule     随机策略  随机选择
     * 3.AvailabilityFilteringRule 可用过滤策略 先过滤掉断路状态和并发量超过阈值的服务，然后轮询
     * 4.WeightedResponseTimeRule 响应时间加权策略 根据平均响应时间计算权重，然后权重越大被选择概率越大。
     * 5.RetryRule 重试策略  轮询如果失败指定时间内会进行重试。
     * 6.BestAvailableRule 最低并发策略  选择最小并发请求的服务
     * 7.ZoneAvoidanceRule 暂不研究
     * 高并发下推荐使用最低并发策略和可用过滤策略
     */

    /**
     * @Author lipu
     * @Date 2020/8/12 20:29
     * @Description 随机策略
     */
    @Bean
    public IRule rule() {
        return new RandomRule();
    }

}
