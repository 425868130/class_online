package top.peaktop.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.peaktop.Model.Mapper.NoticeMapper;
import top.peaktop.Model.Notice;
import top.peaktop.ServiceInterface.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;
    @Override
    public boolean addNotice(Notice notice) {
        return false;
    }
}
