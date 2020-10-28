package cn.huanzi.qch.baseadmin.service.system;

import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.common.service.CommonService;
import cn.huanzi.qch.baseadmin.vo.SysMenuVo;
import cn.huanzi.qch.baseadmin.pojo.system.SysUserMenu;
import cn.huanzi.qch.baseadmin.vo.SysUserMenuVo;

import java.util.List;

public interface SysUserMenuService extends CommonService<SysUserMenuVo, SysUserMenu, String> {
    Result<List<SysMenuVo>> findByUserId(String userId);

    Result<Boolean> saveAllByUserId(String userId, String menuIdList);
}
