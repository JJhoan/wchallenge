package com.wolox.wchallenge.security;

import com.wolox.wchallenge.service.PrivilegeManagementService;
import com.wolox.wchallenge.service.AlbumService;
import com.wolox.wchallenge.service.UserService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {
    private final AuthenticationTrustResolver trustResolver =
            new AuthenticationTrustResolverImpl();

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
            Authentication authentication, MethodInvocation invocation) {
        CustomMethodSecurityExpressionRoot root =
                new CustomMethodSecurityExpressionRoot(authentication);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(this.trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
        root.setPrivilegeManagementService(applicationContext.getBean(PrivilegeManagementService.class));
        root.setUserService(applicationContext.getBean(UserService.class));
        root.setAlbumService(applicationContext.getBean(AlbumService.class));
        return root;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext){
        super.setApplicationContext(applicationContext);
        this.applicationContext = applicationContext;
    }


}
