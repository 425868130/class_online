package top.peaktop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.peaktop.Model.Notice;
import top.peaktop.ServiceInterface.NoticeService;

@Controller
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @RequestMapping("addNotice")
    public String addNotice(@RequestParam("content") String content) {
        Notice notice = new Notice();
        notice.setTitle("test");
        notice.setContent(content);
        notice.setCreatorId(1l);
        noticeService.addNotice(notice);
        return "notice";
    }
}
