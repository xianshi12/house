package com.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.DeveloperEntity;
import com.entity.PropertyCommentEntity;
import com.entity.PropertyInfoEntity;
import com.entity.UserEntity;
import com.entity.UsersEntity;
import com.service.DeveloperService;
import com.service.PropertyInfoService;
import com.service.UserService;
import com.service.UsersService;

public final class PropertyCommentDisplayUtils {
    private PropertyCommentDisplayUtils() {
    }

    public static void fillPage(PageUtils page, UserService userService, PropertyInfoService propertyInfoService,
            DeveloperService developerService, UsersService usersService) {
        if (page == null || page.getList() == null) {
            return;
        }
        for (Object item : page.getList()) {
            fill(item, userService, propertyInfoService, developerService, usersService);
        }
    }

    public static <T> List<T> fillList(List<T> list, UserService userService, PropertyInfoService propertyInfoService,
            DeveloperService developerService, UsersService usersService) {
        if (list == null) {
            return list;
        }
        for (T item : list) {
            fill(item, userService, propertyInfoService, developerService, usersService);
        }
        return list;
    }

    public static <T> T fill(T item, UserService userService, PropertyInfoService propertyInfoService,
            DeveloperService developerService, UsersService usersService) {
        if (!(item instanceof PropertyCommentEntity)) {
            return item;
        }
        PropertyCommentEntity comment = (PropertyCommentEntity) item;
        fillCommentUser(comment, userService);
        fillReplyUser(comment, propertyInfoService, developerService, usersService);
        return item;
    }

    private static void fillCommentUser(PropertyCommentEntity comment, UserService userService) {
        if (comment.getUserid() == null || userService == null) {
            return;
        }
        UserEntity user = userService.selectById(comment.getUserid());
        if (user == null) {
            return;
        }
        if (StringUtils.isNotBlank(user.getAvatar())) {
            comment.setAvatarurl(user.getAvatar());
        }
        if (StringUtils.isNotBlank(user.getFullName())) {
            comment.setNickname(user.getFullName());
        } else if (StringUtils.isNotBlank(user.getAccount())) {
            comment.setNickname(user.getAccount());
        }
    }

    private static void fillReplyUser(PropertyCommentEntity comment, PropertyInfoService propertyInfoService,
            DeveloperService developerService, UsersService usersService) {
        if (StringUtils.isBlank(comment.getReply())) {
            return;
        }
        DeveloperEntity developer = findPropertyDeveloper(comment.getRefid(), propertyInfoService, developerService);
        if (developer != null) {
            comment.setReplyAvatarurl(developer.getAvatar());
            comment.setReplyNickname(StringUtils.defaultIfBlank(developer.getDeveloperName(), developer.getDeveloperCode()));
            comment.setReplyRole("开发商");
            return;
        }
        UsersEntity admin = findAdmin(usersService);
        if (admin != null) {
            comment.setReplyAvatarurl(admin.getAvatar());
            comment.setReplyNickname(admin.getUsername());
            comment.setReplyRole(StringUtils.defaultIfBlank(admin.getRole(), "管理员"));
        }
    }

    private static DeveloperEntity findPropertyDeveloper(Long propertyId, PropertyInfoService propertyInfoService,
            DeveloperService developerService) {
        if (propertyId == null || propertyInfoService == null || developerService == null) {
            return null;
        }
        PropertyInfoEntity propertyInfo = propertyInfoService.selectById(propertyId);
        if (propertyInfo == null || StringUtils.isBlank(propertyInfo.getDeveloperCode())) {
            return null;
        }
        EntityWrapper<DeveloperEntity> ew = new EntityWrapper<DeveloperEntity>();
        ew.eq("developer_code", propertyInfo.getDeveloperCode());
        ew.last("limit 1");
        return developerService.selectOne(ew);
    }

    private static UsersEntity findAdmin(UsersService usersService) {
        if (usersService == null) {
            return null;
        }
        EntityWrapper<UsersEntity> ew = new EntityWrapper<UsersEntity>();
        ew.eq("role", "管理员");
        ew.last("limit 1");
        UsersEntity admin = usersService.selectOne(ew);
        if (admin != null) {
            return admin;
        }
        EntityWrapper<UsersEntity> usernameEw = new EntityWrapper<UsersEntity>();
        usernameEw.eq("username", "admin");
        usernameEw.last("limit 1");
        return usersService.selectOne(usernameEw);
    }
}
