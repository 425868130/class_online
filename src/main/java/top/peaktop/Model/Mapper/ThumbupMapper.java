package top.peaktop.Model.Mapper;


import org.springframework.stereotype.Repository;
import top.peaktop.Model.Thumbup;

@Repository
public interface ThumbupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Thumbup record);

    int insertSelective(Thumbup record);

    Thumbup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Thumbup record);

    int updateByPrimaryKey(Thumbup record);
}