package cn.huanzi.qch.baseadmin.controller.system;

import cn.huanzi.qch.baseadmin.common.controller.CommonController;
import cn.huanzi.qch.baseadmin.pojo.system.SysShortcutMenu;
import cn.huanzi.qch.baseadmin.service.system.SysShortcutMenuService;
import cn.huanzi.qch.baseadmin.vo.SysShortcutMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/sysShortcutMenu/")
public class SysShortcutMenuController extends CommonController<SysShortcutMenuVo, SysShortcutMenu, String> {
    @Autowired
    private SysShortcutMenuService sysShortcutMenuService;
}
