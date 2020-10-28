package cn.huanzi.qch.baseadmin.repository.system;

import cn.huanzi.qch.baseadmin.common.repository.*;
import cn.huanzi.qch.baseadmin.pojo.system.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends CommonRepository<SysUser, String> {
    SysUser findByLoginName(String username);
}
