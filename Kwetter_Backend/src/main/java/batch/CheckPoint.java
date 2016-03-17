/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch;

import java.io.Serializable;

/**
 *
 * @author koenv
 */
public class CheckPoint implements Serializable{
    private long lineNum = 0;
    
    public void increase() {
        lineNum++;
    }

    public long getLineNum() {
        return lineNum;
    }
    
}
