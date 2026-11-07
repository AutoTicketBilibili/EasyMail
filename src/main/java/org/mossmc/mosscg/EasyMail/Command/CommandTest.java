package org.mossmc.mosscg.EasyMail.Command;

import org.mossmc.mosscg.EasyMail.Mail.MailMain;
import org.mossmc.mosscg.EasyMail.Mail.MailSend;
import org.mossmc.mosscg.MossLib.Object.ObjectCommand;
import org.mossmc.mosscg.MossLib.Object.ObjectLogger;

import java.util.ArrayList;
import java.util.List;

public class CommandTest extends ObjectCommand {
    @Override
    public List<String> prefix() {
        List<String> prefixList = new ArrayList<>();
        prefixList.add("test");
        return prefixList;
    }

    @Override
    public boolean execute(String[] args, ObjectLogger logger) {
        String[] receives = MailMain.mailReceive.split(",");
        MailSend.asyncSendMail(
                "测试邮件",
                "测试测试测试",
                "Test",
                receives[0]
        );
        return true;
    }
}
