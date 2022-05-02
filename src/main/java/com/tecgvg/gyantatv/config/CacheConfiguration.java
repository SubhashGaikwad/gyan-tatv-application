package com.tecgvg.gyantatv.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.tecgvg.gyantatv.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.tecgvg.gyantatv.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.tecgvg.gyantatv.domain.User.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.Authority.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.User.class.getName() + ".authorities");
            createCache(cm, com.tecgvg.gyantatv.domain.Product.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.Product.class.getName() + ".transferDetails");
            createCache(cm, com.tecgvg.gyantatv.domain.Categories.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.Unit.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.WareHouse.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.WareHouse.class.getName() + ".securityUsers");
            createCache(cm, com.tecgvg.gyantatv.domain.ProductInventory.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.ProductInventory.class.getName() + ".consumptionDetails");
            createCache(cm, com.tecgvg.gyantatv.domain.Project.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.ConsumptionDetails.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.ProductTransaction.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.PurchaseQuotation.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.PurchaseQuotation.class.getName() + ".purchaseQuotationDetails");
            createCache(cm, com.tecgvg.gyantatv.domain.PurchaseQuotation.class.getName() + ".goodsReciveds");
            createCache(cm, com.tecgvg.gyantatv.domain.PurchaseQuotationDetails.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.GoodsRecived.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.Transfer.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.Transfer.class.getName() + ".transferDetails");
            createCache(cm, com.tecgvg.gyantatv.domain.Transfer.class.getName() + ".transferRecieveds");
            createCache(cm, com.tecgvg.gyantatv.domain.Transfer.class.getName() + ".transferDetailsApprovals");
            createCache(cm, com.tecgvg.gyantatv.domain.TransferDetails.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.TransferDetailsApprovals.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.TransferRecieved.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.SecurityUser.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.SecurityUser.class.getName() + ".securityPermissions");
            createCache(cm, com.tecgvg.gyantatv.domain.SecurityUser.class.getName() + ".securityRoles");
            createCache(cm, com.tecgvg.gyantatv.domain.SecurityUser.class.getName() + ".wareHouses");
            createCache(cm, com.tecgvg.gyantatv.domain.Notification.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.ClientDetails.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.ProductClientAgreement.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.ParameterLookup.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.SecurityRole.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.SecurityRole.class.getName() + ".securityPermissions");
            createCache(cm, com.tecgvg.gyantatv.domain.SecurityRole.class.getName() + ".securityUsers");
            createCache(cm, com.tecgvg.gyantatv.domain.SecurityPermission.class.getName());
            createCache(cm, com.tecgvg.gyantatv.domain.SecurityPermission.class.getName() + ".securityRoles");
            createCache(cm, com.tecgvg.gyantatv.domain.SecurityPermission.class.getName() + ".securityUsers");
            createCache(cm, com.tecgvg.gyantatv.domain.UserAccess.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
