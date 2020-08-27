package com.destiny.security.authority.explanation;

import com.destiny.security.authority.enums.Operation;

import java.util.Arrays;

/**
 * @Date 2020/2/20 14:03
 * @Version 1.0
 **/
public class AndExplanation extends Explanation {

    public AndExplanation(Explanation oprand1, Explanation oprand2){
        super(null, Operation.AND, Arrays.asList(oprand1,oprand2),null);
    }
}
