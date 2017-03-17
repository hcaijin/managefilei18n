package com.gy.common.util.entity;

/**
 * Created by hcj on 3/17/17.
 * cerp.common.[模块].[功能].[key]
 * cerp.[程序].[模块].[功能].[key]
 */
public class MatchCounterInfo {
    /**
     * 要操作的文件夹目录
     */
    private String directoryFile;
    /**
     * 自定义程序名
     */
    private String programName;
    /**
     * 自定义模块名
     */
    private String moduleName;
    /**
     * 自定义功能名称
     */
    private String features;

    public String getDirectoryFile() {
        return directoryFile;
    }

    public void setDirectoryFile(String directoryFile) {
        this.directoryFile = directoryFile;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }
}
