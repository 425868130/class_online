package top.peaktop.Model.Mapper;

import org.springframework.stereotype.Repository;
import top.peaktop.Model.Dynamic;
@Repository
public interface DynamicMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Dynamic record);

    int insertSelective(Dynamic record);

    Dynamic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dynamic record);

    int updateByPrimaryKey(Dynamic record);
}