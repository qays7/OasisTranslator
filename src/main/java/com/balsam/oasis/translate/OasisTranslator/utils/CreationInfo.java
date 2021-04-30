package com.balsam.oasis.translate.OasisTranslator.utils;

import java.lang.annotation.Documented;

@Documented
public @interface CreationInfo {

    String createdBy ()  default "Qays Raed";
    String creationDate () default "N/A" ;
    int currentRevision () default 1 ;
    String lastModified () default "N/A" ;
    String lastModifiedBy () default "N/A" ;
    String description () default "N/A" ;
    String type () default "N/A" ;

}
