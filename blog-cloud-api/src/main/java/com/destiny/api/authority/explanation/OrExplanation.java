package com.destiny.api.authority.explanation;

import com.destiny.api.authority.enums.Operation;

import java.util.Arrays;

/**
 * @Date 2020/2/20 14:07
 * @Version 1.0
 **/
public class OrExplanation extends Explanation {

    public OrExplanation (Explanation operand1,Explanation operand2){
        super(null, Operation.OR, Arrays.asList(operand1,operand2)," ( ".concat(operand1.getSqlFragment()).concat(" or ").concat(operand2.getSqlFragment()).concat(" ) "));
    }
}
