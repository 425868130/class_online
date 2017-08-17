package top.peaktop.Model.Mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import top.peaktop.Model.Notice;

@Repository
public interface NoticeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);
}