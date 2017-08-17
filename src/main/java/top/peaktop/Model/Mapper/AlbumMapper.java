package top.peaktop.Model.Mapper;

import org.springframework.stereotype.Repository;
import top.peaktop.Model.Album;

@Repository
public interface AlbumMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Album record);

    int insertSelective(Album record);

    Album selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Album record);

    int updateByPrimaryKey(Album record);
}