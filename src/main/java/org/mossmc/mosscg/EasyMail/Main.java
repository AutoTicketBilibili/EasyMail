package org.mossmc.mosscg.EasyMail;

import org.mossmc.mosscg.EasyMail.Command.CommandExit;
import org.mossmc.mosscg.EasyMail.Command.CommandReload;
import org.mossmc.mosscg.EasyMail.Mail.MailMain;
import org.mossmc.mosscg.EasyMail.Web.WebMain;
import org.mossmc.mosscg.MossLib.Command.CommandManager;
import org.mossmc.mosscg.MossLib.Config.ConfigManager;
import org.mossmc.mosscg.MossLib.File.FileCheck;
import org.mossmc.mosscg.MossLib.Object.ObjectLogger;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        FileCheck.checkDirExist("./EasyMail");
        ObjectLogger logger = new ObjectLogger("./EasyMail/logs");
        BasicInfo.logger = logger;

        //基础信息输出
        logger.sendInfo("欢迎使用EasyMail软件");
        logger.sendInfo("软件版本：" + BasicInfo.version);
        logger.sendInfo("软件作者：" + BasicInfo.author);

        //配置读取
        logger.sendInfo("正在读取配置文件......");
        BasicInfo.config = ConfigManager.getConfigObject("./EasyMail", "config.yml", "config.yml");
        if (!BasicInfo.config.getBoolean("enable")) {
            logger.sendInfo("你还没有完成配置文件的设置哦~");
            logger.sendInfo("快去配置一下吧~");
            logger.sendInfo("配置文件位置：./EasyMail/config.yml");
            System.exit(0);
        }
        BasicInfo.debug = BasicInfo.config.getBoolean("debug");

        //邮箱初始化
        logger.sendInfo("正在初始化邮箱模块......");
        MailMain.initMail();

        //Http服务初始化
        logger.sendInfo("正在初始化Web模块......");
        WebMain.initWeb();

        //命令行初始化
        CommandManager.initCommand(BasicInfo.logger,true);
        CommandManager.registerCommand(new CommandExit());
        CommandManager.registerCommand(new CommandReload());

        long completeTime = System.currentTimeMillis();
        logger.sendInfo("启动完成！耗时："+(completeTime-startTime)+"毫秒！");
    }

    public static void reloadConfig() {
        BasicInfo.logger.sendInfo("正在重载配置文件......");
        BasicInfo.config = ConfigManager.getConfigObject("./EasyMail", "config.yml", "config.yml");
        BasicInfo.debug = BasicInfo.config.getBoolean("debug");
        BasicInfo.logger.sendInfo("配置文件重载完成！");
    }
}
