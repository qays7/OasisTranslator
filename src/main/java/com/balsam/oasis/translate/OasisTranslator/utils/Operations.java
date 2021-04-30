package com.balsam.oasis.translate.OasisTranslator.utils;

import java.util.HashMap;
import java.util.function.Supplier;


@CreationInfo(description = "Memorize codes in memory and call the appropriate code via the key, handle exceptions if any.",
        createdBy = "Qays-Raed", creationDate = "10-04-2020", type = "Helper")
public  class  Operations<K extends Object,S extends Supplier> extends HashMap<K,S>{
    @SuppressWarnings("compatibility:8463493904422374191")
    private static final long serialVersionUID = 1L;

    private Exception ex ;
    private Object result ;

    public <K,S> Operations() {
        super();
    }

    public static Operations<Object, Supplier> get(){
        return new Operations<Object, Supplier>() ;
    }

    /**
     * Memorize codes .
     * @param k
     * @param r
     * @return
     */
    public Operations<K,S> push(K k,S r){
        this.put(k,r);
        return this ;
    }

    /**
     * call the appropriate code via the key .
     * @param key
     * @return
     */
    public Operations invoke(Object key) {
        try {
            result = this.get(key).get();
        } catch (Exception e) {
            this.ex = e;
        }
        return this;
    }

    /**
     * It works if there are no exceptions.
     * @param os
     * @return
     */
    public Operations then(OperationSuccess os) {
        if (ex == null)
            os.onSuccess(result);
        return this;
    }

    /**
     * handle exceptions if any .
     * @param of
     */
    public void handleErrorsIfPresent(OperationFail of) {
        if (ex != null)
            of.onFail(ex);
    }


}