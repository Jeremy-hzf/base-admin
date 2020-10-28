package cn.huanzi.qch.baseadmin.repository.dataquery;

import cn.huanzi.qch.baseadmin.common.repository.CommonRepository;
import cn.huanzi.qch.baseadmin.pojo.system.SysAuthority;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author huzhengfa
 * @Description
 * @create 2020-10-27 7:41 下午
 */
@Repository
public interface DBInfoRepository  {

    @Query(value = "select new LinkedHashMap(*) from information_schema.TABLES where TABLE_SCHEMA=(select database())")
    List<LinkedHashMap<String,String>> listTable();


}
