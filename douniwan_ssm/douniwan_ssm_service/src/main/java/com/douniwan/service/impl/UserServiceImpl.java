package com.douniwan.service.impl;

import com.douniwan.dao.IUserDao;
import com.douniwan.dao.IUsers_RoleDao;
import com.douniwan.domain.Role;
import com.douniwan.domain.UserInfo;
import com.douniwan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    //这就需要userDao
    @Autowired
    private IUserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private IUsers_RoleDao users_roleDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
             userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
         // UserDetails 中的有一个自己的实现类User  这个noop用加密的话就不用
//        User user = new User(userInfo.getUsername(), "{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus() == 0? false:true,true,true, true,getAuthority(userInfo.getRoles()));
        return user;
    }
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
        list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }
    //查询所有用户
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    public void save(UserInfo userInfo) throws Exception {
        //加密操作
        userInfo.setPassword( bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public UserInfo findUserByIdAndAllRole(String userId) throws Exception {
        return userDao.findUserByIdAndAllRole(userId);
    }

//    @Override
//    public void addRoleToUser(Users_role users_role) throws Exception {
//        users_roleDao.save(users_role);
//    }

    @Override
    public void addRoleToUser(String userId , String roleId) throws Exception {
        users_roleDao.save(userId , roleId);
    }
}
