package top.peaktop.Model.Mapper;


import org.springframework.stereotype.Repository;
import top.peaktop.Model.Message;

@Repository
public interface MessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}