/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.MileageRate;

/**
 *
 * @author koenv
 */
public interface MileageRateDAO {
    public void createMileageRate(MileageRate mr);
    
    public List<MileageRate> getAllRates();
    
    public List<MileageRate> getRatePerArea(String regio);
    
    public List<MileageRate> getRatePerPrizeCategory(double prizecategory);
    
    public List<MileageRate> getRatePerInterval(double interval);
}
