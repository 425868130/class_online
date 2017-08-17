package top.peaktop.Model.Mapper;

import org.springframework.stereotype.Repository;
import top.peaktop.Model.Picture;

@Repository
public interface PictureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);
}